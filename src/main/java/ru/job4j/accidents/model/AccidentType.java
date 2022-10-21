package ru.job4j.accidents.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccidentType {
    private int id;
    private String name;

    public static AccidentType of(int id, String name) {
        AccidentType accidentType = new AccidentType();
        accidentType.id = id;
        accidentType.name = name;
        return accidentType;
    }
}