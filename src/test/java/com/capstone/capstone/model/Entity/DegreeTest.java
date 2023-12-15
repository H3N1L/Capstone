package com.capstone.capstone.model.Entity;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class DegreeTest {


    @Test
    void whenNewDegreeCreated_thenReturnCorrectObject() {

        Degree degree =
                Degree.builder()
                        .degreeCode("CSE")
                        .degreeName("Software engineering")
                        .universityCode("NU")
                        .degreeId(1).build();

        assertThat(degree.getDegreeId())
                .isEqualTo(1);
        assertThat(degree.getDegreeName())
                .isEqualTo("Software engineering");
        assertThat(degree.getDegreeCode())
                .isEqualTo("CSE");
        assertThat(degree.getUniversityCode())
                .isEqualTo("NU");
    }

}