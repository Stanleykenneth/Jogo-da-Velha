package br.com.kenneth.jogodavelha.core;

import java.io.IOException;

import br.com.kenneth.jogodavelha.Constantes;
import br.com.kenneth.jogodavelha.core.pontuacao.ArquivoPontuacao;
import br.com.kenneth.jogodavelha.core.pontuacao.Pontuacao;
import br.com.kenneth.jogodavelha.fc.Face;

public class Jogo {

	private Tabuleiro tabuleiro = new Tabuleiro();
	private Jogador[] jogadores = new Jogador[Constantes.SIMBOLO_JOGADORES.length];
	private int jogadorAtual = -1;
	private Pontuacao pontuacao;

	public void play() throws IOException {
		pontuacao = criaPontuacao();
		
		Face.imprimeNomeJogo();

		for (int i = 0; i < jogadores.length; i++) {
			jogadores[i] = criarJogador(i);
		}
		
		boolean fimDoJogo = false;
		Jogador jogadorAtual = proximoJogador();
		Jogador vencedor = null;
		
		while(!fimDoJogo) {
			tabuleiro.imprime();
			
			boolean sequenceFound;
			try {
			 sequenceFound = jogadorAtual.play();
				
			} catch (InvalidaMoveException e) {
				Face.imprimeTexto("ERRO: " + e.getMessage());
				continue;
			}
			
			
			if (sequenceFound) {
				fimDoJogo = true;
				vencedor = jogadorAtual;
				
			} else if(tabuleiro.cheio()) {
				fimDoJogo = true;
			}	
			
			jogadorAtual = proximoJogador();
		}		
				
		if (vencedor == null) {
			Face.imprimeTexto("O jogo terminou empatado!");
			
		} else {
			Face.imprimeTexto("O jogador '" + vencedor.getNome() + "' venceu o jogo! ");
			
			pontuacao.salvaPontos(vencedor);
		}
		
		tabuleiro.imprime();
		Face.imprimeTexto("Fim do jogo!");
	}

	private Jogador criarJogador(int index) {
		String nome = Face.inserirDados("Jogador " + (index + 1) + " =>");
		char simbolo = Constantes.SIMBOLO_JOGADORES[index];
		Jogador jogador = new Jogador(nome, tabuleiro, simbolo);
		
		Integer ponto = pontuacao.getPontos(jogador);
		
		if(ponto != null) {
			Face.imprimeTexto("O jogador '" + jogador.getNome() + "' já possui " + ponto + " vitória(s)!");
		}

		Face.imprimeTexto("O Jogador '" + nome + "' usará o simbolo '" + simbolo + "'");

		return jogador;
	}
	
	private Jogador proximoJogador() {
		/*jogadorAtual++;
		
		if(jogadorAtual >= jogadores.length) {
			jogadorAtual = 0;
		}		
		*/		
		jogadorAtual = (jogadorAtual + 1) % jogadores.length;
		return jogadores[jogadorAtual];				
	}
	
	private Pontuacao criaPontuacao() throws IOException {
		return new ArquivoPontuacao();
		
	}
}
