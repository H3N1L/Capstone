package com.capstone.capstone.service;

import com.capstone.capstone.model.mongodb.Evidence;
import com.capstone.capstone.repository.EvidenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class TextEvidenceServiceTest {

    @Mock
    @Autowired
    private EvidenceRepository evidenceRepository;

    @InjectMocks TextEvidenceService textEvidenceService;

    @Test
    void checkFindAllMethod() {

        when(evidenceRepository.findAll())
                .thenReturn(List.of(Evidence.builder()
                        .id("123")
                        .evidence("evidence")
                        .KsbCode("s1")
                        .build()));

        List<Evidence> evidences = textEvidenceService.getAllEvidence();

        verify(evidenceRepository, times(1)).findAll();





    }
}