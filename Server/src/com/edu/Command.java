package com.edu;

import java.util.List;

public interface Command {
    public abstract String apply(List<Student> students);
}

