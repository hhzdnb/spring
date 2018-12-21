package com.vesus.audit;

import java.io.Serializable;
import java.util.List;

public interface AuditOperationRecordAware {
    void saveAuditOperationLog(String tableName, Serializable id, List<AuditModifyFieldObject> auditModifyFieldObject, long timestamp, AuditOperationType operationType);
}
