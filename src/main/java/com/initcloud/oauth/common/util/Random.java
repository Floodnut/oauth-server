package com.initcloud.oauth.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Random {

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
