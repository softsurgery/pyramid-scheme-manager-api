package com.pyramidscheme.mainartifact.common.migrations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractSeeder<T, ID>  {
    private final JpaRepository<T, ID> repository;

    @Autowired
    public AbstractSeeder(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Transactional
    public void seed(String seedingMessage) {
        if (repository.count() == 0) {
            seedData();
        } else {
            System.out.println(seedingMessage);
        }
    }

    protected abstract boolean seedData();

    protected void saveEntity(T entity) {
        repository.save(entity);
    }
}

