package com.capstone.capstone.repository.Archive;

import com.capstone.capstone.model.mongodb.University;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUniversityRepository extends MongoRepository<University, String> {
}
