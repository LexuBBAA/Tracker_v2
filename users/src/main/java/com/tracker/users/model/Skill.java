package com.tracker.users.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="skills")
public class Skill {

    @Id
    long id;
}
