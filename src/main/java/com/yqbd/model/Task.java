package com.yqbd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Task {
    private Integer taskId;

    private Integer companyId;

    private Integer userId;

    private String classification;

    private String taskTitle;

    private Integer taskStatus;

    private Double pay;

    private Date publishTime;

    private Date deadline;

    private Date startTime;

    private Date completeTime;

    private Integer signUpPeopleNumber;

    private Integer currentPeopleNumber;

    private Integer maxPeopleNumber;

    private String simpleDrawingAddress;

    private Integer groupId;

    private String province;

    private String city;

    private String district;

    private String taskAddress;

    private String taskDescription;

    private String primaryWork;

    private String otherCompany;

    private String primaryContact;

    private String remark;
}