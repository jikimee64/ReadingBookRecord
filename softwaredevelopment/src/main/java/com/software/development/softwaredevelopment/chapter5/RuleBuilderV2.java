package com.software.development.softwaredevelopment.chapter5;

public class RuleBuilderV2 {
    private final Condition condition;

    private RuleBuilderV2(final Condition condition) {
        this.condition = condition;
    }

    public static RuleBuilderV2 when(final Condition condition){
        return new RuleBuilderV2(condition);
    }

    public Rule then(final Action action){
        return new DefaultRule(condition, action);
    }

}