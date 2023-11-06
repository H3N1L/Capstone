package com.capstone.capstone.repository.Archive;

import com.capstone.capstone.model.mongodb.Evidence;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoEvidenceRepository extends MongoRepository<Evidence, String> {
}
