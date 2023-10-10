package com.capstone.capstone.controller;


import com.capstone.capstone.repository.EvidenceRepository;
import com.capstone.capstone.repository.UserInformationRepository;
import com.capstone.capstone.model.mongodb.Evidence;
import com.capstone.capstone.model.mongodb.UserInformation;
import com.capstone.capstone.service.TextEvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class EvidenceController {

    @Autowired
    private TextEvidenceService textEvidenceService;

    @Autowired
    private EvidenceRepository evidenceRepository;

    @Autowired
    private UserInformationRepository userInformationRepository;


    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
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

    @PostMapping("/addMarvin")
    public ResponseEntity<String> addMarvin () {
        return new ResponseEntity<>(makeMarvin(), HttpStatus.ACCEPTED);
    }

    public String makeMarvin() {
        UserInformation userInformation =
                UserInformation.builder()
                        .firstName("Marvin")
                        .lastName("Mbasu")
                        .email("marvin@marvin.com")
                        .id("2")
                        .universityCode("NUC2023")
                        .degreeCode("SE2022")
                        .key(UUID.randomUUID().toString())
                        .specialKsb(new ArrayList<>(Collections.singleton("K1")))
                        .build();

        userInformationRepository.insert(userInformation);

        return "Marvin is in the building";
    }
}
