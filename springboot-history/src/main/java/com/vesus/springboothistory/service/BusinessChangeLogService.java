package com.vesus.springboothistory.service;

import com.vesus.springboothistory.model.BusinessChangeLog;
import com.vesus.springboothistory.model.User;

import java.util.List;

public interface BusinessChangeLogService {

    public List<BusinessChangeLog> findAll();

    public void saveBusinessChangeLog(BusinessChangeLog businessChangeLog);

    public BusinessChangeLog findOne(long id);

    public void delete(long id);

    public List<BusinessChangeLog> findByName(String name);
}
