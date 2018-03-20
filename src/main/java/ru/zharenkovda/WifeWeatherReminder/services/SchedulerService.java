package ru.zharenkovda.WifeWeatherReminder.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService {

    private ScheduledThreadPoolExecutor scheduledExecutorService;

    private ScheduledThreadPoolExecutor getScheduledExecutorService(){
        if (scheduledExecutorService == null || scheduledExecutorService.isTerminated()){
            return new ScheduledThreadPoolExecutor(10);
        } else
            return scheduledExecutorService;
    }

    public void executeRunnableTask(Runnable runnable, Long offset, TimeUnit unit){
        getScheduledExecutorService().scheduleAtFixedRate(runnable,0,offset,unit);
    }

    public void clearQueue(){
        getScheduledExecutorService().getQueue().clear();
    }

    public void stopExecutorService(){
        clearQueue();
        getScheduledExecutorService().shutdown();
    }
}
