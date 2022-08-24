package com.sys.bo.task.templete.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sys.bo.common.mapper.FileEntity;
import com.sys.bo.config.message.ResponseMsg;
import com.sys.bo.config.string.CosntPage;
import com.sys.bo.task.board.entity.BoardDomain;
import com.sys.bo.task.board.entity.BoardEntity;
import com.sys.bo.task.templete.service.TempleteService;
import com.sys.bo.util.UtilFile;
import com.sys.bo.util.excel.Excel;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value="/bo/templete")
public class TempleteController {
	
	@Autowired private TempleteService templeteService;

	@Autowired UtilFile utilFile;
	
	@GetMapping(value="/templetePage")
	public ModelAndView templetePage() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoTempletePage+"/templetePage");
        return mv;
	}
	
	@GetMapping(value="/templete2")
	public ModelAndView templete2() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoTempletePage+"/templete2");
        return mv;
	}
	
	@GetMapping(value="/templete")
	public ModelAndView templete() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoTempletePage+"/templete");
        return mv;
	}
	
	@PostMapping(value="/templete")
	public ModelAndView templeteData() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoTempletePage+"/templete");
        return mv;
	}
	@PostMapping(value="/templete2")
	public ModelAndView templete2Data() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoTempletePage+"/templete2");
        return mv;
	}
	@PostMapping(value="/listJpa")
	public ResponseEntity<ResponseMsg> listJpa(HttpServletRequest request, BoardEntity board, Pageable pageable){
		Long logId = (Long) request.getAttribute("lid");
		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null );
		responseMsg.setLogId(logId);
    	responseMsg.setData(templeteService.findAll(board, pageable));
    	responseMsg.setLogId(logId);
		return new ResponseEntity<ResponseMsg>(responseMsg ,HttpStatus.OK);
	}
	
	@PostMapping(value="/list")
	public ResponseEntity<ResponseMsg> list(BoardDomain board , HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, IOException{
		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null);
		List<BoardDomain> b = templeteService.selectTemplete(board);
		if("false".equals(board.getIsPaging())) {
			new Excel<BoardDomain>().downLoad(b, BoardDomain.class, response.getOutputStream());
			return null;
		}else {
			responseMsg.setData(b);
			return new ResponseEntity<ResponseMsg>(responseMsg ,HttpStatus.OK);	
		}
	}
	
	@GetMapping(value="/templeteSave")
	public ModelAndView templeteSavePage(BoardEntity board) {
    	ModelAndView mv = new ModelAndView(CosntPage.BoTempletePage+"/templeteSave");
    	String type = "NOTICE";
    	if(null != board.getBoardNo()) {
    		BoardEntity b = templeteService.findById(board).get();
    		mv.addObject("board", b);
    		type = b.getBoardType();
    	}
		mv.addObject("type", type);
        return mv;
	}
	
	@GetMapping("/p/templeteSave")
    public ModelAndView templeteSavePopup() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoTempletePage+"/p/popTempleteSave");
        return mv;
    }

	@GetMapping("/p/templeteFileSave")
    public ModelAndView templeteFileSave() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoTempletePage+"/p/popTempleteFileSave");
        return mv;
    }
	
	@PostMapping("/templeteSave")
	public ResponseEntity<ResponseMsg> templeteSave(BoardEntity board) {
		ResponseMsg responseMsg = templeteService.templeteSave(board);
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

	
	@PostMapping("/templeteFileSave")
	public ResponseEntity<ResponseMsg> templeteFileSave(BoardDomain board, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) 
			throws IllegalStateException, IOException {
		
		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null );
		if(0<files.length){
			//List<FileEntity> f = uFile.uploadFiles(files, "test");
			FileEntity entity = new FileEntity();
			Map<String, Object> result = utilFile.setPath("test")
							.setFiles(files)
							.upload();
        }
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}
	
	@PostMapping("/excel")
	public ResponseEntity<ResponseMsg> readExcel(@RequestParam("files") MultipartFile[] files, Model model) throws IOException { 

		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null );

		Map<String, Object> ret = utilFile.setPath("test")
				.setFiles(files)
				.setExt("excel")
				.upload();
		System.out.println("## file");
		System.out.println(ret);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		if((boolean) ret.get("success")) {
			List<FileEntity> file = (List<FileEntity>) ret.get("data");
			System.out.println(file);
			for(FileEntity exc : file) {
				String path = Paths.get(exc.getFilePath(), exc.getFileSaveNm()+"."+exc.getFileExt()).toString();
				//result = new UtilExcel().upload(path);
			}
		}
	
    	responseMsg.setData(result);
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

}
