package com.capstone.capstone.service;


import com.capstone.capstone.model.Entity.Evidence;
import com.capstone.capstone.model.Entity.EvidenceKsbMapper;
import com.capstone.capstone.model.Entity.UserEvidence;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.EvidenceKsbMapperRepository;
import com.capstone.capstone.repository.EvidenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvidenceServiceTest {

    @Mock
    ApprenticeRepository apprenticeRepository;

    @Mock
    EvidenceRepository evidenceRepository;

    @Mock
    EvidenceKsbMapperRepository evidenceKsbMapperRepository;

    @InjectMocks EvidenceService evidenceService;



    @Test
    void whenRetrievingAllEvidenceById_thenReturnList() {
        when(evidenceRepository.findAll(any(Specification.class)))
                .thenReturn(List.of(Evidence.builder()
                        .evidenceGuid("Guid").build()));


        List<Evidence> evidences = evidenceService.retrieveAllEvidenceByApprenticeId(1);
        assertThat(evidences.size()).isEqualTo(1);
    }

    @Test
    void whenEvidenceProvided_thenSubmit() throws Exception {

        UserEvidence userEvidence = UserEvidence
                .builder()
                .evidenceId("Guid")
                .ksbID("K2")
                .type("type")
                .description("description")
                .submittedContent("SubmittedContent")
                .specialism("Specialism")
                .dateCreated("15/12.2023")
                .build();

        when(evidenceRepository.save(any()))
                .thenReturn(Evidence
                        .builder()
                        .evidenceGuid("Guid")
                        .evidenceId(1)
                        .ksbId("K2")
                        .insertTimestamp(LocalDateTime.now())
                        .specialism("Specialism")
                        .evidenceDescription("Description")
                        .type("Type")
                        .build());

        Evidence evidence = evidenceService.uploadSubmittedEvidence(userEvidence, "fileKey");

        assertThat(evidence.getEvidenceDescription())
                .isEqualTo("Description");

        verify(evidenceRepository, times(1)).save(any());
    }


    @Test
    void whenEvidenceToKsbMapperInvoked_thenSave() throws Exception {

        evidenceService.uploadEvidenceToKsbMapper(mock(Evidence.class));
        verify(evidenceKsbMapperRepository, times(1)).save(any());

    }

    @Test
    void whenRetrieveMediaContentKey_thenPeformSuccessfully() throws Exception {

        when(evidenceRepository.findOne(any(Specification.class)))
                .thenReturn(Optional.of(Evidence.builder()
                        .type("Type")
                        .evidenceDescription("Description")
                        .evidenceGuid("Guid")
                        .s3Guid("s3Guid")
                        .build()));

        String s3Guid = evidenceService.retrieveMediaContentKey("1");
        assertThat(s3Guid).isEqualTo("s3Guid");
    }

    @Test
    void retrieveSpecificEvidenceSuccessfully() throws Exception {

        when(evidenceRepository.findOne(any(Specification.class)))
                .thenReturn(Optional.of(Evidence.builder()
                        .s3Guid("s3Guid")
                        .evidenceGuid("Guid")
                        .build()));

        Evidence evidence = evidenceService.retrieveSpecificEvidence("Guid");
        assertThat(evidence.getEvidenceId()).isNull();
    }

    @Test
    void retrieveEvidenceKsbMapSuccessfully() throws Exception {
        when(evidenceKsbMapperRepository.findOne(any(Specification.class)))
                .thenReturn(Optional.of(EvidenceKsbMapper.builder().build()));

        EvidenceKsbMapper evidenceKsbMapper = evidenceService.retrieveEvidencKsbMap(mock(Evidence.class));
        assertThat(evidenceKsbMapper.getEvidenceGuid()).isNull();
    }



}