package com.vesus.springbootlog.service.impl;

import com.vesus.springbootlog.annotation.SystemServiceLog;
import com.vesus.springbootlog.model.SystemLog;
import com.vesus.springbootlog.repository.SystemLogRepository;
import com.vesus.springbootlog.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/5/20 上午11:45
 * @Version: 1.0
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    SystemLogRepository systemLogRepository ;
    @Override
    public List<SystemLog> findAll() {
        return null;
    }

    @Override
    public void saveUser(SystemLog log) {
        systemLogRepository.save(log);
    }

    @Override
    public SystemLog findOne(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
