package com.vesus.springboothistory.model;

import com.vesus.springboothistory.annotation.BusinessNotRecord;
import com.vesus.springboothistory.annotation.BusinessAlias;
import com.vesus.springboothistory.annotation.BussinessID;
import com.vesus.springboothistory.annotation.TableName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_user")
@TableName(name = "用户基本信息")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    @BussinessID
    private int id ;

    @Column(name = "t_name")
    @BusinessAlias(alias = "用户名")
    private String name ;

    @Column(name = "t_age")
    @BusinessAlias(alias = "年龄")
    private int age ;

    @Column(name = "t_address")
    @BusinessAlias(alias = "地址")
    private String address ;

    @Column(name = "t_pwd")
    @BusinessNotRecord
    private String password ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
