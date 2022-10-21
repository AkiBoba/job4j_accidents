package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidenTypeService;
import ru.job4j.accidents.service.AccidentRuleService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private static final String SQL_GET_TYPE = "SELECT type_id FROM accident WHERE id = ?";
    private final JdbcTemplate jdbc;
    private final AccidentRuleService accidentRuleService;
    private final AccidenTypeService accidenTypeService;

    public int save(Accident accident, int typeId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into accident (name, text, address, type_id) values (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, typeId);
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        assert keys != null;
        return (int) keys.get("id");
    }

    public List<Accident> getAll() {
        List<Accident> accidents = jdbc.query(
                "select * from accident",
                new BeanPropertyRowMapper<>(Accident.class)
        );
        accidents.forEach(accident -> accident.setType(accidenTypeService.getById(
                jdbc.queryForObject(SQL_GET_TYPE,
                        new Object[] {accident.getId()},
                                Integer.class
                        )
                )
        ));
        accidents.forEach(accident -> accident.setRule(accidentRuleService.getByAccId(accident.getId()
                )
        ));
        return accidents;

    }

    public Accident findById(int id) {
        return jdbc.query(
                "select * from accident where id = ?",
                new Object[] {id},
                new BeanPropertyRowMapper<>(Accident.class)
                )
                .stream()
                .findAny()
                .orElse(null);
    }

    public Boolean update(Accident accident, int typeId) {
        return jdbc.update(
                "UPDATE accident SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                typeId,
                accident.getId()
                ) > 0;
    }

    public Boolean delete(int id) {
        return jdbc.update(
                "DELETE FROM accident WHERE id = ?",
                id
                ) > 0;
    }
}