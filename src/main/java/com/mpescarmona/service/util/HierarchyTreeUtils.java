package com.mpescarmona.service.util;

import com.mpescarmona.domain.Nation;
import com.mpescarmona.domain.Office;
import com.mpescarmona.domain.Province;

import java.util.List;
import java.util.Optional;

public class HierarchyTreeUtils {
    public static void populateNation(List<String> result, Nation nation) {
        result.add(nation.getName()
                + ": (" + nation.getAgent().getAgentId() + ") "
                + nation.getAgent().getFirstName() + " " + nation.getAgent().getLastName());
    }

    public static void populateProvince(List<String> result, Nation nation, Province province) {
        result.add(nation.getName() + " -> " + province.getName()
                + ": (" + province.getAgent().getAgentId() + ") "
                + province.getAgent().getFirstName() + " " + province.getAgent().getLastName());
    }

    public static void populateOffice(List<String> result, Nation nation, Province province, Office office) {
        result.add(nation.getName() + " -> " + province.getName() + " -> " + office.getName()
                + ": (" + office.getAgent().getAgentId() + ") "
                + office.getAgent().getFirstName() + " " + office.getAgent().getLastName());
    }

    public static Nation getNationOfAgentId(Integer agentId, List<Nation> nationList) {
        if (agentId == null || !Optional.ofNullable(nationList).isPresent()) {
            return null;
        }
        return nationList.stream()
                .filter(nation -> nation.getAgent() != null)
                .filter(nation -> nation.getAgent().getAgentId().equals(agentId))
                .findFirst()
                .orElse(null);
    }

    public static Province getProvinceOfAgentId(Integer agentId, List<Province> provinceList) {
        if (agentId == null || !Optional.ofNullable(provinceList).isPresent()) {
            return null;
        }
        return provinceList.stream()
                .filter(province -> province.getAgent() != null)
                .filter(province -> province.getAgent().getAgentId().equals(agentId))
                .findFirst()
                .orElse(null);
    }

    public static Office getOfficeOfAgentId(Integer agentId, List<Office> officeList) {
        if (agentId == null || !Optional.ofNullable(officeList).isPresent()) {
            return null;
        }
        return officeList.stream()
                .filter(office -> office.getAgent() != null)
                .filter(office -> office.getAgent().getAgentId().equals(agentId))
                .findFirst()
                .orElse(null);
    }
}
