package com.pyramidscheme.mainartifact.common.database;

import lombok.Data;

import java.util.List;

@Data
public class Filter {
    private String field;
    private FilterOperator operator;
    private Object value;
    private List<String> columns;
}
