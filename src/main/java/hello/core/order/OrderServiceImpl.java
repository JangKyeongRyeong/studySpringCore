package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    DIP( Dependency Inversion Principle)위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    /**
     * DIP 준수
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 1. 주문생성
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 2. 회원 조회
        Member member = memberRepository.findById(memberId);
        // 3. 할인 적용
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 4. return
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
