package core;

public abstract class StateMachine implements Runnable {

    protected Status currentStatus;
    private boolean working = true;

    public StateMachine(){
        this.currentStatus = Status.IDLE;
    }

    @Override
    public void run(){
        while(this.working){
            step();
        }
    }

    protected void step(){
        switch(this.currentStatus){
            case IDLE:
                onIdle();
                break;
            case RUNNING:
                onRunning();
                break;
            case PAUSED:
                onPaused();
                break;
            case RESUMED:
                onResumed();
                break;
            case ENDED:
                onEnded();
                this.working = false;
        }
    }

    protected abstract void onIdle();
    protected abstract void onRunning();
    protected abstract void onPaused();
    protected abstract void onResumed();
    protected abstract void onEnded();

    public Status getCurrentStatus() {
        return this.currentStatus;
    }
}
