package com.vesus.domain;


import com.vesus.listener.CustomRevisionListener;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;


@Entity
@Table(name = "t_revisions_info")
@RevisionEntity(CustomRevisionListener.class)
public class CustomerRevisionEntity {
    @Id
    @GeneratedValue
    @RevisionNumber
    @Column(name = "id")
    private int id;

    @RevisionTimestamp
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "user_name")
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
