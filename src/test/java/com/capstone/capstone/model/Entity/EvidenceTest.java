package com.capstone.capstone.model.Entity;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class EvidenceTest {

    @Test
    void returnObjectWhenBuilderUsed() {
        String evidencGuid = UUID.randomUUID().toString();
        LocalDateTime localDateTime = LocalDateTime.now();
        Evidence evidence =
                Evidence.builder()
                        .evidenceId(1)
                        .evidenceGuid(evidencGuid)
                        .type("type")
                        .specialism("specialism")
                        .apprenticeId(2)
                        .s3Guid("s3Guid")
                        .ksbId("S5")
                        .insertTimestamp(localDateTime)
                        .evidenceDescription("description")
                        .build();

        assertThat(evidence.getEvidenceId()).isEqualTo(1);
        assertThat(evidence.getEvidenceGuid()).isEqualTo(evidencGuid);
        assertThat(evidence.getType()).isEqualTo("type");
        assertThat(evidence.getSpecialism()).isEqualTo("specialism");
        assertThat(evidence.getApprenticeId()).isEqualTo(2);
        assertThat(evidence.getS3Guid()).isEqualTo("s3Guid");
        assertThat(evidence.getKsbId()).isEqualTo("S5");
        assertThat(evidence.getInsertTimestamp()).isEqualTo(localDateTime);
        assertThat(evidence.getEvidenceDescription()).isEqualTo("description");
    }

}