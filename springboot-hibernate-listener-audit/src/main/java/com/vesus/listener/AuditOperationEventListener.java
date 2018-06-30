package com.vesus.listener;


import com.vesus.audit.AuditOperationRecord;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AuditOperationEventListener extends DefaultLoadEventListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

    @Autowired
    AuditOperationRecord auditOperationRecord;

    public void onPostUpdate(PostUpdateEvent event) {
        Object entity = event.getEntity();
        Object[] oldState = event.getOldState();
        Object[] state = event.getState();
        Serializable id = event.getId();
        EntityPersister persister = event.getPersister();
        String[] propertyNames = persister.getPropertyNames();
        Type[] types = persister.getPropertyTypes();
        auditOperationRecord.onUpdateRecord(entity, id, state, oldState, propertyNames, types);
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        Object entity = event.getEntity();
        Object[] state = event.getDeletedState();
        Serializable id = event.getId();
        EntityPersister persister = event.getPersister();
        String[] propertyNames = persister.getPropertyNames();
        Type[] types = persister.getPropertyTypes();
        auditOperationRecord.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        Object entity = event.getEntity();
        Object[] state = event.getState();
        Serializable id = event.getId();
        EntityPersister persister = event.getPersister();
        String[] propertyNames = persister.getPropertyNames();
        Type[] types = persister.getPropertyTypes();
        auditOperationRecord.onSaveRecord(entity, id, state, propertyNames, types);

    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        System.out.println("=================requiresPostCommitHanding====================");
        return false;
    }

}
