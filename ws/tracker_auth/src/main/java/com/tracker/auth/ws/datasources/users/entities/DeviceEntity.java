package com.tracker.auth.ws.datasources.users.entities;

import com.tracker.auth.ws.datasources.users.enums.DeviceOS;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Devices")
public class DeviceEntity {
    @Id
    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "device_os")
    private DeviceOS deviceOs;

    @Column(name = "device_os_version")
    private String deviceOsVersion;

    @Column(name = "last_lat")
    private Float lastLat;

    @Column(name = "last_lon")
    private Float latLon;

    @Column(name = "last_known_interaction")
    private Date lastKnownInteraction;

    @Column(name = "registered_date")
    private Date registeredDate;

    public DeviceEntity() {
    }
}
