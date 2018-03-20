package ru.zharenkovda.WifeWeatherReminder.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService {

    private ScheduledThreadPoolExecutor scheduledExecutorService =  new ScheduledThreadPoolExecutor(10);

    public void executeRunnableTask(Runnable runnable, Long offset, TimeUnit unit){
        scheduledExecutorService.scheduleAtFixedRate(runnable,0,offset,unit);
    }

    public void clearQueue(){
        scheduledExecutorService.getQueue().clear();
    }

    public void stopExecutorService(){
        clearQueue();
        scheduledExecutorService.shutdown();
    }

    public void startExecitorServiceAfterStop(){
        if ( scheduledExecutorService.isTerminated()) {
            scheduledExecutorService = new ScheduledThreadPoolExecutor(10);
        }
    }
}