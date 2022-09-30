package com.java.jwtspring.Repo;

import com.java.jwtspring.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member,String> {

    Member findByUsername(String username);
}
