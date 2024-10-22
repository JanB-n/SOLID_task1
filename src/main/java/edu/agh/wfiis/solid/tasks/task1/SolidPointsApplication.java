package edu.agh.wfiis.solid.tasks.task1;

public class SolidPointsApplication {
    public static void main(String[] args) {
        SolidPoints alice = new SolidPoints("Alice");
        CodeReviewer bob = new CodeReviewer("Bob");
        PointsViewer charlie = new PointsViewer("Charlie");

        // Adding points directly for Alice
            alice.addPoints(10);
            alice.displayPoints();
            alice.addPoints(20);
            alice.displayPoints();

            // Reviewer Bob reviews code and adds points for Alice
            bob.reviewCode(alice, "excellent");
            alice.displayPoints();

            // Viewer Charlie views Alice's points
            charlie.viewPoints(alice);

            // Subtract points for bad review
            bob.reviewCode(alice, "bad");
            alice.displayPoints();
    }
}

