package br.com.guitcamargo.springprimefaces.endpoints;

import br.com.guitcamargo.springprimefaces.SpringPrimefacesApplicationTests;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@TestMethodOrder(OrderAnnotation.class)
public class PlanetaEndpointTest extends SpringPrimefacesApplicationTests<PlanetaEndpointTest> {

    @Test
    @Order(1)
    public void shouldReturnNotFoundWhenGetPlanetaById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get
                                (PlanetaEndpoint.PLANETA_PATH.replace("{idPlaneta}", "9999"))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    @Order(2)
    public void shouldReturnOKWhenGetPlanetaById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get
                                (PlanetaEndpoint.PLANETA_PATH.replace("{idPlaneta}", "1"))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Tatooine"));
    }

    @Test
    @Order(3)
    public void shouldReturnOkWhenGetPlanetasByFilterValue() throws Exception{
        String uri = PlanetaEndpoint.PLANETA_MATRIX.replace("{matrixVariables}", ";clima=Clima");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$._embedded").exists())
                .andExpect(jsonPath("$._embedded.planetas").isArray())
                .andExpect(jsonPath("$._embedded.planetas.size()").value(3))
                .andExpect(jsonPath("$._embedded.planetas[0].id").value(1))
                .andExpect(jsonPath("$._embedded.planetas[0].nome").value("Tatooine"))
                .andExpect(jsonPath("$._embedded.planetas[1].id").value(2))
                .andExpect(jsonPath("$._embedded.planetas[1].nome").value("Alderaan"))
                .andExpect(jsonPath("$._embedded.planetas[2].id").value(3))
                .andExpect(jsonPath("$._embedded.planetas[2].nome").value("Mirial"))
                .andExpect(jsonPath("$.page.size").value(20))
                .andExpect(jsonPath("$.page.totalElements").value(3))
                .andExpect(jsonPath("$.page.totalPages").value(1))
                .andExpect(jsonPath("$.page.number").value(0));
    }

    @Test
    @Order(4)
    public void shouldReturnEmptyValueWhenGetPlanetasByFilterValue() throws Exception{
        String uri = PlanetaEndpoint.PLANETA_MATRIX.replace("{matrixVariables}", ";nome=Novo");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$._embedded").exists())
                .andExpect(jsonPath("$._embedded.planetas").isArray())
                .andExpect(jsonPath("$._embedded.planetas.size()").value(0))
                .andExpect(jsonPath("$.page.size").value(20))
                .andExpect(jsonPath("$.page.totalElements").value(0))
                .andExpect(jsonPath("$.page.totalPages").value(0))
                .andExpect(jsonPath("$.page.number").value(0));
    }

    @Test
    @Order(5)
    public void shouldReturnOkWhenDeletedPlanetaById() throws Exception{
        String uri = PlanetaEndpoint.PLANETA_PATH.replace("{idPlaneta}", "1");

        mockMvc.perform(MockMvcRequestBuilders.get
                                (uri)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Tatooine"));


        mockMvc.perform(MockMvcRequestBuilders.delete
                                (uri)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get
                                (uri)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    @Order(6)
    public void shouldReturnCreatedPlaneta() throws Exception{
        String uri = PlanetaEndpoint.PLANETA_BASE_URI;

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType("application/json")
                        .content(super.getScenarioRequestBody("createPlanetaOk")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").value(4));
    }

    @Test
    @Order(7)
    public void shouldReturnBadRequestPlanetaError() throws Exception{
        String uri = PlanetaEndpoint.PLANETA_BASE_URI;

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType("application/json")
                        .content(super.getScenarioRequestBody("createPlanetaError")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
