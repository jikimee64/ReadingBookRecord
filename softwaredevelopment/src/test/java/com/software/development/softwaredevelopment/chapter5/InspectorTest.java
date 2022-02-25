package com.software.development.softwaredevelopment.chapter5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class InspectorTest {

    @Test
    void inspectOneConditionEvaluatesTrue(){
        final Facts facts = new Facts();
        facts.setFacts("jobTitle", "CEO");
        final List<ConditionalAction> conditionalActionList = new ArrayList<>();
        final ConditionalAction conditionalAction = new JobTitleCondition();
        conditionalActionList.add(conditionalAction);
        final Inspector inspector = new Inspector(conditionalActionList);

        final List<Report> reportList = inspector.inspect(facts);

        assertEquals(1, reportList.size());
    }

    private static class JobTitleCondition implements ConditionalAction {

        @Override
        public void perform(Facts facts) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean evaluate(Facts facts) {
            return "CEO".equals(facts.getFacts("jobTitle"));
        }
    }

}