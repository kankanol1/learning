package com.qxd.learn.service;

import com.qxd.learn.entity.Person;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class PersonDaoImpl {
    public List<Person> test() {
        Person p1 = new Person("Tom", "Swith");
        p1.setId(1L);
        Person p2 = new Person("Jack", "Kook");
        p2.setId(2L);

        save(p1);
        save(p2);
        listAll();

        return getAll();
    }

    private void listAll() {
        List<Person> persons = getAll();
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    @PersistenceContext
    private EntityManager em;

    public Long save(Person person) {
        em.persist(person);
        return person.getId();
    }

    public List<Person> getAll() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }
}
