package com.vesus.service.imp;


import com.vesus.domain.TUser;
import com.vesus.repository.TUserRepository;
import com.vesus.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("tUserService")
@Transactional(propagation = Propagation.REQUIRED)
public class TUserServiceImpl implements TUserService {

    @Autowired
    private TUserRepository tUserRepository;

    //
    //@PersistenceContext
    //private EntityManager em;

    @Override
    public TUser save(TUser tUser) {
        return tUserRepository.save(tUser);
    }

    @Override
    public <S extends TUser> Iterable<S> saveAll(Iterable<S> list) {
        // return  tUserRepository.saveAll(list);
        return null;
    }


    @Override
    public TUser findOne(Integer id) {
        //  return  tUserRepository.findById(id).get();

        return tUserRepository.findOne(id);
    }

    @Override
    public Optional<TUser> findById(Integer id) {
        //return  tUserRepository.findById(id);
        return null;
    }


    @Override
    public boolean existsById(Integer id) {
        //return tUserRepository.existsById(id);

        return tUserRepository.exists(id);
    }

    @Override
    public List<TUser> findAll() {
        return (List<TUser>) tUserRepository.findAll();
    }

    @Override
    public Long count() {
        return tUserRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        //  tUserRepository.deleteById(id);
        tUserRepository.delete(id);
    }

    @Override
    public void delete(TUser tUser) {
        tUserRepository.delete(tUser);
    }


    @Override
    public void deleteAll(Iterable<? extends TUser> iter) {
        //  tUserRepository.deleteAll(iter);
    }

    @Override
    public Page<TUser> findAllPaged(Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        return tUserRepository.findAllPaged(pageable);
    }

    public List<TUser> findAllByEntityManager(String excuteSql, Class returnType) {
        //Query query = em.createNativeQuery(excuteSql, returnType);
        //List list = query.getResultList();
        return null;
    }

}
