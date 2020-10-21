package com.ew.coronavirustracker.controller;

import com.ew.coronavirustracker.model.LocationStat;
import com.ew.coronavirustracker.service.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private VirusDataService dataService;

    @GetMapping("/")
    public String index(Model model) {
        List<LocationStat> allStats = dataService.getAllStats();
        int total = allStats.stream().mapToInt(s -> s.getLatestTotalCases()).sum();
        int totalNew = allStats.stream().mapToInt(s -> s.getDiffFormPreDay()).sum();
        model.addAttribute("stats", dataService.getAllStats());
        model.addAttribute("total", total);
        model.addAttribute("totalNew", totalNew);
        return "index";
    }
}
