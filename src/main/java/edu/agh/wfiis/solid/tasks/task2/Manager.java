package edu.agh.wfiis.solid.tasks.task2;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class Manager {
    List<Review> reviews;
    List<CodingUser> codingUsers;

    Manager() {
        this.reviews = new ArrayList<>();
        this.codingUsers = new ArrayList<>();
    }
    void validateComments(List<String> comments) throws IllegalArgumentException{
        if (comments == null || comments.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    Review FindReviewForUser(CodingUser codingUser) throws NoSuchElementException {
        int idx = -100;
        for (int i = 0; i < codingUsers.size(); i++) {
            CodingUser currentUser = codingUsers.get(i);
            String currentUserId = currentUser.getId();
            if (currentUserId.equals(codingUser.getId())) {
                idx = i;
                break;
            }
        }
        if (idx == -100){
            System.out.println("Cannot update");
            throw new NoSuchElementException();
        }

        return reviews.get(idx);
    }

    void createReview(CodingUser codingUser, List<String> comments, String reviewer) {
        validateComments(comments);
        Review review = new Review(reviews.size() + 1, codingUser.getId(), comments, reviewer);
        reviews.add(review);
        codingUsers.add(codingUser);
    }

    void updateReview(CodingUser codingUser, List<String> comments) {
        validateComments(comments);
        Review review = FindReviewForUser(codingUser);
        review.updateReview(comments);
    }


    String compareCodingUsersPoints(CodingUser user1, CodingUser user2) {
        if (user1 == null || user2 == null) {
            throw new NullPointerException("is null");
        }

        System.out.println("Comparing " + user1.getId() + " with " + user2.getId());
        return user1.getPoints() > user2.getPoints() ? user1.getId() : user2.getId();
    }

    boolean isUserInCodingUsers(CodingUser codingUser) {
        String userId = codingUser.getId();
        boolean userInCodingUsers = false;
        for (CodingUser user : codingUsers) {
            String currentUserId = user.getId();
            if (currentUserId.equals(userId)) {
                userInCodingUsers = true;
                break;
            }
        }
        return userInCodingUsers;
    }

    void checkRatingConditions(CodingUser codingUser, Review review) throws NoSuchElementException {
        String userId = codingUser.getId();
        boolean isUserInCodingUsers = false;
        for (CodingUser user : codingUsers) {
            String currentUserId = user.getId();
            if (currentUserId.equals(userId)) {
                isUserInCodingUsers = true;
                break;
            }
        }

        if (review.isProcessed()) {
            System.out.println("Review has already been processed. No points will be awarded.");
            throw new NoSuchElementException();
        } else if (!isUserInCodingUsers) {
            System.out.println("User not found with handle: " + userId);
            throw new NoSuchElementException();
        }
    }

    void rateCodingUser(CodingUser codingUser, int score, String feedback) {
        Review review = FindReviewForUser(codingUser);
        checkRatingConditions(codingUser, review);

        review.changeStatus();
        codingUser.updatePoints(score, feedback);
        System.out.println("Review completed for " + codingUser.getId() + ". Points awarded: " + score);

    }
}
