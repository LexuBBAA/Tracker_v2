package com.tracker.users.ws.datasource.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tracker.users.ws.datasource.entities.UserEntity;

public class UserPreviewDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4156853969927852811L;
	
	@JsonProperty("id")
	public Long id;
	@JsonProperty("userId")
    public String userId;
	@JsonProperty("username")
    public String username;
    @JsonProperty("avatarUrl")
    public String avatarUrl;
	
	public UserPreviewDto() {
		
	}
	
	public UserPreviewDto(UserEntity entity) {
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.username = entity.getUsername();
		this.avatarUrl = entity.getAvatar();
	}

}
