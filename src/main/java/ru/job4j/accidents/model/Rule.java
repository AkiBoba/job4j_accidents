package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "rule")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    private String name;

}