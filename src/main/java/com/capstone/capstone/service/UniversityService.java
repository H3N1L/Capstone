package com.capstone.capstone.service;

import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.model.specifications.ApprenticeSpecification;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.capstone.capstone.model.specifications.ApprenticeSpecification.hasUniversityCode;

@Service
public class UniversityService {


    @Autowired private ApprenticeRepository apprenticeRepository;
    @Autowired private UniversityRepository universityRepository;


    public List<Apprentice> retrieveAllApprenticesByUniCode(String uniCode) {
       return apprenticeRepository.findAll(hasUniversityCode(uniCode));
    }




}
