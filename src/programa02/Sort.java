package programa02;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Sort {
	
	private int passo;
	
	private double inicioMerge, inicioQuick;
	private double fimMerge, fimQuick;
	private double tempoMerge, tempoQuick;
	
	File arquivoMerge = new File("./MergeSort.txt");	// Nomes dos arquivos de saída
	File arquivoQuick = new File("./QuickSort.txt");	// Ambos são salvos no diretório do projeto java desse programa
	
	private Arquivo dados = new Arquivo();
	
	private Candidato[] candidatosMergeSort;	// Criamos vetores de objetos da classe Candidato para cada metódo de ordenação
	private Candidato[] candidatosQuickSort;
	
  	public Sort(Candidato[] candidatos) throws IOException {	// Criamos um construtor para receber os dados do vetor Candidato carregado com os dados do arquivo de entrada 
		this.candidatosMergeSort = new Candidato[candidatos.length];
		this.candidatosQuickSort = new Candidato[candidatos.length];
		
		for(int i = 0; i < candidatos.length; i++) {	// Inicializamos os dois vetores da classe Sort com os dados dos arquivos de entrada
			this.candidatosMergeSort[i] = new Candidato(candidatos[i].getNome(), candidatos[i].getIdade(), 
					candidatos[i].getAcertosGerais(), candidatos[i].getAcertosEspec(), candidatos[i].getMediaPonderada());
			this.candidatosQuickSort[i] = new Candidato(candidatos[i].getNome(), candidatos[i].getIdade(), 
					candidatos[i].getAcertosGerais(), candidatos[i].getAcertosEspec(), candidatos[i].getMediaPonderada());
		}
 	}
	
  	public void executarMetodos() throws IOException {
  		
  		try(BufferedWriter arqMerge = new BufferedWriter(new FileWriter(arquivoMerge, false))) { 	// Aqui é criado ou aberto para adicionar o arquivo MergeSort.txt
		} catch(IOException e) {
			e.printStackTrace();
		}
		try(BufferedWriter arqQuick = new BufferedWriter(new FileWriter(arquivoQuick, false))) {	// Aqui é criado ou aberto para adicionar o arquivo QuickSort.txt
		} catch(IOException e) {
			e.printStackTrace();
		}
  		
		//MergeSort
  		passo = 0; 	// Variável para contar os passos de cada algoritmo
  		dados.adicionarLinha(arquivoMerge, candidatosMergeSort, passo);	// Adiciona no arquivo de saida a ordem dos candidatos antes de serem ordenados
		passo++;
		inicioMerge = System.currentTimeMillis();	// Começo da contagem de tempo
		mergeSort(0, candidatosMergeSort.length - 1);	// Inicializamos o MergeSort com os valores inicial e final (o -1 é devido ao fato de que o metodo length não contar do 0)
		fimMerge = System.currentTimeMillis();// Final da contagem de tempo
		tempoMerge = (fimMerge - inicioMerge)/1000;	// Tempo de execução (em segundos)
		dados.adicionarTempo(arquivoMerge, tempoMerge);	// Adicionamos o tempo no arquivo de saida
		
		//QuickSort
		passo = 0;	// Mesma lógica do MergeSort
		dados.adicionarLinha(arquivoQuick, candidatosQuickSort, passo);
		passo++;
		inicioQuick = System.currentTimeMillis();
		quickSort(0, candidatosQuickSort.length - 1);
		fimQuick = System.currentTimeMillis();
		tempoQuick = (fimQuick - inicioQuick)/1000;
		dados.adicionarTempo(arquivoQuick, tempoQuick);
  	}
  	
  	public double getTempoMerge() {
  		return tempoMerge;
  	}
  	
  	public double getTempoQuick() {
  		return tempoQuick;
  	}
  	
	public Candidato[] getCandidatosMergeSort() {
		return candidatosMergeSort;
	}
	
	public Candidato[] getCandidatosQuickSort() {
		return candidatosQuickSort;
	}
	
	private void mergeSort(int inicio, int fim) throws IOException {
		if(inicio < fim) {
			int meio = (inicio + fim) / 2;
			mergeSort(inicio, meio);
			mergeSort(meio+1, fim);
			merge(inicio, meio, fim);
		}	
	}
	
	private void merge(int inicio, int meio, int fim) throws IOException {
		int i = 0, j = 0;
		int k = inicio;
		int tamEsq = meio - inicio + 1;
		int tamDir = fim - meio;
		
		Candidato[] candidatosEsq = new Candidato[tamEsq];	// Usamos o algoritmo que foi disponibilizado pelo professor
		Candidato[] candidatosDir = new Candidato[tamDir];	// Nos criamos dois vetores de objeto Candidato
		
		for(int l = 0; l < tamEsq; l++) {
			candidatosEsq[l] = candidatosMergeSort[inicio + l];	// Inicializamos o vetor esquerdo com os valores da esquerda até o meio do vetor principal
		}
		
		for(int l = 0; l < tamDir; l++) {
			candidatosDir[l] = candidatosMergeSort[l + meio + 1];	// Inicializamos o vetor direito com os valores da direita até o meio do vetor principal
		}
		
		while(i < tamEsq && j < tamDir) {	// No laço While aplicamos os casos de desempate da prova
			if(candidatosEsq[i].getMediaPonderada() > candidatosDir[j].getMediaPonderada()) {
				candidatosMergeSort[k] = candidatosEsq[i];
				k++;
				i++;
			} else if(candidatosEsq[i].getMediaPonderada() == candidatosDir[j].getMediaPonderada()) {
				
				if(candidatosEsq[i].getAcertosEspec() > candidatosDir[j].getAcertosEspec()) {
					candidatosMergeSort[k] = candidatosEsq[i];
					k++;
					i++;
				} else if(candidatosEsq[i].getAcertosEspec() == candidatosDir[j].getAcertosEspec()) {
					
					if(candidatosEsq[i].getAcertosGerais() > candidatosDir[j].getAcertosGerais()) {
						candidatosMergeSort[k] = candidatosEsq[i];
						k++;
						i++;
					} else if(candidatosEsq[i].getAcertosGerais() == candidatosDir[j].getAcertosGerais()) {
						
						if(candidatosEsq[i].getIdade() > candidatosDir[j].getIdade()) {
							candidatosMergeSort[k] = candidatosEsq[i];
							k++;
							i++;
						} else if(candidatosEsq[i].getIdade() == candidatosDir[j].getIdade()) {
							candidatosMergeSort[k] = candidatosEsq[i];
							k++;
							i++;
						} else {
							candidatosMergeSort[k] = candidatosDir[j];
							k++;
							j++;
						}
						
					} else {
						candidatosMergeSort[k] = candidatosDir[j];
						k++;
						j++;
					}
					
				} else {
					candidatosMergeSort[k] = candidatosDir[j];
					k++;
					j++;
				}
				
			} else {
				candidatosMergeSort[k] = candidatosDir[j];
				k++;
				j++;
			}
			
		}
		
		while(i < tamEsq) {
			candidatosMergeSort[k] = candidatosEsq[i];
			k++;
			i++;
		}
		
		
		while(j < tamDir) {
			candidatosMergeSort[k] = candidatosDir[j];
			k++;
			j++;
		}
		
		dados.adicionarLinha(arquivoMerge, candidatosMergeSort, passo);	// Adicionamos o estado de ordenação atual dos candidatos no arquivo de saida MergeSort.txt
		passo++;
		
	}
	
	private void quickSort(int inicio, int fim) throws IOException {	// O pivô selecionado foi o último elemento do vetor
		if(inicio < fim) {
			int posPivo = particionar(inicio, fim);
			quickSort(inicio, posPivo - 1);
			quickSort(posPivo + 1, fim);
		}
	}
	
	private int particionar(int inicio, int fim) throws IOException { 	// Diferente do MergeSort, não precisamos utilizar vetores auxiliares
		int i = inicio - 1;
		Candidato pivo = new Candidato(candidatosQuickSort[fim].getNome(), candidatosQuickSort[fim].getIdade(), 
				candidatosQuickSort[fim].getAcertosGerais(), candidatosQuickSort[fim].getAcertosEspec(), candidatosQuickSort[fim].getMediaPonderada()); 	// Inicializamos o pivô com os dados do último candidato presente no vetor candidatosQuickSort
		for(int j = inicio; j < fim; j++) {	// No laço de comparação adicionamos as regras de desempate
			if(candidatosQuickSort[j].getMediaPonderada() > pivo.getMediaPonderada()) {
				i++;
				trocar(i, j);
			} else if(candidatosQuickSort[j].getMediaPonderada() == pivo.getMediaPonderada()) {
				
				if(candidatosQuickSort[j].getAcertosEspec() > pivo.getAcertosEspec()) {
					i++;
					trocar(i, j);
				} else if(candidatosQuickSort[j].getAcertosEspec() == pivo.getAcertosEspec()) {
					
					if(candidatosQuickSort[j].getAcertosGerais() > pivo.getAcertosGerais()) {
						i++;
						trocar(i, j);
					} else if(candidatosQuickSort[j].getAcertosGerais() == pivo.getAcertosGerais()) {
						
						if(candidatosQuickSort[j].getIdade() >= pivo.getIdade()) {
							i++;
							trocar(i, j);
						} 
						
					} 
					
				} 
				
			} 
		}
		
		trocar(i+1, fim);
		
		dados.adicionarLinha(arquivoQuick, candidatosQuickSort, passo);
		passo++;
		
		return i+1;
	}
	
	private void trocar(int i, int j) {
		Candidato aux = new Candidato(candidatosQuickSort[i].getNome(), candidatosQuickSort[i].getIdade(), 
				candidatosQuickSort[i].getAcertosGerais(), candidatosQuickSort[i].getAcertosEspec(), candidatosQuickSort[i].getMediaPonderada());

		candidatosQuickSort[i] = candidatosQuickSort[j];
		candidatosQuickSort[j] = aux;
	}
}