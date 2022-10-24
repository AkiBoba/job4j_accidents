package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class RuleHibernate {
    @Autowired
    private final CrudRepository crudRepository;

    public List<Rule> getAll() {
        return crudRepository
                .query("from Rule", Rule.class);
    }

    public Rule getById(int id) {
        return crudRepository.get(
                "from Rule where id = :fId", Rule.class,
                Map.of("fId", id)
        );
    }
}
