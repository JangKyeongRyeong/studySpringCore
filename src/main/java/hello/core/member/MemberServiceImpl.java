package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //DIP 위반 : MemberRepository(추상화),MemoryMemberRepository(구현체) 둘다 의존..
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * DIP 준수
     */
    private final MemberRepository memberRepository;

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
}
