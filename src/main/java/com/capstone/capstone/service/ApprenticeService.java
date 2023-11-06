package com.capstone.capstone.service;

import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.repository.ApprenticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ApprenticeService {

    @Autowired
    private ApprenticeRepository apprenticeRepository;


    //Add functionality
    public Apprentice addApprentice(Apprentice apprentice) {
        return apprenticeRepository.save(apprentice);
    }

    //Update first_name
    public Apprentice updateFirstName(Integer apprenticeId, String newFirstName) {
        Optional<Apprentice> apprentice = apprenticeRepository.findById(apprenticeId);

        if (apprentice.isEmpty()) {
            log.info("###### No apprentice with provided id found in database");
            return null;
        }
        Apprentice retrievedApprentice = apprentice.get();
        retrievedApprentice.setFirstName(newFirstName);
        return apprenticeRepository.save(retrievedApprentice);
    }


}
