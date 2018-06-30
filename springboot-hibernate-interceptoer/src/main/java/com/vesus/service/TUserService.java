package com.vesus.service;


import com.vesus.domain.TUser;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author wx11055@163.com
 * @date 2018-06-27 23:19:28
 * Created by wuxia .
 */
public interface TUserService {

    TUser save(TUser tUser);

    <S extends TUser> Iterable<S> saveAll(Iterable<S> list);

    TUser findOne(Integer id);

    Optional<TUser> findById(Integer id);

    boolean existsById(Integer id);

    List<TUser> findAll();

    Long count();

    void deleteById(Integer id);

    void delete(TUser tUser);

    void deleteAll(Iterable<? extends TUser> iter);

    Page<TUser> findAllPaged(Integer pageNum, Integer pageSize);

    List<TUser> findAllByEntityManager(String excuteSql, Class returnType);
}
