package programa02;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.print("\t\t\tATENÇÂO!!\n\nColoque o arquivo que deseja ler no diretório desse projeto Java\n\n\n");
		System.out.print("Digite o nome do arquivo que deseja ler: ");
		String nomeArquivo = sc.nextLine();	
		System.out.println();
		
		File arquivoEntrada = new File("./" + nomeArquivo);	// Criamos um objeto a classe File com o diretório do arquivo de entrada
		
		Arquivo dados = new Arquivo(); 
		
		Candidato[] candidatos = dados.carregarArquivo(arquivoEntrada);	// Criamos um vetor de objetos da classe Candidato, que recebera os dados dos candidadatos do arquivo de entrada
		
		Sort organizar = new Sort(candidatos);	//	Criamos um objeto da classe Sort para usar os metódos de ordenação no vetor candidatos
		
		organizar.executarMetodos();	// Chamamos o método que executa tanto o método MergeSort quanto o QuickSort
		
		System.out.println("Arquivos MergeSort.txt e QuickSort.txt gerados com sucesso!");
		
		System.out.printf("\nTempo MergeSort: %.3fs\nTempo QuickSort: %.3fs\n\n", organizar.getTempoMerge(), organizar.getTempoQuick());
		
		if(organizar.getTempoMerge() < organizar.getTempoQuick()) {	// Mostramos no console qual método foi o mais rápido
			System.out.println("O MergeSort organizou a lista de candidatos mais rápido!");
		} else if(organizar.getTempoMerge() > organizar.getTempoQuick()) {
			System.out.println("O QuickSort organizou a lista de candidatos mais rápido!");
		} else {
			System.out.println("Ambos métodos tiveram o mesmo tempo de execução!");
		}
		
		sc.close();
	}	
}
