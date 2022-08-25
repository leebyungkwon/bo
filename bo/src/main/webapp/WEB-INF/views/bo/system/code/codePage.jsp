<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">

var Grid1 = Object.create(GRID); 
var Grid2 = Object.create(GRID); 

Page.setup = function(){};
Page.bind = function(){};

Page.load = function(){
	let url1 = "/bo/system/code/mstList";
	var g_id1 = KTAB.mid+"_gird1";
	Grid1.set({
		  id		: g_id1
		, area		: "grid_area1"
  		, url		: url1
	    , width		: "100%"
	    , check		: true
  		, headCol	: ["코드번호", "상위코드", "상위코드명", "상위코드설명"]
  		, bodyCol	: 
  			[
				{type:"string"	, name:'cdMstNo'		, index:'cdMstNo'	, width:"10px"	, id:true , hidden:true}
				,{type:"string"	, name:'cdMstCd'		, index:'cdMstCd'	, width:"20%"	, align:"center"}
				,{type:"string"	, name:'cdMstNm'		, index:'cdMstNm'	, width:"40%"	, align:"center"}
				,{type:"string"	, name:'cdMstDesc'		, index:'cdMstDesc'	, width:"40%"	, align:"center"}		
			]
		, sortTable	: true
		, sortNm : "prd_no"
		, sort : "DESC"
		, rowClick	: {/* color:"#ccc",  */retFunc : detailPop}
		, gridSearch : "search1,searchBtn1"
		, isPaging : true
		, tableBtn	: {
			button : {
				 "등록" : {retFunc : fnMstReg}
			}
		  }
		, excel : url1
		, excelFileNm : "템플릿"
		, size : 10
	});

	let url2 = "/bo/system/code/detList";
	var g_id2 = KTAB.mid+"_gird2";
	Grid2.set({
		  id		: g_id2
		, area		: "grid_area2"
  		, url		: url2
	    , width		: "100%"
	    , check		: true
  		, headCol	: ["코드번호", "상위코드", "상위코드명", "상위코드설명", "사용여부"]
  		, bodyCol	: 
  			[
				{type:"string"	, name:'cdMstNo'		, index:'cdMstNo'	, width:"10px"	, id:true , hidden:true}
				,{type:"string"	, name:'cdMstCd'		, index:'cdMstCd'	, width:"20%"	, align:"center"}
				,{type:"string"	, name:'cdMstNm'		, index:'cdMstNm'	, width:"30%"	, align:"center"}
				,{type:"string"	, name:'cdMstDesc'		, index:'cdMstDesc'	, width:"40%"	, align:"center"}
				,{type:"string"	, name:'useYn'			, index:'useYn'		, width:"10%"	, align:"center"}			
			]
		, sortTable	: true
		, sortNm : "prd_no"
		, sort : "DESC"
		, rowClick	: {/* color:"#ccc",  */retFunc : detailPop}
		, gridSearch : "search2,searchBtn2"
		, isPaging : true
		, tableBtn	: {
			button : {
				 "등록" : {retFunc : fnMstReg}
			}
		  }
		, excel : url2
		, excelFileNm : "템플릿"
		, size : 10
		, initTable : false
	});
}
function fnMstReg(){
	let p = {
		id : "codeDet"
		, param : ""
		, url : "/bo/system/code/mst"
		, title : "상위코드등록"
	}
	LibUtil.openContent(p);
}
function fnCustom(){
	return "aaaaaaaaa";	
}
function detail(idx, data){
	location.href = "/bo/templete/templeteSave?id="+data.id;
}
function detailPop(idx, data){
	let p = {
		id : "codeDet"
		, data : data
		, url : "/bo/templete/p/templeteSave"
	}
	LibUtil.openContent(p);
}
</script>
	<div class="k_content" style="width:40%; float:left;">
		<div class="k_search" id="search1">
			<div class="searchbx">
				<table style="float:left;">
					<colgroup>
						<col width="10%"/>
						<col width="20%"/>
						<col width="20%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="20%"/>
					</colgroup>
					<tbody>
						<tr>
							<td class="tbh">
								<span class="red">상위코드</span>
							</td>
							<td><input type="text" class="intxt w70" name="cdMstCd" class="" value=""></td>
							<td class="tbh">
								<span class="red">상위코드명</span>
							</td>
							<td><input type="text" class="intxt w70" name="cdMstNm" class=""></td>
							<td class="tbh">
								<span class="red">사용여부</span>
							</td>
							<td>
								<select name="useYn">
									<option>전체</option>
									<option value="Y">예</option>
									<option value="N">아니오</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="schBtnBx">
				<button type="button" class="btn gridSearch" id="searchBtn1">조회</button>
			</div>
		</div>
		<div id="grid_area1" ></div>
	</div>
	<div class="k_content" style="width:59%; float:right;">
		<div class="k_search" id="search2">
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
				<button type="button" class="btn gridSearch" id="searchBtn2">조회</button>
			</div>
		</div>
		<div id="grid_area2"></div>
	</div>