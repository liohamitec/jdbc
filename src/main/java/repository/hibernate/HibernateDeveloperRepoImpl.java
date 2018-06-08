package repository.hibernate;

import model.Developer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.DeveloperRepository;
import util.SessionFactoryCreator;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

public class HibernateDeveloperRepoImpl implements DeveloperRepository {

    protected final SessionFactory sessionFactory;

    public HibernateDeveloperRepoImpl() {
        sessionFactory = SessionFactoryCreator.createFactory();
    }

    public int add(Developer dev) {
        int id = 0;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(dev);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int remove(Long id) {
        int removedAmount = 0;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
            CriteriaDelete<Developer> delete = cb.createCriteriaDelete(Developer.class);

            Root devRoot = delete.from(Developer.class);
            delete.where(cb.equal(devRoot.get("id"), id));

            removedAmount = session.createQuery(delete).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return removedAmount;
    }

    public int update(Developer dev) {
        int updatedAmount = 0;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Developer> update = cb.createCriteriaUpdate(Developer.class);
            Root<Developer> devRoot = update.from(Developer.class);

            update.set("name", dev.getName());
            update.set("age", dev.getAge());
            update.set("salary", dev.getSalary());

            update.where(cb.equal(devRoot.get("id"), dev.getId()));

            updatedAmount = session.createQuery(update).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return updatedAmount;
    }

    public Developer getById(Long id) {
        Developer dev = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            dev = session.get(Developer.class,id);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return dev;
    }

    public Collection<Developer> getAll() {
        List<Developer> devCollection = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            devCollection = session.createQuery("from model.Developer").list();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return devCollection;
    }
}
