package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

@Repository
@AllArgsConstructor
public class TypeHibernate {
    private final SessionFactory sf;

    public AccidentType getById(int typeId) {
        try (Session session = sf.openSession()) {
            return session.find(AccidentType.class, typeId);
        }
    }

    public List<AccidentType> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }
}
