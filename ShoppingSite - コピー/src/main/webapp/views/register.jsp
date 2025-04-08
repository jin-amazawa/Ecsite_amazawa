<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/register.css?v=<%= System.currentTimeMillis() %>"> 
    <title>新規登録</title>
</head>
<body>
    <h2>新規登録</h2>
    <form action="<%= request.getContextPath() %>/register" method="post">
        <label for="username">ユーザー名:</label>
        <input type="text" id="username" name="username" required maxlength="50" pattern="[A-Za-z0-9]+"><br>

        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password" required maxlength="100"><br>

        <input type="submit" value="登録">
    </form>

    <p>すでにアカウントをお持ちですか？ <a href="${pageContext.request.contextPath}/views/login.jsp">ログインはこちら</a></p>

    <%-- エラーメッセージ表示 --%>
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>
</body>
</html>
    