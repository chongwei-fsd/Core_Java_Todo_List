package mini_proj;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Event extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String title, String description, boolean isComplete, LocalDateTime startTime, LocalDateTime endTime) {
        super(title, description, isComplete);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }






}
