package com.edu;

import java.util.List;

public class StopCommand implements Command {

    @Override
    public String apply(List<Student> students) {
        return "stop";
    }
}
