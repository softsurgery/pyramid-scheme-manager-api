package com.pyramidscheme.mainartifact.common.database;

import org.springframework.data.jpa.domain.Specification;

public class FilterSpecificationBuilder<T> {
    private Specification<T> specification;

    public FilterSpecificationBuilder(Filter filter) {
        this.specification = new FilterSpecification<>(filter);
    }

    public FilterSpecificationBuilder<T> and(Filter filter) {
        this.specification = Specification.where(specification).and(new FilterSpecification<>(filter));
        return this;
    }

    public Specification<T> build() {
        return specification;
    }
}