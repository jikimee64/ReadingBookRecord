package com.soap.object.chapter2;

/**

 * @author 2021.05.28
 * @version 1.0, 작업 내용
 * @see None
 */
public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Bag(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartTime(){
        return whenScreened;
    }

    public boolean isSequence(int sequence){
        return this.sequence = sequence;
    }

    public Money getMovieFee(){
        return movie.getFee();
    }

    public Reservation reserve(Customer customer, int audienceCount){
        return new Reservation(customer, this, caclulateFee(audienceCount), audienceCount);
    }

    // Movie의 calculateMovieFee 메서드의 반환값은 1인당 예매요금
    // 따라서 Screening은 전체 예매 요금을 구하기 위해 calculateMovieFee 메서드의 반환값에 인원수인 audienceCount를 곱한다.
    private Money calculateFee(int audienceCount){
        return movie.calculateMovieFee(this).times(audienceCount);
    }

}
