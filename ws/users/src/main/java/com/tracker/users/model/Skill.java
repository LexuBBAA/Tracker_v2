package com.tracker.users.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "users"})
@JsonIgnoreProperties(value = {"users"})
@ApiObject(name = "Skill", description = "A skill object")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField(description = "The unique identifier for skill")
    private long id;

    @ApiObjectField(description = "The name of the skill")
    private String name;

    @ApiObjectField(description = "The description of the skill")
    private String description;

    @ApiObjectField(description = "The type of the skill")
    private String type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "skills")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();
}
