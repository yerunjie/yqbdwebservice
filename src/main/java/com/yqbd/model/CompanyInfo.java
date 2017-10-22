package com.yqbd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyInfo {
    private Integer companyId;

    private String companyName;

    private String companyAccount;

    private String password;

    private String classification;

    private String summary;

    private String headPortraitAddress;
}