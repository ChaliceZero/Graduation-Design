<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script type="text/javascript">
	function addBook() {
		window.location.href = "${pageContext.request.contextPath}/book_addBookPage.action";
	}
	function search() {
		var keywords = document.getElementById("keywords").value;
		window.location.href = "${pageContext.request.contextPath}/book_searchKeys.action?keywords="+keywords;
	}
	//&#28155;&#21152;添加
</script>
</head>
<body>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<tbody>
			<tr>
				<td align="center" bgColor="#afd1f3">
					<strong>图书列表</strong>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					<button type="button" style="width:154px; height:28px;" value="添加" onclick="addBook()">
						添加图书
					</button>
					&nbsp;&nbsp;图书名：<input type="text" id="keywords" name="keywords"/>
					&nbsp;&nbsp;<input type="button" value="检索" name="keywords" onclick="search()"/>
					<s:actionerror/>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<th align="center" width="5%">序号</th>
							<th align="center" width="10%">图书图片</th>
							<th align="center" width="10%">图书名称</th>
							<th align="center" width="8%">图书价格</th>
							<th align="center" width="8%">市场价格</th>
							<th align="center" width="8%">是否热门</th>
							<th align="center" width="6%">编辑</th>
							<th align="center" width="6%">删除</th>
						</tr>
						<s:iterator var="b" value="pageBean.list" status="status">
							<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="5%">
									<s:property value="#status.count"/>
								</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
									<img width="60" height="70" src="${pageContext.request.contextPath}/<s:property value="#b.image"/>" title="<s:property value="#b.bname"/>" alt="<s:property value="#b.bname"/>"/>
								</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
									<s:property value="#b.bname" />
								</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
									<s:property value="#b.shop_price" />
								</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
									<s:property value="#b.market_price" />
								</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
									<s:if test="#b.is_hot == 1">
										是
									</s:if>
									<s:else>
										否
									</s:else>
								</td>
								<td align="center" style="HEIGHT: 22px" width="6%">
									<a href="${pageContext.request.contextPath}/book_editBookPage.action?bid=<s:property value="#b.bid"/>">
										<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
									</a>
								</td>
								<td align="center" style="HEIGHT: 22px" width="6%">
									<a href="${pageContext.request.contextPath}/book_deleteBook.action?bid=<s:property value="#b.bid"/>">
										<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
									</a>
								</td>
							</tr>
						</s:iterator>
						<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td align="center" width="18%" colspan="8">
								第 <s:property value="pageBean.page" /> / <s:property value="pageBean.totalPage" /> 页
								<s:if test="pageBean.page != 1">
									<a href="${ pageContext.request.contextPath }/book_adminFindAll.action?pageNum=1">首页</a> |
									<a href="${ pageContext.request.contextPath }/book_adminFindAll.action?pageNum=<s:property value="pageBean.page-1"/>">上一页</a> |
								</s:if> 
								<s:if test="pageBean.page != pageBean.totalPage">
									<a href="${ pageContext.request.contextPath }/book_adminFindAll.action?pageNum=<s:property value="pageBean.page+1"/>">下一页</a> |
									<a href="${ pageContext.request.contextPath }/book_adminFindAll.action?pageNum=<s:property value="pageBean.totalPage"/>">尾页</a>
								</s:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>

