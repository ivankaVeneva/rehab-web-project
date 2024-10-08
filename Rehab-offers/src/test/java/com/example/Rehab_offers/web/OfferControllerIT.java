package com.example.Rehab_offers.web;

import static com.Rehab_offers.model.enums.DeviceTypeEnum.DELSYS_EMG;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.Rehab_offers.model.entity.OfferEntity;
import com.Rehab_offers.model.enums.DeviceTypeEnum;
import com.Rehab_offers.repository.OfferRepository;
import com.jayway.jsonpath.JsonPath;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
    username = "pesho@example.com",
    roles = {"USER", "ADMIN"}
)
public class OfferControllerIT {

  @Container
  @ServiceConnection
  static MySQLContainer<?> mySQLContainer =
      new MySQLContainer<>("mysql");

  @Autowired
  private OfferRepository offerRepository;

  @Autowired
  private MockMvc mockMvc;

  @AfterEach
  public void tearDown() {
    offerRepository.deleteAll();
  }

  @Test
  public void testGetById() throws Exception {

    // Arrange
    var actualEntity = createTestOffer();

    // ACT
    ResultActions result = mockMvc
        .perform(get("/offers/{id}", actualEntity.getId())
            .contentType(MediaType.APPLICATION_JSON));

    // Assert
    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(actualEntity.getId().intValue())))
        .andExpect(jsonPath("$.description", is(actualEntity.getDescription())))
        .andExpect(jsonPath("$.mileage", is(actualEntity.getMileage())))
        .andExpect(jsonPath("$.price", is(actualEntity.getPrice())));
  }

  @Test
  public void testOfferNotFound() throws Exception {
    mockMvc
        .perform(get("/offers/{id}", "1000000")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testCreateOffer() throws Exception {
    MvcResult result = mockMvc.perform(post("/offers")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                  {
                    "description": "Test description",
                    "mileage": 12345,
                    "price": 123,
                    "deviceType": "DELSYS_EMG"
                  }
                """)
        ).andExpect(status().isCreated())
        .andExpect(header().exists("Location"))
        .andReturn();

    String body = result.getResponse().getContentAsString();

    int id = JsonPath.read(body, "$.id");

    Optional<OfferEntity> createdOfferOpt = offerRepository.findById((long) id);

    Assertions.assertTrue(createdOfferOpt.isPresent());

    OfferEntity createdOffer = createdOfferOpt.get();

    Assertions.assertEquals("Test description", createdOffer.getDescription());
    Assertions.assertEquals(12345, createdOffer.getMileage());
    Assertions.assertEquals(123, createdOffer.getPrice());
    Assertions.assertEquals(DELSYS_EMG, createdOffer.getDevice());

  }

  @Test
  public void testDeleteOffer() throws Exception {

    var actualEntity = createTestOffer();

    mockMvc.perform(delete("/offers/{id}", actualEntity.getId())
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isNoContent());

    Assertions.assertTrue(
        offerRepository.findById(actualEntity.getId()).isEmpty()
    );
  }

  private OfferEntity createTestOffer() {
    return offerRepository.save(
        new OfferEntity()
            .setDescription("test offer")
            .setDevice(DELSYS_EMG)
            .setMileage(10000)
            .setPrice(2000)
    );
  }

}
