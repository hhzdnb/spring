package com.vesus.service;


import com.vesus.domain.AuditRecordLog;

import java.util.List;

public interface AuditRecordLogService {

    List<AuditRecordLog> findAll();

    AuditRecordLog save(AuditRecordLog businessChangeLog);

    AuditRecordLog findOne(long id);

    void delete(long id);

    List<AuditRecordLog> findByName(String name);
}
