package br.ufpb.dce.poo.projetopack;
import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;


public class Biblioteca {
	Configuracao configuracao = Configuracao.getInstance();
	List<Livro> livros;
	List<Emprestimo> emprestimosAtivos;
	List<Usuario> usuarios;
	
	public void CadastrarLivro (Livro L){
		this.livros.add(L);
	}
	
	public void CadastrarUsuario (Usuario U){
		this.usuarios.add(U);
	}
	
	public Usuario getUsuario(String mat) throws UsuarioInexistenteException{
		for (Usuario u: usuarios){
			if (u.getMatricula() == mat){
				return u;
			}
		}
		throw new UsuarioInexistenteException ("Este usuário não existe");
	}
	
	public Livro getLivro (String codLivro) throws LivroInexistenteException{
		for (Livro l: livros){
			if(l.getCodigo().equals(codLivro)){
				return l;
			}
		}
		throw new LivroInexistenteException ("Este Livro não está cadastrado");
	}
	
	public List<Emprestimo> listarEmprestimosEmAtraso () throws ListaDeAtrasoInexistenteException{
		Calendar DiaDeHoje = Calendar.getInstance();
		List<Emprestimo> atraso = new LinkedList<Emprestimo>();
		for (Emprestimo e: this.emprestimosAtivos){
			if (e.getDataDevolucao().before(DiaDeHoje)){
				atraso.add(e);
			}
		}
		if (atraso.size() == 0) {
			throw new ListaDeAtrasoInexistenteException ("Não existem usuarios em atraso");
		}
		return atraso;
		
	}
	
	public double calcularMulta (Usuario u) throws ListaDeAtrasoInexistenteException{
		
		long diasAtraso = 0;
		Configuracao c = Configuracao.getInstance();
		double total = 0;
		for (Emprestimo e: this.listarEmprestimosEmAtraso()){
			if (e.getUsuario().equals(u)){
				diasAtraso += diasEntre(e.getDataDevolucao(), Calendar.getInstance());
			}
			
		}
		total = diasAtraso * c.getValorMulta();
		
		return total;
		
		
	}
	
	public long diasEntre(Calendar a, Calendar b){ 
		Calendar dInicial = a;
		Calendar dFinal = b;
		long diferenca = dFinal.getTimeInMillis() - dInicial.getTimeInMillis();
		int tempoDia = 1000 * 60 * 60 * 24;
		long diasDiferenca = diferenca / tempoDia;
		return diasDiferenca;
	   }
	
	public Calendar calculaDataDevolucaoProfessor(){
		Calendar dataAtual = Calendar.getInstance();
		System.out.println(dataAtual);
		dataAtual.set(Calendar.DAY_OF_MONTH, 30);
		System.out.println(dataAtual);
		
		return dataAtual;
	}
	
	public void emprestarLivro (Usuario u, Livro lv) throws NumeroDeLivrosEmprestadosException, UsuarioEmAtrasoException, QuantidadeDeLivrosInsuficienteException, ListaDeAtrasoInexistenteException{
		if (u.getEmprestimos().size() == 3){
			throw new NumeroDeLivrosEmprestadosException ("Usuário atingiu limite de emprestimos");
		}
		for (Emprestimo e: this.listarEmprestimosEmAtraso()){
			if (e.getUsuario().equals(u)){
				throw new UsuarioEmAtrasoException ("O Caboclo está devendo, cobre ao danado!");
			}
		}
		for (Livro l: this.livros){
			if (l.getCodigo().equals(lv.getCodigo()) && l.getQuantidade() == 1){
				throw new QuantidadeDeLivrosInsuficienteException("Quantidade de livros insuficientes para emprestimo.");
			}
		}
		
		Calendar diaDevolucao = Calendar.getInstance();
		diaDevolucao.add(Calendar.DAY_OF_YEAR, u.getQuantDiasEmprestimo());
		Emprestimo e = new Emprestimo (u, lv, Calendar.getInstance(), diaDevolucao);
		for (Livro livro: this.livros){
			if (livro.getCodigo().equals(lv.getCodigo())){
				lv.setQuantidade(lv.getQuantidade()-1);
			}
		}
		this.emprestimosAtivos.add(e);
		u.adicionarEmprestimo(e);		
			
	}
	
	public void devolverLivro (Usuario u, Livro lv)throws EmprestimoInexistenteException{
		boolean emprestou = false;
		for (Emprestimo el: u.getEmprestimos()){
			if (el.getLivro().equals(lv)){
				for (Emprestimo el2: this.emprestimosAtivos){
					if (el2.getLivro().equals(lv) && el2.getUsuario().equals(u)){
						this.emprestimosAtivos.remove(el2);
						u.getEmprestimos().remove(el2);
						emprestou = true;
						for (Livro livro : this.livros){
							if (livro.getCodigo().equals(lv.getCodigo())){
								livro.setQuantidade(livro.getQuantidade()+1);
							}
						}
					}
				}
			}
		}
		if (emprestou == false){
			throw new EmprestimoInexistenteException ("O usuário não possui o emprestimo referênte.");
		}
		
	}
	
	
	
	public static void main(String[] args) {
		/*Calendar a = Calendar.getInstance();
		Calendar d = Calendar.getInstance();
		d.set(2014, 1, 1);
		Biblioteca b = new Biblioteca();
		b.calculaDataDevolucaoProfessor();**/
		
		/*Calendar dataAtual = Calendar.getInstance();
		System.out.println(Calendar.DAY_OF_MONTH +" "+ Calendar.MONTH );
		dataAtual.add(Calendar.DAY_OF_MONTH, 10);
		System.out.println(Calendar.DAY_OF_MONTH +" " + Calendar.MONTH);*/
		
		//Calendar c = Calendar.getInstance();
		//System.out.println(c);
		//DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT); //DateFormat apenas para formatar. Para gerenciar as datas não usa cabeção!!!
		//df.format(c.getTime());
		
		//c.add(Calendar.DAY_OF_YEAR,30 );
		///System.out.println(df.format(c.getTime()));
		//System.out.println(Calendar.DAY_OF_MONTH);
		//System.out.println(c.getTime());
		
		Calendar diaDevolucao = Calendar.getInstance();
		diaDevolucao.add(Calendar.DAY_OF_YEAR, 1);
		System.out.println(diaDevolucao.DAY_OF_MONTH +  " "  + diaDevolucao.DAY_OF_YEAR);
		//Emprestimo e = new Emprestimo (u, lv, Calendar.getInstance(), diaDevolucao);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



