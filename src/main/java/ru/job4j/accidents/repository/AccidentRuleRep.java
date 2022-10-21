package ru.job4j.accidents.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Set;

@Repository
public class AccidentRuleRep {

    private final static String SQL_GET_RULES = "SELECT * FROM rule";
    private static final String SQL_GET_RULE_BY_ID = "SELECT * FROM rule WHERE id = ?";
    private static final String SQL_SAVE_RULE = "INSERT INTO accident_rules (acc_id, rule_id) VALUES (?, ?)";
    private static final String SQL_GET_RULES_BY_ACCID = "SELECT a.rule_id, r.name FROM accident_rules a JOIN rule r ON a.rule_id = r.id WHERE a.acc_id = ?";
    private static final String SQL_DELETE_RULES_BY_ACCID = "DELETE FROM accident_rules WHERE acc_id = ?";
    private final JdbcTemplate jdbc;

    public AccidentRuleRep(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Rule> getRules() {
        return jdbc.query(
                SQL_GET_RULES,
                new BeanPropertyRowMapper<>(Rule.class)
        );
    }

    public Rule getById(int ruleId) {
        return jdbc.query(
                        SQL_GET_RULE_BY_ID,
                        new Object[] {ruleId},
                        new BeanPropertyRowMapper<>(Rule.class)
                )
                .stream()
                .findAny()
                .orElse(null);
    }

    public Set<Rule> getByAccId(int accId) {
        return Set.copyOf(jdbc.query(
                        SQL_GET_RULES_BY_ACCID,
                        new Object[] {accId},
                        new BeanPropertyRowMapper<>(Rule.class)
                )
        );
    }

    public void save(int acc_id, Set<Rule> rules) {
        rules.forEach(rule_id -> jdbc.update(
                SQL_SAVE_RULE,
                acc_id,
                rule_id.getId()
                )
        );
    }

    public void update(int acc_id, Set<Rule> rules) {
        jdbc.update(SQL_DELETE_RULES_BY_ACCID,
                acc_id);
        save(acc_id, rules);
    }

}
