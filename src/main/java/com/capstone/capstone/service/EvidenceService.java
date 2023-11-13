package com.capstone.capstone.service;

import com.capstone.capstone.model.Entity.Evidence;
import com.capstone.capstone.model.specifications.EvidenceSpecification;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EvidenceService {

    @Autowired private ApprenticeRepository apprenticeRepository;
    @Autowired private EvidenceRepository evidenceRepository;



    public List<Evidence> retrieveAllEvidence(Integer apprenticeId) {
        return evidenceRepository.findAll(EvidenceSpecification.hasApprenticeId(apprenticeId));
    }

//    public List<Evidence> retrieveAllMonthlyApprenticeEvidence(Integer apprenticeId) {
//        return evidenceRepository
//                .findByApprenticeIdAndInsertTimestamp(EvidenceSpecification
//                        .hasApprenticeIdAndInsertTimestamp(apprenticeId));
//    }
//
//    public List<Evidence> retrieveApprenticeEvidenceByDate(Integer apprenticeId, LocalDateTime submittedOn) {
//        return evidenceRepository.findByInsertTimestamp(EvidenceSpecification.hasInsertTimestamp(apprenticeId,submittedOn));
//
//    }


}
