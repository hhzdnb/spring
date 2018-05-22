package com.vesus.springbootjwt.repository;

import com.vesus.springbootjwt.model.TokenInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<TokenInfo,Integer>,JpaSpecificationExecutor<TokenInfo> {

}
