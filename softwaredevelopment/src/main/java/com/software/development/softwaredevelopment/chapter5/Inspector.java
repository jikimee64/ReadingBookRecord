package com.software.development.softwaredevelopment.chapter5;

import java.util.ArrayList;
import java.util.List;

public class Inspector {

    private final List<ConditionalAction> conditionalActionList;

    public Inspector(List<ConditionalAction> conditionalActionList) {
        this.conditionalActionList = conditionalActionList;
    }

    public List<Report> inspect(final Facts facts){
        final List<Report> reportList = new ArrayList<>();
        for(ConditionalAction conditionalAction: conditionalActionList){
            final boolean conditionResult = conditionalAction.evaluate(facts);
            reportList.add(new Report(facts, conditionalAction, conditionResult));
        }
        return reportList;
    }

}