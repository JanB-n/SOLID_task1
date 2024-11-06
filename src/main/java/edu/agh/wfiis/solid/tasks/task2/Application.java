package edu.agh.wfiis.solid.tasks.task2;

import java.util.ArrayList;
import java.util.List;

class Application {
    public static void main(String[] args) {
        Manager manager = new Manager();
        CodingUser JohnDoe = new CodingUser("JohnDoe", "john.doe@example.com", "password123", 100, true);
        CodingUser jane = new CodingUser("jane", "jane.smith@example.com", "pass", 50, false);

        StringBuilder errors = JohnDoe.validate();
        if (errors.length() > 0) {
            System.out.println("Validation errors: " + errors);
        }
        errors = jane.validate();
        if (errors.length() > 0) {
            System.out.println("Validation errors: " + errors);
        }

        List<String> comments = new ArrayList<>();
        comments.add("SOLID rules preserved");
        comments.add("Good naming");
        comments.add("Readable code");

        manager.createReview(JohnDoe, comments, "Bob");
        manager.rateCodingUser(JohnDoe,10, "Great job, keep it up!");

        comments.add("LSP violated");
        comments.add("Good naming");
        manager.createReview(jane, comments, "Bob");
        manager.updateReview(jane, new ArrayList<>(){{this.add("I forgot that ISP violation should be fixed too");}});
        manager.rateCodingUser(jane, 1, "Need some improvement!");

        String betterUser = manager.compareCodingUsersPoints(JohnDoe, jane);
        System.out.println("User with better points: " + betterUser);
    }
}
