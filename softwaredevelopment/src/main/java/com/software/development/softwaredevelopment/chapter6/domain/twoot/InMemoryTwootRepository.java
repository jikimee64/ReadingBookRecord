package com.software.development.softwaredevelopment.chapter6.domain.twoot;

import com.software.development.softwaredevelopment.chapter6.domain.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class InMemoryTwootRepository implements TwootRepository{

    private final List<Twoot> twoots = new ArrayList<>();
    private Position currentPosition = Position.INITIAL_POSITION;

    @Override
    public Twoot add(String id, String userId, String content) {
        currentPosition = currentPosition.next();

        var twootPosition = currentPosition;
        var twoot = new Twoot(id, userId, content, twootPosition);
        twoots.add(twoot);
        return twoot;
    }

    @Override
    public Optional<Twoot> get(String id) {
        return Optional.empty();
    }

    @Override
    public void delete(Twoot twoot) {

    }

    @Override
    public void query(TwootQuery twootQuery, Consumer<Twoot> callback) {

    }

    @Override
    public void clear() {
        twoots.clear();
    }

}