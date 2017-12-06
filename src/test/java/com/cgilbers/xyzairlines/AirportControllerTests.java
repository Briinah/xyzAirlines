package com.cgilbers.xyzairlines;

import com.cgilbers.xyzairlines.controllers.AirportController;
import com.cgilbers.xyzairlines.models.Airport;
import com.cgilbers.xyzairlines.models.Plane;
import com.cgilbers.xyzairlines.repositories.AirportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AirportControllerTests {

    @InjectMocks
    private AirportController airportController;

    @Mock
    private AirportRepository airportRepository;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
    }

    @Test
    public void addAirportTest() throws Exception{

        List<Plane> planes = new ArrayList<>();
        Airport airport = new Airport("Utrecht", planes);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(airport);

        when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(airport);

        this.mockMvc.perform(post("/api/airport/add/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(airport.getId()))
                .andExpect(jsonPath("$.location").value(airport.getLocation()))
                .andExpect(jsonPath("$.planes").value(airport.getPlanes()))
                .andExpect(status().isOk());

    }
}
