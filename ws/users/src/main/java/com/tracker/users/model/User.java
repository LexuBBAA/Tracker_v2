package com.tracker.users.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "skills"})
@ApiObject(name = "User", description = "An user object")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField(description = "The unique identifier for user")
    private long id;

    @ApiObjectField(description = "The first name of the user")
    private String firstName;

    @ApiObjectField(description = "The last name of the user")
    private String lastName;

    @ApiObjectField(description = "The username of the user")
    private String userName;

    @ApiObjectField(description = "The email of the user")
    private String email;

    @ToString.Exclude
    private String password;

    @ApiObjectField(description = "The phone number of the user")
    private String phone;

    @ApiObjectField(description = "The date of birth of the user")
    private Date birthday;

    @ApiObjectField(description = "The experience of the user(in years)")
    private Float experience;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @Fetch(FetchMode.JOIN)
    @ApiObjectField(description = "List of @Skill of the user")
    private Set<Skill> skills = new HashSet<>();

//    @ManyToMany
//    @ApiObjectField(description = "List of @Profile of the user")
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
