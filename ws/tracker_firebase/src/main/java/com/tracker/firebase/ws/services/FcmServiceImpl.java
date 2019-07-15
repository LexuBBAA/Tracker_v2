package com.tracker.firebase.ws.services;

import com.tracker.firebase.ws.configuration.FcmInterceptorConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@Service
public class FcmServiceImpl implements FcmService {
    private static final String GENERAL_MESSAGE = "You have updates awaiting!\nDon't miss out on important work.";
    private static final String GENERAL_TITLE = "Updates available";

    @Autowired
    private RestTemplate restTemplate;

    @NonNull
    @Override
    public String sendNotification(
            @NonNull String deviceId,
            @Nullable String title,
            @Nullable String message,
            @Nullable JSONObject payload
    ) throws JSONException {
        JSONObject body = new JSONObject();
        body.put("to", deviceId);

        JSONObject notification = new JSONObject();
        notification.put("body", message != null ? message: GENERAL_MESSAGE);
        notification.put("title", title != null ? title: GENERAL_TITLE);

        body.put("notification", notification);
        if(payload != null) body.put("data", payload);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        String response = restTemplate.postForObject(FcmInterceptorConfig.FCM_API_URL, request, String.class);

        System.out.println(FcmServiceImpl.class.getSimpleName() + " >>>>>>>>>>>> Response: " + response);

        if(response != null) {
            return response;
        }

        return "";
    }
}
