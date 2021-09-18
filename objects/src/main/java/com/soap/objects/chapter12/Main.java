package com.soap.objects.chapter12;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Lecture lecture = new Lecture(70, "객체지향 프로그래밍", Arrays.asList(81, 95, 75, 50, 45));
        String evaluation = lecture.evaluate();

        Lecture lecture1 = new GradeLecture("객체지향 프로그래밍",
            70,
            Arrays.asList(new Grade("A", 100, 95),
                new Grade("B", 94, 80),
                new Grade("C", 79, 70),
                new Grade("D", 69, 50),
                new Grade("F", 49, 0)),
            Arrays.asList(81, 95, 75, 50, 45)
        );
        lecture1.evaluate();

        Professor professor = new Professor("다익스트라", new Lecture(70, "알고리즘", Arrays.asList(81,95,75,50,45)));
        String statistics = professor.compileStatistics();


        Professor professor2 = new Professor("다익스트라", new GradeLecture("객체지향 프로그래밍",
            70,
            Arrays.asList(new Grade("A", 100, 95),
                new Grade("B", 94, 80),
                new Grade("C", 79, 70),
                new Grade("D", 69, 50),
                new Grade("F", 49, 0)),
            Arrays.asList(81, 95, 75, 50, 45)
        ));
        String statistics2 = professor2.compileStatistics();
    }

}
