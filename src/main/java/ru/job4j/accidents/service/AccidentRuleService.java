package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleHibernate;

import java.util.Collection;

@Service
public class AccidentRuleService {

    private final RuleHibernate accidentRuleRep;

    public AccidentRuleService(RuleHibernate accidentRuleRep) {
        this.accidentRuleRep = accidentRuleRep;
    }

    public Collection<Rule> getRules() {
        return accidentRuleRep.getAll();

    }

    public Rule getById(int ruleId) {
        return accidentRuleRep.getById(ruleId);
    }
}
