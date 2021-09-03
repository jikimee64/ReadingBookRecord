package com.soap.objects.chapter4;

import lombok.Getter;
import lombok.Setter;

/**
 * 문제 점이 많은 코드
 * 1. 코드 중복 확률이 높음
 * 2. 인스턴스 변수가 바뀌면 get,set 메서드를 수정해야한다.
 */
@Getter
@Setter
public class Rectangle {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Rectangle(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    // 2가지의 문제 해결법(캡슐화)
    // 객체 내부에서 가로와 세로길이를 변경
    public void enlarge(int multiple){
        right *= multiple;
        bottom *= multiple;
    }

}

//문제점1과 관련(외부에서 Rectangle의 가로와 세로 길이를 변경)
class Anyclass{
    void anyMethod(Rectangle rectangle, int multiple){
        rectangle.setRight(rectangle.getRight() * multiple);
        rectangle.setBottom(rectangle.getBottom() * multiple);
    }
}