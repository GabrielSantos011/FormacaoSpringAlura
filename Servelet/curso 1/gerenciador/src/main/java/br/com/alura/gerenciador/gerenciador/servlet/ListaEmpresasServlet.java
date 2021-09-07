package br.com.alura.gerenciador.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import br.com.alura.gerenciador.gerenciador.model.Empresa;
import br.com.alura.gerenciador.gerenciador.repository.SimulaBancoDeDados;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/listaEmpresas")
public class ListaEmpresasServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SimulaBancoDeDados banco = new SimulaBancoDeDados();
		List<Empresa> empresas = banco.getEmpresas();
		
		PrintWriter out = resp.getWriter();
		out.println("<html><body><ul>");
		
		for (Empresa empresa : empresas) {
			out.println("<li>" + empresa.getNome() + "</li>");
		}
		
		out.println("</ul></body><html>");
	}
	
}