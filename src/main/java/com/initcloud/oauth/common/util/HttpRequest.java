package com.initcloud.oauth.common.util;


import com.initcloud.oauth.common.exception.ApiException;
import com.initcloud.oauth.common.response.error.ErrorCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@NoArgsConstructor
public class HttpRequest {

    public static String redirect(HttpServletResponse response,
                                HttpParam.Header header,
                                String base,
                                String path,
                                String query){

        String fullUrl = base;

        if(path != null)
            fullUrl += path;

        if(query != null)
            fullUrl += query;

        return fullUrl;
    }

    public static Object get(HttpParam.Header header,
                             String base,
                             String path,
                             String query){
        try{
            String fullUrl = base;

            if(path != null)
                fullUrl += path;

            if(query != null)
                fullUrl += query;

            JSONParser jsonParser = new JSONParser();
            URL url = new URL(fullUrl);
            log.info(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            if(header != null){
                for(int i = 0 ; i < header.getKeys().size() ; i++)
                    conn.setRequestProperty(header.getKeys().get(i), header.getValues().get(i));
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();

            return jsonParser.parse(response.toString());
        } catch (Exception e){
            throw new ApiException(ErrorCode.ERROR_4001);
        }
    }

    public static Object post(HttpParam.Header header,
                              HttpParam.Body body,
                              String base,
                              String path,
                              String query){
        try{
            String fullUrl = base;

            if(path != null)
                fullUrl += path;

            if(query != null)
                fullUrl += query;

            URL url = new URL(fullUrl);
            log.info(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            if(header != null){
                for(int i = 0 ; i < header.getKeys().size() ; i++)
                    conn.setRequestProperty(header.getKeys().get(i), header.getValues().get(i));
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();

            return response.toString();
        } catch (Exception e){
            throw new ApiException(ErrorCode.ERROR_4001);
        }
    }
}
