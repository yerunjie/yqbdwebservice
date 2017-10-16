package com.yqbd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskTypeKey {
    private Integer taskId;

    private Integer typeId;

    public TaskTypeKey(Integer taskId, Integer typeId) {
        this.taskId = taskId;
        this.typeId = typeId;
    }
}