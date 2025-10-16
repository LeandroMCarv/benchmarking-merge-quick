package programa02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Arquivo {
	
	/*
	 * Essa classe foi criada para lidar com o arquivo de entrada e os de saida
	 */
	
	private int numCandidatos;
	private int pesoGerais, pesoEspec;
	
	public Candidato[] carregarArquivo(File arquivo) throws FileNotFoundException {
		
		//	Esse método serve para carregar o vetor de objetos da classe Candidatos com os dados presentes no arquivo de entrada
		// Tem como retorno esse mesmo vetor
		
		try(BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {	// Tentamos ler o arquivo de entrada
			
			String linha;
			
			linha = leitor.readLine();
			
			String[] primeiraLinha = linha.split(" ");	// O arquivo de entrada está estruturado com espaços
			
			// A primeira linha do arquivo são dados gerais da prova
			numCandidatos = Integer.parseInt(primeiraLinha[0]); 	// Pegamos o número de candidatos
			pesoGerais = Integer.parseInt(primeiraLinha[1]);	// O valor do peso nas questões gerais
			pesoEspec = Integer.parseInt(primeiraLinha[2]);	// Peso questões específicas
			
			Candidato[] candidatos = new Candidato[numCandidatos];	// Criamos um vetor de objetos da classe Candidato
			
			int i = 0;
			
			while((linha = leitor.readLine()) != null) {	// Lê as linhas restantes, que são os dados dos candidatos, até o final do arquivo
				
				String[] dados = linha.split(" ");
				
				candidatos[i] = new Candidato(dados[0], Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), Integer.parseInt(dados[3]));
				
				i++;
			}
			
			calcularMediaPonderada(candidatos);	// Calcula a média ponderada de cada candidato e adiciona em seus respectivos objetos
			
			return candidatos;	// Retornamos o vetor candidatos com todos os dados do arquivo
			
		} catch(FileNotFoundException e) {
			throw e;
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public void adicionarLinha(File arquivo, Candidato[] candidatos, int passo) throws IOException {
		
		// Esse método serve para podermos adicionar os estados de organização dos candidatos em cada passo de execução dos algoritmos ordenativos nos arquivos de saida
		
		int i = 0;
		
		try(BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo, true))) {
			
			escritor.write("Passo "+ passo + ": ");
			
			while(i < candidatos.length) {
				escritor.write(candidatos[i].getNome() + "-" + String.format("%.2f", candidatos[i].getMediaPonderada()) + " ");
				i++;
			}
			
			escritor.write("\n");
		
		} catch(IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public void adicionarTempo(File arquivo, double tempo) {
		
		// Serve para adicionar o tempo de execução no arquivo de saida
		
		try(BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo, true))) {
					
			escritor.write("Tempo de Execução: " + tempo + "segundo(s)");;
			
		} catch(IOException e) {
				e.printStackTrace();
		}
	}

	public int getNumCandidatos() {
		return numCandidatos;
	}
	
	public int getPesoGerais() {
		return pesoGerais;
	}

	public int getPesoEspec() {
		return pesoEspec;
	}

	private void calcularMediaPonderada(Candidato[] candidatos) {
		
		// Calcula a média ponderada de cada candidato e adiciona em seus respectivos objetos;
		
		double mediaPonderada;
		
		for(int i = 0; i < candidatos.length; i++) {
			mediaPonderada = (double)((candidatos[i].getAcertosGerais() * pesoGerais) + (candidatos[i].getAcertosEspec() * pesoEspec)) / (pesoGerais + pesoEspec);
			candidatos[i].setMediaPonderada(mediaPonderada);
		}	
	}
}
