package com.tracker.firebase.ws.datasource.requests;

import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.io.Serializable;

public class SendNotificationPayload implements Serializable {
    private static final long serialVersionUID = 5277067333773066527L;

    @Nullable
    public String title;
    @Nullable
    public String message;
    @Nullable
    public JSONObject data;

}
