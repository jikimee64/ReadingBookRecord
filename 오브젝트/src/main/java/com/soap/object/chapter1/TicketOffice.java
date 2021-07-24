package com.soap.object.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 관람객에게 판매할 티켓과 티켓의 판매금액을 보관하는 인스턴스 변수 작성
 * getTicket : 티켓을 판매
 * plusAmount / minusAmount : 판매금액 증가 및 감소
 *
 * @author 2020.10.05
 * @version 1.0, 작업 내용
 * @see None
 */
public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, Ticket ... tickets){
        this.amount -= amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    /*
     * tickets.remove(0); 맨 첫번째 위치의 Ticket 반환
     * ArrayList.remove(int index)
     * 인자로 전달된 인덱스 위치의 아이템을 리스트에서 삭제하고 그 객체를 리턴
     * public E remove(int index)
     */
    public Ticket getTicket(){
        return tickets.remove(0);
    }

    public void minusAmount(Long amount){
        this.amount -= amount;
    }

    public void plusAmount(Long amount){
        this.amount += amount;
    }
}
