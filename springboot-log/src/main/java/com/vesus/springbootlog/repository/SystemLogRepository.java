package com.vesus.springbootlog.repository;

import com.vesus.springbootlog.model.SystemLog;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/5/20 上午11:31
 * @Version: 1.0
 */
public interface SystemLogRepository extends CrudRepository<SystemLog,Long> {
}
