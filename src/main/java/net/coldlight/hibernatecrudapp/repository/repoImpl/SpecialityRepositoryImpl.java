package net.coldlight.hibernatecrudapp.repository.repoImpl;

import net.coldlight.hibernatecrudapp.model.Skill;
import net.coldlight.hibernatecrudapp.model.Speciality;
import net.coldlight.hibernatecrudapp.repository.HibernateUtils;
import net.coldlight.hibernatecrudapp.repository.SpecialityRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SpecialityRepositoryImpl implements SpecialityRepository {
    @Override
    public List<Speciality> getAll() {
        List<Speciality> specialityList;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        specialityList = session.createQuery("from Speciality").list();
        transaction.commit();
        session.close();
        return specialityList;
    }

    @Override
    public Speciality getByID(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Speciality speciality = session.get(Speciality.class, id);
        transaction.commit();
        session.close();
        return speciality;
    }

    @Override
    public Speciality save(Speciality speciality) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(speciality);
        transaction.commit();
        session.close();
        return speciality;
    }

    @Override
    public Speciality update(Speciality speciality) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(speciality);
        transaction.commit();
        session.close();
        return speciality;
    }

    @Override
    public void delete(Speciality speciality) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(speciality);
        transaction.commit();
        session.close();
    }
}
