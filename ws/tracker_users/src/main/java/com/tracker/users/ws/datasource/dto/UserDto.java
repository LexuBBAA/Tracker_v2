package com.tracker.users.ws.datasource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tracker.users.ws.datasource.entities.UserEntity;
import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2507555509191328313L;

	@JsonProperty("id")
	public Long id;
	@JsonProperty("userId")
    public String userId;
	@JsonProperty("firstName")
    public String firstname;
	@JsonProperty("lastName")
    public String lastname;
	@JsonProperty("username")
    public String username;
	@JsonProperty("email")
    public String email;
    @JsonProperty("createdBy")
    public String createdById;
    @JsonProperty("avatarUrl")
    public String avatarUrl;
	@JsonProperty("createdAt")
    public LocalDateTime createdAt;

    public UserDto() {
    	
    }
    
    public UserDto(UserEntity entity) {
    	this.id = entity.getId();
        this.userId = entity.getUserId();
        this.firstname = entity.getFirstname();
        this.lastname = entity.getLastname();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.createdById = entity.getCreatedBy();
        this.avatarUrl = entity.getAvatar();
        this.createdAt = entity.getCreatedAt();
    }

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", username=" + username + ", email=" + email + ", createdAt=" + createdAt + "]";
	}
}
