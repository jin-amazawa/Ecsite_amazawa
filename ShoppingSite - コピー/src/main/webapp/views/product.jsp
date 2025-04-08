<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="servlet.model.Product" %>
<%
    Product product = (Product) request.getAttribute("product");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title><%= product.getName() %> - 商品詳細</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/product.css?v=<%= System.currentTimeMillis() %>">    
</head>
<body>
    <header>
        <h1><%= product.getName() %></h1>
        <nav>
            <a href="<%= request.getContextPath() %>/">商品一覧へ</a>
            <a href="cart">カート</a>
        </nav>
    </header>
    <main>
        <div class="product-detail">
            <img src="<%= product.getImage() %>" alt="<%= product.getName() %>">
            <div class="details">
                <p>価格: ¥<%= product.getPrice() %></p>
                <p><%= product.getDescription() %></p>
                <a href="add-to-cart?id=<%= product.getId() %>" class="btn">カートに追加</a>
            </div>
        </div>
    </main>
    <footer>
        <p>&copy; ECサイト</p>
    </footer>
</body>
</html>
