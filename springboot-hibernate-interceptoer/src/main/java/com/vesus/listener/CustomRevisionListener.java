package com.vesus.listener;


import com.vesus.domain.CustomerRevisionEntity;
import org.hibernate.envers.RevisionListener;

/**
 * 修订监听器
 */
public class CustomRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        CustomerRevisionEntity exampleRevEntity = (CustomerRevisionEntity) revisionEntity;
        exampleRevEntity.setUserName("matosiki");
    }
}