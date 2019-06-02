<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
	<form action="${pageContext.request.contextPath}/book_saveBook.action" method="post" enctype="multipart/form-data">
		<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td align="center" bgColor="#afd1f3" colSpan="4" height="26">
					<strong>添加书籍</strong>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe">
					书籍名称：
				</td>
				<td bgColor="#ffffff">
					<input type="text" name="bname" value=""/>
				</td>
				<td align="center" bgColor="#f5fafe">
					二级分类：
				</td>
				<td bgColor="#ffffff">				
					<select name="csid">
						<s:iterator var="cs" value="csList">
					   		<option value="<s:property value="#cs.csid"/>"><s:property value="#cs.csname"/></option>
					    </s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe">
					 书籍价格：
				</td>
				<td bgColor="#ffffff">
					<input type="text" name="shop_price" value=""/>
				</td>
				<td align="center" bgColor="#f5fafe">
					市场价格：
				</td>
				<td bgColor="#ffffff">
					<input type="text" name="market_price" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe">
					是否是热门书籍：
				</td>
				<td bgColor="#ffffff">
					<input type="radio" name="is_hot" checked value="1"/>是
					<input type="radio" name="is_hot"  value="0"/>否
				</td>
				<td align="center" bgColor="#f5fafe">
					书籍库存：
				</td>
				<td bgColor="#ffffff">
					<input type="text" name="num" value=""/>
				</td>
			</tr>		
			<tr>
				<td align="center" bgColor="#f5fafe">
					书籍图片：
				</td>
				<td bgColor="#ffffff" colSpan="3">
					<input type="file" name="upload" size="30" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe">
					书籍简介：
				</td>
				<td bgColor="#ffffff" colSpan="3">
					<textarea name="bdesc" cols="30" rows="3" style="WIDTH: 96%"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colSpan="4">
					<img src="${pageContext.request.contextPath}/images/shim.gif">
				</td>
			</tr>
			<tr>
				<td style="width: 100%" align="center" bgColor="#f5fafe" colSpan="4">
					<button type="submit" name="submit" value="&#30830;&#23450;" style="width:80px;height:28px;">
						&#30830;&#23450;
					</button>
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<button type="reset" value="&#37325;&#32622;" style="width:80px;height:28px;">
						&#37325;&#32622;
					</button>
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<input type="button" onclick="history.go(-1)" value="返回" style="width:80px;height:28px;"/>
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>