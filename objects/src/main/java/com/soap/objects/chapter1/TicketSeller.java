package com.soap.objects.chapter1;

/**
 * 초대장을 티켓으로 교환해 주거나 티켓을 판매하는 역할을 수행하는 클래스
 *
 * @author 2020.10.05
 * @version 1.0, 작업 내용
 */
public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice){
        this.ticketOffice = ticketOffice;
    }

    public TicketOffice getTicketOffice(){
        return ticketOffice;
    }
}
