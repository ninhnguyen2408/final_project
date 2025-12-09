package models;

import org.apache.poi.hssf.record.SubRecord;

public class Task {
    private String subject;
    private String hourlyRate;
    private String startDate;
    private String dueDate;
    private String priority;
    private String repeatEvery;
    private String relatedTo;
    private String assignee;
    private String follower;
    private String tag;
    private String description;
    private boolean isPublic;
    private boolean isBillable;

    public Task(String subject, String hourlyRate, String startDate, String dueDate, String priority, String repeatEvery,
                String relatedTo, String assignee, String follower, String tag,
                String description, boolean isPublic, boolean isBillable) {
        this.subject = subject;
        this.hourlyRate = hourlyRate;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.priority = priority;
        this.repeatEvery = repeatEvery;
        this.relatedTo = relatedTo;
        this.assignee = assignee;
        this.follower = follower;
        this.tag = tag;
        this.description = description;
        this.isPublic = isPublic;
        this.isBillable = isBillable;
    }

    public static class Builder {
        private String subject;
        private String hourlyRate;
        private String startDate;
        private String dueDate;
        private String priority;
        private String repeatEvery;
        private String relatedTo;
        private String assignee;
        private String follower;
        private String tag;
        private String description;
        private boolean isPublic;
        private boolean isBillable;

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder hourlyRate(String hourlyRate) {
            this.hourlyRate = hourlyRate;
            return this;
        }

        public Builder startDate(String startDate){
            this.startDate = startDate;
            return this;
        }

        public Builder dueDate(String dueDate){
            this.dueDate = dueDate;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder repeatEvery(String repeatEvery) {
            this.repeatEvery = repeatEvery;
            return this;
        }

        public Builder relatedTo(String relatedTo) {
            this.relatedTo = relatedTo;
            return this;
        }

        public Builder assignee(String assignee) {
            this.assignee = assignee;
            return this;
        }

        public Builder follower(String follower) {
            this.follower = follower;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public Builder isBillable(boolean isBillable) {
            this.isBillable = isBillable;
            return this;
        }

        public Task build() {
            return new Task(subject, hourlyRate, startDate, dueDate, priority, repeatEvery, relatedTo,
                          assignee, follower, tag, description, isPublic, isBillable);
        }
    }

    public String getSubject() { return subject; }
    public String getHourlyRate() { return hourlyRate; }
    public String getStartDate() { return startDate;}
    public String getDueDate() { return dueDate;}
    public String getPriority() { return priority; }
    public String getRepeatEvery() { return repeatEvery; }
    public String getRelatedTo() { return relatedTo; }
    public String getAssignee() { return assignee; }
    public String getFollower() { return follower; }
    public String getTag() { return tag; }
    public String getDescription() { return description; }
    public boolean isPublic() { return isPublic; }
    public boolean isBillable() { return isBillable; }

    @Override
    public String toString() {
        return "Task{" +
                "subject='" + subject + '\'' +
                ", priority='" + priority + '\'' +
                ", assignee='" + assignee + '\'' +
                ", isPublic=" + isPublic +
                ", isBillable=" + isBillable +
                '}';
    }
}