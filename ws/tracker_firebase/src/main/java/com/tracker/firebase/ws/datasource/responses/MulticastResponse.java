package com.tracker.firebase.ws.datasource.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class MulticastResponse implements Serializable {
    private static final long serialVersionUID = -4581555294623601150L;

    private Long multicastId;
    private Integer success;
    private Integer failure;
    private Integer canonicalIds;
    private List<SendToDeviceResponse> results;

    @JsonProperty(value = "multicastId")
    public Long getMulticastId() {
        return multicastId;
    }

    @JsonProperty(value = "multicast_id")
    public void setMulticastId(Long multicastId) {
        this.multicastId = multicastId;
    }

    @JsonProperty(value = "failure")
    public Integer getFailure() {
        return failure;
    }

    @JsonProperty(value = "failure")
    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    @JsonProperty(value = "success")
    public Integer getSuccess() {
        return success;
    }

    @JsonProperty(value = "success")
    public void setSuccess(Integer success) {
        this.success = success;
    }

    @JsonProperty(value = "canonicalIds")
    public Integer getCanonicalIds() {
        return canonicalIds;
    }

    @JsonProperty(value = "canonical_ids")
    public void setCanonicalIds(Integer canonicalIds) {
        this.canonicalIds = canonicalIds;
    }

    @JsonProperty(value = "results")
    public List<SendToDeviceResponse> getResults() {
        return results;
    }

    @JsonProperty(value = "results")
    public void setResults(List<SendToDeviceResponse> results) {
        this.results = results;
    }
}
