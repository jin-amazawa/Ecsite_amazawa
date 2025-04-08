<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.OrderHistoryDto" %>

<%
    List<OrderHistoryDto> orders = (List<OrderHistoryDto>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>購入履歴一覧（管理者用）</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #aaa; padding: 8px; }
    </style>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/order.css?v=<%= System.currentTimeMillis() %>">
    
</head>
<body>
    <h1>購入履歴一覧</h1>

    <table>
        <tr>
            <th>購入者</th>
            <th>商品名</th>
            <th>数量</th>
            <th>購入日時</th>
        </tr>
        <% for (OrderHistoryDto order : orders) { %>
        <tr>
            <td><%= order.getUsername() %></td>
            <td><%= order.getProductName() %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= order.getOrderDate() %></td>
        </tr>
        <% } %>
    </table>

    <p><a href="<%= request.getContextPath() %>/admin">管理者メニューに戻る</a></p>
</body>
</html>
