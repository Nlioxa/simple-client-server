package com.edu;

import java.security.InvalidParameterException;
import java.util.List;

public class RemoveCommand implements Command {

    enum Mode {
        STUDENT,
        TEAM,
        ALL
    }

    private Mode mode;
    String arg;

    public RemoveCommand(String[] args) {
        try {
            switch (args[0]) {
                case "-s":
                    this.mode = Mode.STUDENT;
                    this.arg = args[1];
                    break;
                case "-t":
                    this.mode = Mode.TEAM;
                    this.arg = args[1];
                    Integer.parseInt(this.arg);
                    break;
                case "-a":
                    this.mode = Mode.ALL;
                    break;
                default:
                    throw new Exception("");
            }
        } catch (Exception e) {
            throw new InvalidParameterException("Invalid command parameters.");
        }
    }

    @Override
    public String apply(List<Student> students) {
        var result = "";
        var deleted_students = students.size();
        switch (this.mode) {
            case STUDENT: {
                students.removeIf(student -> student.name.equals(arg));
                deleted_students -= students.size();
            }
            break;
            case TEAM: {
                var teamId = Integer.parseInt(this.arg);
                students.removeIf(student -> student.id == teamId);
                deleted_students -= students.size();
            }
            break;
            case ALL: {
                students.clear();
            }
            break;
        }
        return "removed " + deleted_students + " students\n";
    }
}
