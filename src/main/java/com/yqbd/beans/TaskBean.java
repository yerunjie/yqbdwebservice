package com.yqbd.beans;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11022 on 2017/7/17.
 */
@Data
@NoArgsConstructor
public class TaskBean implements Serializable {
    private Integer taskId;

    private Integer companyId;

    private Integer userId;

    private String classification;

    private String taskTitle;

    private Integer taskStatus;

    private Double pay;

    private Long publishTime;

    private Long deadline;

    private Long startTime;

    private Long completeTime;

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

    private Integer isGroup;
    private List<TypeBean> typeBeans;
    private String name;

}
