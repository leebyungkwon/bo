<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">

var Grid = Object.create(GRID); 

Page.setup = function(){};
Page.bind = function(){};
Page.load = function(){

	let url = "/bo/prd/list";
	var g_id1 = KTAB.mid+"_gird1";
	
	Grid.set({
		  id		: g_id1
		, area		: "grid_area1"
  		, url		: url
	    , width		: "100%"
	    , check		: true
  		, headCol	: ["상품번호", "상품명", "상품상태", "정가","판매가","등록일"]
  		, bodyCol	: 
  			[
				{type:"string"	, name:'prdNo'		, index:'prdNo'		, width:"10px"	, id:true}
				,{type:"string"	, name:'prdNm'		, index:'prdNm'		, width:"20%"	, align:"center"}
				,{type:"string"	, name:'prdSts'		, index:'prdSts'	, width:"10%"	, align:"center"}
				,{type:"string"	, name:'norPrc'		, index:'norPrc'	, width:"10%"	, align:"center"}		
				,{type:"string"	, name:'salPrc'		, index:'salPrc'	, width:"10%"	, align:"center"}			
				,{type:"string"	, name:'regDate'	, index:'regDate'	, width:"20%"	, sortable:false}	
				//,{type:"string"	, name:'btn'		, index:'btn'			, width:"20%"	, align:"center", button:{"수정": "modify","지우기": "del"} }
			]
		, sortTable	: true
		, sortNm : "prd_no"
		, sort : "DESC"
		, rowClick	: {/* color:"#ccc",  */retFunc : detailPop}
		, gridSearch : "search,searchBtn"
		, isPaging : true
		, tableBtn	: {
			button : {
				 "등록" : {retFunc : fnBtn}
				,"수정" : {retFunc : fnBtn}
			}
		  }
		, excel : url
		, excelFileNm : "템플릿"
		, size : 10
	});
}
function fnBtn(){
	var p = {
			url : '/bo/prd/det'
	}
	LibUtil.openPopup(p);
}
function fnCustom(){
	return "aaaaaaaaa";	
}
function detail(idx, data){
	location.href = "/bo/templete/templeteSave?id="+data.id;
}
function detailPop(idx, data){
	let p = {
		id : "testReg"
		, data : data
		, url : "/bo/templete/p/templeteSave"
	}
	LibUtil.openContent(p);
}
</script>
	<div class="k_content">
		<div class="k_search" id="search">
			<div class="searchbx">
				<table style="float:left;">
					<colgroup>
						<col width="5%"/>
						<col width="10%"/>
						<col width="5%"/>
						<col width="10%"/>
						<col width="5%"/>
						<col width="10%"/>
						<col width="5%"/>
						<col width="10%"/>
					</colgroup>
					<tbody>
						<tr>
							<td class="tbh">
								<span class="red">상품명</span>
							</td>
							<td><input type="text" class="intxt" name="prdNm" class="" value=""></td>
							<td class="tbh">
								<span class="red">상품상세</span>
							</td>
							<td><input type="text" class="intxt" name="prdDesc" class=""></td>
							<td class="tbh">
								<span class="red">상태</span>
							</td>
							<td>
								<select>
									<option>전체</option>
									<option>판매중</option>
									<option>품절</option>
									<option>단종</option>
								</select>
							</td>
							<td class="tbh">
								<span class="red">사용여부</span>
							</td>
							<td colspan="5">
								
								<input type="radio" value="예"/> 예
								<input type="radio" value="아니오"/> 아니오
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="schBtnBx">
				<button type="button" class="btn gridSearch" id="searchBtn">조회</button>
			</div>
		</div>
		<div id="grid_area1"></div>
	</div>