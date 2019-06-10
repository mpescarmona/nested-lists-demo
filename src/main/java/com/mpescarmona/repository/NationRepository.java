package com.mpescarmona.repository;

import com.mpescarmona.domain.Agent;
import com.mpescarmona.domain.Nation;

import java.util.Arrays;
import java.util.List;

public class NationRepository {
    public List<Nation> getAll() {
        Nation nation1 = Nation.builder()
                .nationId(1)
                .name("Nation 1")
                .agent(Agent.builder().agentId(1).FirstName("John").LastName("Lennon").build())
                .build();
        return Arrays.asList(nation1);
    }
}
