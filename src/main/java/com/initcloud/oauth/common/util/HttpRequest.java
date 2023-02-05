package com.initcloud.oauth.common.util;


import com.initcloud.oauth.common.exception.ApiException;
import com.initcloud.oauth.common.response.error.ErrorCode;
import com.initcloud.oauth.security.config.Properties;
import lombok.NoArgsConstructor;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@NoArgsConstructor
public class HttpRequest {

    public static Object get(String base, String path, String query){
        try{
            String fullUrl = base;

            if(path != null)
                fullUrl += path;

            if(query != null)
                fullUrl += query;

            JSONParser jsonParser = new JSONParser();
            URL url = new URL(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

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

    public static String post(){
        return null;
    }
}
