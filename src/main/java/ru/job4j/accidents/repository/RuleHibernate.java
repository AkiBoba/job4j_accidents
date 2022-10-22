package ru.job4j.accidents.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;

@Repository
public class RuleHibernate {
    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Rule getById(int id) {
        try (Session session = sf.openSession()) {
            return session.find(Rule.class, id);
        }
    }

    public List<Rule> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }
}
