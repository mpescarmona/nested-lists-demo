package com.mpescarmona.service.util;

import com.mpescarmona.domain.Agent;

public class BaseHierarchyTree {
    public Agent getLoggedAgent() {
        return Agent.builder().agentId(1).FirstName("John").LastName("Doe").build();
    }
}
