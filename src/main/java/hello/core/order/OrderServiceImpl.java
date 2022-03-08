package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    DIP( Dependency Inversion Principle)위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    /**
     * DIP 준수
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자가 한개일 경우에만 @Autowired 생략 가능
    @Autowired
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

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }


}
