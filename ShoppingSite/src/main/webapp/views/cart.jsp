<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.ProductDto" %>
<%
    List<ProductDto> cart = (List<ProductDto>) session.getAttribute("cart");
    if(cart == null) {
        cart = new java.util.ArrayList<>();
    }
    
    /*Integer total = (Integer) session.getAttribute("total");
    if (total == null) {
        total = 0;
    }*/
    
    
    int total = 0;
    for (ProductDto p : cart) {
        total += p.getPrice() * p.getStock();
    }
    
    
    /*int total = 0;
    for(Product p : cart) {
        total += p.getPrice();
    }*/
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>カート内容</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/cart.css?v=<%= System.currentTimeMillis() %>">
    <script src="${pageContext.request.contextPath}/static/js/confirm.js"></script>
</head>
<body>
    <header>
        <h1>カート内容</h1>
        <nav>
            <a href="<%= request.getContextPath() %>/">HOMEへ</a>
        </nav>
    </header>
    <main>
        <% if(cart.isEmpty()) { %>
            <p>カートは空です。</p>
        <% } else { %>
	        <form action="<%= request.getContextPath() %>/update-cart" method="post">
	            <ul class="cart-list">
	                <% for(ProductDto item : cart) { %>
	                    <li>
	                    	<div class="cart-item-details">
		                        <img src="<%= item.getImagePath() %>" alt="<%= item.getName() %>" width="50">
		                        <span><%= item.getName() %> - ¥<%= item.getPrice() %></span>
		                        <input type="number" name="quantity_<%= item.getId() %>" value="<%= item.getStock() %>" min="1">
	                        </div>
	                        <div class="cart-item-actions">
	                            <button type="submit" name="update" value="<%= item.getId() %>">更新</button>
	                            <button type="submit" name="remove" value="<%= item.getId() %>" onclick="return confirmRemoveCartItem();">削除</button>
                            </div>
	                    </li>
	                <% } %>
	            </ul>
	            <p>合計金額: ¥<%= total %></p>
	        </form>
	        
            <form action="<%= request.getContextPath() %>/views/checkout.jsp" method="get" class = "purchase">
                <button type="submit">購入画面へ進む</button>
            </form>
	        
        <% } %>
    </main>
    <footer>
        <p>&copy; ECサイト</p>
    </footer>
</body>
</html>
