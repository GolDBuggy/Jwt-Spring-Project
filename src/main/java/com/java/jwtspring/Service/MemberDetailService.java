package com.java.jwtspring.Service;

import com.java.jwtspring.Model.Member;
import com.java.jwtspring.Repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepo memberRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member=memberRepo.findByUsername(username);

        return new User(member.getUsername(),member.getPassword(), Arrays.asList(member.getRoles().split(",")).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

}
