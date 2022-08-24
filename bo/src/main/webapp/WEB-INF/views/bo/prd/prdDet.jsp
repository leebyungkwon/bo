<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script>
Popup.load = function(){
	alert(2);
	document.getElementById('btn_reg').onclick = function () {
		var p = {
				name : "prdSave"
				, success: function(opt, result) {
					LibUtil.closePopup();
				}	                
		}
		AjaxUtil.form('p');
	};
}

function popupInit(){
	$(DOC).find('#btn_reg').on("click",  function () {
		var p = {
				name : "prdSave"
				, success: function(opt, result) {
					LibUtil.closePopup();
				}	                
		}
		AjaxUtil.form('p');
	});
}
</script>	
<div class="k_detail_page">
	<section class="k_inputform">
		<form name="prdSave" action="/bo/prd/prdSave" method="POST">
		<input type="text" name="id" value="${prd.id}"/>
		<input type="text" name="prdSts" value="${prdSts}"/>
		<ul class="k_tb_lst">
			<li class="tit_row">
				<div class="in_td"><strong class="type">상품명</strong></div>
				<div class="in_td">
					<div class="">
						<input type="text" name="prdNm" id="u_title" placeholder="상품명을 입력하세요." class="" value="${prd.prdNm}">
					</div>
				</div>
				<div class="in_td"><strong class="type">상품상태</strong></div>
				<div class="in_td">
					<div class="">
						<input type="text" name="prdSts" id="u_title" placeholder="상품명을 입력하세요." class="" value="${prd.prdSts}">
					</div>
				</div>
			</li>
			<li class="cnt_row chk">
				<div class="in_td"><strong class="type">내용</strong></div>
				<div class="in_td">
					<div class="inptxtbx">
						<textarea id="u_content" name="prdDesc" rows="5" cols="1" placeholder="내용을 입력하세요." class="field_inp">${prd.prdDesc}</textarea>
					</div>
				</div>
			</li>
			
		</ul>
		<div class="btn_bx">
			<button type="button" class="btn_black" id="btn_reg">저장하기</button>
		</div>
		</form>
	</section>
</div>
