package com.tracker.firebase.ws.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface FcmService {

    @NonNull
    String sendNotification(
            @NonNull String deviceId, @Nullable String title,
            @Nullable String message, @Nullable JSONObject payload
    ) throws JSONException;

}
