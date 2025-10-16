package programa02;

public class Candidato {

	/*
	 * Essa classe Candidato serve como estrutura para os dados dos candidatos da prova
	 */
	private String nome;
	private int idade;
	private int acertosGerais;
	private int acertosEspec;
	private double mediaPonderada;
	
	public Candidato(String nome, int idade, int acertosGerais, int acertosEspec) {
		this.nome = nome;
		this.idade = idade;
		this.acertosGerais = acertosGerais;
		this.acertosEspec = acertosEspec;
	}
	
	public Candidato(String nome, int idade, int acertosGerais, int acertosEspec, double mediaPonderada) {
		this.nome = nome;
		this.idade = idade;
		this.acertosGerais = acertosGerais;
		this.acertosEspec = acertosEspec;
		this.mediaPonderada = mediaPonderada;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public int getAcertosGerais() {
		return acertosGerais;
	}
	
	public int getAcertosEspec() {
		return acertosEspec;
	}

	public double getMediaPonderada() {
		return mediaPonderada;
	}

	public void setMediaPonderada(double mediaPonderada) {
		this.mediaPonderada = mediaPonderada;
	}
	
}
