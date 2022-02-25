package com.software.development.softwaredevelopment.chapter5;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class BusinessRuleEngineTest {

//    @Test
//    void shouldHaveNoRulesInitially() {
//        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
//
//        assertEquals(0, businessRuleEngine.count());
//    }
//
//    @Test
//    void shouldAddTwoActions() {
//        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
//
//        businessRuleEngine.addAction(() -> {
//        });
//        businessRuleEngine.addAction(() -> {
//        });
//
//        assertEquals(2, businessRuleEngine.count());
//    }
//
//    @Test
//    void shouldExecuteOneAction() {
//        //given
//        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
//        final Action mockAciton = mock(Action.class); //Action의 목 생성
//
//        //when
//        businessRuleEngine.addAction(mockAciton);
//        businessRuleEngine.run();
//
//        //then
//        verify(mockAciton).execute(); //메소드 호출 확인
//    }

    @Test
    void shouldExecuteAnActionWithFacts() {
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        verify(mockAction).execute(mockFacts); //메소드 호출 확인

    }

}

