package edu.agh.wfiis.solid.tasks.task2;

import java.util.Set;

class CodingUser {
    private String id;
    private String email;
    private String password;
    private int points;
    private boolean isActive;

    CodingUser(String id, String email, String password, int points, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.points = points;
        this.isActive = isActive;
    }

    String getId(){
        return id;
    }

    int getPoints(){
        return points;
    }


    StringBuilder validate() {
        StringBuilder errors = new StringBuilder();
        if (this.id.isEmpty()) {
            errors.append("User key cannot be empty.\n");
        }
        if (!this.email.contains("@")) {
            errors.append("Client email is invalid.\n");
        }
        if (this.password.length() < 6) {
            errors.append("Pass must be at least 6 characters long.\n");
        }

        Set<Character> invalidChars = Set.of('!', '@', '#', '$', '%', '^', '&', '*');
        for (char c : this.password.toCharArray()) {
            if (invalidChars.contains(c)) {
                errors.append("Password contains an invalid character: ").append(c).append("\n");
            }
        }

        if (!this.isActive) {
            errors.append("Customer is currently inactive.\n");
        }
        return errors;
    }

    boolean validateInfo(String info){
        return info != null && !info.isEmpty();
    }
    void updatePoints(int score, String info) {
        if (validateInfo(info)) {
            points += score;
            System.out.println(info + " - New points total: " + points);
        } else {
            System.out.println("No additional information provided. Points not updated.");
        }
    }
}

