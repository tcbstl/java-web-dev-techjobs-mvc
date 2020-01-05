package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value = "results")

    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", columnChoices);
        ArrayList<Job> jobs;

        if (searchType.toLowerCase().equals("all")) {
            if (searchTerm.toLowerCase().equals("all")){
                jobs = JobData.findAll();
                model.addAttribute("title", "All Jobs");
                model.addAttribute("jobs", jobs);
            }
            else {
                jobs = JobData.findByValue(searchTerm);
                model.addAttribute("title", "All Jobs");
                model.addAttribute("jobs", jobs);
            }

        } else if (searchType.toLowerCase().equals("employer")) {
            jobs = JobData.findByColumnAndValue("employer", searchTerm);
            model.addAttribute("title", "Jobs With Employer: " + searchTerm);
            model.addAttribute("jobs", jobs);

        } else if (searchType.toLowerCase().equals("location")) {
            jobs = JobData.findByColumnAndValue("location", searchTerm);
            model.addAttribute("title", "Jobs With Location: " + searchTerm);
            model.addAttribute("jobs", jobs);

        } else if (searchType.toLowerCase().equals("corecompetency")) {
            jobs = JobData.findByColumnAndValue("coreCompetency", searchTerm);
            model.addAttribute("title", "Jobs With Skill: " + searchTerm);
            model.addAttribute("jobs", jobs);

        } else if (searchType.toLowerCase().equals("positiontype")) {
            jobs = JobData.findByColumnAndValue("positionType", searchTerm);
            model.addAttribute("title", "Jobs With PositionType :"+ searchTerm);
            model.addAttribute("jobs", jobs);
        }

        else {
            jobs = JobData.findAll();
        }

        return "search";
    }
}