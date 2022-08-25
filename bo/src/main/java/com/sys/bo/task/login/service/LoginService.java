package com.sys.bo.task.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.bo.task.login.domain.MemberDomain;
import com.sys.bo.task.login.domain.MemberRoleDomain;
import com.sys.bo.task.login.mapper.MemberMapper;
import com.sys.bo.task.login.mapper.MemberRoleMapper;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private MemberMapper memberRepository;

	@Autowired
	private MemberRoleMapper memberRoleRepository;


    @Transactional
    public String saveUser(MemberDomain memberDomain) {
        // 비밀번호 암호화
    	/*
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	memberRepository.setPassword(passwordEncoder.encode(memberDomain.getPassword()));

    	long returnSeq = memberRepository.save(memberDomain).getMemberNo();
    	*/
        return "";
    }

    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
    	MemberDomain result = memberRepository.findById(memberId);

		// 권한 조회
		List<MemberRoleDomain> roleList = memberRoleRepository.getMemberRoles(memberId);
		if(roleList.size() > 0 && !roleList.isEmpty()) {
			result.setRoles(roleList);
		}

		return
			Optional.ofNullable(result)
			.filter(member -> member!= null)
			.map(member -> new SecurityMember((MemberDomain) member)).get();
	}

	public void saveRole(MemberRoleDomain role) {
		role.setRole("MEMBER");
		//memberRoleRepository.save(role);
	}

}