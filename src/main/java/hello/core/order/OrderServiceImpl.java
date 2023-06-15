package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //새로운 할인 정책인 RateDiscountPolicy를 적용하려먼 이 코드를 변경해야 한다.
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private DiscountPolicy discountPolicy;  //인터페이스에만 의존

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //OrderService 입장에서는 할인에 대한 것은 모르겠고 DiscountPolicy가 알아서 해줘! 하고 설계한 것. 단일책임의 원칙

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
