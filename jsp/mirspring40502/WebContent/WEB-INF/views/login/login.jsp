<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>
<!DOCTYPE html>
<html>
<head>
<title>HKProject</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-ui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery.cookie.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="style/style.css"/>

<style type="text/css">
			#login_wrap {
				margin:10px;
			}
			#login_wrap th {
				font-weight:bold;
			}
			#main_wrap { width:800px; margin-left:auto; margin-right:auto; padding:0px; }			
			#content_wrap { width: 100%; height: 500px; background-image:url("image/skaret.jpg"); background-repeat:no-repeat; background-position:top center;  }
						
			.login_title_warp {
				width:500px; color:#FFFFFF; text-align:center; background-color:#3e5fba; border:solid 1px #EFEFEF; font-weight:bold; height:60px;
			}
    .content_table { width:98%; border-bottom:1px solid #EFEFEF; border-right:1px solid #EFEFEF; border-collapse:collapse; margin-left:auto; margin-right:auto;  clear:both; }
	.content_table td, .content_table th { text-align:center; border-top:1px solid #EFEFEF; border-left:1px solid #EFEFEF; padding:0.3em; }
	.content_table th { background-color:#4D6BB3; color:#FFFFFF; line-height: 1.7em; font-weight:normal;}
 	.content_table td { padding-left:5px; text-align:left; line-height: 1.7em; }
	.content_table td.contents { width:100%; height:400px; overflow:auto; }
	.content_table th, .content_table td { vertical-align:middle; }

	.content_table select { height:19px; border:#CCCCCC solid 1px; vertical-align:middle; line-height: 1.8em; padding-left:0px; }
	.content_table select option { margin-right:10px; }
			
		</style>	
</head>
<body>
<div id="main_wrap">
<div id="middle_wrap">
<div id="content_wrap">
<div style="width:502px; height:166px; margin-left:auto; margin-right:auto; position:relative; top:100px;">
<div class="login_title_warp">
<div style="margin-top:12px;"><h2>HK BBS 로그인</h2></div>
</div>
	<div id="login_wrap">	
<form name="frmForm" id="_frmForm" action="loginAf.do" method="post">
<table class="content_table" style="width:75%;">
<colgroup>
		<col style="width:30%;" />
		<col style="width:70%;" />							
	</colgroup>	
<tbody>
<tr>
	
				<th style="background-color:#eeeeee; color:#3e5fba;">아이디</th>
				<td>&nbsp;<input type="text" id="_userid" name="id" value="" data-msg="ID를" size="15" title="아이디" style="border:1px solid #dddddd;" />
					<input type="checkbox" id="_chk_save_id" />ID저장
				</td>
			</tr>
			<tr>
				<th style="background-color:#eeeeee; color:#3e5fba;">패스워드</th>
				<td >&nbsp;<input type="password" id="_pwd" name="pwd" value="" data-msg="암호를" size="15" title="패스워드" style="border:1px solid #dddddd;"/></td>
			</tr>
			<tr>
				<td colspan="2" style="height:50px; text-align:center;">
					<span><a href="#none" id="_btnLogin" title="로그인"><img src="image/login_btn.jpg" alt="로그인" /></a>
					<a href="#none" id="_btnRegi" title="회원가입"><img src="image/regi.jpg" alt="회원가입" /></a></span>
				</td>
			</tr>
</tbody>
</table>
</form>
</div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
			$("#_btnLogin").click(function() {
				if ($("#_userid").val() == "") {
					alert($("#_userid").attr("data-msg") + " 입력해 주십시요.");
					$("#_userid").focus();
				} else if ($("#_pwd").val() == "") {
					alert($("#_pwd").attr("data-msg") + " 입력해 주십시요.");
					$("#_pwd").focus();
				} else {
					//$("#_frmForm").attr("target","ifrmSpace").submit();
					$("#_frmForm").attr("target","_self").submit();
				}
			});
			$("#_btnRegi").click(function() {
				location.href='regi.do';
			});
			$("#_userid").keypress(function(event) {
				if (event.which == '13') {
					event.preventDefault();
					$("#_pwd").focus();
				}
			});			

			$("#_pwd").keypress(function(event) {
				if (event.which == '13') {
					event.preventDefault();
					$("#_btnLogin").click();
				}
			});
			
			//id저장
	        var user_id = $.cookie("user_id");
			//alert(user_id);
	        if (user_id != null) {
	        	$("#_userid").val(user_id);
	            $("#_chk_save_id").prop("checked",true);
	        }			
			
			$("#_chk_save_id").click(function () {

			    if ($(this).prop("checked")) {
			    	//alert('cookie');
			        if ($("#_userid").val() == "") {
			            $(this).prop("checked", false);
			            alert("아이디를 입력해 주십시요.");
			        } else {
			            $.cookie("user_id", $("#_userid").val(), { path:"/", expires:365 });
			        }
			    } else {
			    	$.cookie("user_id", null, { path:"/", expires:-1 });
			    }
			});			

		</script>
</body>
</html> 