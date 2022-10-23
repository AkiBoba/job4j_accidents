package ru.job4j.accidents.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;

@Repository
public class RuleHibernate implements Wrapper {
    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Rule> getAll() {
        return this.tx(session -> session
                .createQuery("from Rule", Rule.class).list(), sf);
    }

    public Rule getById(int id) {
        return this.tx(session -> session.find(Rule.class, id), sf);
    }
}
