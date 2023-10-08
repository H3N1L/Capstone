package com.capstone.capstone.controller;


import com.capstone.capstone.repository.EvidenceRepository;
import com.capstone.capstone.repository.UserInformationRepository;
import com.capstone.capstone.model.Evidence;
import com.capstone.capstone.model.UserInformation;
import com.capstone.capstone.service.TextEvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
}
