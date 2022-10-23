package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

@Repository
@AllArgsConstructor
public class TypeHibernate implements Wrapper {
    private final SessionFactory sf;

    public List<AccidentType> getAll() {
        return this.tx(session -> session
                .createQuery("from AccidentType", AccidentType.class).list(), sf);
    }

    public AccidentType getById(int id) {
        return this.tx(session -> session.find(AccidentType.class, id), sf);
    }
}
