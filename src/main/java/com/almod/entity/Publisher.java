package com.almod.entity;

public enum Publisher {
    MOSCOW("Москва"),
    PETER("Питер"),
    O_REILLY(" O’Reilly");

    private String name;

    Publisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
