package com.capstone.capstone.model.Entity;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class EvidenceKsbMapperTest {

    @Test
    void returnCorrectObjectWhenBuilderUsed() {
        String evidencGuid = UUID.randomUUID().toString();

        EvidenceKsbMapper evidenceKsbMapper =
                EvidenceKsbMapper.builder()
                        .evidenceToKsbId(1)
                        .evidenceGuid(evidencGuid)
                        .ksbCode("S5")
                        .build();

        assertThat(evidenceKsbMapper.getEvidenceToKsbId()).isEqualTo(1);
        assertThat(evidenceKsbMapper.getKsbCode()).isEqualTo("S5");
        assertThat(evidenceKsbMapper.getEvidenceGuid()).isEqualTo(evidencGuid);
    }

}