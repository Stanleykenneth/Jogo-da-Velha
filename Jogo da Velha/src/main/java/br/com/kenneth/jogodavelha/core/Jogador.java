package br.com.kenneth.jogodavelha.core;

import br.com.kenneth.jogodavelha.fc.Face;

public class Jogador {

	private String nome;
	private Tabuleiro tabuleiro;
	private char simbolo;	
	
	public Jogador(String nome, Tabuleiro tabuleiro, char simbolo) {
		super();
		this.nome = nome;
		this.tabuleiro = tabuleiro;
		this.simbolo = simbolo;
	}	
	
	private Move validaMovimento() throws InvalidaMoveException {
		String moveStr = Face.inserirDados("Jogador '" + nome + "' =>");
		Move m = new Move(moveStr);
		return m;
	}
	
    public boolean play() throws InvalidaMoveException{
		Move move = validaMovimento();
		return tabuleiro.play(this, move);
	}
    
    public String getNome() {
		return nome;
	}	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

}
