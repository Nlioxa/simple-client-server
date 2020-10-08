package com.edu;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class AddCommand implements Command {

    private final int id;
    private final String[] names;

    public AddCommand(String[] args) throws InvalidParameterException {
        try {
            if (args[0].equals("-t")) {
                this.id = Integer.parseInt(args[1]);
                this.names = Arrays.copyOfRange(args, 2, args.length);
            } else {
                this.id = 0;
                this.names = args;
            }
        } catch (Exception e) {
            throw new InvalidParameterException("Invalid command parameters.");
        }
    }

    @Override
    public String apply(List<Student> students) {
        for (var name: names) {
            var student = new Student(name, this.id);
            students.add(student);
        }
        return "added " + names.length + " students to the " + this.id + " team\n";
    }

}
