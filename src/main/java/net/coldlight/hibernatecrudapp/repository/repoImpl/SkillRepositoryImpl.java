package net.coldlight.hibernatecrudapp.repository.repoImpl;

import net.coldlight.hibernatecrudapp.model.Developer;
import net.coldlight.hibernatecrudapp.model.Skill;
import net.coldlight.hibernatecrudapp.repository.HibernateUtils;
import net.coldlight.hibernatecrudapp.repository.SkillRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {
    @Override
    public List<Skill> getAll() {
        List<Skill> skillList;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        skillList = session.createQuery("from Skill").list();
        transaction.commit();
        session.close();
        return skillList;
    }

    @Override
    public Skill getByID(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, id);
        transaction.commit();
        session.close();
        return skill;
    }

    @Override
    public Skill save(Skill skill) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(skill);
        transaction.commit();
        session.close();
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(skill);
        transaction.commit();
        session.close();
        return skill;
    }

    @Override
    public void delete(Skill skill) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(skill);
        transaction.commit();
        session.close();
    }
}
