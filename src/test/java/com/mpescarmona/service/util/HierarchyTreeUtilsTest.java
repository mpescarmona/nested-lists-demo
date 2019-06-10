package com.mpescarmona.service.util;

import com.mpescarmona.domain.Agent;
import com.mpescarmona.domain.Nation;
import com.mpescarmona.domain.Office;
import com.mpescarmona.domain.Province;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HierarchyTreeUtilsTest {
    /* Nation tests */
    @Test
    public void testNullNation() {
        assertNull(HierarchyTreeUtils.getNationOfAgentId(1, null));
    }

    @Test
    public void testNationWithNullAgentId() {
        List<Nation> nations = Arrays.asList(Nation.builder().name("Test Nation").build());
        assertNull(HierarchyTreeUtils.getNationOfAgentId(null, nations));
    }

    @Test
    public void testNationWithNullAgent() {
        List<Nation> nations = Arrays.asList(Nation.builder().name("Test Nation").build());
        assertNull(HierarchyTreeUtils.getNationOfAgentId(1, nations));
    }

    @Test
    public void testNationWithAgent() {
        Agent agent1 = Agent.builder()
                .agentId(1)
                .FirstName("Name 1")
                .LastName("LastName 1")
                .build();
        Agent agent2 = Agent.builder()
                .agentId(2)
                .FirstName("Name 2")
                .LastName("LastName 2")
                .build();
        Nation nation1 = Nation.builder()
                .name("Test Nation 1")
                .agent(agent1)
                .build();

        Nation nation2 = Nation.builder()
                .name("Test Nation 2")
                .agent(agent2)
                .build();
        List<Nation> nations = Arrays.asList(nation1, nation2);
        Nation result = HierarchyTreeUtils.getNationOfAgentId(2, nations);
        assertEquals(nation2, result);
    }

    @Test
    public void testNationWithNotMatchingAgent() {
        Agent agent1 = Agent.builder()
                .agentId(1)
                .FirstName("Name 1")
                .LastName("LastName 1")
                .build();
        Agent agent2 = Agent.builder()
                .agentId(2)
                .FirstName("Name 2")
                .LastName("LastName 2")
                .build();
        Nation nation1 = Nation.builder()
                .name("Test Nation 1")
                .agent(agent1)
                .build();

        Nation nation2 = Nation.builder()
                .name("Test Nation 2")
                .agent(agent2)
                .build();
        List<Nation> nations = Arrays.asList(nation1, nation2);
        Nation result = HierarchyTreeUtils.getNationOfAgentId(5, nations);
        assertNull(result);
    }

    /* Province tests */
    @Test
    public void testNullProvince() {
        assertNull(HierarchyTreeUtils.getProvinceOfAgentId(1, null));
    }

    @Test
    public void testProvinceWithNullAgentId() {
        List<Province> Provinces = Arrays.asList(Province.builder().name("Test Province").build());
        assertNull(HierarchyTreeUtils.getProvinceOfAgentId(null, Provinces));
    }

    @Test
    public void testProvinceWithNullAgent() {
        List<Province> Provinces = Arrays.asList(Province.builder().name("Test Province").build());
        assertNull(HierarchyTreeUtils.getProvinceOfAgentId(1, Provinces));
    }

    @Test
    public void testProvinceWithAgent() {
        Agent agent1 = Agent.builder()
                .agentId(1)
                .FirstName("Name 1")
                .LastName("LastName 1")
                .build();
        Agent agent2 = Agent.builder()
                .agentId(2)
                .FirstName("Name 2")
                .LastName("LastName 2")
                .build();
        Province Province1 = Province.builder()
                .name("Test Province 1")
                .agent(agent1)
                .build();

        Province Province2 = Province.builder()
                .name("Test Province 2")
                .agent(agent2)
                .build();
        List<Province> Provinces = Arrays.asList(Province1, Province2);
        Province result = HierarchyTreeUtils.getProvinceOfAgentId(2, Provinces);
        assertEquals(Province2, result);
    }

    @Test
    public void testProvinceWithNotMatchingAgent() {
        Agent agent1 = Agent.builder()
                .agentId(1)
                .FirstName("Name 1")
                .LastName("LastName 1")
                .build();
        Agent agent2 = Agent.builder()
                .agentId(2)
                .FirstName("Name 2")
                .LastName("LastName 2")
                .build();
        Province Province1 = Province.builder()
                .name("Test Province 1")
                .agent(agent1)
                .build();

        Province Province2 = Province.builder()
                .name("Test Province 2")
                .agent(agent2)
                .build();
        List<Province> Provinces = Arrays.asList(Province1, Province2);
        Province result = HierarchyTreeUtils.getProvinceOfAgentId(5, Provinces);
        assertNull(result);
    }

    /* Office tests */
    @Test
    public void testNullOffice() {
        assertNull(HierarchyTreeUtils.getOfficeOfAgentId(1, null));
    }

    @Test
    public void testOfficeWithNullAgentId() {
        List<Office> Offices = Arrays.asList(Office.builder().name("Test Office").build());
        assertNull(HierarchyTreeUtils.getOfficeOfAgentId(null, Offices));
    }

    @Test
    public void testOfficeWithNullAgent() {
        List<Office> Offices = Arrays.asList(Office.builder().name("Test Office").build());
        assertNull(HierarchyTreeUtils.getOfficeOfAgentId(1, Offices));
    }

    @Test
    public void testOfficeWithAgent() {
        Agent agent1 = Agent.builder()
                .agentId(1)
                .FirstName("Name 1")
                .LastName("LastName 1")
                .build();
        Agent agent2 = Agent.builder()
                .agentId(2)
                .FirstName("Name 2")
                .LastName("LastName 2")
                .build();
        Office Office1 = Office.builder()
                .name("Test Office 1")
                .agent(agent1)
                .build();

        Office Office2 = Office.builder()
                .name("Test Office 2")
                .agent(agent2)
                .build();
        List<Office> Offices = Arrays.asList(Office1, Office2);
        Office result = HierarchyTreeUtils.getOfficeOfAgentId(2, Offices);
        assertEquals(Office2, result);
    }

    @Test
    public void testOfficeWithNotMatchingAgent() {
        Agent agent1 = Agent.builder()
                .agentId(1)
                .FirstName("Name 1")
                .LastName("LastName 1")
                .build();
        Agent agent2 = Agent.builder()
                .agentId(2)
                .FirstName("Name 2")
                .LastName("LastName 2")
                .build();
        Office Office1 = Office.builder()
                .name("Test Office 1")
                .agent(agent1)
                .build();

        Office Office2 = Office.builder()
                .name("Test Office 2")
                .agent(agent2)
                .build();
        List<Office> Offices = Arrays.asList(Office1, Office2);
        Office result = HierarchyTreeUtils.getOfficeOfAgentId(5, Offices);
        assertNull(result);
    }
}