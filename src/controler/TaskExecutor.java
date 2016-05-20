package controler;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by noodle on 18.05.16.
 */
public class TaskExecutor {



    private Thread thread = new Thread();

    private ReentrantLock lock = new ReentrantLock();

    private Runnable runnable = null;


    public void next(Runnable run){
        lock.lock();
        runnable = run;
        lock.unlock();
        if(!thread.isAlive()){
            start();
        }
    }


    private void start(){

        if(runnable != null){

            lock.lock();
            final Runnable currentRun = runnable;
            runnable = null;
            lock.unlock();

            Runnable execute = new Runnable(){
                @Override
                public void run() {
                    currentRun.run();
                    start();
                }
            };

            thread = new Thread(execute);
            thread.start();
        }

    }




}
