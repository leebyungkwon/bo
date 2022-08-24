package com.sys.bo.task.prd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sys.bo.config.message.ResponseMsg;
import com.sys.bo.config.string.CosntPage;
import com.sys.bo.task.prd.entity.PrdDomain;
import com.sys.bo.task.prd.service.PrdService;
import com.sys.bo.util.UtilFile;
import com.sys.bo.util.excel.Excel;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/bo/prd")
public class PrdController {
	
	@Autowired private PrdService prdService;

	@Autowired UtilFile utilFile;
	
	@GetMapping(value="/list")
	public ModelAndView prdPage() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoPrdPage+"/prdPage");
        return mv;
	}

	@GetMapping(value="/det")
	public ModelAndView prdDet() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoPrdPage+"/prdDet");
        return mv;
	}
	
	@PostMapping(value="/list")
	public ResponseEntity<ResponseMsg> list(PrdDomain prd , HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, IOException{
		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null);
		List<PrdDomain> p = prdService.selectPrd(prd);
		if("false".equals(prd.getIsPaging())) {
			new Excel<PrdDomain>().downLoad(p, PrdDomain.class, response.getOutputStream());
			return null;
		}else {
			responseMsg.setData(p);
			return new ResponseEntity<ResponseMsg>(responseMsg ,HttpStatus.OK);	
		}
	}
}
