package com.software.development.softwaredevelopment.chapter5;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleEngineV2 {

    private final List<Rule> rules;
    private final Facts facts;

    public BusinessRuleEngineV2(List<Rule> rules, Facts facts) {
        this.rules = new ArrayList<>();
        this.facts = facts;
    }

    public void addRule(final Rule rule){
        this.rules.add(rule);
    }

    public void run(){
        this.rules.forEach(rule -> rule.perform(facts));
    }

}