package core;

import java.util.concurrent.Semaphore;

public abstract class StateMachine implements Runnable {

    protected Status currentStatus;
    private Semaphore stateCheckSemaphore;

    public StateMachine(){
        this.currentStatus = Status.IDLE;
        this.stateCheckSemaphore = new Semaphore(1);
    }

    @Override
    public void run(){
        boolean appRunning = true;
        while(appRunning){
            try {
                this.stateCheckSemaphore.acquire();
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
                        appRunning = false;
                }
               this.stateCheckSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void onIdle();
    protected abstract void onRunning();
    protected abstract void onPaused();
    protected abstract void onResumed();
    protected abstract void onEnded();

    public void setStatus(Status status){
        try {
            this.stateCheckSemaphore.acquire();
            this.currentStatus = status;
            this.stateCheckSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
