package com.tracker.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private Date birthday;
    private Integer experience;

//    @ManyToMany
//    private List<Skill> skills;
//
//    @ManyToMany
//    private List<Profile> profiles;
//
//    @ManyToMany
//    private List<Role> roles;
//
//    @ManyToMany
//    private List<ThirdPartyAccount> thirdPartyAccounts;
//
//    @OneToOne
//    private User manager;
//
//    @ManyToOne
//    private Team team;
//
//    @ManyToOne
//    private Department department;
//
//    @OneToOne
//    private Contract contract;
}
