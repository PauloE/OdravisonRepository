package br.ufpb.dce.poo.projetopack;

import java.util.LinkedList;
import java.util.List;

public abstract class UsuarioComposto implements Usuario {
	List<Emprestimo> emprestimos;
	
	private String matricula;
	private String nome;
	private String cpf;
	private String departamento;
	private String periodoIngresso;
	private String curso;
	
	public void Aluno(String matricula, String nome, String cpf, String curso, String periodoIngresso){
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.curso = curso;
		this.periodoIngresso = periodoIngresso;
		this.emprestimos = new LinkedList<Emprestimo>();
	}
	
	
	public int getQuantDiasEmprestimo() {
		return Configuracao.getInstance().getDiasEmprestimoProfessor();
	}
	
	public List<Emprestimo> getEmprestimos(){
		return this.emprestimos;
	}
	
	public String getMatricula() {
		return this.matricula;
	}
	
	public String getDepartamento(){
		return this.departamento;
	}
	public String getNome() {
		return this.nome;
	}
	
	public String getCPF(){
		return this.cpf;
	}
	
	public String getCurso() {
		return curso;
	}

	public void adicionarEmprestimo(Emprestimo emprestimo){
		this.adicionarEmprestimo(emprestimo);
	}
	
	public String getPeriodoIngresso(){
		return this.periodoIngresso;
	}


	public void removerEmprestimo(Emprestimo emprestimo) {
		for(Emprestimo e: this.emprestimos){
			if(e.equals(emprestimo)){
				this.emprestimos.remove(e);
				break;
			}
		}
	}


}
