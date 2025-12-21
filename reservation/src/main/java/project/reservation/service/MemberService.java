package project.reservation.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.reservation.domain.Member;
import project.reservation.repository.MemberRepository;
import project.reservation.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean login(String inputid, String inputPass) {
        Optional<Member> member = memberRepository.findByName(inputid);
        if (member.isPresent() && passwordEncoder.matches(inputPass, member.get().getEncodedPass())) {
            return true;
        }
        return false;
    }
    
    public void changePassword(String id, String pass, String newPass) {
        Optional<Member> member = memberRepository.findByName(id);
        if (member.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        if (!passwordEncoder.matches(pass, member.get().getEncodedPass())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
        String newEncodedPass = passwordEncoder.encode(newPass);
        member.get().setEncodedPass(newEncodedPass);
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
