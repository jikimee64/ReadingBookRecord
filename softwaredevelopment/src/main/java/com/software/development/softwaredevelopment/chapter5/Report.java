package com.software.development.softwaredevelopment.chapter5;

public class Report {

    private final Facts facts;
    private final ConditionalAction conditionalAction;

    private final boolean isPositive;

    public Report(Facts facts,
        ConditionalAction conditionalAction, boolean isPositive) {
        this.facts = facts;
        this.conditionalAction = conditionalAction;
        this.isPositive = isPositive;
    }

    public ConditionalAction getConditionalAction() {
        return conditionalAction;
    }

    public Facts getFacts() {
        return facts;
    }

    public boolean isPositive() {
        return isPositive;
    }

    @Override
    public String toString() {
        return "Report{" + "conditionalAction=" + conditionalAction + ", facts=" + facts
            + ", isPositive=" + isPositive + '}';
    }
}
