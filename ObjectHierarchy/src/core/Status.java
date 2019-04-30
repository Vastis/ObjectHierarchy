package core;

public enum Status {
    IDLE(0),
    RUNNING(1),
    PAUSED(2),
    RESUMED(3),
    ENDED(4);

    private int state;

    Status(int state){
        this.state = state;
    }
}
