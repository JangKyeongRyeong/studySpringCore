package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //DIP 위반 : MemberRepository(추상화),MemoryMemberRepository(구현체) 둘다 의존..
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * DIP 준수
     */
    private final MemberRepository memberRepository;

    // 생성자에 @Autowired 붙혀주면
    // MemberRepository 타입들을 찾아서 자동으로 의존관계 주입해준다.
    @Autowired  // ac.getBean(MemberRepository.class)와 비슷한 동작
    public MemberServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {

        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }


    //테스트 용도
    public MemberRepository getMemberRepository(){

        return memberRepository;
    }

}
