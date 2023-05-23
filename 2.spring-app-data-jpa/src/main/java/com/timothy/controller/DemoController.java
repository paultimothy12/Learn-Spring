package com.timothy.controller;

import com.timothy.entities.TestDataRepository;
import com.timothy.utils.AttributeNames;
import com.timothy.utils.Mappings;
import com.timothy.utils.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DemoController {

    TestDataRepository testDataRepository;
    public DemoController() {
    }
    @Autowired
    public DemoController(TestDataRepository testDataRepository) {
        this.testDataRepository = testDataRepository;
    }


    @ModelAttribute("demoController")
    public DemoController demoController() {
        return new DemoController();
    }
    @ModelAttribute("Message")
    public String Message() {
        return " Welcome to web app";
    }

    List<String> strings = List.of("Paul", "D", "Timothy");

    public List<String> getStrings() {
        return strings;
    }
    @GetMapping("/")
    public String welcome(@RequestParam String UserName, Model model) {
        model.addAttribute(AttributeNames.USERNAME, UserName);
        return ViewNames.WELCOME_VIEW;
    }


    @GetMapping(Mappings.ADD_DATA)
    public String redirect() {
        return ViewNames.ADD_DATA_VIEW;
    }

    @GetMapping(Mappings.VIEW_DATA)
    public String viewData(Model model) {
        model.addAttribute("testDatabaseList",testDataRepository.findAll());
        return ViewNames.VIEW_DATA_VIEW;
    }


    @PostMapping(Mappings.ADD_DATA)
    public String addData(@RequestParam Integer number, @RequestParam String name) {
        testDataRepository.save(new TestData(number,name));
        return ViewNames.ADD_DATA_VIEW;
    }
}
