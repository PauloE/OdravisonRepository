package br.ufpb.dce.poo.projetopack;
import java.util.LinkedList;
import java.util.List;


public class Professor extends UsuarioComposto{
		
	public Professor(String matricula, String nome, String cpf){
		super(matricula, nome, cpf);
	}
	
	public int getQuantDiasEmprestimo() {
		return Configuracao.getInstance().getDiasEmprestimoProfessor();
	}
	
	public List<Emprestimo> getEmprestimos(){
		return this.emprestimos;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public String getDepartamento(){
		return this.departamento;
	}
	
	public void adicionarEmprestimo(Emprestimo emprestimo){
		this.adicionarEmprestimo(emprestimo);
	}


	public void removerEmprestimo(Emprestimo emprestimo) {
		for(Emprestimo e: this.emprestimos){
			if(e.equals(emprestimo)){
				this.emprestimos.remove(e);
				break;
			}
		}
	}

	public String getNome() {
		return nome;
	}
	
	public String getCPF(){
		return cpf;
	}

}
