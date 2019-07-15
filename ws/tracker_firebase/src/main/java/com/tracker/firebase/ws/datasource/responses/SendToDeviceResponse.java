package com.tracker.firebase.ws.datasource.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public class SendToDeviceResponse implements Serializable {
    private static final long serialVersionUID = -5840903854790512899L;

    private String messageId;

    @JsonProperty(value = "messageId")
    public String getMessageId() {
        return messageId;
    }

    @JsonProperty(value = "message_id")
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
