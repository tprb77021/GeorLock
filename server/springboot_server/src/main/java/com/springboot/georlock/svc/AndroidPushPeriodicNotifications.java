package com.springboot.georlock.svc;

import com.springboot.georlock.dto.Login;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

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


    public static String PeriodicNotificationJson(Login tokens, int type, String door) throws JSONException {
        LocalDate localDate = LocalDate.now();
        String title = "Georlock!";
        String bodys = "empNo :" + tokens.getEmpNo() + " open please";
        String sampleData[] = {tokens.getToken()};
        if (type == 2) {
            title = "door";
            bodys = door + "@" + tokens.getEmpNo();
        }

        log.debug(bodys);
        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();  //<~>는 <String>값이 생략

        for (int i = 0; i < sampleData.length; i++) {
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for (int i = 0; i < tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();

        //title : 알림의 제목
        notification.put("title", title);
        //body : 알림의 내용
        notification.put("body", bodys);

        body.put("notification", notification);

        return body.toString();
    }
}
/*
 * tokenlist : 알림을 보낼 디바이스의 디바이스토큰을 넣는 list( registration_ids의 값으로 들어간다.)
 *
 * */