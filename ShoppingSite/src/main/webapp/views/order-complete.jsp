<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="servlet.model.Product" %>

<%
    // セッションから購入確定後の情報を取得
    List<Product> orderedProducts = (List<Product>) session.getAttribute("cart");

    // カートが空ならトップページへリダイレクト
    if (orderedProducts == null || orderedProducts.isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }

    // 合計金額の計算
    int total = 0;
    for (Product item : orderedProducts) {
        total += item.getPrice() * item.getQuantity();
    }

    double taxRate = 0.10; // 消費税率10%
    int tax = (int) (total * taxRate);
    int grandTotal = total + tax;

    // セッションのデータを削除（購入完了後の情報をクリア）
    session.removeAttribute("cart");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文完了</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/order-complete.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
    <header>
        <h1>注文完了</h1>
    </header>
    <main>
        <h2>ご注文ありがとうございました！</h2>

        <h3>注文内容</h3>
        <table border="1">
            <tr>
                <th>商品名</th>
                <th>数量</th>
                <th>小計</th>
            </tr>
            <% for (Product item : orderedProducts) { %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>¥<%= item.getPrice() * item.getQuantity() %></td>
                </tr>
            <% } %>
        </table>

        <p>合計金額: ¥<%= total %></p>
        <p>消費税 (10%): ¥<%= tax %></p>
        <p><strong>お支払い金額合計: ¥<%= grandTotal %></strong></p>

        <p>
            <a href="<%= request.getContextPath() %>/" class="return-btn">トップページへ戻る</a>
        </p>
    </main>
    <footer>
        <p>&copy; ECサイト</p>
    </footer>
</body>
</html>
