package com.tracker.users.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="benefits")
public class Benefit {

    @Id
    private long id;
}
