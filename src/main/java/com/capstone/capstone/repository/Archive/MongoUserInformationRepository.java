package com.capstone.capstone.repository.Archive;

import com.capstone.capstone.model.mongodb.UserInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoUserInformationRepository extends MongoRepository<UserInformation, String> {

    @Query("{ 'firstName' : ?0 }")
    public UserInformation findByFistName(String firstName);
}
