package com.tracker.users.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contracts")
public class Contract {

    @Id
    private long id;
}
