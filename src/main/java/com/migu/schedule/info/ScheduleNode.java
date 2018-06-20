package com.migu.schedule.info;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */
public class ScheduleNode {

    private int nodeId;

    private int totalComsuption;

    private List<ScheduleTask> scheduleTasks;

    public ScheduleNode(int nodeId) {
        this.nodeId = nodeId;
    }
    public List<ScheduleTask> getScheduleTasks() {
        return scheduleTasks;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getTotalComsuption() {
        return totalComsuption;
    }

    public void setTotalComsuption(int totalComsuption) {
        this.totalComsuption = totalComsuption;
    }

    public void setScheduleTasks(List<ScheduleTask> scheduleTasks) {
        this.scheduleTasks = scheduleTasks;
    }
}
