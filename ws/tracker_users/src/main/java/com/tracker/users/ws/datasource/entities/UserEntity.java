package com.tracker.users.ws.datasource.entities;

import com.tracker.users.ws.datasource.dto.UserDto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@DynamicUpdate
public class UserEntity implements Serializable {
	private static final long serialVersionUID = -3491457484818684481L;
	
	@Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "userid", nullable = false)
    private String userId;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "createdby", nullable = true)
    private String createdBy;
    @Column(name = "avatarurl", nullable = true)
    private String avatarUrl;
    
    @Column(name = "createdat")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    public UserEntity() {
    }

    public UserEntity(UserDto userDto) {
    	this.id = userDto.id;
        this.userId = userDto.userId;
        this.firstname = userDto.firstname;
        this.lastname = userDto.lastname;
        this.username = userDto.username;
        this.email = userDto.email;
        if(userDto.createdAt != null) {
        	this.createdAt = userDto.createdAt;
        }
        if(userDto.createdById != null) {
        	this.createdBy = userDto.createdById;
        }
        if(userDto.avatarUrl != null) {
        	this.avatarUrl = userDto.avatarUrl;
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdById) {
		this.createdBy = createdById;
	}

	public String getAvatar() {
		return avatarUrl;
	}

	public void setAvatar(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", username=" + username + ", email=" + email + ", createdAt=" + createdAt + "]";
	}
}
