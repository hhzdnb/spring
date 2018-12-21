package com.vesus.repository;

import com.vesus.domain.AuditRecordLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("operationRevisionLogRepository")
public interface AuditRecordLogRepository extends CrudRepository<AuditRecordLog, Long> {

}
