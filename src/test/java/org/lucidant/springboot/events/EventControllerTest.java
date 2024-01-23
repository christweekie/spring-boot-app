package org.lucidant.springboot.events;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lucidant.springboot.entity.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizerRepository organizerRepository;

    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void testGetOrganizers() throws Exception {
        Organizer org1 = new Organizer(1, "Organizer 1", "Description 1");
        Organizer org2 = new Organizer(2, "Organizer 2", "Description 2");

        List<Organizer> organizers = Arrays.asList(org1, org2);

        given(organizerRepository.findAll()).willReturn(organizers);

        mockMvc.perform(MockMvcRequestBuilders.get("/organizers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(org1.getId())))
                .andExpect(jsonPath("$[0].name", is(org1.getName())))
                .andExpect(jsonPath("$[0].description", is(org1.getDescription())))
                .andExpect(jsonPath("$[1].id", is(org2.getId())))
                .andExpect(jsonPath("$[1].name", is(org2.getName())))
                .andExpect(jsonPath("$[1].description", is(org2.getDescription())));

        Assertions.assertThat(organizerRepository.findAll()).isEqualTo(organizers);
    }
}
