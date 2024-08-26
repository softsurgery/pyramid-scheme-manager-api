package com.pyramidscheme.mainartifact.common.database;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public class FilterSpecification<T> implements Specification<T> {
    private Filter filter;

    public FilterSpecification(Filter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Path<?> fieldPath = root.get(filter.getField());

        switch (filter.getOperator()) {
            case EQUALS:
                return criteriaBuilder.equal(fieldPath, filter.getValue());
            case NOT_EQUALS:
                return criteriaBuilder.notEqual(fieldPath, filter.getValue());
            case GREATER_THAN:
                return criteriaBuilder.greaterThan((Expression<? extends Comparable>) fieldPath, (Comparable) filter.getValue());
            case LESS_THAN:
                return criteriaBuilder.lessThan((Expression<? extends Comparable>) fieldPath, (Comparable) filter.getValue());
            case GREATER_THAN_OR_EQUALS:
                return criteriaBuilder.greaterThanOrEqualTo((Expression<? extends Comparable>) fieldPath, (Comparable) filter.getValue());
            case LESS_THAN_OR_EQUALS:
                return criteriaBuilder.lessThanOrEqualTo((Expression<? extends Comparable>) fieldPath, (Comparable) filter.getValue());
            case LIKE:
                return criteriaBuilder.like((Expression<String>) fieldPath, "%" + filter.getValue() + "%");
            case IN:
                return fieldPath.in((Collection<?>) filter.getValue());
            case NOT_IN:
                return criteriaBuilder.not(fieldPath.in((Collection<?>) filter.getValue()));
            default:
                return null;
        }
    }

    public void applyColumnSelector(Root<T> root, CriteriaQuery<?> query) {
        if (filter.getColumns() != null && !filter.getColumns().isEmpty()) {
            query.multiselect(filter.getColumns().stream().map(root::get).toArray(Expression[]::new));
        }
    }
}
