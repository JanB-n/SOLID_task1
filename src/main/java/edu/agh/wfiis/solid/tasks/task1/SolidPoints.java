package edu.agh.wfiis.solid.tasks.task1;

public class SolidPoints extends User {
    protected int points = 0;

    SolidPoints(String name){
        super(name);
    }
    public void addPoints(int points) {
        if (points > 0 && points < 100) {
            this.points += points;
        } else {
            System.out.println("Points must be positive and no more than 100.");
        }
    }

    public void subtractPoints(int points) {
        if (points > 0 && points <= this.points) {
            this.points -= points;
        } else {
            System.out.printf("Points must be positive and no more than %d.", points);
        }
    }
    public void displayPoints() {
        System.out.println("Total SOLID points: " + this.points + " for user: " + this.name);
    }

    public void resetPoints() {
        this.points = 0;
    }
}