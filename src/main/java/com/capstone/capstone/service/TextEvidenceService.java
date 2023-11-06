package com.capstone.capstone.service;

import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.model.amazon.EvidenceInformation;
import com.capstone.capstone.model.mongodb.Evidence;
import com.capstone.capstone.model.mongodb.EvidenceCount;
import com.capstone.capstone.model.mongodb.UserInformation;
import com.capstone.capstone.repository.Archive.MongoEvidenceCountRepository;
import com.capstone.capstone.repository.Archive.MongoEvidenceRepository;
import com.capstone.capstone.repository.Archive.MongoUserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TextEvidenceService {

    @Autowired private MongoEvidenceRepository evidenceRepository;
    @Autowired private MongoEvidenceCountRepository evidenceCountRepository;
    @Autowired private MongoUserInformationRepository userInformationRepository;


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

    public String insertUser(UserInformation userInformation) {
        Apprentice apprentice = Apprentice.builder().build();




        String userKey = UUID.randomUUID().toString();
        userInformation.setKey(userKey);
        userInformationRepository.insert(userInformation);
        return userKey;
    }




}
