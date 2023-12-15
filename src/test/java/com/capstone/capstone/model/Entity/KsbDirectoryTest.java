package com.capstone.capstone.model.Entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class KsbDirectoryTest {

    @Test
    void returnCorrectObjectWhenBuilderUsed() {
        KsbDirectory ksbDirectory =
                KsbDirectory.builder()
                        .ksbDirectoryId(1)
                        .ksbName("KsbName")
                        .ksbDirectoryId(1)
                        .degreeCode("CSE")
                        .description("Description")
                        .build();

        assertThat(ksbDirectory.getKsbDirectoryId()).isEqualTo(1);
        assertThat(ksbDirectory.getKsbName()).isEqualTo("KsbName");
        assertThat(ksbDirectory.getKsbDirectoryId()).isEqualTo(1);
        assertThat(ksbDirectory.getDegreeCode()).isEqualTo("CSE");
        assertThat(ksbDirectory.getDescription()).isEqualTo("Description");

    }

}