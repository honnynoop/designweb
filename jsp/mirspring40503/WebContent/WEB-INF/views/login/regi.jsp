<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>
<!DOCTYPE html>
<html>
<head>
<title>regi</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery.cookie.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>

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
<div style="margin-top:12px;"><h2>HK BBS 회원가입</h2></div>
</div>
	<div id="login_wrap">	


<form action="" method="post" id="_frmForm" name="frmForm">
<table class="content_table" style="width:75%;">
<colgroup>
		<col style="width:30%;" />
		<col style="width:70%;" />							
	</colgroup>	
	<tr>
<th>아이디체크</th>
<td><input type="text" name="sid" id="_id" size="30"/>
<a href="#none" id="_btnGetid" title="회원가입"><img src="image/idcheck.png" alt="회원가입" /></a></span>
<div id='_rgetid'></div>
</td>
</tr>

<tr>
<th>아이디</th>
<td><input readonly="readonly"
 data-msg="아이디" type="text" name="id" id="_userid" size="30"/></td>
</tr>
<tr>
<th>이름</th>
<td><input  data-msg="이름" type="text" name="name"  id="_name" size="30" /></td>
</tr>
<tr>
<th>이메일</th>
<td><input data-msg="이메일" type="text" name="email"  id="_email" size="30" /></td>
</tr>
<tr>
<th>패스워드</th>
<td><input data-msg="패스워드" type="password" name="pwd"  id="_pwd" size="30" /></td>
</tr>
<tr>
<td colspan="2" style="height:50px; text-align:center;">
<a href="#none" id="_btnRegi" title="회원가입"><img src="image/regi.jpg" alt="회원가입" /></a>
<a href="#none" id="_btnLogin" title="로그인"><img src="image/login_btn.jpg" alt="로그인" /></a></td>
</tr>
</table>
</form>
</div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
$("#_btnLogin").click(function() {
	location.href='login.do';
});
$("#_btnRegi").click(function() {
	if ($("#_userid").val() == "") {
		alert($("#_userid").attr("data-msg") + " 입력해 주십시요.");
		$("#_userid").focus();
	} else if ($("#_name").val() == "") {
		alert($("#_name").attr("data-msg") + " 입력해 주십시요.");
		$("#_name").focus();
	} else if ($("#_email").val() == "") {
		alert($("#_email").attr("data-msg") + " 입력해 주십시요.");
		$("#_email").focus();
	}
	else if ($("#_pwd").val() == "") {
		alert($("#_pwd").attr("data-msg") + " 입력해 주십시요.");
		$("#_pwd").focus();
	}
	else {
		$("#_frmForm").attr({ "target":"_self", "action":"regiAf.do" }).submit();
	}
});
$("#_btnGetid").click(function() {	
	
	var _id=$("#_id").val();

	
	if(_id == ''){
		alert('아이디를 넣으세요');
	}else{
		showallcust3(_id);
	}
});
function showallcust3(id){
	$.ajax({
		   type: "POST",
			url: "./getID.do",
		   async: true,		   
		   data: "id="+id,	
		   success: function(msg){
				outputList2(msg);
		   }
	 });
};
function outputList2(msg){  
	if(msg.message=='SUCS'){
		//alert(msg.message);
		$("#_rgetid").html("사용할 수 없는 아이디 입니다.");
		$("#_rgetid").css("background-color","#FF0000");
		$("#_userid").val("");
	}else{
		//alert(msg.message);
		$("#_rgetid").html("사용할 수 있는 아이디 입니다.");
		$("#_rgetid").css("background-color","#0000FF");
		$("#_userid").val($("#_id").val());
		
	}
	  
} ;
</script>
</body>
</html>
