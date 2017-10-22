package com.yqbd.beans;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 11022 on 2017/7/17.
 */
@Data
@NoArgsConstructor
public class TaskBean implements Serializable {
    private Integer taskId;
    private Integer userId;
    private Integer companyId;
    private String taskTitle;
    private String taskDescription;
    private String taskAddress;
    private Double pay;
    private Integer taskStatus;
    private Long publishTime;
    private Long completeTime;
    private Long startTime;
    private Integer maxPeopleNumber;
    private Long deadline;

    private String simpleDrawingAddress;
    private Integer isGroup;
    private List<TypeBean> typeBeans;
    private String name;
    private String primaryWork;
    private String otherCompany;
    private String primaryContact;
    private String remark;
}
