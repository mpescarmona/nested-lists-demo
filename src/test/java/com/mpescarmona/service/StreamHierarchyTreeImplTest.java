package com.mpescarmona.service;

import com.mpescarmona.domain.Agent;
import com.mpescarmona.repository.NationRepository;
import com.mpescarmona.repository.OfficeRepository;
import com.mpescarmona.repository.ProvinceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.mpescarmona.service.helper.HierarchyTreeTestHelper.prepareNations;
import static com.mpescarmona.service.helper.HierarchyTreeTestHelper.prepareOffices;
import static com.mpescarmona.service.helper.HierarchyTreeTestHelper.prepareProvinces;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
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
        StreamHierarchyTreeImpl spy = Mockito.spy(streamHierarchyTree);

        Mockito.when(nationRepository.getAll()).thenReturn(prepareNations());
        Mockito.when(provinceRepository.getAll()).thenReturn(prepareProvinces());
        Mockito.when(officeRepository.getAll()).thenReturn(prepareOffices());

        Mockito.doReturn(null).when(spy).getLoggedAgent();

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

        List<String> agents = spy.getHierarchyTree();

        assertThat(agents.size(), is(10));
        assertThat(agents, is(expectedAgents));
    }

    @Test
    public void getHierarchyTreeFilteredByAgentIdAtNationLevel() {
        StreamHierarchyTreeImpl spy = Mockito.spy(streamHierarchyTree);

        Mockito.when(nationRepository.getAll()).thenReturn(prepareNations());
        Mockito.when(provinceRepository.getAll()).thenReturn(prepareProvinces());
        Mockito.when(officeRepository.getAll()).thenReturn(prepareOffices());

        Agent mockedAgent = Agent.builder()
                .agentId(1)
                .FirstName("John")
                .LastName("Doe")
                .build();
        Mockito.doReturn(mockedAgent).when(spy).getLoggedAgent();

        List<String> expectedAgents = Arrays.asList(
                "Nation 1: (1) John Lennon",
                "Nation 1 -> Province 1: (3) Billy Joel",
                "Nation 1 -> Province 1 -> Office 1: (7) Sid Barrett",
                "Nation 1 -> Province 2: (4) Phill Collins",
                "Nation 1 -> Province 2 -> Office 2: (8) David Gilmour"
        );

        List<String> agents = spy.getHierarchyTree();

        assertThat(agents.size(), is(5));
        assertThat(agents, is(expectedAgents));
    }

    @Test
    public void getHierarchyTreeFilteredByAgentIdAtProvinceLevel() {
        StreamHierarchyTreeImpl spy = Mockito.spy(streamHierarchyTree);

        Mockito.when(nationRepository.getAll()).thenReturn(prepareNations());
        Mockito.when(provinceRepository.getAll()).thenReturn(prepareProvinces());
        Mockito.when(officeRepository.getAll()).thenReturn(prepareOffices());

        Agent mockedAgent = Agent.builder()
                .agentId(3)
                .FirstName("Peter")
                .LastName("Smith")
                .build();
        Mockito.doReturn(mockedAgent).when(spy).getLoggedAgent();

        List<String> expectedAgents = Arrays.asList(
                "Nation 1: (1) John Lennon",
                "Nation 1 -> Province 1: (3) Billy Joel",
                "Nation 1 -> Province 1 -> Office 1: (7) Sid Barrett"
        );

        List<String> agents = spy.getHierarchyTree();

        assertThat(agents.size(), is(3));
        assertThat(agents, is(expectedAgents));
    }

    @Test
    public void getHierarchyTreeFilteredByAgentIdAtOfficeLevel() {
        StreamHierarchyTreeImpl spy = Mockito.spy(streamHierarchyTree);

        Mockito.when(nationRepository.getAll()).thenReturn(prepareNations());
        Mockito.when(provinceRepository.getAll()).thenReturn(prepareProvinces());
        Mockito.when(officeRepository.getAll()).thenReturn(prepareOffices());

        Agent mockedAgent = Agent.builder()
                .agentId(8)
                .FirstName("David")
                .LastName("Adams")
                .build();
        Mockito.doReturn(mockedAgent).when(spy).getLoggedAgent();

        List<String> expectedAgents = Arrays.asList(
                "Nation 1: (1) John Lennon",
                "Nation 1 -> Province 2: (4) Phill Collins",
                "Nation 1 -> Province 2 -> Office 2: (8) David Gilmour"
        );

        List<String> agents = spy.getHierarchyTree();

        assertThat(agents.size(), is(3));
        assertThat(agents, is(expectedAgents));
    }
}