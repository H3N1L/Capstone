package com.capstone.capstone.controller;

import com.capstone.capstone.model.amazon.EvidenceInformation;
import com.capstone.capstone.service.AmazonClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StorageController {

    @Autowired
    private AmazonClientService amazonClientService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam(value = "multipartFile") MultipartFile multipartFile,
            @RequestParam(value = "UserId") String userID) {
        return new ResponseEntity<>(amazonClientService.uploadFile(multipartFile, userID), HttpStatus.OK);
    }

    @PostMapping("/uploadEvidence")
    public ResponseEntity<String> uploadEvidence(
            @RequestParam(value = "evidenceInformation") String evidenceInformation,
            @RequestParam(value = "multipartFile") MultipartFile multipartFile) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        EvidenceInformation evidenceInformation1 =
                objectMapper.readValue(evidenceInformation, EvidenceInformation.class);
        return new ResponseEntity<>(amazonClientService.submitMediaEvidence(multipartFile, evidenceInformation1),
                HttpStatus.CREATED);
    }
}
