package com.soap.object.chapter1;

/**
 * 관람객이 가지고 올 수 있는 소지품 인스턴스 변수 추가
 * hasInvitation : 초대장의 보유 여부
 * hasTicket : 티켓의 소유 여부
 * plusAmount / minusAmount : 현금 증가 및 감소
 * setTicket : 초대장을 티켓으로 교환
 * --
 * 이벤트 당첨 관람객 : 현금 + 초대장
 * 이벤트 미당첨 관람객 : 현금
 * 각각의 생성자 추가
 *
 * @author 2020.10.05
 * @version 1.0, 작업 내용
 * @see None
 */
public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    public Bag(long amount){
        this(null, amount);
    }

    public Bag(Invitation invitation, long amount){
        this.invitation = invitation;
        this.amount = amount;
    }

    public boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
