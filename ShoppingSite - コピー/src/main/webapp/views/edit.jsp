<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dao.ProductDao, dto.ProductDto" %>

<%
    String idStr = request.getParameter("product_id");
    int id = (idStr != null) ? Integer.parseInt(idStr) : 0;
    
    ProductDao productDao = new ProductDao();
    ProductDto product = productDao.getProductById(id);
%>

<html>
<head>
    <title>商品編集</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/edit.css?v=<%= System.currentTimeMillis() %>">    
    <script src="${pageContext.request.contextPath}/static/js/confirm.js"></script>
</head>
<body>
    <h2>商品編集</h2>
    <form action="${pageContext.request.contextPath}/edit-product" method="post" onsubmit="return confirmUpdateProduct();">
        <input type="hidden" name="product_id" value="<%= product.getId() %>">
        <label>商品名:</label>
        <input type="text" name="name" value="<%= product.getName() %>" required><br>

        <label>説明:</label>
        <input type="text" name="description" value="<%= product.getDescription() %>" required><br>

        <label>価格:</label>
        <input type="number" name="price" value="<%= product.getPrice() %>" required><br>

        <label>在庫:</label>
        <input type="number" name="stock" value="<%= product.getStock() %>" required><br>

        <input type="submit" value="更新">
    </form>
</body>
</html>
