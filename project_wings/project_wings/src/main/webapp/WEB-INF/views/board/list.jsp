<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 주르륵</title>
</head>
<body>
		<c:forEach var="list" items="${list}">
			<tr align="center">
				<td align="center">
					${list.postId}
				</td>
				<td align="center">
					${list.title}
				</td>
				<td align="center">
					${list.text}
				</td>
				<td align="center">
					${list.hitNum}
				</td>
			</tr>
		</c:forEach>
</body>
</html>