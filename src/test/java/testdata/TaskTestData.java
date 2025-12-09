package testdata;

import models.Task;

public class TaskTestData {

    public static Task getAddNewTask1() {
        return new Task.Builder()
                .subject("Complete Project Documentation")
                .hourlyRate("50.00")
                .startDate("09-12-2025")
                .dueDate("24-12-2025")
                .priority("High")
                .repeatEvery("Week")
                .relatedTo("Ticket")
                .assignee("Admin Anh Tester")
                .follower("Project Manager")
                .tag("test")
                .description("Complete all project documentation and submit for review")
                .isPublic(true)
                .isBillable(true)
                .build();
    }


}
