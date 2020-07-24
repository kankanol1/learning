package com.qxd.learn.service;

import com.qxd.learn.entity.ProfessorEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfessorServiceImpl {

    @PersistenceContext
    private EntityManager em;

    public ProfessorServiceImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * 创建元素
     * @param professor
     * @return
     */
    public ProfessorEntity createProfessor(ProfessorEntity professor) {
        em.persist(professor);
        return professor;
    }

    /**
     * 删除元素
     *
     * @param id
     */
    public void removeProfessor(int id) {
        ProfessorEntity professorEntity = findProfessor(id);
        if (professorEntity != null) {
            em.remove(professorEntity);
        }
    }

    /**
     * 调整工资
     *
     * @param id
     * @param raise
     * @return
     */
    public ProfessorEntity raisePrfessorSalary(int id, long raise) {
        ProfessorEntity professorEntity = em.find(ProfessorEntity.class, id);
        if (professorEntity != null) {
            professorEntity.setSalary(professorEntity.getSalary() + raise);
        }
        return professorEntity;
    }

    /**
     * 查找元素
     * @param id
     * @return
     */
    public ProfessorEntity findProfessor(int id) {
        return em.find(ProfessorEntity.class, id);
    }

    /**
     * 查找所有元素
     * @return
     */
    public List<ProfessorEntity> findAllProfessor() {
        TypedQuery<ProfessorEntity> query = em.createQuery("SELECT e FROM ProfessorEntity e", ProfessorEntity.class);
        return query.getResultList();
    }

}
