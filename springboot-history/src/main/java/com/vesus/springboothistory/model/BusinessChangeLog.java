package com.vesus.springboothistory.model;

import com.vesus.springboothistory.annotation.BusinessAlias;
import com.vesus.springboothistory.annotation.BusinessNotRecord;
import com.vesus.springboothistory.annotation.BussinessID;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_bussiness_log")
public class BusinessChangeLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    @BussinessID
    private int id ;

    @Column(name = "t_entity")
    private String entity ;

    @Column(name = "t_property")
    private String property ;

    @Column(name = "t_alias")
    private String alias ;

    @Column(name = "t_oldvalue")
    private String oldvalue ;

    @Column(name = "t_newvalue")
    private String newvalue ;

    @Column(name = "t_userid")
    private int userid ;

    @Column(name = "t_username")
    private String username ;

    @Column(name = "t_updatedate")
    private int updatedate ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldvalue() {
        return oldvalue;
    }

    public void setOldvalue(String oldvalue) {
        this.oldvalue = oldvalue;
    }

    public String getNewvalue() {
        return newvalue;
    }

    public void setNewvalue(String newvalue) {
        this.newvalue = newvalue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(int updatedate) {
        this.updatedate = updatedate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "BusinessChangeLog{" +
                "id=" + id +
                ", entity='" + entity + '\'' +
                ", property='" + property + '\'' +
                ", alias='" + alias + '\'' +
                ", oldvalue='" + oldvalue + '\'' +
                ", newvalue='" + newvalue + '\'' +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", updatedate=" + updatedate +
                '}';
    }
}
