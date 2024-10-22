package edu.agh.wfiis.solid.tasks.task2;

import java.util.ArrayList;
import java.util.List;

class Application {
    public static void main(String[] args) {
        Manage manage = new Manage();
        UserWithCoding u1 = new UserWithCoding("JohnDoe", "john.doe@example.com", "password123", 100, true);
        UserWithCoding jane = new UserWithCoding("jane", "jane.smith@example.com", "pass", 50, false);

        StringBuilder errors = new StringBuilder();
        manage.handleUserValidationProcess(u1, errors);
        if (errors.length() > 0) {
            System.out.println("Validation errors: " + errors);
        }
        manage.handleUserValidationProcess(jane, errors);
        if (errors.length() > 0) {
            System.out.println("Validation errors: " + errors);
        }

        List<String> comments = new ArrayList<>();
        comments.add("SOLID rules preserved");
        comments.add("Good naming");
        comments.add("Readable code");

        Order review = manage.handle(u1, comments, "Bob");
        manage.process(u1.id, review.ref, 10, "Great job, keep it up!");

        comments.add("LSP violated");
        comments.add("Good naming");
        Order review2 = manage.handle(jane, comments, "Bob");
        manage.handleExt(jane, new ArrayList<>(){{this.add("I forgot that ISP violation should be fixed too");}});
        manage.process(jane.id, review2.ref, 1, "Need some improvement!");

        String betterUser = manage.compare(u1, jane);
        System.out.println("User with better points: " + betterUser);
    }
}
