package com.tracker.users.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="third_party_accounts")
public class ThirdPartyAccount {

    @Id
    private long id;



}
