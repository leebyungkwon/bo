package com.sys.bo.task.system.controller;

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
import com.sys.bo.task.system.entity.CodeDetEntity;
import com.sys.bo.task.system.entity.CodeMstEntity;
import com.sys.bo.task.system.service.CodeService;
import com.sys.bo.util.UtilFile;
import com.sys.bo.util.excel.Excel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/bo/system/code")
public class CodeController {
	
	@Autowired private CodeService codeService;

	@Autowired UtilFile utilFile;
	
	@GetMapping(value="/list")
	public ModelAndView codePage() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoSystemPage+"/codePage");
        return mv;
	}

	@GetMapping(value="/mst")
	public ModelAndView codeDet() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoSystemPage+"/p/codeMst");
        return mv;
	}
	
	@PostMapping(value="/mstList")
	public ResponseEntity<ResponseMsg> mstList(CodeMstEntity code , HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, IOException{
		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null);
		List<CodeMstEntity> p = codeService.mstFindById(code);
		if("false".equals(code.getIsPaging())) {
			new Excel<CodeMstEntity>().downLoad(p, CodeMstEntity.class, response.getOutputStream());
			return null;
		}else {
			responseMsg.setData(p);
			return new ResponseEntity<ResponseMsg>(responseMsg ,HttpStatus.OK);	
		}
	}
	
	@PostMapping(value="/detList")
	public ResponseEntity<ResponseMsg> detList(CodeMstEntity code , HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, IOException{
		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null);
		List<CodeDetEntity> p = codeService.findByCodeMst(code);
		if("false".equals(code.getIsPaging())) {
			new Excel<CodeDetEntity>().downLoad(p, CodeDetEntity.class, response.getOutputStream());
			return null;
		}else {
			responseMsg.setData(p);
			return new ResponseEntity<ResponseMsg>(responseMsg ,HttpStatus.OK);	
		}
	}
	
	@PostMapping(value="/mstSave")
	public ResponseEntity<ResponseMsg> mstSave(CodeMstEntity code , HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, IOException{
		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null);
		responseMsg = codeService.codeMstSave(code);
		return new ResponseEntity<ResponseMsg>(responseMsg ,HttpStatus.OK);
	}
}
