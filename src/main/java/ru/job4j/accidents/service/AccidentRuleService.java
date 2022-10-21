package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRuleRep;

import java.util.Collection;
import java.util.Set;

@Service
public class AccidentRuleService {

    private final AccidentRuleRep accidentRuleRep;

    public AccidentRuleService(AccidentRuleRep accidentRuleRep) {
        this.accidentRuleRep = accidentRuleRep;
    }

    public Collection<Rule> getRules() {
        return accidentRuleRep.getRules();

    }

    public Rule getById(int ruleId) {
        return accidentRuleRep.getById(ruleId);
    }

    public void save(int accid, Set<Rule> rules) {
        accidentRuleRep.save(accid, rules);
    }

    public void update(int accid, Set<Rule> rules) {
        accidentRuleRep.update(accid, rules);
    }

    public Set<Rule> getByAccId(int accId) {
        return accidentRuleRep.getByAccId(accId);
    }
}
