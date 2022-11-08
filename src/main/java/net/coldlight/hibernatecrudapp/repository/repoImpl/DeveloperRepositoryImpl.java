package net.coldlight.hibernatecrudapp.repository.repoImpl;

import net.coldlight.hibernatecrudapp.model.Developer;
import net.coldlight.hibernatecrudapp.model.Skill;
import net.coldlight.hibernatecrudapp.repository.DeveloperRepository;
import net.coldlight.hibernatecrudapp.repository.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public List<Developer> getAll() {
        List<Developer> developerList;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        developerList = session.createQuery("select distinct d from Developer d " +
                "left join fetch d.skills s " +
                "left join fetch d.speciality sp").getResultList();
        transaction.commit();
        session.close();
        return developerList;
    }

    @Override
    public Developer getByID(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, id);
        transaction.commit();
        session.close();
        return developer;
    }

    @Override
    public Developer save(Developer developer) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(developer);
        transaction.commit();
        session.close();
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(developer);
        transaction.commit();
        session.close();
        return developer;
    }

    @Override
    public void delete(Developer developer) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean haveSkill(long developerId, long skillId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, developerId);
        Skill skill = session.get(Skill.class, skillId);
        transaction.commit();
        session.close();
        for (Skill s: developer.getSkills()) {
            if (s.getId() == skill.getId()) {
                return true;
            }
        }
        return false;
    }
}
