package com.qxd.learn.service;

import com.qxd.learn.entity.Professor2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class ProfessorDaoImpl2 {
    public Professor2 test(){
        Professor2 emp =new Professor2();
//        emp.setId(1);
        emp.setName("name");
        emp.setSalary(12345);
        emp.setPhoneNum("123456789");
        em.persist(emp);
        return emp;
    }

    @PersistenceContext
    private EntityManager em;

}
