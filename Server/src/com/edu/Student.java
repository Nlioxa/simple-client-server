package com.edu;

import java.util.Objects;

public class Student {
    int id;
    String name;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
