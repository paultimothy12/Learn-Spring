package com.timothy.controller;

import com.timothy.entities.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RestAPIController {
    TestDataRepository testDataRepository;
    @Autowired
    public RestAPIController(TestDataRepository testDataRepository) {
        this.testDataRepository = testDataRepository;
    }

    @GetMapping("")
    public String welcome(){
        return "welcome";
    }
    @GetMapping("/api/string-data")
    public List<String> getData(){
        return List.of("Paul","D","Timothy");
    }





    @GetMapping("/api/get-test-data")
    public List<TestData> getTestData(){
        return testDataRepository.findAll();
    }

    @PostMapping("/api/post-test-data")
    public ResponseEntity<String> addNewData(@RequestBody TestData testData){
        testDataRepository.save(testData);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(testData.getNumber())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/api/put-test-data")
    public ResponseEntity<String> updateData(@RequestBody TestData testData){
        TestData testDataObj = testDataRepository.findById(testData.getNumber()).get();

        testDataObj.setName(testData.getName());
        testDataRepository.save(testDataObj);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(testData.getNumber())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //using @PathVariable
    @DeleteMapping("/api/delete-test-data/{id}")    //Delete Mapping doesn't allow payloads
    public ResponseEntity<String> deleteDataPathVariable(@PathVariable Integer id){
        testDataRepository.deleteById(id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/api/get-specific-test-data/{id}")
    public Optional<TestData> getSpecificTestData(@PathVariable Integer id){
        return testDataRepository.findById(id);
    }

}
