package com.capstone.capstone.model.Entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({MockitoExtension.class})
class EvidenceCountTest {

    @Test
    void returnCorrentObjectWhenBuilderUsed() {

        LocalDateTime localDateTime = LocalDateTime.now();

        EvidenceCount evidenceCount =
                EvidenceCount.builder()
                        .evidenceCountId(1)
                        .apprenticeId(2)
                        .ksbCode("S5")
                        .insertTimestamp(localDateTime)
                        .count(12)
                        .build();

        assertThat(evidenceCount.getEvidenceCountId()).isEqualTo(1);
        assertThat(evidenceCount.getApprenticeId()).isEqualTo(2);
        assertThat(evidenceCount.getKsbCode()).isEqualTo("S5");
        assertThat(evidenceCount.getInsertTimestamp()).isEqualTo(localDateTime);
        assertThat(evidenceCount.getCount()).isEqualTo(12);
    }

}