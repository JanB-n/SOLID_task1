package edu.agh.wfiis.solid.tasks.task1;

public class CodeReviewer extends User {
    public CodeReviewer(String name) {
        super(name);
    }

    public void reviewCode(SolidPoints solidPoints, String codeQuality) {
        switch (codeQuality) {
            case "good" -> {
                solidPoints.addPoints(10);
                //System.out.println("Good code reviewed. Points awarded to " + SolidPoints.name + ".");
            }
            case "excellent" -> solidPoints.addPoints(75);
            case "fantastic" -> solidPoints.addPoints(100);
            default -> {
                solidPoints.subtractPoints(10);

                //System.out.println("Bad code reviewed. Points subtracted from " + user.name + ".");
            }
        }
        System.out.printf("Code reviewed for user: %s. Code quality: %s.\n", solidPoints.getName(), codeQuality);
    }
}

