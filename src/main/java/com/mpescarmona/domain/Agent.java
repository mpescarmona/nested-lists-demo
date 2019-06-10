package com.mpescarmona.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Agent {
    Integer agentId;
    String FirstName;
    String LastName;
}
