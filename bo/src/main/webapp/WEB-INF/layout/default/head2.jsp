<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
var h_gridPage = 0;

var Page = {
	setup: function(){},
	bind: function(){},
	load: function(){},
}
window.onload = function() {

	TabUtil.setup();
	
	if('${historyback}' == 'Y'){
    	$(".k_search").html(sessionStorage.getItem('H_'+window.location.pathname));
    	var b = JSON.parse(sessionStorage.getItem('P_'+window.location.pathname));
    	h_gridPage = sessionStorage.getItem('PAGE_'+window.location.pathname)
    	Page.setup();
    	WebUtil.setHistoryParam(b);
    	removeBackHistory();
    	Page.bind();
    	Page.load();
	}else{
		if(null != sessionStorage.getItem('H_'+window.location.pathname) &&
				null != sessionStorage.getItem('P_'+window.location.pathname) &&
				null != sessionStorage.getItem('PAGE_'+window.location.pathname)){
				window.onpageshow = function(event) {
				    if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
				    	$(".k_search").html(sessionStorage.getItem('H_'+window.location.pathname));
				    	var b = JSON.parse(sessionStorage.getItem('P_'+window.location.pathname));
				    	h_gridPage = sessionStorage.getItem('PAGE_'+window.location.pathname)
				    	Page.setup();
				    	WebUtil.setHistoryParam(b);
				    	removeBackHistory();
				    	Page.bind();
				    	Page.load();
				    }else{
				    	Page.setup();
				    	removeBackHistory();
				    	Page.bind();
				    	Page.load();
				    }
				}
			}else{
				Page.setup();
				removeBackHistory();
				Page.bind();
				Page.load();
			}
	}
	
	
}
function removeBackHistory(){
	sessionStorage.removeItem('H_'+window.location.pathname);
	sessionStorage.removeItem('P_'+window.location.pathname);
	sessionStorage.removeItem('PAGE_'+window.location.pathname);
}
</script>