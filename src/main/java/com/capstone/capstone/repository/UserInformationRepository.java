package com.capstone.capstone.repository;

import com.capstone.capstone.model.UserInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserInformationRepository extends MongoRepository<UserInformation, String> {

    @Query("{ 'firstName' : ?0 }")
    public UserInformation findByFistName(String firstName);
}
