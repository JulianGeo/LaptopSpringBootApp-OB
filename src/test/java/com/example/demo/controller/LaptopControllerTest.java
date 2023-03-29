package com.example.demo.controller;

import com.example.demo.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder=restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate=new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void getLaptops() {
        ResponseEntity<Laptop[]> response=
                testRestTemplate.getForEntity("/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void getLaptopById() {
        ResponseEntity<Laptop> response=
                testRestTemplate.getForEntity("/laptops/id", Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void saveLaptop() {
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json="""
                {
                    "trademark": "Apple",
                    "price": 150
                }
                """;

        HttpEntity<String> request=new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response=testRestTemplate.exchange("/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop result=response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Apple", result.getTrademark());


    }

    @Test
    void updateLaptop() {
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json="""
                {
                            "laptopID": "f544c058-b314-4ab9-9264-168e70f0a696",
                            "trademark": "SAMSUNG",
                            "price": 1500.0
                }
                """;

        HttpEntity<String> request=new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response=testRestTemplate.exchange("/laptops", HttpMethod.PUT, request, Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteLaptops() {

        ResponseEntity<String> response=testRestTemplate.exchange("/laptops", HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //@Test
    void testDeleteLaptops() {

        ResponseEntity<String> response=testRestTemplate.exchange("/laptops/someID", HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}