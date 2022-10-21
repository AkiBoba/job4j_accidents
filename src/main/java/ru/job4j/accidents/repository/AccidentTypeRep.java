package ru.job4j.accidents.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

@Repository
public class AccidentTypeRep {
    private final JdbcTemplate jdbc;

    private final static String SQL_GET_TYPES = "SELECT * FROM accidenttype";
    private final static String SQL_GET_TYPE_BY_ID = "SELECT * FROM accidenttype WHERE id = ?";

    public AccidentTypeRep(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<AccidentType> getTypes() {
        return jdbc.query(
                SQL_GET_TYPES,
                new BeanPropertyRowMapper<>(AccidentType.class)
        );
    }

    public AccidentType getById(int typeId) {
        return jdbc.query(
                SQL_GET_TYPE_BY_ID,
                        new Object[] {typeId},
                        new BeanPropertyRowMapper<>(AccidentType.class)
                )
                .stream()
                .findAny()
                .orElse(null);
    }

}
