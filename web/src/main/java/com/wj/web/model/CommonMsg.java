package com.wj.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonMsg<T> {

    private T t;

    private String status;
    private String message;
}
