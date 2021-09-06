package com.soap.objects.chapter6;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice){
        this.ticketOffice = ticketOffice;
    }

    public TicketOffice getTicketOffice(){
        return ticketOffice;
    }

    public void sellTo(Audience audience){
        ticketOffice.plusAmount(
            audience.buy(ticketOffice.getTicket())
        );

    }
}