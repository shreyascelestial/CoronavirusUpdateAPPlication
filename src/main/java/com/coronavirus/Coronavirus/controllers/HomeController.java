package com.coronavirus.Coronavirus.controllers;

import com.coronavirus.Coronavirus.CoronavirusApplication;
import com.coronavirus.Coronavirus.Services.CoronavirusDataServices;
import com.coronavirus.Coronavirus.model.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronavirusDataServices coronavirusDataServices ;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronavirusDataServices.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);

        return "home";
    }

}
