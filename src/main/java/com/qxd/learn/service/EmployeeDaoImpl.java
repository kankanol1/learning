package com.qxd.learn.service;

import com.qxd.learn.entity.Employee;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class EmployeeDaoImpl {
    public Employee test() {
        Employee emp = new Employee();
        emp.setName("Tom");

        em.persist(emp);

        emp = em.find(Employee.class, emp.getId());

        Employee managedEmp = em.merge(emp);
        managedEmp.setLastAccessTime(new Date());
        return emp;
    }

    @PersistenceContext
    private EntityManager em;
}
