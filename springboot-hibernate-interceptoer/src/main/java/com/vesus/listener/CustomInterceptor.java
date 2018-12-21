package com.vesus.listener;


import com.vesus.audit.AuditOperationRecord;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class CustomInterceptor extends EmptyInterceptor {

    @Autowired(required = false)
    AuditOperationRecord auditOperationRecord;


    public void setAuditOperationRecord(AuditOperationRecord auditOperationRecord) {
        this.auditOperationRecord = auditOperationRecord;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        auditOperationRecord.onSaveRecord(entity, id, state, propertyNames, types);
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        auditOperationRecord.onUpdateRecord(entity, id, currentState, previousState, propertyNames, types);

        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);

    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        auditOperationRecord.onDelete(entity, id, state, propertyNames, types);
        super.onDelete(entity, id, state, propertyNames, types);

    }
}