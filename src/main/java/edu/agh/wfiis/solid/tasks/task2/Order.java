package edu.agh.wfiis.solid.tasks.task2;

import java.util.List;

class Order {
    int ref;
    boolean status;
    String data;
    List<String> dataExt;

    Order(int ref, String data, List<String> dataExt, boolean status) {
        this.ref = ref;
        this.data = data;
        this.dataExt = dataExt;
        this.status = status;
    }

    void process(List<String> list, boolean updateOnly) {
        if (!updateOnly && status) {
            throw new IllegalArgumentException("Order with ID " + ref + " has already been processed. Cannot process again.");
        } else {
            if (!updateOnly) {
                status = true;
                System.out.println("Order with ID " + ref + " has been processed.");
            }
            if (updateOnly) {
                for (String data : list) {
                    System.out.println("Comment: " + data);
                    if (data.length() > 100) {
                        System.out.println("too long!");
                    } else {
                        dataExt.add(data);
                    }
                }
            }
        }
    }

    String isFinished() {
        return status ? "Processed" : "Not Processed";
    }
}
