package com.java.jwtspring.Controller;

import com.java.jwtspring.Model.AuthRequest;
import com.java.jwtspring.Model.Member;
import com.java.jwtspring.Service.JwtService;
import com.java.jwtspring.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/save")
    public ResponseEntity<Member> save(@RequestBody Member member){
        memberService.save(member);
        return ResponseEntity.ok(member);
    }


    @PostMapping("/generate")
    public ResponseEntity<String> tokenGenerate(@RequestBody AuthRequest authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),authRequest.getPassword()));
        return ResponseEntity.ok(jwtService.generateToken(authRequest.getUsername()));
    }

    @GetMapping("/")
    public ResponseEntity<String> welcMessage(){
        return ResponseEntity.ok("Hoşgeldiniz!");
    }
}
