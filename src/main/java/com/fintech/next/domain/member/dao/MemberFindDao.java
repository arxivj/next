package com.fintech.next.domain.member.dao;

import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.exception.EmailNotFoundException;
import com.fintech.next.domain.member.exception.MemberNotFoundException;
import com.fintech.next.domain.model.Email;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MemberFindDao {
    private final MemberRepository memberRepository;

    public MemberFindDao(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findById(final Long id) {
        final Optional<Member> member = memberRepository.findById(id); //Optional<Member>는 Wrapper Class
        member.orElseThrow(() -> new MemberNotFoundException(id));
        return member.get(); //따라서 get()을 호출하거나 다른 로직을 수행해야 한다
// final Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
// 위 코드는 같은 로직이지만 Member 객체를 직접 반환하므로 좀 더 간결하게 작성됐다
    }

    public Member findByEmail(final Email email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException(email.getValue()));
    }

}
