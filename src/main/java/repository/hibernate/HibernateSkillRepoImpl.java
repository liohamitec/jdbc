package repository.hibernate;

import model.Skill;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.SkillRepository;
import util.SessionFactoryCreator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class HibernateSkillRepoImpl implements SkillRepository {
    protected final SessionFactory sessionFactory;

    public HibernateSkillRepoImpl() {
        sessionFactory = SessionFactoryCreator.createFactory();
    }

    public int add(Skill skill) {
        int id = 0;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(skill);
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
            CriteriaDelete<Skill> delete = cb.createCriteriaDelete(Skill.class);

            Root skillRoot = delete.from(Skill.class);
            delete.where(cb.equal(skillRoot.get("id"), id));

            removedAmount = session.createQuery(delete).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return removedAmount;
    }

    public int update(Skill skill) {
        int updatedAmount = 0;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Skill> update = cb.createCriteriaUpdate(Skill.class);
            Root<Skill> skillRoot = update.from(Skill.class);

            update.set("name", skill.getName());
            update.where(cb.equal(skillRoot.get("id"), skill.getId()));

            updatedAmount = session.createQuery(update).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return updatedAmount;
    }

    public Skill getById(Long id) {
        Skill skill = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            skill = session.get(Skill.class,id);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return skill;
    }


    public Collection<Skill> getAll() {
        List<Skill> skillCollection = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            skillCollection = session.createQuery("FROM Skill").list();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return skillCollection;
    }
}
