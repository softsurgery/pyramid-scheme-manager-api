package com.pyramidscheme.mainartifact.modules.job.controllers;

import com.pyramidscheme.mainartifact.modules.job.entities.JobModel;
import com.pyramidscheme.mainartifact.modules.job.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    public List<JobModel> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobModel getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @GetMapping("/api/job/list")
    public Page<JobModel> getJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean sortAsc,
            @RequestParam Map<String, String> filters) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortAsc ? Sort.Order.asc(sortBy) : Sort.Order.desc(sortBy)));
        Specification<JobModel> specification = jobService.buildSpecification(filters);
        return jobService.getJobs(specification, pageable);
    }

    @PostMapping
    public JobModel createJob(@RequestBody JobModel jobDetails) {
        return jobService.saveJob(jobDetails);
    }

    @PutMapping("/{id}")
    public JobModel updateJob(@PathVariable Long id, @RequestBody JobModel jobDetails) {
        return jobService.updateJob(id, jobDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}
