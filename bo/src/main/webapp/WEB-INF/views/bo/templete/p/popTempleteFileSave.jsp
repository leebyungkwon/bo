<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">

(function() {
	document.getElementById('btn_save').onclick = function () {
 		var p = {
			  name : 'save'
			, success : function (opt,result) {
				//Grid.refresh();
				//$("#btn_close").trigger('click');
    	    }
		}
		AjaxUtil.files(p);
	};
	document.getElementById('btn_close').onclick = function () {
		$(".closeLayer").trigger('click');
	};
})();

</script>	
<div class="article_right">
	<section class="k_inputform">
			<h3  class="article_tit"><span>팝업템플릿2저장하기</span></h3>
			<fieldset>
			<form name="save" action="/bo/templete/excel" method="POST" enctype="multipart/form-data">
			<input type="text" name="boardNo" value=""/>
			<input type="text" name="boardType" value="NOTICE"/>
			<table class="inputTable" style="width:500px;">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tbody>
					<tr>
						<td class="leftTd"><span class="type">제목</span></td>
						<td class="rightTd">
							<input type="text" name="boardTitle" id="u_title" placeholder="제목을 입력하세요." class="" data-vd='{"type":"text","len":"1,20","req":true,"msg":"제목을 입력하세요"}'>
						</td>
					</tr>
					<tr>
						<td class="leftTd"><span class="type">내용</span></td>
						<td class="rightTd">
							<textarea id="u_content" name="boardCnts" rows="5" cols="1" placeholder="내용을 입력하세요." class="field_inp" data-vd='{"type":"text","len":"1,400","req":true,"msg":"내용을 입력하세요"}'></textarea>
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
					<tr>
						<td class="leftTd"><span class="type">(RADIO)사용</span></td>
						<td class="rightTd">
							<span class="rabx1">
							    <input type="radio" name="useYn1" id="radioY" value="Y">
							    <label title="예" for="radioY">예</label>
							</span>
							<span class="rabx1">
							    <input type="radio" name="useYn1" id="radioN" value="N">
							    <label title="아니오" for="radioN">아니오</label>
							</span>
						</td>
					</tr>
					<tr>
						<td class="leftTd"><span class="type">(CHECKBOX)사용</span></td>
						<td class="rightTd">
							<span class="ckbx1">
						    	<input type="checkbox" name="useYn2" id="chkY" value="Y">
						    	<label class="checkbox" title="예" for="chkY">예</label>
							</span>
							<span class="ckbx1">
						    	<input type="checkbox" name="useYn2" id="chkN" value="N">
						    	<label class="checkbox" title="아니오" for="chkN">아니오</label>
							</span>
						</td>
					</tr>
					<tr>
						<td class="leftTd"><span class="type">엑셀업로드</span></td>
						<td class="rightTd">
							<input type="file" id="u_file" class="" name="files" multiple="multiple">
						</td>
					</tr>
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
