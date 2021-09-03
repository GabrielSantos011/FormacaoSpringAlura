package br.com.alura.gerenciador.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.gerenciador.model.Empresa;

public class SimulaBancoDeDados {
	
	private static List<Empresa> empresas = new ArrayList<Empresa>();
	
	static {
		Empresa empresa1 = new Empresa();
		empresa1.setNome("Alura");
		
		Empresa empresa2 = new Empresa();
		empresa2.setNome("Fatec");
		
		empresas.add(empresa1);
		empresas.add(empresa2);
	}
	
	public void adicionaEmpresa(Empresa empresa) {
		empresas.add(empresa);
	}
	
	public List<Empresa> getEmpresas(){
		return SimulaBancoDeDados.empresas;
	}

}
