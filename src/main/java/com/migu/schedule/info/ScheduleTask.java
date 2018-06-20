package com.migu.schedule.info;

/**
 * Created by Administrator on 2018/6/20.
 */
public class ScheduleTask {

    private int taskId;

    private int comsuption;

    public ScheduleTask(int taskId ,int comsuption) {
        this.taskId = taskId;
        this.comsuption =comsuption;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getComsuption() {
        return comsuption;
    }

    public void setComsuption(int comsuption) {
        this.comsuption = comsuption;
    }
}
