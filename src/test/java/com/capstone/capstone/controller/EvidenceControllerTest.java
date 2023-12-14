package com.capstone.capstone.controller;


import com.capstone.capstone.model.Entity.Evidence;
import com.capstone.capstone.model.Entity.UserEvidence;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.EvidenceKsbMapperRepository;
import com.capstone.capstone.repository.EvidenceRepository;
import com.capstone.capstone.service.AmazonClientService;
import com.capstone.capstone.service.ApprenticeService;
import com.capstone.capstone.service.EvidenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvidenceControllerTest {

    @Mock
    ApprenticeRepository apprenticeRepository;
    @Mock
    ApprenticeService apprenticeService;
    @Mock
    AmazonClientService amazonClientService;
    @Mock
    EvidenceRepository evidenceRepository;
    @Mock
    EvidenceKsbMapperRepository evidenceKsbMapperRepository;
    @Mock
    EvidenceService evidenceService;
    @InjectMocks EvidenceController evidenceController;



    @Test
    void whenEvidenceProvided_thenSubmitSuccessfully() throws Exception {

        when(amazonClientService.uploadEvidencePayload(any()))
                .thenReturn("fileKeyMock");

        when(evidenceService.uploadSubmittedEvidence(any(), any()))
                .thenReturn(Evidence.builder()
                        .evidenceId(1)
                        .evidenceDescription("Description")
                        .evidenceGuid("Guid")
                        .ksbId("K2")
                        .s3Guid("fileKeyMock")
                        .insertTimestamp(LocalDateTime.now())
                        .specialism("specialism")
                        .build());

        Evidence evidence = evidenceController.addFrontEndEvidence(mock(UserEvidence.class));
        assertThat(evidence.getS3Guid()).isEqualTo("fileKeyMock");
        verify(amazonClientService, times(1)).uploadEvidencePayload(any());
        verify(evidenceService, times(1)).uploadSubmittedEvidence(any(), any());
    }

    @Test
    void whenRetrievingSubmittedContent_thenPerformMethodSuccessfully() throws Exception {

        when(evidenceService.retrieveMediaContentKey(any()))
                .thenReturn("Guid");

        when(amazonClientService.retrieveSubmittedContent("Guid"))
                .thenReturn("SubmittedContent");

        String result = evidenceController.retrieveSubmittedContent("String");

        assertThat(result).isEqualTo("SubmittedContent");
        verify(evidenceService, times(1)).retrieveMediaContentKey(any());
        verify(amazonClientService, times(1)).retrieveSubmittedContent(any());
    }

    @Test
    void whenRequestToViewAllEvidence_thenProvideList() {
        when(evidenceRepository.findAll(any(Specification.class)))
                .thenReturn(List.of(
                        Evidence.builder()
                                .specialism("Specialism")
                                .insertTimestamp(LocalDateTime.now())
                                .evidenceId(1)
                                .evidenceGuid("Guid")
                                .type("Type")
                                .apprenticeId(1)
                                .s3Guid("S3_GUID")
                                .build()));

        List<UserEvidence> result = evidenceController.retrieveAllApprenticeEvidence(1);
        assertThat(result.get(0).getEvidenceId()).isEqualTo("Guid");
        assertThat(result.size()).isEqualTo(1);
    }












}