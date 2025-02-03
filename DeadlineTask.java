package mini_proj;

import java.time.LocalDateTime;

public class DeadlineTask extends Task{
    private LocalDateTime deadline;

    public DeadlineTask(String title, String description, boolean isComplete, LocalDateTime deadline) {
        super(title, description, isComplete);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
