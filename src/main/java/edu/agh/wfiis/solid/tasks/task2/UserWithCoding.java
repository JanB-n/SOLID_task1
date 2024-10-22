package edu.agh.wfiis.solid.tasks.task2;

class UserWithCoding {
    String id;
    String userEmailAddress;
    String userPasswordString;
    int ptsInt;
    boolean isUserAccountActive;

    UserWithCoding(String id, String userEmailAddress, String userPasswordString, int pts, boolean isUserAccountActive) {
        this.id = id;
        this.userEmailAddress = userEmailAddress;
        this.userPasswordString = userPasswordString;
        this.ptsInt = pts;
        this.isUserAccountActive = isUserAccountActive;
    }

    void handle(int score, String info) {
        if (info != null && !info.isEmpty()) {
            ptsInt += score;
            System.out.println(info + " - New points total: " + ptsInt);
        } else {
            System.out.println("No additional information provided. Points not updated.");
        }
    }
}

