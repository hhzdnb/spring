package com.vesus.domain;

import com.vesus.audit.AuditEntityAlias;
import com.vesus.audit.AuditFieldAlias;
import com.vesus.audit.AuditPrimaryID;
import com.vesus.listener.CustomAuditListener;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;


/**
 * @author wx11055@163.com
 * @date 2018-06-27 23:19:28
 * Created by wuxia .
 */
@Entity
@Table(name = "t_user")
@Audited
@EntityListeners(CustomAuditListener.class)
@AuditEntityAlias("用户信息表")
//@AuditTable(value = "_revisions_info")
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

    @Column(name = "t_creator")
    @CreatedBy
    @LastModifiedBy
    private String tCreator;


    @Column(name = "t_modified_date")
    @LastModifiedDate
    private String tModifiedDate;

    public String gettCreator() {
        return tCreator;
    }

    public void settCreator(String tCreator) {
        this.tCreator = tCreator;
    }

    public String gettModifiedDate() {
        return tModifiedDate;
    }

    public void settModifiedDate(String tModifiedDate) {
        this.tModifiedDate = tModifiedDate;
    }

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


    //@PrePersist
    //public void onPrePersist() {
    //
    //    System.out.println("============>>>>>>>持久化保存之前");
    //}
    //
    //@PreUpdate
    //public void onPreUpdate() {
    //    System.out.println("=============>>>>>>>更新之前");
    //}
    //
    //@PreRemove
    //public void onPreRemove() {
    //    System.out.println("=================>>>>>删除前");
    //}

}
