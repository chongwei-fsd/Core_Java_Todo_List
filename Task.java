package mini_proj;

public abstract class Task {
    private  String title;
    private String description;
    private boolean isComplete=false;

    public Task(String title, String description, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public boolean isComplete() {
//        return isComplete;
//    }

    public String isComplete() {
        return isComplete?"complete":"not complete";
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }


}
