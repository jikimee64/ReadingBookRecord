package com.software.development.softwaredevelopment.chapter4.bookcode4;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

public class Query implements Predicate<Document> {

    private final Map<String, String> clauses;

    private Query(final Map<String, String> clauses) {
        this.clauses = clauses;
    }

    static Query parse(final String query) {
        return new Query(Arrays.stream(query.split(","))
            .map(str -> str.split(":"))
            .collect(toMap(x -> x[0], x -> x[1])));
    }

    @Override
    public boolean test(Document document) {
        return clauses.entrySet()
            .stream()
            .allMatch(entry -> {
                final String documentValue = document.getAttribute(entry.getKey());
                final String queryValue = entry.getValue();
                return documentValue != null && documentValue.contains(queryValue);
            });
    }

}