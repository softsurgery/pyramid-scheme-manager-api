package com.pyramidscheme.mainartifact.modules.job.services;

import com.pyramidscheme.mainartifact.common.database.Filter;
import com.pyramidscheme.mainartifact.modules.job.entities.JobModel;
import com.pyramidscheme.mainartifact.modules.job.repositories.JobRepository;
import org.hibernate.query.sqm.ComparisonOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobModel> getAllJobs() {
        return jobRepository.findAll();
    }

    public Page<JobModel> getJobs(Specification<JobModel> specification, Pageable pageable) {
        return jobRepository.findAll(specification, pageable);
    }

    public Specification<JobModel> buildSpecification(Map<String, String> filters) {
        Specification<JobModel> specification = Specification.where(null);

        for (Map.Entry<String, String> filterEntry : filters.entrySet()) {
            String field = filterEntry.getKey();
            String value = filterEntry.getValue();

            // Define filter
            Filter filter = new Filter();
            filter.setField(field);
            filter.setOperator(); // Or determine based on filter type
            filter.setValue(value);

            // Add filter to specification
            specification = Specification.where(specification).and(new FilterSpecification<>(filter));
        }

        return specification;
    }

    public JobModel getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public JobModel saveJob(JobModel admin) {
        return jobRepository.save(admin);
    }

    public JobModel updateJob(Long id, JobModel jobModelDetails) {
        JobModel jobModel = jobRepository.findById(id).orElse(null);
        if (jobModel != null) {
            jobModel.setLabel(jobModelDetails.getLabel());
            jobModel.setSector(jobModelDetails.getSector());
            return jobRepository.save(jobModel);
        }
        return null;
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
