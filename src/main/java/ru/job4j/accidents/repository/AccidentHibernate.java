package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentHibernate  implements Wrapper {
    private final SessionFactory sf;

    public void save(Accident accident) {
        this.tx(session -> session.save(accident), sf);
    }

    public List<Accident> getAll() {
        return this.tx(session -> session.createQuery("select distinct a from Accident a join fetch a.rules").list(), sf);
    }

    public Accident getById(int id) {
        return (Accident) this.tx(session -> session.createQuery("select distinct a from Accident a join fetch a.rules where a.id = :id")
                .setParameter("id", id).uniqueResult(), sf);
    }

    public void update(Accident accident) {
        this.tx(session -> {
            session.update(accident);
            return accident;
        }, sf);
    }

    public void delete(Accident accident) {
        this.tx(session -> {
            session.delete(accident);
            return accident;
        }, sf);
    }

}