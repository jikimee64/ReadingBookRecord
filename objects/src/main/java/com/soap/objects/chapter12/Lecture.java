package com.soap.objects.chapter12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lecture {

    private int pass;
    private String title;
    private List<Integer> scores = new ArrayList<>();

    public Lecture(int pass, String title, List<Integer> scores) {
        this.pass = pass;
        this.title = title;
        this.scores = scores;
    }

    //getEvaluationMethod : 현재 클래스의 메소드를 호출하는 것이 아니라 현재 객체엑 메시지를 전송하는 것
    //현재 객체 : self 참조가 가리키는 자기 자신
    public String stats(){
        return String.format("Title: %s, Evaluation Method: %s", title, getEvaluationMethod());
    }

    public String getEvaluationMethod(){
        return "Pass or Fail";
    }

    public double average() {
        return scores.stream()
            .mapToInt(Integer::intValue)
            .average().orElse(0);
    }

    //Collections.unmodifiableList : 리스트에 데이터를 추가한 뒤 더 이상 데이터 삭제, 추가를 막기
    public List<Integer> getScores() {
        return Collections.unmodifiableList(scores);
    }

    public String evaluate() {
        return String.format("Pass:%d Fail:%d", passCount(), failCount());
    }

    private long passCount() {
        return scores.stream().filter(score -> score >= pass).count();
    }

    private long failCount() {
        return scores.size() - passCount();
    }

}