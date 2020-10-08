package com.edu;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetCommand implements Command {

    enum Mode {
        ALL,
        TEAM,
        STUDENTS
    }

    private final Mode mode;
    private final int teamId;
    private final String[] args;

    public SetCommand(String[] args) {
        try {
            switch (args[0]) {
                case "-a":
                    this.mode = Mode.ALL;
                    this.teamId = Integer.parseInt(args[1]);
                    this.args = Arrays.copyOfRange(args, 2, args.length);
                    break;
                case "-t":
                    this.mode = Mode.TEAM;
                    this.teamId = Integer.parseInt(args[1]);
                    this.args = Arrays.copyOfRange(args, 2, args.length);
                    break;
                default:
                    this.mode = Mode.STUDENTS;
                    this.teamId = Integer.parseInt(args[0]);
                    this.args = Arrays.copyOfRange(args, 1, args.length);
            }
        } catch (Exception e) {
            throw new InvalidParameterException("Invalid command parameters.");
        }
    }

    @Override
    public String apply(List<Student> students) {
        var result = "";
        switch (this.mode) {
            case ALL: {
                for (var student : students) {
                    student.id = this.teamId;
                }
                result = "set team id " + this.teamId + " to all students\n";
            }
            break;
            case TEAM: {
                var modified_students = new ArrayList<Student>();
                for (var student : students) {
                    for (var idStr : this.args) {
                        try {
                            var id = Integer.parseInt(idStr);
                            if (student.id == id) {
                                student.id = this.teamId;
                                modified_students.add(student);
                            }
                        } catch (NumberFormatException e) {
                            // ignore this exception
                        }
                    }
                }

                if (modified_students.size() > 0) {
                    result = "set team id " + this.teamId + " to " + modified_students.size() + " students:\n";
                    for (var student : modified_students) {
                        result += " - " + student.name + "\n";
                    }
                }
            }
            break;
            case STUDENTS: {
                var modified_students = new ArrayList<Student>();
                for (var student : students) {
                    for (var studentName : this.args) {
                        if (student.name.equals(studentName)) {
                            student.id = this.teamId;
                            modified_students.add(student);
                        }
                    }
                }
                if (modified_students.size() > 0) {
                    result = "set team id " + this.teamId + " to " + modified_students.size() + " students:\n";
                    for (var student : modified_students) {
                        result += " - " + student.name + "\n";
                    }
                }
            }
            break;
        }
        return result;
    }
}
