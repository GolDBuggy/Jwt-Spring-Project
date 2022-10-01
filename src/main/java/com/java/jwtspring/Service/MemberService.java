package com.java.jwtspring.Service;

import com.java.jwtspring.Model.Member;
import com.java.jwtspring.Repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo repo;
    private final BCryptPasswordEncoder encoder;

    private static String DEFAULT_ROLE="ROLE_MEMBER";

    public void save(Member member){
        member.setRoles(DEFAULT_ROLE);
        member.setPassword(encoder.encode(member.getPassword()));
        repo.save(member);
    }

}
