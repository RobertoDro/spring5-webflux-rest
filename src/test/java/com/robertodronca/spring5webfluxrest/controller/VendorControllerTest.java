package com.robertodronca.spring5webfluxrest.controller;

import com.robertodronca.spring5webfluxrest.domain.Vendor;
import com.robertodronca.spring5webfluxrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class VendorControllerTest {
    VendorController vendorController;
    VendorRepository vendorRepository;
    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void vendorFlux() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(
                        Vendor.builder().lastName("alex").firstName("paul").build(),
                        Vendor.builder().firstName("alexandra").lastName("pavel").build()));

        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void getVendorById() {

        BDDMockito.given(vendorRepository.findById("firstId"))
                .willReturn(Mono.just(Vendor.builder().lastName("paula").lastName("istrate").build()));

        webTestClient.get().uri("/api/v1/vendors/firstId")
                .exchange()
                .expectBody(Vendor.class);
    }
}
