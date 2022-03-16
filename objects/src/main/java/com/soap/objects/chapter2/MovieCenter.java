package com.soap.objects.chapter2;

public class MovieCenter {

    public Reservation enter(Movie movie, Screening screening){
        Reservation reservation = new Reservation(
            screening,
            3
        );
        reservation.setTotalPrice(movie);
        if(movie.checkDiscountCondition(reservation)) {
            reservation = movie.checkDiscountPolicy(reservation);
        }
        return reservation;
    }

}