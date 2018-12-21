package com.vesus.domain;

import com.vesus.audit.AuditEntityAlias;
import com.vesus.audit.AuditFieldAlias;
import com.vesus.audit.AuditPrimaryID;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wx11055@163.com
 * @date 2018-06-27 23:19:28
 * Created by wuxia .
 */
@Entity
@Table(name = "t_user")
@AuditEntityAlias("用户信息表")
public class TUser implements Serializable {

    /**
     * 编号
     */
    @Id
    @GeneratedValue
    @Column(name = "t_id")
    @AuditPrimaryID
    private Integer id;

    @Column(name = "t_address")
    @AuditFieldAlias("联系地址")
    private String tAddress;

    @Column(name = "t_age")
    @AuditFieldAlias("年龄")
    private Integer tAge;

    @Column(name = "t_name")
    @AuditFieldAlias("姓名")
    private String tName;

    @Column(name = "t_pwd")
    @AuditFieldAlias("密码")
    private String tPwd;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    public Integer gettAge() {
        return tAge;
    }

    public void settAge(Integer tAge) {
        this.tAge = tAge;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettPwd() {
        return tPwd;
    }

    public void settPwd(String tPwd) {
        this.tPwd = tPwd;
    }


}
