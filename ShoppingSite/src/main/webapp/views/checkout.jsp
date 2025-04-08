<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.ProductDto" %>

<%
    List<ProductDto> cart = (List<ProductDto>) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
        // カートが空の場合、商品一覧ページへリダイレクト
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }

    int total = 0;
    for (ProductDto item : cart) {
        total += item.getPrice() * item.getStock();
    }

    double taxRate = 0.10; // 消費税率10%
    int tax = (int) (total * taxRate);
    int grandTotal = total + tax;
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>購入確認</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/checkout.css?v=<%= System.currentTimeMillis() %>">
    <script src="${pageContext.request.contextPath}/static/js/confirm.js"></script>
</head>
<body>
    <header>
        <h1>購入確認</h1>
        <nav>
            <a href="<%= request.getContextPath() %>/views/cart.jsp">カートに戻る</a>
        </nav>
    </header>
    <main>
        <h2>注文内容</h2>
        <table border="1">
            <tr>
                <th>商品名</th>
                <th>数量</th>
                <th>小計</th>
            </tr>
            <% for (ProductDto item : cart) { %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><%= item.getStock() %></td>
                    <td>¥<%= item.getPrice() * item.getStock() %></td>
                </tr>
            <% } %>
        </table>
        <p>合計金額: ¥<%= total %></p>
        <p>消費税 (10%): ¥<%= tax %></p>
        <p>お支払い金額合計: ¥<%= grandTotal %></p>

        <!-- 購入確定ボタン -->
        <form action="<%= request.getContextPath() %>/confirm-order" method="post" onsubmit="return confirmPurchase();">
            <button type="submit">購入を確定する</button>
        </form>
    </main>
    <footer>
        <p>&copy; ECサイト</p>
    </footer>
</body>
</html>
