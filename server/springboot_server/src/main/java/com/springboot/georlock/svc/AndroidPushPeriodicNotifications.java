package com.springboot.georlock.svc;

import com.springboot.georlock.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * 이 클래스의 용도는 하루에 한번 씩 보내는 주기적인 알림이므로
 * 클래스명이 Android Push Periodic Notification 이다.
 * 현재 코드에서는 /send로 접속을 할 시에만 알림이 push된다.
 * */
@Slf4j
@Service
public class AndroidPushPeriodicNotifications {



    public static String PeriodicNotificationJson(String tokens) throws JSONException, UnsupportedEncodingException {
        LocalDate localDate = LocalDate.now();
        String token = tokens;

        String name = "open please";
        log.debug(name);
        JSONObject body = new JSONObject();
        JSONArray array = new JSONArray();


            array.put(token);


        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();

        //title : 알림의 제목
        notification.put("title", "Georlock!");
        //body : 알림의 내용
        notification.put("body", name);


        body.put("notification", notification);



        return body.toString();
    }
}
/*
 * tokenlist : 알림을 보낼 디바이스의 디바이스토큰을 넣는 list( registration_ids의 값으로 들어간다.)
 *
 * */