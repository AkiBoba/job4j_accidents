package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionFactory sf;

    public Accident save(Accident accident, int typeId) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }

    public Accident getById(int id) {
        try (Session session = sf.openSession()) {
            return session.find(Accident.class, id);
        }
    }

    public void delete(Accident accident) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.delete(accident);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void update(Accident accident) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.update(accident);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}