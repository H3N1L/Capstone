package com.capstone.capstone.model.Entity;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class UniversityTest {



    @Test
    void returnCorrectObjectWhenBuilderUsed() {
        University university =
                University.builder()
                        .universityId(10)
                        .universityCode("Code")
                        .universityName("Name")
                        .build();

        assertThat(university.getUniversityId()).isEqualTo(10);
        assertThat(university.getUniversityCode()).isEqualTo("Code");
        assertThat(university.getUniversityName()).isEqualTo("Name");
    }

}