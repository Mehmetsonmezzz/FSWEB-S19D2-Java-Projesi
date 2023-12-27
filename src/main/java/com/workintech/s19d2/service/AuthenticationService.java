package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Member register(String email, String password){
      Optional<Member> byEmail= memberRepository.findByEmail(email);
      if(byEmail.isPresent()){
          throw new RuntimeException("User with given email already exist. Email: "+email);
      }
     String encodedPassword= passwordEncoder.encode(password);

        /*Optional<Role> userRole=roleRepository.findByAuthority("USER");
        if (!userRole.isPresent()){
        Role user=new Role();
        user.setAuthority("USER");
        roleRepository.save(user);
        }*/
        List<Role> roleList=new ArrayList<>();
        Optional<Role> adminRole= roleRepository.findByAuthority("ADMIN");
        if (!adminRole.isPresent()){
            Role admin=new Role();
            admin.setAuthority("ADMIN");
            roleList.add(roleRepository.save(admin));
        }
        else {
            roleList.add(adminRole.get());
            //roleList.add(userRole.get());
        }
      //  roleRepository.findByAuthority("ADMIN").get();

        Member member =new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setRoles(roleList);
        return memberRepository.save(member);
    }




}
