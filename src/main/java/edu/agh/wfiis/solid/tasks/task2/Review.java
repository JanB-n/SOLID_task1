package edu.agh.wfiis.solid.tasks.task2;

import java.time.Instant;
import java.util.List;

class Review {
    private int id;
    private String userId;
    private List<String> comments;
    private Instant creationDate;
    private String reviewer;
    private boolean processed;

    Review(int id, String userId, List<String> comments, String reviewer) {
        this.id = id;
        this.userId = userId;
        this.comments = comments;
        this.creationDate = Instant.now();
        this.reviewer = reviewer;
        this.processed = false;
    }

    void updateReview(List<String> comments){
        for (String comment : comments) {
            System.out.println("Comment: " + comment);
            if (comment.length() > 100) {
                System.out.println("too long!");
            } else {
                this.comments.add(comment);
            }
        }
    }

    void changeStatus() {
        this.processed = true;
        System.out.println("Review with ID " + this.id + " has been processed.");
    }


    boolean isProcessed() {
        return processed;
    }
}
