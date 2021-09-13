package com.soap.objects.chapter9.servicelocation;

import com.soap.objects.chapter2.DiscountPolicy;

//의존성을 해결할 객체들을 보관하는 일종의 저장소
//(외부에서 객체에게 의존성을 전달하는 의존성 주입과는 반대)
//단점 : 필자는 비추, 의존성을 감춘다는 단점(코드를 직접 까봐야 안다는 것(런타임 시점에 의존성 주입이 안된걸 알고있음)
//단점2 : 단위 테스트작성 어려움( 만들어진 DiscountPolicy 객체가 공유됨 : 각 테스트는 고립되어야 한다는 원칙 위반)
public class ServiceLocator {

    private static ServiceLocator soleInstance = new ServiceLocator();
    private DiscountPolicy discountPolciy;

    public static DiscountPolicy discountPolicy() {
        return soleInstance.discountPolciy;
    }

    public static void provide(DiscountPolicy discountPolicy) {
        soleInstance.discountPolciy = discountPolicy;
    }

    private ServiceLocator() {
    }
}
