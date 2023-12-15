package com.capstone.capstone.model.Entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class UserEvidenceTest {


    @Test
    void returnCorrectObjectWhenBuilderUsed() {

        String date = LocalDateTime.now().toString();
        UserEvidence userEvidence =
                UserEvidence.builder()
                        .evidenceId("String")
                        .ksbID("10")
                        .type("type")
                        .specialism("Specialism")
                        .dateCreated(date)
                        .submittedContent("SubmittedContent")
                        .description("Description")
                        .build();


        assertThat(userEvidence.getEvidenceId()).isEqualTo("String");
        assertThat(userEvidence.getKsbID()).isEqualTo("10");
        assertThat(userEvidence.getType()).isEqualTo("type");
        assertThat(userEvidence.getSpecialism()).isEqualTo("Specialism");
        assertThat(userEvidence.getDateCreated()).isEqualTo(date);
        assertThat(userEvidence.getSubmittedContent()).isEqualTo("SubmittedContent");
        assertThat(userEvidence.getDescription()).isEqualTo("Description");
    }

}