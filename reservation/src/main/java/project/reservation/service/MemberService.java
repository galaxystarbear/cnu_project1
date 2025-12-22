package project.reservation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.reservation.domain.Member;
import project.reservation.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    public boolean login(String inputid, String inputPass) {
        Optional<Member> member = memberRepository.findByName(inputid);
        if (member.isPresent() && member.get().getName().equals(inputid) && member.get().getPass().equals(inputPass)) {
            memberRepository.save(member.get());
            return true;
        }
        return false;
    }
    
    public void changePassword(String id, String pass, String newPass) {
        Optional<Member> member = memberRepository.findByName(id);
        if (member.isPresent() && member.get().getPass().equals(pass)) {
            member.get().setPass(newPass);
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
