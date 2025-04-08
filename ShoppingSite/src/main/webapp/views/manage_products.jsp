<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, dto.ProductDto" %>
<jsp:useBean id="products" scope="request" type="java.util.List<dto.ProductDto>" />

<html>
<head>
    <title>商品管理</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/manage_product.css?v=<%= System.currentTimeMillis() %>">
    <script src="/ShoppingSite/static/js/confirm.js"></script>
</head>
<body>
    
    <h1>商品管理</h1>

    <h2>商品一覧</h2>
    <table border="1">
        <tr>
            <th>ID</th><th>商品名</th><th>説明</th><th>価格</th><th>在庫</th><th>操作</th>
        </tr>
        <% for (ProductDto product : products) { %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getStock() %></td>
            <td class = "actions">
                <a href="<%= request.getContextPath() %>/views/edit.jsp?product_id=<%= product.getId() %>" class = "edit">編集</a>
                <form action="delete-product" method="post" " onsubmit="return confirmDeleteProduct();">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
                    <input type="submit" value="削除">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    
    <h2>新商品を追加</h2>
	<form action="add-product" method="post" onsubmit="return confirmAddProduct();" class ="new_product_add_form">
	    <label>商品名:</label>
	    <input type="text" name="name" required><br>
	
	    <label>説明:</label>
	    <input type="text" name="description" required><br>
	
	    <label>価格:</label>
	    <input type="number" name="price" required><br>
	
	    <label>在庫:</label>
	    <input type="number" name="stock" required><br>
	
	    <label>画像URL:</label>
	    <input type="text" name="image_path"><br>
	
	    <input type="submit" value="商品を追加">
	</form>
	
	<p><a href="<%= request.getContextPath() %>/orders" class="link-button">購入履歴を見る</a></p>
    
<a href="logout">ログアウト</a>    

</body>
</html>
