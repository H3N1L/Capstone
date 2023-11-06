package com.capstone.capstone.controller;


import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.model.mongodb.University;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.Archive.MongoEvidenceRepository;
import com.capstone.capstone.repository.Archive.MongoUniversityRepository;
import com.capstone.capstone.repository.Archive.MongoUserInformationRepository;
import com.capstone.capstone.model.mongodb.Evidence;
import com.capstone.capstone.model.mongodb.UserInformation;
import com.capstone.capstone.service.ApprenticeService;
import com.capstone.capstone.service.TextEvidenceService;
import com.capstone.capstone.util.UniversitySeedData;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@RestController
public class EvidenceController {

    @Autowired private TextEvidenceService textEvidenceService;
    @Autowired private MongoEvidenceRepository evidenceRepository;
    @Autowired private MongoUserInformationRepository userInformationRepository;
    @Autowired private MongoUniversityRepository universityRepository;
    @Autowired private ApprenticeRepository apprenticeRepository;

    @Autowired private ApprenticeService apprenticeService;



    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/addApprentice")
    public Apprentice  addApprentice() {
        return apprenticeRepository.save(
                Apprentice.builder()
                        .email("henil.soni13@gmail.com")
                        .firstName("Fenil")
                        .lastName("Soni")
                        .build());
    }

    @PostMapping("/updateApprenticeFirstName")
    public Apprentice updateApprenticeName(){
        return apprenticeService.updateFirstName(1, "Henil");
    }

    @GetMapping("/evidence")
    public List<Evidence> getAllEvidence() {
        return textEvidenceService.getAllEvidence();
    }

    @GetMapping("/users")
    public List<UserInformation> getAllUsers() {
        return userInformationRepository.findAll();
    }

    @GetMapping("/updateUserKey")
    public UserInformation updateUserKey() {

        UserInformation userInformation =  userInformationRepository.findByFistName("Henil");
        userInformation.setKey(UUID.randomUUID().toString());
        return userInformationRepository.save(userInformation);
    }

//    @PostMapping("/addMarvin")
//    public ResponseEntity<String> addMarvin () {
//        return new ResponseEntity<>(makeMarvin(), HttpStatus.ACCEPTED);
//    }
//
//    public String makeMarvin() {
//        UserInformation userInformation =
//                UserInformation.builder()
//                        .firstName("Marvin")
//                        .lastName("Mbasu")
//                        .email("marvin@marvin.com")
//                        .id("2")
//                        .universityCode("NUC2023")
//                        .degreeCode("SE2022")
//                        .key(UUID.randomUUID().toString())
//                        .specialKsb(new ArrayList<>(Collections.singleton("K1")))
//                        .build();
//
//        userInformationRepository.insert(userInformation);
//
//        return "Marvin is in the building";
//    }

//    @PostMapping("/addUniveristySeedData")
//    public List<University> addUniversitySeedData(){
//        List<University> universityList = UniversitySeedData.insertUniversitySeedData();
//        universityList.forEach(university -> {
//            universityRepository.insert(university);
//        });
//        return universityList;
//    }
}
