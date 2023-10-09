package com.capstone.capstone.controller;

import com.capstone.capstone.service.AmazonClientService;
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
    public ResponseEntity<String> uploadFile(@RequestParam(value = "multipartFile") MultipartFile multipartFile) {
        return new ResponseEntity<>(amazonClientService.uploadFile(multipartFile), HttpStatus.OK);
    }
}
