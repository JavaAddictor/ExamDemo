package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.ScheduleNode;
import com.migu.schedule.info.ScheduleTask;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.List;

/*
*类名和方法不能修改
 */
public class Schedule {

    private List<ScheduleNode> nodeList;

    private List<ScheduleTask> taskHandup;

    public int init() {
        if(nodeList == null && taskHandup == null) {
            nodeList = new ArrayList<ScheduleNode>();
            taskHandup = new ArrayList<ScheduleTask>();
            return ReturnCodeKeys.E001;
        }
        if(taskHandup != null) {
            taskHandup.clear();
        }
        if(nodeList != null) {
            nodeList.clear();
            return ReturnCodeKeys.E001;
        }
        return ReturnCodeKeys.E000;
    }


    public int registerNode(int nodeId) {
        if(nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        if(isNodeExist(nodeId)) {
            return ReturnCodeKeys.E005;
        }
        ScheduleNode scheduleNode = new ScheduleNode(nodeId);
        nodeList.add(scheduleNode);
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        if(nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        if(!isNodeExist(nodeId)) {
            return ReturnCodeKeys.E007;
        }
        for(ScheduleNode scheduleNode : nodeList) {
            if(scheduleNode.getNodeId() == nodeId) {
                if(scheduleNode.getScheduleTasks() == null || scheduleNode.getScheduleTasks().size() == 0) {
                    return ReturnCodeKeys.E006;
                }
                for(ScheduleTask scheduleTask : scheduleNode.getScheduleTasks()) {
                    taskHandup.add(scheduleTask);
                }
                nodeList.remove(scheduleNode);
            }
        }
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        if(taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        if(taskHandup != null && taskHandup.size()>0) {
            for(ScheduleTask scheduleTask :taskHandup) {
                if(scheduleTask.getTaskId() == taskId) {
                    return ReturnCodeKeys.E010;
                }
            }
        }

        for(ScheduleNode scheduleNode : nodeList) {
            if(scheduleNode.getScheduleTasks() == null || scheduleNode.getScheduleTasks().size() == 0) {
                continue;
            }
            for(ScheduleTask scheduleTask : scheduleNode.getScheduleTasks()) {
                if(scheduleTask.getTaskId() == taskId) {
                    return ReturnCodeKeys.E010;
                }
            }
        }
        ScheduleTask scheduleTask = new ScheduleTask(taskId,consumption);
        taskHandup.add(scheduleTask);//待任务调度
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if(taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        for(ScheduleTask scheduleTask : taskHandup) {
            if(scheduleTask.getTaskId() == taskId) {
                return ReturnCodeKeys.E011;
            }
        }
        for(ScheduleNode scheduleNode : nodeList) {
            if(scheduleNode.getScheduleTasks() == null || scheduleNode.getScheduleTasks().size() == 0) {
                continue;
            }
            for(ScheduleTask scheduleTask : scheduleNode.getScheduleTasks()) {
                if(scheduleTask.getTaskId() == taskId) {
                    scheduleNode.getScheduleTasks().remove(scheduleTask);
                    return ReturnCodeKeys.E011;
                }
            }
        }
        return ReturnCodeKeys.E012;
    }


    public int scheduleTask(int threshold) {

        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        tasks.clear();
        for(ScheduleNode scheduleNode : nodeList) {
            if(scheduleNode.getScheduleTasks() == null || scheduleNode.getScheduleTasks().size() == 0) {
                continue;
            }
            for(ScheduleTask scheduleTask : scheduleNode.getScheduleTasks()) {
                TaskInfo taskInfo = new TaskInfo();
                taskInfo.setNodeId(scheduleNode.getNodeId());
                taskInfo.setTaskId(scheduleTask.getTaskId());
                tasks.add(taskInfo);
            }
        }
        if(taskHandup == null) {
            return ReturnCodeKeys.E015;
        }
        for(ScheduleTask scheduleTask : taskHandup) {
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setNodeId(-1);
            taskInfo.setNodeId(-1);
            taskInfo.setTaskId(scheduleTask.getTaskId());
            tasks.add(taskInfo);
        }
        return ReturnCodeKeys.E015;
    }

    private boolean isNodeExist(int nodeId) {
        for(ScheduleNode scheduleNode : nodeList) {
            if(scheduleNode.getNodeId() == nodeId) {
                return true;
            }
        }
        return false;
    }


}
