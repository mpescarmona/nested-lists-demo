package com.mpescarmona.repository;

import com.mpescarmona.domain.Agent;
import com.mpescarmona.domain.Province;

import java.util.Arrays;
import java.util.List;

public class ProvinceRepository {
    public List<Province> getAll() {
        Province province1 = Province.builder()
                .nationId(1)
                .provinceId(1)
                .name("Province 1")
                .agent(Agent.builder().agentId(3).FirstName("Billy").LastName("Joel").build())
                .build();
        Province province2 = Province.builder()
                .nationId(1)
                .provinceId(2)
                .name("Province 2")
                .agent(Agent.builder().agentId(4).FirstName("Phill").LastName("Collins").build())
                .build();
        return Arrays.asList(province1, province2);
    }
}
