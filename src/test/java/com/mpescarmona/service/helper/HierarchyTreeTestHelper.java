package com.mpescarmona.service.helper;

import com.mpescarmona.domain.Agent;
import com.mpescarmona.domain.Nation;
import com.mpescarmona.domain.Office;
import com.mpescarmona.domain.Province;

import java.util.Arrays;
import java.util.List;

public class HierarchyTreeTestHelper {
    public static List<Nation> prepareNations() {
        Nation nation1 = Nation.builder()
                .nationId(1)
                .name("Nation 1")
                .agent(Agent.builder().agentId(1).FirstName("John").LastName("Lennon").build())
                .build();
        Nation nation2 = Nation.builder()
                .nationId(2)
                .name("Nation 2")
                .agent(Agent.builder().agentId(2).FirstName("Paul").LastName("Mc Cartney").build())
                .build();
        return Arrays.asList(nation1, nation2);
    }

    public static List<Province> prepareProvinces() {
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
        Province province3 = Province.builder()
                .nationId(2)
                .provinceId(3)
                .name("Province 3")
                .agent(Agent.builder().agentId(5).FirstName("Peter").LastName("Cetera").build())
                .build();
        Province province4 = Province.builder()
                .nationId(2)
                .provinceId(4)
                .name("Province 4")
                .agent(Agent.builder().agentId(6).FirstName("Bon").LastName("Scott").build())
                .build();
        return Arrays.asList(province1, province2, province3, province4);
    }

    public static List<Office> prepareOffices() {
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
        Office office3 = Office.builder()
                .provinceId(3)
                .officeId(3)
                .name("Office 3")
                .agent(Agent.builder().agentId(9).FirstName("Ron").LastName("Wood").build())
                .build();
        Office office4 = Office.builder()
                .provinceId(4)
                .officeId(4)
                .name("Office 4")
                .agent(Agent.builder().agentId(10).FirstName("Elvis").LastName("Presley").build())
                .build();
        return Arrays.asList(office1, office2, office3, office4);
    }
}
