package com.capstone.capstone.repository;

import com.capstone.capstone.model.Evidence;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EvidenceRepository extends MongoRepository<Evidence, String> {
}
