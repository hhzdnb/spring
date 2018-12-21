package com.vesus.repository;

import com.vesus.domain.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * @author wx11055@163.com
 * @date 2018-06-27 23:19:28
 * Created by CodeGen .
 */
@Repository("tUserRepository")
public interface TUserRepository extends CrudRepository<TUser, Integer> {
    @Query("select l from TUser l")
    Page<TUser> findAllPaged(Pageable pageable);

    //Page<TUser> findAllAndSortPaged(Pageable pageable, Sort sort);

    @Query("select l from TUser l")
    Stream<TUser> findAllAndStreamPaged(Pageable pageable);

    //Page<TUser> queryFirst10ByName(String name, Pageable pageable);

}
