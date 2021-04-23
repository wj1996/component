package com.wj;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistryInfo {

    private String ip;
    private int port;
}
