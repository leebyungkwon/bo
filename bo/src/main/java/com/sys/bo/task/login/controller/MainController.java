package com.sys.bo.task.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.bo.config.string.CosntPage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value="/bo/main")
public class MainController {

	//@Autowired private CommonService commonService;

	@GetMapping(value="")
	public String main() {

		System.out.println("######로그인 성공########");

		return CosntPage.BoMainPage+"main";
	}

	@GetMapping(value="/getVer")
	public String getVer(Long verId) {
		//VersionEntity versionDomain = new VersionEntity();
		//versionDomain.setVerId(verId);

		//commonService.getVer(versionDomain);
		return CosntPage.BoMainPage+"main";
	}
}
