package com.vesus.listener;


import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class CustomAuditListener {


    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyOperation(Object object)  {
    }


}
