package com.sys.bo.task.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sys.bo.config.string.CosntPage;
import com.sys.bo.task.login.domain.MemberDomain;
import com.sys.bo.task.login.domain.MemberRoleDomain;
import com.sys.bo.task.login.service.LoginService;

import lombok.extern.java.Log;

@Log
@RestController
public class LoginController {

	@Autowired private LoginService loginService;

    // 로그인 페이지
    @GetMapping("/login")
    public ModelAndView dispLogin() {
        ModelAndView mv = new ModelAndView(CosntPage.Common+"/login");
        return mv;
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public ModelAndView dispSignup() {
        ModelAndView mv = new ModelAndView(CosntPage.Common+"/signup");
        return mv;
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public ModelAndView execSignup(MemberDomain mem) {
        String returnId = loginService.saveUser(mem);
        MemberRoleDomain role = new MemberRoleDomain();
        role.setSysopid(returnId);
        loginService.saveRole(role);
        ModelAndView mv = new ModelAndView(CosntPage.Common+"/login");
        return mv;
    }

    // 로그인 결과 페이지
    @GetMapping("/login/result")
    public ModelAndView dispLoginResult() {
        ModelAndView mv = new ModelAndView(CosntPage.Common+"/loginSuccess");
        return mv;
    }

    @PostMapping("/logout")
    public String logout() {
    	return "redirect:/login";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/denied")
    public ModelAndView dispDenied( ) {
    	ModelAndView mv = new ModelAndView(CosntPage.Error+"/denied");
        return mv;
    }


}
