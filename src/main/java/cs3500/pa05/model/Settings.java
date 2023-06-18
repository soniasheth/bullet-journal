package cs3500.pa05.model;

public class Settings {

    String name;
    String email;
    int eventMax;
    int taskMax;

    public Settings() {
        this.name = "John Doe";
        this.email = "JohnDoe@fakeEmail.com";
        this.eventMax = 0;
        this.taskMax = 0;
    }

    public Settings(String name, String email, int eventMax, int taskMax) {
        this.name = name;
        this.email = email;
        this.eventMax = eventMax;
        this.taskMax = taskMax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEventMax(int eventMax) {
        this.eventMax = eventMax;
    }

    public void setTaskMax(int taskMax) {
        this.taskMax = taskMax;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getEventMax() {
        return eventMax;
    }

    public int getTaskMax() {
        return taskMax;
    }

}
