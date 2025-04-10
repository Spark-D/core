package hello.core.member;

public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemberMemoryRepository(); // 배우가 직접 담당 배우를 설정하는것과 같은 거
    private final MemberRepository memberRepository; // 이제 추상화에만 의존하게됨!

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findByIdMember(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
