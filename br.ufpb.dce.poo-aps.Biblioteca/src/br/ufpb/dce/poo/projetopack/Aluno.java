package br.ufpb.dce.poo.projetopack;

import java.util.LinkedList;
import java.util.List;


public class Aluno extends UsuarioComposto {
	
	public Aluno(String matricula, String nome, String cpf, String curso, String periodoIngresso){
		super(matricula);
		this.nome = nome;
		this.cpf = cpf;
		this.curso = curso;
		this.periodoIngresso = periodoIngresso;
		this.emprestimos = new LinkedList<Emprestimo>();
	}

	public int getQuantDiasEmprestimo() {
		return Configuracao.getInstance().getDiasEmprestimoAluno();
	}
}