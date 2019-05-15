package com.hzxy.robot.utils;

import java.util.UUID;

public class MyUUIDUtils {
    public static String getUUID() {
        //fajkfjlk-fkldsjalk-fskaj
        String uuid = UUID.randomUUID().toString();
        //将 '-' 去掉
        uuid = uuid.replaceAll("-", "");
        //fajkfjlkfkldsjalkfskaj
        return uuid;
    }
}
