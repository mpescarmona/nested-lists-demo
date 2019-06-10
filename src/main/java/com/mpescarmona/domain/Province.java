package com.mpescarmona.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Province {
    Integer nationId;
    Integer provinceId;
    String name;
    Agent agent;
}
