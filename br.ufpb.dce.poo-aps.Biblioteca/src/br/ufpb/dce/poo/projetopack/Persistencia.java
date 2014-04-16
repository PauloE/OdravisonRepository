package br.ufpb.dce.poo.projetopack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Persistencia {
	
	List<Livro> livros;
	List<Emprestimo> emprestimosAtivos;
	List<Usuario> usuarios;
	
	public Persistencia(){
		this.livros = new LinkedList<Livro>();
		this.emprestimosAtivos = new LinkedList<Emprestimo>();
		this.usuarios = new LinkedList<Usuario>();
	}
	public void gravarEmprestimos(String nomeArquivo) throws IOException {
		BufferedWriter gravador = null;
		try {
			gravador = new BufferedWriter(new FileWriter("C:\\date\\datas.txt"));
			for (Emprestimo e: this.emprestimosAtivos){
				gravador.write(e.getUsuario().getMatricula() +"\n");
				gravador.write(e.getLivro().getCodigo() +"\n");
				gravador.write(e.getDataEmprestimo() +"\n");
				//gravador.write(e.getDataEmprestimo().MONTH +"\n");
				//gravador.write(e.getDataEmprestimo().YEAR +"\n");
				gravador.write(e.getDataDevolucao() +"\n");
			}
		} finally {
			if (gravador!=null){
				gravador.close();
			}
		}		
	}
	
	/*public void carregarEmprestimosDeArquivo(String nomeArquivo) throws IOException, EmprestimoJaExisteException{
		BufferedReader leitor = null;
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));
			String matricula = null;
			String codigoLivro = null;
			String 
			do {
				matricula = leitor.readLine(); // lê a próxima linha do arquivo: matricula do usuário
				
				if (nomeProf != null){
                    String matriculaProf = leitor.readLine(); //Lê a próxima linha do arquivo, matrícula do professor
				    this.cadastraProfessor(nomeProf, matriculaProf);
                }
			} 
		while(matricula != null); //vai ser null quando chegar no fim do arquivo
		} 
		finally {
			if (leitor!=null){
				leitor.close();
			}
		}*/
		
		/*try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));
			String dado = null;
			do {
				dado = leitor.readLine(); // lê a próxima linha do arquivo, dado do emprestimo
				if (nomeProf != null){
                    String matriculaProf = leitor.readLine(); //Lê a próxima linha do arquivo, matrícula do professor
				    this.cadastraProfessor(nomeProf, matriculaProf);
                }
			} 
		while(nomeProf != null); //vai ser null quando chegar no fim do arquivo
		} 
		finally {
			if (leitor!=null){
				leitor.close();
			}
		}*/
	}
