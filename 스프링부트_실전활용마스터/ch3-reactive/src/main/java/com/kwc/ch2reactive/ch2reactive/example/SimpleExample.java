package com.kwc.ch2reactive.ch2reactive.example;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://spring.io/blog/2019/04/16/flight-of-the-flux-2-debugging-caveats
/* 스택트레이스를 보면 스레드 시작전에 어느경로를 타고 리스트가 생성되었는지 나오지 않는다.,
   이는 리스트에서 5번째 원소를 가져오는 스레드와 리스트를 생성한 스레드가 다르기 때문이다.
   이를 해결하기 위해 리액터의 Hooks.onOperatorDebug() 메서드
* */
public class SimpleExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // 긴 List와 짧은 List를 임의로 생성
        List<Integer> source;
        if(new Random().nextBoolean()) {
            source = IntStream.range(1, 11).boxed() //
                .collect(Collectors.toList());
        }else{
            source = Arrays.asList(1,2,3,4);
        }

        //List를 생성한 스레드가 아닌 다른 스레드에서 람다식을 통해 List의 5번째 원소를 추출
        try{
            executor.submit( () -> source.get(5)).get();
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }finally{
            executor.shutdown();
        }
    }
}
