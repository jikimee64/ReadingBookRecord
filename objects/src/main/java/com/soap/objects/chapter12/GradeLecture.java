package com.soap.objects.chapter12;

import static java.util.stream.Collectors.joining;

import java.util.List;

public class GradeLecture extends Lecture {
    private List<Grade> grades;

    public GradeLecture(String name, int pass, List<Grade> grades, List<Integer> scores){
        super(pass, name, scores);
        this.grades = grades;
    }

    @Override
    public String evaluate() {
        return super.evaluate() + ", " + gradesStatistics();
    }

    @Override
    public String getEvaluationMethod() {
        return "Grade";
    }

    public double average(String gradeName){
        return grades.stream()
            .filter(each -> each.isName(gradeName))
            .findFirst()
            .map(this::gradeAverage)
            .orElse(0d);
    }

    private double gradeAverage(Grade grade){
        return getScores().stream()
            .filter(grade::include)
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0);
    }

    private String gradesStatistics(){
        return grades.stream()
            .map(this::format)
            .collect(joining(" "));
    }

    private String format(Grade grade){
        return String.format("%s %d", grade.getName(), gradeCount(grade));
    }

    private long gradeCount(Grade grade){
        return getScores().stream()
            .filter(grade::include)
            .count();
    }

}