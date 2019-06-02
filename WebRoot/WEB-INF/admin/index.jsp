<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理中心 - 教育书城</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, 
	pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, font, img, ins, kbd, q, s,
	samp, small, strike, strong, sub, sup, tt, var, dd, dl, dt, li, ol, ul, fieldset, label, 
	legend, table, caption, tbody, tfoot, thead, {
		padding: 0px;
		margin: 0px;
		border: 0px;
		outline: 0px;
	}
	body {
		position: relative;
	  	color: white;
	  	font-size: 18px;
	  	font-family: '宋体';
	  	font-weight: 500;
	}
	#fm {
		margin-top: 100px;
	}
	ul li {
		list-style: none;
	}
	#logo {
		margin: 5% auto 0px 9%;
	}
	#logo h3 {
		margin-top: 40px;
		margin-left: 230px;
	}
</style>
<script type="text/javascript">
	function checkAdmin() {
		var name = document.getElementById("adminName").value;
		var pwd = document.getElementById("adminPwd").value;
		if (name == '') {
			alert("请输入管理员姓名");
			return false;
		} 
		if (pwd == '') {
			alert("请输入管理员密码");
			return false;
		}
		return true;
	}
</script>
</head>
<body style="width:100%; height:100%; background: #278296">
<div id="logo">
	<h1>教育书城后台管理系统</h1>
	<h3>—— Backstage Management System of Educational Bookstore</h3>
</div>
<form method="post" id="fm" action="${pageContext.request.contextPath}/admin_login.action" onsubmit="return checkAdmin()">
	<table cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td align="center">
				<table>
					<tr>
						<td>管理员姓名：</td>
						<td><input id="adminName" type="text" name="adminName"/></td>
					</tr>
					<tr><td><br/></td></tr>
					<tr>
						<td>管理员密码：</td>
						<td><input id="adminPwd" type="password" name="adminPwd"/></td>
					</tr>
					<tr><td><br/></td></tr>
					<tr>
						<!-- <td colspan="2" align="center"><input type="checkbox" value="1" name="remember" id="remember"/><label for="remember">请保存我这次的登录信息</label></td> -->
					</tr>
					<tr><td><p></p></td></tr>
					<tr>
						<td></td>
						<td align="left"><input type="submit" value="进入管理中心" class="button" /></td>	
					</tr>  
				</table>
			</td>
		</tr>
		<tr>
			<td align="center" style="color:red;font-weight: 600;"><s:actionerror/></td>
		</tr>
	</table>
</form>
<script language="JavaScript">
<!--
  document.forms['theForm'].elements['username'].focus();
  
  /**
   * 检查表单输入的内容
   */
  function validate()
  {
    var validator = new Validator('theForm');
    validator.required('username', user_name_empty);
    //validator.required('password', password_empty);
    if (document.forms['theForm'].elements['captcha'])
    {
      validator.required('captcha', captcha_empty);
    }
    return validator.passed();
  }
  
-->
</script>
</body>
</html>