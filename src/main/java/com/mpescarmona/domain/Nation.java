package com.mpescarmona.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Nation {
    Integer nationId;
    String name;
    Agent agent;
}
