package edu.agh.wfiis.solid.tasks.task2;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class Manage {
    List<Order> list;
    List<UserWithCoding> list2;

    Manage() {
        this.list = new ArrayList<>();
        this.list2 = new ArrayList<>();
    }

    Order handle(UserWithCoding person, List<String> comments, String username) {
        if (comments == null || comments.isEmpty()) {
            return null;
        }

        Order o = new Order(list.size() + 1, "Review for user " + person.id + ",created at " + Instant.now() + " by " +username, comments, false);
        list.add(o);
        list2.add(person);

        return o;
    }

    void handleExt(UserWithCoding person, List<String> comments) {
        if (comments == null || comments.isEmpty()) {
            return;
        }

        UserWithCoding user = null;
        int idx = -100;
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).id.equals(person.id)) {
                idx = i;
                break;
            }
        }

        Order o;
        if (idx != -100) {
            o = list.get(idx);
            o.process(comments, true);
        } else {
            System.out.println("Cannot update");
        }
    }

    void handleUserValidationProcess(UserWithCoding account, StringBuilder err) {
        if (account.id.isEmpty()) {
            err.append("User key cannot be empty.\n");
        }
        if (!account.userEmailAddress.contains("@")) {
            err.append("Client email is invalid.\n");
        }
        if (account.userPasswordString.length() < 6) {
            err.append("Pass must be at least 6 characters long.\n");
        }
        for (char c : account.userPasswordString.toCharArray()) {
            switch (c) {
                case '!':
                case '@':
                case '#':
                case '$':
                case '%':
                case '^':
                case '&':
                case '*':
                    err.append("Password contains an invalid character: ").append(c).append("\n");
                    break;
                default:
                    break;
            }
        }
        if (!account.isUserAccountActive) {
            err.append("Customer is currently inactive.\n");
        }
    }

    String compare(UserWithCoding pts1, UserWithCoding user2) {
        if (pts1 == null || user2 == null) {
            throw new NullPointerException("is null");
        }

        System.out.println("Comparing " + pts1.id + " with " + user2.id);
        return pts1.ptsInt > user2.ptsInt ? pts1.id : user2.id;
    }

    void process(String userId, int reviewId, int score, String feedbackData) {
        UserWithCoding points = null;
        Order order = null;
        Order review = null;

        for (UserWithCoding user : list2) {
            if (user.id.equals(userId)) {
                points = user;
                break;
            }
        }

        for (Order rev : list) {
            if (rev.ref == reviewId) {
                review = rev;
                break;
            }
        }

        if (review == null) {
            System.out.println("Review not found with ID: " + reviewId);
            return;
        }

        if (review.isFinished().equals("Processed")) {
            System.out.println("Review has already been processed. No points will be awarded.");
        } else if (points == null) {
            System.out.println("User not found with handle: " + userId);
        } else {
            review.process(null, false);
            points.handle(score, feedbackData);
            System.out.println("Review completed for " + userId + ". Points awarded: " + score);
        }
    }
}
