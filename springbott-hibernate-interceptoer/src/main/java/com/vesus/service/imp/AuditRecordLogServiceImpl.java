package com.vesus.service.imp;


import com.vesus.domain.AuditRecordLog;
import com.vesus.repository.AuditRecordLogRepository;
import com.vesus.service.AuditRecordLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditRecordLogServiceImpl implements AuditRecordLogService {

    @Autowired
    private AuditRecordLogRepository auditRecordLogRepository;

    @Override
    public List<AuditRecordLog> findAll() {
        return null;
    }

    @Override
    public AuditRecordLog save(AuditRecordLog auditRecordLog) {
        return auditRecordLogRepository.save(auditRecordLog);
    }

    @Override
    public AuditRecordLog findOne(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<AuditRecordLog> findByName(String name) {
        return null;
    }
}
