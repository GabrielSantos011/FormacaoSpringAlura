package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import br.com.alura.gerenciador.repository.SimulaBancoDeDados;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/removeEmpresa")
public class RemoveEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		System.out.println(id);
		
		SimulaBancoDeDados banco = new SimulaBancoDeDados();
		banco.removeEmpresa(id);
		
		response.sendRedirect("listaEmpresas");
		
	}

}
