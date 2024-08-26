package com.pyramidscheme.mainartifact.common.migrations.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyramidscheme.mainartifact.common.migrations.AbstractSeeder;
import com.pyramidscheme.mainartifact.modules.job.entities.JobModel;
import com.pyramidscheme.mainartifact.modules.job.repositories.JobRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class JobSeeder extends AbstractSeeder<JobModel, Long> {
    @Autowired
    public JobSeeder(JobRepository jobRepository) {
        super(jobRepository);
    }

    @Override
    public boolean seedData() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = new ClassPathResource("job.data.json").getInputStream()) {
            List<JobModel> jobs = mapper.readValue(inputStream, new TypeReference<List<JobModel>>() {});
            jobs.forEach(this::saveEntity);
            System.out.println("Jobs seeded.");
            return true;
        } catch (IOException e) {
            System.out.println("Unable to seed jobs: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error occurred while seeding jobs.");
            return false;
        }
    }
}
