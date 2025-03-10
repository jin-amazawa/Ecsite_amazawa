<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="servlet.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>


<%
    HttpSession sessionObj = request.getSession(false);
    String username = null;

    if (sessionObj != null && sessionObj.getAttribute("username") != null) {
        username = (String) sessionObj.getAttribute("username");
    }
%>



<%
    List<Product> products = (List<Product>) application.getAttribute("products");

	if (products == null) {
	    products = new ArrayList<>();

	    application.setAttribute("products", products);
	}
%>

<%
    List<Product> sortedProducts = (List<Product>) request.getAttribute("sortedProducts");

    if (sortedProducts == null) {
        sortedProducts = products; // デフォルトリスト
    }
%>

<% 
    List<Product> cart = (List<Product>) session.getAttribute("cart");
    if(cart == null) {
        cart = new java.util.ArrayList<>();
    }
    //String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ECサイト - 商品一覧</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/style.css?v=<%= System.currentTimeMillis() %>">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/slider.js"></script>
	
	

</head>
<body>
    <header>
        <h1>HOME</h1>
        <img src="<%= request.getContextPath() %>/static/images/logo3.jpg" alt = "logo3">
        <img src="<%= request.getContextPath() %>/static/images/logo_cinema.jpg" alt = "logo_cinema">
       	<form action="<%= request.getContextPath() %>/search" method="GET" class="search_form">
   			<input type="text" name="query" placeholder="商品名で検索" class="search"
              value="<%= request.getParameter("query") != null ? request.getParameter("query") : "" %>">
   			<button type="submit" class="search_submit">検索</button>
		</form>
        
        <nav>
        	<img src="<%= request.getContextPath() %>/static/images/rogo2.jpg" alt = "rogo1">
            <a href="cart">カート (<%= cart.size() %>)</a>
            
            <% if (username != null) { %>
	            <span>こんにちは、<%= username %> さん</span>
	            <a href="logout">ログアウト</a>
            <% } else { %>
                <a href="login">ログイン</a>
            <% } %>
        </nav>
    </header>
    <main>
		
		<div class="slider-container">
	        <div class="slider">
	            <% for(Product product : sortedProducts) { %>
	                <div>
	                    <a href="<%= request.getContextPath() %>/product?id=<%= product.getId() %>"><img src="<%= product.getImage() %>" alt="<%= product.getName() %>"></a>
	                </div>
	            <% } %>
	        </div>
	    </div>
	    
		
		<form id="sortForm" action="ProductListServlet" method="GET">
		    <label for="sort">並び替え：</label>
		    <select name="sort" id="sort" onchange="document.getElementById('sortForm').submit();">
		        <option value="" ${param.sort == null || param.sort == '' ? 'selected' : ''}>--選択してください--</option>
		        <option value="price_asc" ${param.sort == 'price_asc' ? 'selected' : ''}>価格の安い順</option>
		        <option value="price_desc" ${param.sort == 'price_desc' ? 'selected' : ''}>価格の高い順</option>
		        <option value="name_asc" ${param.sort == 'name_asc' ? 'selected' : ''}>商品名（A→Z）</option>
		        <option value="name_desc" ${param.sort == 'name_desc' ? 'selected' : ''}>商品名（Z→A）</option>
		    </select>
		</form>

		
		
        <ul class="product-list">
            <% for(Product product : sortedProducts) { %>
            <li class="product-item">
                <a href="<%= request.getContextPath() %>/product?id=<%= product.getId() %>"><img src="<%= product.getImage() %>" alt="<%= product.getName() %>"></a>
                <h2><a href="<%= request.getContextPath() %>/product?id=<%= product.getId() %>"><%= product.getName() %></a></h2>
                <p>価格: ¥<%= product.getPrice() %></p>
                <p><%= product.getDescription() %></p>
                <a href="add-to-cart?id=<%= product.getId() %>" class="btn">カートに追加</a>
            </li>
            <% } %>
        </ul>
        
        
        
        
    </main>
    <footer>
        <p>&copy; ECサイト</p>
    </footer>
</body>
</html>
