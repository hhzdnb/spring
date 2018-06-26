package com.vesus.springboothistory.repository;

import com.vesus.springboothistory.model.BusinessChangeLog;
import org.springframework.data.repository.CrudRepository;

public interface BusinessChangeLogRepository extends CrudRepository<BusinessChangeLog,Long> {

}
