package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 붙은 필드만 생성자를 만들어준다.
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증 로직
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 문제가 있다면 예외가 발생하게 동작
        /**
         * 2명이 동시에 회원가입을 하게 되면 로직을 통과 후 문제가 발생하기 때문에
         * db의 이름을 unique로 설정해주는 게 좋다.(이중 방어)
         */
        List<Member> findMembers = memberRepository.findByName(member.getName());
        /**
         * 이 방법보단 멤버를 찾았는데 해당 멤버의 수가 0보다 크면 예외를 던지는게 최적화할 수 있음
         */
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member one = memberRepository.findOne(id);
        one.setName(name);
    }
}
