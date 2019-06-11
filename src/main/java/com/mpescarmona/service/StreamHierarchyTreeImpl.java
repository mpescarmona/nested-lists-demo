package com.mpescarmona.service;

import com.mpescarmona.domain.Agent;
import com.mpescarmona.domain.Nation;
import com.mpescarmona.domain.Office;
import com.mpescarmona.domain.Province;
import com.mpescarmona.repository.NationRepository;
import com.mpescarmona.repository.OfficeRepository;
import com.mpescarmona.repository.ProvinceRepository;
import com.mpescarmona.service.util.BaseHierarchyTree;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mpescarmona.service.util.HierarchyTreeUtils.getNationOfAgentId;
import static com.mpescarmona.service.util.HierarchyTreeUtils.getOfficeOfAgentId;
import static com.mpescarmona.service.util.HierarchyTreeUtils.getProvinceOfAgentId;
import static com.mpescarmona.service.util.HierarchyTreeUtils.populateNation;
import static com.mpescarmona.service.util.HierarchyTreeUtils.populateOffice;
import static com.mpescarmona.service.util.HierarchyTreeUtils.populateProvince;

@Service
@AllArgsConstructor
public class StreamHierarchyTreeImpl extends BaseHierarchyTree implements HierarchyTree {

    @Autowired
    NationRepository nationRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    OfficeRepository officeRepository;

    public List<String> getHierarchyTree() {
        List<Nation> nations = nationRepository.getAll();
        List<Province> provinces = provinceRepository.getAll();
        List<Office> offices = officeRepository.getAll();

        Integer loggedAgentId = Optional.ofNullable(getLoggedAgent())
                .map(Agent::getAgentId)
                .orElse(null);

        Nation filteredNation = getNationOfAgentId(loggedAgentId, nations);
        Province filteredProvince = getProvinceOfAgentId(loggedAgentId, provinces);
        Office filteredOffice = getOfficeOfAgentId(loggedAgentId, offices);

        if (filteredOffice != null) {
            provinces = provinces.stream()
                    .filter(province -> province.getProvinceId().equals(filteredOffice.getProvinceId()))
                    .collect(Collectors.toList());

            Integer nationOfProvince = provinces.stream().findFirst().map(Province::getNationId).get();

            nations = nations.stream()
                    .filter(nation -> nation.getNationId().equals(nationOfProvince))
                    .collect(Collectors.toList());

            offices = Arrays.asList(filteredOffice);
        }

        if (filteredProvince != null) {
            nations = nations.stream()
                    .filter(nation -> nation.getNationId().equals(filteredProvince.getNationId()))
                    .collect(Collectors.toList());
            provinces = Arrays.asList(filteredProvince);
        }

        if (filteredNation != null) {
            nations = Arrays.asList(filteredNation);
        }

        List<String> result = new ArrayList<>();

        List<Province> filteredProvinces = provinces;
        List<Office> filteredOffices = offices;

        nations.forEach(nation -> {
            populateNation(result, nation);
            filteredProvinces.stream()
                    .filter(province -> nation.getNationId().equals(province.getNationId()))
                    .forEach(province -> {
                        populateProvince(result, nation, province);
                        filteredOffices.stream()
                                .filter(office -> province.getProvinceId().equals(office.getProvinceId()))
                                .forEach(office -> populateOffice(result, nation, province, office));
                    });
        });

        return result;
    }
}
