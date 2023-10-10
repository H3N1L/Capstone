package com.capstone.capstone.repository;

import com.capstone.capstone.model.mongodb.EvidenceCount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvidenceCountRepository extends MongoRepository<EvidenceCount, String> {
}
