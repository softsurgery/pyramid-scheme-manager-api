package com.pyramidscheme.mainartifact.modules.job.repositories;

import com.pyramidscheme.mainartifact.modules.job.entities.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobModel, Long> , JpaSpecificationExecutor<JobModel> {}


