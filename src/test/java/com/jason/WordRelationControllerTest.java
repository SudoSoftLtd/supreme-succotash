//package com.jason;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.test.annotation.DirtiesContext;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//public class WordRelationControllerTest {
//
//    @Value(value = "${local.server.port}")
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void checkModelValidation() {
//        WordRelationModel wordRelationModel = new WordRelationModel(
//                null,
//                " M A N !",
//                " C O L D ",
//                WordRelationModel.Relation.ANTONYM);
//
//        wordRelationModel.lowerCaseAndWhiteSpaceValidation();
//
//        assertEquals("man", wordRelationModel.getWordOne());
//        assertEquals("cold", wordRelationModel.getWordTwo());
//    }
//
//    @Test
//    public void defaultEmptyDatabaseValueForGet() {
//        assertThat(
//                this.restTemplate.getForObject("http://localhost:" + port + "/api/v1", String.class)
//        ).contains("[]");
//    }
//
//    @Test
//    public void populateSingleDatabaseValueForGet() {
//
//        WordRelationModel wordRelationModel = new WordRelationModel(
//                null,
//                "hot",
//                "cold",
//                WordRelationModel.Relation.ANTONYM);
//
//        this.restTemplate.postForObject("http://localhost:" + port + "/api/v1",
//                wordRelationModel,
//                WordRelationModel.class);
//
//        assertThat(
//                this.restTemplate.getForObject("http://localhost:" + port + "/api/v1", String.class)
//        ).contains("[{\"id\":1,\"wordOne\":\"hot\",\"wordTwo\":\"cold\",\"relation\":\"ANTONYM\"}]");
//    }
//
//    @Test
//    public void filterByRelation() {
//
//        this.restTemplate.postForObject("http://localhost:" + port + "/api/v1",
//                new WordRelationModel(
//                        null,
//                        "hot",
//                        "cold",
//                        WordRelationModel.Relation.ANTONYM),
//                WordRelationModel.class);
//
//        this.restTemplate.postForObject("http://localhost:" + port + "/api/v1",
//                new WordRelationModel(
//                        null,
//                        "rabbit",
//                        "hare",
//                        WordRelationModel.Relation.SYNONYM),
//                WordRelationModel.class);
//
//        this.restTemplate.postForObject("http://localhost:" + port + "/api/v1",
//                new WordRelationModel(
//                        null,
//                        "pine",
//                        "apple",
//                        WordRelationModel.Relation.RELATED),
//                WordRelationModel.class);
//
//        assertThat(
//                this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/filter/1", String.class)
//        ).isEqualTo("[{\"id\":1,\"wordOne\":\"hot\",\"wordTwo\":\"cold\",\"relation\":\"ANTONYM\"}]");
//
//        assertThat(
//                this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/filter/0", String.class)
//        ).isEqualTo("[{\"id\":2,\"wordOne\":\"rabbit\",\"wordTwo\":\"hare\",\"relation\":\"SYNONYM\"}]");
//
//        assertThat(
//                this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/filter/2", String.class)
//        ).isEqualTo("[{\"id\":3,\"wordOne\":\"pine\",\"wordTwo\":\"apple\",\"relation\":\"RELATED\"}]");
//    }
//
//}