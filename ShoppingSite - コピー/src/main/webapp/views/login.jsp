<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/login.css?v=<%= System.currentTimeMillis() %>">
        
</head>
<body>
    <header>
        <h1>ログイン</h1>
        <nav>
            <a href="<%= request.getContextPath() %>/">商品一覧</a>
        </nav>
    </header>
    <main>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
            <p style="color:red;"><%= errorMessage %></p>
        <% } %>
        <form method="post" action="<%= request.getContextPath() %>/login">
            <label for="username">ユーザー名:</label>
            <input type="text" id="username" name="username" required>
            <br>
            <label for="password">パスワード:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit">ログイン</button>
        </form>
        
        <p>アカウントをお持ちでない方は <a href="${pageContext.request.contextPath}/views/register.jsp">新規登録はこちら</a></p>
        
    </main>
    <footer>
        <p>&copy; ECサイト</p>
    </footer>
</body>
</html>
