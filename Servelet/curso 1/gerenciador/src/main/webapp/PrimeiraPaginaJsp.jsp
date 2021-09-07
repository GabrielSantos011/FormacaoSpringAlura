<%
	String texto = "texto";
%>

<html>
	<body>
		Estou impriminto este <% out.println(texto); %> pela primeira vez <br>
		Estou impriminto este <%= texto %> pela segunda vez
	</body>
</html>