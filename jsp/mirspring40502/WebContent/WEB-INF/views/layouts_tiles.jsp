<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<tiles:insertAttribute name="common"/>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<tiles:insertAttribute name="header"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
		<style type="text/css">
		</style>	
	</head>
	<body>
		<div id="body_wrap">
			<div id="main_wrap">
				<div id="header_wrap">
					<tiles:insertAttribute name="top_inc"/>
					<tiles:insertAttribute name="top_menu"/>
				</div>						
				
				<div id="middle_wrap">
					<div id="sidebar_wrap">
					<tiles:insertAttribute name="left_main"/>	
					</div>
					
					<div id="content_wrap">		
						<div id="content_title_wrap">
							<div class="title">${doc_title}</div>
						</div>
			                 <tiles:insertAttribute name="main"/>	
					</div> <!--  //content_wrap -->		
				</div><!--  //middle_wrap -->
				
				<div id="footer_wrap">
					<tiles:insertAttribute name="bottom_inc"/>
				</div><!--  //footer_wrap -->
						
			</div>	<!--  //main_wrap -->	
		</div><!--  //body_wrap -->		
		
		<script type="text/javascript">
		$(document).ready(function() {
			$("#content_title_wrap div.title").css("background-image", "url('<%=request.getContextPath()%>/image/ico_sub_sb.gif')");
		});
		</script>
	</body>
</html>
