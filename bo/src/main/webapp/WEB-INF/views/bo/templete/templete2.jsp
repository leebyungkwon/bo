<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">

var Grid = Object.create(GRID); 

Page.setup = function(){}
Page.bind = function(){}
Page.load = function(){

	let url = "/bo/templete/list";
	Grid.set({
		  id		: "grid"
  		, url		: url
	    , width		: "100%"
	    , check		: true
  		, headCol	: ["번호", "타입", "제목", "등록시간"]
  		, bodyCol	: 
  			[
				{type:"string"	, name:'boardNo'	, index:'boardNo'		, width:"10px"	, id:true}
				,{type:"string"	, name:'boardType'	, index:'boardType'		, width:"20%"	, align:"center"}
				,{type:"string"	, name:'boardTitle'	, index:'boardTitle'	, width:"40%"	, align:"center"}		
				,{type:"string"	, name:'regDate'	, index:'regDate'		, width:"30%"	, sortable:false, custom:fnCustom}	
			]
		, sortTable	: true
		, sortNm : "board_no"
		, sort : "DESC"
		, rowClick	: {/* color:"#ccc",  */retFunc : detailPop}
		, gridSearch : "search,searchBtn"
		, isPaging : true
		, excel : url
		, excelFileNm : "템플릿"
		, size : 10
	});
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
								<span class="red">제목</span>
							</td>
							<td><input type="text" class="intxt" name="boardTitle" class="" value=""></td>
							<td class="tbh">
								<span class="red">제목</span>
							</td>
							<td><input type="text" class="intxt" name="boardType" class=""></td>
							<td class="tbh">
								<span class="red">제목</span>
							</td>
							<td><select><option>주문</option><option>배송</option></select></td>
							<td class="tbh">
								<span class="red">제목</span>
							</td>
							<td><input type="text" class="intxt" name="boardCnts" class=""></td>
						</tr>
						<tr>
							<td class="tbh">
								<span class="red">사용여부</span>
							</td>
							<td colspan="5">
								
								<input type="radio" value="예"/> 예
								<input type="radio" value="아니오"/> 아니오
							</td>
							<td class="tbh">
								<span class="red">제목</span>
							</td>
							<td><input type="text" class="intxt" name="boardCnts" class=""></td>
						</tr>
						<tr>
							<td class="tbh">
								<span class="red">상태</span>
							</td>
							<td colspan="7">
						    	<input type="checkbox" name="useYn2" id="chkN" value="N">
						    	<label class="checkbox" title="예" for="chkY">결제완료</label>
						    	<input type="checkbox" name="useYn2" id="chkN" value="N">
						    	<label class="checkbox" title="예" for="chkY">상품준비중</label>
						    	<input type="checkbox" name="useYn2" id="chkN" value="N">
						    	<label class="checkbox" title="예" for="chkY">배송준비중</label>
						    	<input type="checkbox" name="useYn2" id="chkN" value="N">
						    	<label class="checkbox" title="예" for="chkY">배송중</label>
						    	<input type="checkbox" name="useYn2" id="chkN" value="N">
						    	<label class="checkbox" title="예" for="chkY">배송완료</label>
						    	<input type="checkbox" name="useYn2" id="chkN" value="N">
						    	<label class="checkbox" title="예" for="chkY">구매확정</label>
							</td>
						</tr>
						
					</tbody>
				</table>
			</div>
			<div class="schBtnBx">
				<button type="button" class="btn gridSearch" id="searchBtn">조회</button>
			</div>
		</div>
		<div class="btnbx">
			<div class="evtBtnBx" id="excelBx">
			</div>
		</div>
		<div id="grid"></div>
	</div>