<%@ page import= "java.lang.*" %>
<% 
	String empresa = (String) request.getAttribute("empresa");
%>


<html>
	<body>
		Empresa <%= empresa %> cadastrada
	</body>
</html>