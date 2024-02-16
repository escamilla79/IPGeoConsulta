<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.IPInfo" %>
<%@ page import="java.util.List" %>

<html >
<head>
    <title>IP Info</title>
</head>
<body>
    <h1>Información de IP</h1>
    <table border="1">
        <tr>
            <th>IP</th>
            <th>Ciudad</th>
            <th>Región</th>
            <th>País</th>
            <th>Código Postal</th>
        </tr>
        
		<% 
		 List<IPInfo> ipInfoList = (List<IPInfo>) request.getAttribute("ipInfoList");
        if(ipInfoList != null) {
            for(IPInfo ipInfo : ipInfoList) {
	    %>
	    <tr>
	        <td><%= ipInfo.getIp() %></td>
	        <td><%= ipInfo.getCity() %></td>
	        <td><%= ipInfo.getCountry() %></td>
	        <td><%= ipInfo.getLoc() %></td>
	        <td><%= ipInfo.getOrg() %></td>
	        <td><%= ipInfo.getPostal() %></td>
	        <td><%= ipInfo.getTimezone() %></td>
	        <td><a href="eliminar.jsp?id=<%= ipInfo.getId() %>">Eliminar</a></td>
	    </tr>
	    <% 
	        }
        }
	    %>
    </table>
</body>
</html>
