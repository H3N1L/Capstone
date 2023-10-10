package com.capstone.capstone.service;

import com.capstone.capstone.model.amazon.EvidenceInformation;
import com.capstone.capstone.model.mongodb.Evidence;
import com.capstone.capstone.model.mongodb.EvidenceCount;
import com.capstone.capstone.repository.EvidenceCountRepository;
import com.capstone.capstone.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TextEvidenceService {

    @Autowired
    private EvidenceRepository evidenceRepository;

    @Autowired
    private EvidenceCountRepository evidenceCountRepository;


    public List<Evidence> getAllEvidence() {
        return evidenceRepository.findAll();
    }

    public void addEvidenceLink(EvidenceInformation evidenceInformation) {
        evidenceCountRepository.insert(EvidenceCount.builder()
                .ksbCode(evidenceInformation.getKsbCode())
                .count(BigDecimal.ZERO)
                .userId(evidenceInformation.getUserId())
                .build());
    }

    public void insertEvidence(EvidenceInformation evidenceInformation, String s3BucketKey) {
        evidenceRepository.insert(Evidence.builder()
                .evidenceKey(s3BucketKey)
                .UserId(evidenceInformation.getUserId())
                .KsbCode(evidenceInformation.getKsbCode())
                .build());

    }


}
