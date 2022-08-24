package com.sys.bo.task.login.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sys.bo.task.login.entity.Member;
import com.sys.bo.task.login.mapper.MemberMapper;
import com.sys.bo.task.login.mapper.MemberRoleMapper;
import com.sys.bo.task.member.entity.MemberEntity;
import com.sys.bo.task.member.entity.MemberRoleEntity;

@Service
public class LoginService implements UserDetailsService {
    
	@Autowired private MemberMapper memberRepository;
	@Autowired private MemberRoleMapper memberRoleRepository;
    
    MemberEntity member;
    @Transactional
    public Long saveUser(MemberEntity memberEntity) {
        // 비밀번호 암호화
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	memberEntity.setPassword(passwordEncoder.encode(memberEntity.getPassword()));

        return memberRepository.save(memberEntity).getMemberNo();
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return 
			Optional.ofNullable(memberRepository.findByEmail(email))
			.filter(member -> member!= null)
			.map(member -> new Member((MemberEntity) member)).get();
	}

	public void saveRole(MemberRoleEntity role) {
		role.setRoleName("MEMBER");
		memberRoleRepository.save(role);
	}

}