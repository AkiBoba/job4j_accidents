package ru.job4j.accidents.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rule {
    private int id;
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
    }
}