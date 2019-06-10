package com.mpescarmona.repository;

import com.mpescarmona.domain.Agent;
import com.mpescarmona.domain.Office;

import java.util.Arrays;
import java.util.List;

public class OfficeRepository {
    public List<Office> getAll() {
        Office office1 = Office.builder()
                .provinceId(1)
                .officeId(1)
                .name("Office 1")
                .agent(Agent.builder().agentId(7).FirstName("Sid").LastName("Barrett").build())
                .build();
        Office office2 = Office.builder()
                .provinceId(2)
                .officeId(2)
                .name("Office 2")
                .agent(Agent.builder().agentId(8).FirstName("David").LastName("Gilmour").build())
                .build();
        return Arrays.asList(office1, office2);
    }
}
