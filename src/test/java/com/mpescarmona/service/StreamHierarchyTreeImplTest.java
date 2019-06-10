package com.mpescarmona.service;

import com.mpescarmona.repository.NationRepository;
import com.mpescarmona.repository.OfficeRepository;
import com.mpescarmona.repository.ProvinceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.mpescarmona.service.helper.HierarchyTreeTestHelper.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StreamHierarchyTreeImplTest {

    @InjectMocks
    private StreamHierarchyTreeImpl streamHierarchyTree;

    @Mock
    private NationRepository nationRepository;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private OfficeRepository officeRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getHierarchyTreeWithNullAgentId() {
        Mockito.when(nationRepository.getAll()).thenReturn(prepareNations());
        Mockito.when(provinceRepository.getAll()).thenReturn(prepareProvinces());
        Mockito.when(officeRepository.getAll()).thenReturn(prepareOffices());

        List<String> expectedAgents = Arrays.asList(
                "Nation 1: (1) John Lennon",
                "Nation 1 -> Province 1: (3) Billy Joel",
                "Nation 1 -> Province 1 -> Office 1: (7) Sid Barrett",
                "Nation 1 -> Province 2: (4) Phill Collins",
                "Nation 1 -> Province 2 -> Office 2: (8) David Gilmour",
                "Nation 2: (2) Paul Mc Cartney",
                "Nation 2 -> Province 3: (5) Peter Cetera",
                "Nation 2 -> Province 3 -> Office 3: (9) Ron Wood",
                "Nation 2 -> Province 4: (6) Bon Scott",
                "Nation 2 -> Province 4 -> Office 4: (10) Elvis Presley"
        );

        List<String> agents = streamHierarchyTree.getHierarchyTree(null);

        assertThat(agents.size(), is(10));
        assertThat(agents, is(expectedAgents));
    }

    @Test
    public void getHierarchyTreeFilteredByAgentIdAtNationLevel() {
        Mockito.when(nationRepository.getAll()).thenReturn(prepareNations());
        Mockito.when(provinceRepository.getAll()).thenReturn(prepareProvinces());
        Mockito.when(officeRepository.getAll()).thenReturn(prepareOffices());

        List<String> expectedAgents = Arrays.asList(
                "Nation 1: (1) John Lennon",
                "Nation 1 -> Province 1: (3) Billy Joel",
                "Nation 1 -> Province 1 -> Office 1: (7) Sid Barrett",
                "Nation 1 -> Province 2: (4) Phill Collins",
                "Nation 1 -> Province 2 -> Office 2: (8) David Gilmour"
        );

        List<String> agents = streamHierarchyTree.getHierarchyTree(1);

        assertThat(agents.size(), is(5));
        assertThat(agents, is(expectedAgents));
    }

    @Test
    public void getHierarchyTreeFilteredByAgentIdAtProvinceLevel() {
        Mockito.when(nationRepository.getAll()).thenReturn(prepareNations());
        Mockito.when(provinceRepository.getAll()).thenReturn(prepareProvinces());
        Mockito.when(officeRepository.getAll()).thenReturn(prepareOffices());

        List<String> expectedAgents = Arrays.asList(
                "Nation 1: (1) John Lennon",
                "Nation 1 -> Province 1: (3) Billy Joel",
                "Nation 1 -> Province 1 -> Office 1: (7) Sid Barrett"
        );

        List<String> agents = streamHierarchyTree.getHierarchyTree(3);

        assertThat(agents.size(), is(3));
        assertThat(agents, is(expectedAgents));
    }

    @Test
    public void getHierarchyTreeFilteredByAgentIdAtOfficeLevel() {
        Mockito.when(nationRepository.getAll()).thenReturn(prepareNations());
        Mockito.when(provinceRepository.getAll()).thenReturn(prepareProvinces());
        Mockito.when(officeRepository.getAll()).thenReturn(prepareOffices());

        List<String> expectedAgents = Arrays.asList(
                "Nation 1: (1) John Lennon",
                "Nation 1 -> Province 2: (4) Phill Collins",
                "Nation 1 -> Province 2 -> Office 2: (8) David Gilmour"
        );

        List<String> agents = streamHierarchyTree.getHierarchyTree(8);

        assertThat(agents.size(), is(3));
        assertThat(agents, is(expectedAgents));
    }
}