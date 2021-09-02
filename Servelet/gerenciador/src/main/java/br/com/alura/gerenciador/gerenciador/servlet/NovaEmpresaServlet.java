package br.com.alura.gerenciador.gerenciador.servlet;

import java.io.IOException;

import br.com.alura.gerenciador.gerenciador.model.Empresa;
import br.com.alura.gerenciador.gerenciador.repository.SimulaBancoDeDados;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/novaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {
	
	//este método rcebe qualquer requisição, mas nós podemos usar outros 2
	//métodos, o doPost e doGet que são específicos
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String nomeEmpresa= req.getParameter("nome");
		
		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		
		SimulaBancoDeDados banco = new SimulaBancoDeDados();
		banco.adicionaEmpresa(empresa);
		
//		PrintWriter out = resp.getWriter();
//		out.println("<html><body>");
//		out.println("Empresa " + nomeEmpresa + " cadastrada");
//		out.println("</body><html>");
		
		RequestDispatcher rd = req.getRequestDispatcher("/novaEmpresaCriada.jsp");
		req.setAttribute("empresa", empresa.getNome());
		rd.forward(req, resp);
		
	}
	
}
