package com.capstone.capstone.service;

import com.capstone.capstone.model.mongodb.Evidence;
import com.capstone.capstone.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextEvidenceService {

    @Autowired
    private EvidenceRepository evidenceRepository;


    public List<Evidence> getAllEvidence() {
        return evidenceRepository.findAll();
    }


}
