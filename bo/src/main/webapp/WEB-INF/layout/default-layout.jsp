<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
		<!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<tiles:insertAttribute name="css" />
		<tiles:insertAttribute name="js" />
		<tiles:insertAttribute name="head" />
	</head>
	<body>
		
		<div class="k_container">
		<%-- <tiles:insertAttribute name="header" /> --%>
		</div>
		
		<section class="k_content_wrap">
			<div class="my_wrap">		
				<tiles:insertAttribute name="left" />
				<div class="k_right">
				<tiles:insertAttribute name="category" />
				<div class="k_main">
					<div class="k_page">
						<!-- <div class="k_content_tit">공지사항</div> -->
						<tiles:insertAttribute name="body" />
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- body -->
		<%-- 
		<tiles:insertAttribute name="footer" />
		 --%>
		<!-- /WEB-INF/views/common/layout/footer.jsp -->
		<div class="layerPopupBase" style="display:none;">
			<button type="button" class="pop_btn_close closeLayer">X</button>
	    	<div class="layerContent">
	    	</div>
		</div>
		<div class="msgLayerPopupBase" style="display:none;">
			<button type="button" class="pop_btn_close msgCloseLayer">X</button>
	    	<div class="layerContent">
	    	</div>
		</div>
		<div class="pageContentsBase" style="display:none;">
			<button type="button" class="pop_btn_close closeLayer">X</button>
	    	<div class="layerContent">
	    	</div>
		</div>
		<div class="pageDetailBase" style="display:none;">
	    	<div class="pageDetailContent">
	    	</div>
		</div>
		<div class="viewLoading" id="viewLoading" style="display: none;"></div>
		<div class="loading-bar" id="loading-bar" style="display: none;">
			<div class="loading-dot1 loading-dot"></div>
			<div class="loading-dot2 loading-dot"></div>
			<div class="loading-dot3 loading-dot"></div>
			<div class="loading-dot4 loading-dot"></div>
			<div class="loading-dot5 loading-dot"></div>
			<div class="loading-dot6 loading-dot"></div>
			<div class="loading-dot7 loading-dot"></div>
			<div class="loading-dot8 loading-dot"></div>
			<div class="loading-dot9 loading-dot"></div>
			<div class="loading-dot10 loading-dot"></div>
			<div class="loading-dot11 loading-dot"></div>
			<div class="loading-dot12 loading-dot"></div>
		</div>
	</body>
</html>