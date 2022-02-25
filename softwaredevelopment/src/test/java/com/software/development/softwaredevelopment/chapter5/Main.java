package com.software.development.softwaredevelopment.chapter5;

public class Main {
    public static void main(String[] args) {

        Rule rule = new RuleBuilder()
            .when(facts -> "CEO".equals(facts.getFacts("jonTitle")))
            .then(facts -> {
                var name = facts.getFacts("name");
                //Mailer.sendEmail("ss", "ss: " + name);
            })
            .createRule();

    }
}