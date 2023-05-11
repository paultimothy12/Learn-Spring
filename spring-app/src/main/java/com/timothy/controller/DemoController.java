package com.timothy.controller;

import com.timothy.utils.AttributeNames;
import com.timothy.utils.Mappings;
import com.timothy.utils.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {

    @ModelAttribute("demoController")
    public DemoController demoController() {
        return new DemoController();
    }

    @GetMapping("/")
    public String welcome(@RequestParam String UserName, Model model) {
        model.addAttribute(AttributeNames.USERNAME, UserName);
        return ViewNames.WELCOME_VIEW;
    }

    @ModelAttribute("Message")
    public String Message() {
        return " Welcome to web app";
    }

    List<String> strings = List.of("Paul", "D", "Timothy");

    public List<String> getStrings() {
        return strings;
    }


    @GetMapping(Mappings.REDIRECT)
    public String redirect() {
        return ViewNames.REDIRECT_VIEW;
    }

    @GetMapping(Mappings.VIEW_DATA)
    public String viewData() {
        return ViewNames.VIEW_DATA_VIEW;
    }

    static List<Database> databaseList = new ArrayList<>();

    @PostMapping(Mappings.REDIRECT)
    public String addData(@RequestParam Integer number, @RequestParam String name) {
        databaseList.add(new Database(number, name));
        System.out.println(databaseList.get(0));
        return ViewNames.REDIRECT_VIEW;
    }

    public List<Database> getDatabaseList() {
        return databaseList;
    }

}
