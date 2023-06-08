package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //여기서 문제는 OCP와 DIP가 지켜지지 않는다는 것이다.
    //MemberServiceImpl은 MemberRepository에도 의존하고 구현체인 MemoryMemberRepository에도 의존한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
