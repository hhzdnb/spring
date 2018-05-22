package com.vesus.springbootjwt.repository;

import com.vesus.springbootjwt.model.UserInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo,String>,JpaSpecificationExecutor<UserInfo> {

}
