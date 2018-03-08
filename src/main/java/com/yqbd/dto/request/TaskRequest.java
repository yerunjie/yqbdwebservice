package com.yqbd.dto.request;

import com.yqbd.beans.TaskBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaskRequest extends YQBDBaseRequest {
    TaskBean taskBean;
}
