package com.capstone.capstone.repository.Archive;

import com.capstone.capstone.model.mongodb.EvidenceMongo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoEvidenceRepository extends MongoRepository<EvidenceMongo, String> {
}
