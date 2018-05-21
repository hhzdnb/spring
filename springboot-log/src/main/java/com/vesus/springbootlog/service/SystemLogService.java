package com.vesus.springbootlog.service;

import com.vesus.springbootlog.model.SystemLog;

import java.util.List;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/5/20 上午11:43
 * @Version: 1.0
 */
public interface SystemLogService {

    public List<SystemLog> findAll();

    public void saveUser(SystemLog log);

    public SystemLog findOne(long id);

    public void delete(long id);

}
