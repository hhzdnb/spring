package com.vesus.springboothistory.service.impl;

import com.vesus.springboothistory.model.BusinessChangeLog;
import com.vesus.springboothistory.model.User;
import com.vesus.springboothistory.repository.BusinessChangeLogRepository;
import com.vesus.springboothistory.service.BusinessChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessChangeLogServiceImpl implements BusinessChangeLogService {

    @Autowired
    BusinessChangeLogRepository bussinessChangeLogRepository ;

    @Override
    public List<BusinessChangeLog> findAll() {
        return null;
    }

    public void saveBusinessChangeLog(BusinessChangeLog businessChangeLog) {
        bussinessChangeLogRepository.save(businessChangeLog);
    }

    @Override
    public BusinessChangeLog findOne(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<BusinessChangeLog> findByName(String name) {
        return null;
    }
}
