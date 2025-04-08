<%@ page import="java.util.List, servlet.model.Product" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>検索結果</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/style.css?v=<%= System.currentTimeMillis() %>">
</head>
<body class = "search-results-container">
	    <h1>検索結果</h1>
	    <ul>
	        <%
	            List<Product> products = (List<Product>) request.getAttribute("products");
	            if (products != null && !products.isEmpty()) {
	                for (Product product : products) {
	            
	        
	        %>
	            <li>
	                <img src="<%= product.getImage() %>" width="100">
	                <a href="productDetail?id=<%= product.getId() %>"><%= product.getName() %></a>
	                <p>価格: ¥<%= product.getPrice() %></p>
	                <p><%= product.getDescription() %></p>
	                <a href="add-to-cart?id=<%= product.getId() %>" class="btn">カートに追加</a>
	            </li>
	        <%
	                }
	            } else {
	        %>
	            <p>該当する商品がありません。</p>
	        <%
	            }
	        %>
	    </ul>
	    
	    
	    
		<a href="<%= request.getContextPath() %>/">
		    <button type="button">戻る</button>
		</a>
    
    
</body>
</html>
