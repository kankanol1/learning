package com.qxd.learn.service.wx;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class KFAccountDaoImpl {

    @PersistenceContext
    private EntityManager kfaccount;

    public KFAccountDaoImpl(EntityManager token) {
        this.kfaccount = token;
    }




}
