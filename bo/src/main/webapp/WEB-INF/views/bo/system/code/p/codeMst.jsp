<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">

(function() {
	document.getElementById('btn_save').onclick = function () {
		var p = {
			  name : 'save'
			, success : function (opt,result) {
				Grid1.refresh();
				LibUtil.closePopup();
    	    }
		}
		AjaxUtil.form(p);
	};
})();

</script>	
<div class="article_right">
	<section class="k_inputform">
			<fieldset>
			<form name="save" action="/bo/system/code/mstSave" method="POST">
			<table class="inputTable">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tbody>
					<tr>
						<td class="leftTd"><span class="type">상위코드</span></td>
						<td class="rightTd">
							<input type="text" name="cdMstCd" id="u_title" placeholder="상위코드를 입력하세요." class="" value="" data-vd='{"type":"text","len":"1,20","req":true,"msg":"상위코드을 입력하세요"}'>
						</td>
					</tr>
					<tr>
						<td class="leftTd"><span class="type">상위코드명</span></td>
						<td class="rightTd">
							<input type="text" name="cdMstNm" id="u_title" placeholder="상위코드명을 입력하세요." class="" value="" data-vd='{"type":"text","len":"1,20","req":true,"msg":"상위코드명을 입력하세요"}'>
						</td>
					</tr>
					<tr>
						<td class="leftTd"><span class="type">상위코드 설명</span></td>
						<td class="rightTd">
							<textarea id="u_content" name="cdMstDesc" rows="5" cols="1" placeholder="내용을 입력하세요." class="field_inp" data-vd='{"type":"text","len":"1,400","msg":"상위코드 설명을 입력하세요"}'>${board.boardCnts}</textarea>
						</td>
					</tr>
					<tr>
						<td class="leftTd"><span class="type">(SELECT)사용</span></td>
						<td class="rightTd">
							<select name="useYn">
								<option value="Y">예</option>
								<option value="N">아니오</option>
							</select>
						</td>
					</tr>
					<!-- 
					<tr>
						<td class="leftTd"><span class="type">(RADIO)사용</span></td>
						<td class="rightTd">
							<span class="rabx1">
							    <input type="radio" name="useYn" id="radioY" value="Y">
							    <label title="예" for="radioY">예</label>
							</span>
							<span class="rabx1">
							    <input type="radio" name="useYn" id="radioN" value="N">
							    <label title="아니오" for="radioN">아니오</label>
							</span>
						</td>
					</tr>
					<tr>
						<td class="leftTd"><span class="type">(CHECKBOX)사용</span></td>
						<td class="rightTd">
							<span class="ckbx1">
						    	<input type="checkbox" name="useYn" id="chkY" value="Y">
						    	<label class="checkbox" title="예" for="chkY">예</label>
							</span>
							<span class="ckbx1">
						    	<input type="checkbox" name="useYn" id="chkN" value="N">
						    	<label class="checkbox" title="아니오" for="chkN">아니오</label>
							</span>
						</td>
					</tr>
					 -->
				</tbody>
			</table>
			
			<div class="btn_bx">
				<button type="button" class="btn_black" id="btn_save">저장하기</button>
				<button type="button" class="btn_black" id="btn_close">닫기</button>
			</div>
		</form>
		</fieldset>
	</section>
</div>
