package com.yqbd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupInfo {
    private Integer groupId;

    private Integer companyId;

    private String groupTitle;

    private Integer currentPeopleNumber;

    private Integer maxPeopleNumber;

    private String groupDescription;

    public GroupInfo(Integer companyId, String groupTitle, Integer currentPeopleNumber, Integer maxPeopleNumber, String groupDescription) {
        this.companyId = companyId;
        this.groupTitle = groupTitle;
        this.currentPeopleNumber = currentPeopleNumber;
        this.maxPeopleNumber = maxPeopleNumber;
        this.groupDescription = groupDescription;
    }
}