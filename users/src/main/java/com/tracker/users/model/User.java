package com.tracker.users.model;

import lombok.*;

import javax.persistence.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private Date birthday;
    private Integer experience;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_skills", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private Set<Skill> skills = new HashSet<>();

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
