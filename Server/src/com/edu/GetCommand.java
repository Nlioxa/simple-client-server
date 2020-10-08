package com.edu;

import java.security.InvalidParameterException;
import java.util.List;

public class GetCommand implements Command {

    enum Mode {
        STUDENT,
        TEAM,
        ALL
    }

    private Mode mode;
    private String arg;

    public GetCommand(String[] args) throws InvalidParameterException {
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

        switch (this.mode) {
            case STUDENT: {
                result = "no such student\n";
                for (var student : students) {
                    if (student.name.equals(this.arg)) {
                        result = arg + "'s id: " + student.id;
                        break;
                    }
                }
            }
            break;
            case TEAM: {
                var team_id = Integer.parseInt(this.arg);
                result = "team #" + team_id + ":\n";

                var team_found = false;
                for (var student : students) {
                    if (student.id == team_id) {
                        result += " - " + student.name + "\n";
                        team_found = true;
                    }
                }

                if (!team_found) {
                    result = "no such team\n";
                }
            }
            break;
            case ALL: {
                if (students.size() > 0) {
                    result = "students:\n";
                    for (var student : students) {
                        result += " - team #" + student.id + " " + student.name + "\n";
                    }
                } else {
                    result = "students group is empty\n";
                }
            }
        }

        return result;
    }
}
