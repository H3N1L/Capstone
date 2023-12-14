package com.capstone.capstone.model.Entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class ApprenticeTest {

    @InjectMocks Apprentice apprentice;

    @Test
    void whenBuildingObjectWithCorrectParameter_thenReturnObject() {

        Apprentice apprentice = Apprentice.builder()
                .firstName("Henil")
                .lastName("Soni")
                .email("test@email.com")
                .universityCode("NU")
                .degreeCode("CSC23")
                .apprenticeId(1)
                .build();

        assertThat(apprentice.getFirstName())
                .isEqualTo("Henil");
        assertThat(apprentice.getApprenticeId())
                .isEqualTo(1);
        assertThat(apprentice.getLastName())
                .isEqualTo("Soni");
        assertThat(apprentice.getEmail())
                .isEqualTo("test@email.com");
        assertThat(apprentice.getDegreeCode())
                .isEqualTo("CSC23");
        assertThat(apprentice.getUniversityCode())
                .isEqualTo("NU");



    }



}