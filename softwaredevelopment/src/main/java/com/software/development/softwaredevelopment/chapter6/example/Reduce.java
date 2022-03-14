package com.software.development.softwaredevelopment.chapter6.example;

import com.software.development.softwaredevelopment.chapter6.domain.Position;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.Twoot;
import java.util.List;
import java.util.function.BinaryOperator;
import javax.swing.Popup;

import static java.util.Comparator.comparingInt;
import static java.util.function.BinaryOperator.maxBy;

public class Reduce {

    private final BinaryOperator<Position> maxPosition = maxBy(comparingInt(Position::getValue));

    Twoot combineTwootsBy(final List<Twoot> twoots, final String senderId, final String newId){
        return twoots
            .stream()
            .reduce(
                new Twoot(newId, senderId, "", Position.INITIAL_POSITION),
                (acc, twoot) -> new Twoot(
                    newId,
                    senderId,
                    twoot.getContent() + acc.getContent(),
                    maxPosition.apply(acc.getPosition(), twoot.getPosition())
                )
            );
    }

}
