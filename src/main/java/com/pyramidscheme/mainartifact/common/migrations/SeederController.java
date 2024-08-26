package com.pyramidscheme.mainartifact.common.migrations;

import com.pyramidscheme.mainartifact.common.migrations.job.JobSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seed")
public class SeederController {
    @Autowired
    private JobSeeder jobSeeder;


    @GetMapping("/job")
    public String seedUsers() {
        boolean isSeedingSuccess = jobSeeder.seedData();
        return isSeedingSuccess ? "Job Seeding success." : "Job Seeding fail";
    }
}
