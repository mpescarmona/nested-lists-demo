package com.mpescarmona.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Office {
    Integer provinceId;
    Integer officeId;
    String name;
    Agent agent;
}
