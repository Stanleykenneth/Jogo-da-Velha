package br.com.kenneth.jogodavelha.fc;

import br.com.softblue.commons.io.Console;

public class Face {

	public static void imprimeTexto(String texto){
		System.out.println(texto);
	}
	
	public static void imprimeMesmaLinha(String texto) {
		System.out.print(texto);
	}
	
	public static void pulaLinha() {
		System.out.println();
	}
	
	public static  void imprimeNomeJogo() {
		imprimeTexto("=================");
		imprimeTexto("| JOGO DA VELHA |");
		imprimeTexto("=================");
		pulaLinha();
	}
	
	public static String inserirDados(String texto) {
		imprimeMesmaLinha(texto + " ");
		return Console.readString();
	}
}
