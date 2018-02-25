package com.yqbd.dto.request;

import com.yqbd.model.UserCollectKey;
import lombok.Data;

import java.util.List;

@Data
public class UncollectTaskRequest extends YQBDBaseRequest {
    private List<Integer> userCollectList;
}
