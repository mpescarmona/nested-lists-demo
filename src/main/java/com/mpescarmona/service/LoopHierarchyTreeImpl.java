package com.mpescarmona.service;

import com.mpescarmona.domain.Nation;
import com.mpescarmona.domain.Office;
import com.mpescarmona.domain.Province;
import com.mpescarmona.repository.NationRepository;
import com.mpescarmona.repository.OfficeRepository;
import com.mpescarmona.repository.ProvinceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mpescarmona.service.util.HierarchyTreeUtils.getNationOfAgentId;
import static com.mpescarmona.service.util.HierarchyTreeUtils.getOfficeOfAgentId;
import static com.mpescarmona.service.util.HierarchyTreeUtils.getProvinceOfAgentId;
import static com.mpescarmona.service.util.HierarchyTreeUtils.populateNation;
import static com.mpescarmona.service.util.HierarchyTreeUtils.populateOffice;
import static com.mpescarmona.service.util.HierarchyTreeUtils.populateProvince;

@Service
@AllArgsConstructor
public class LoopHierarchyTreeImpl implements HierarchyTree {

    @Autowired
    NationRepository nationRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    OfficeRepository officeRepository;

    public List<String> getHierarchyTree(Integer agentId) {
        List<Nation> nations = nationRepository.getAll();
        List<Province> provinces = provinceRepository.getAll();
        List<Office> offices = officeRepository.getAll();

        Nation filteredNation = getNationOfAgentId(agentId, nations);
        Province filteredProvince = getProvinceOfAgentId(agentId, provinces);
        Office filteredOffice = getOfficeOfAgentId(agentId, offices);

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

        for (Nation nation : nations) {
            populateNation(result, nation);
            for (Province province : provinces) {
                if (nation.getNationId().equals(province.getNationId())) {
                    populateProvince(result, nation, province);
                    for (Office office : offices) {
                        if (province.getProvinceId().equals(office.getProvinceId())) {
                            populateOffice(result, nation, province, office);
                        }
                    }
                }
            }
        }

        return result;
    }
}
