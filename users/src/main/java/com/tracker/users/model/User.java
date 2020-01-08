package com.tracker.users.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.jsondoc.core.annotation.ApiObject;

import javax.persistence.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "skills"})
@ApiObject
public class User{

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @Fetch(FetchMode.JOIN)
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
