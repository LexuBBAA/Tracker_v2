package com.tracker.users.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_entity_right_relations")
public class UserEntityRightRelation {

    @Id
    private long id;
}
