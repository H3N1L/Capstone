package com.capstone.capstone.repository.Archive;

import com.capstone.capstone.model.mongodb.EvidenceCount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoEvidenceCountRepository extends MongoRepository<EvidenceCount, String> {
}
