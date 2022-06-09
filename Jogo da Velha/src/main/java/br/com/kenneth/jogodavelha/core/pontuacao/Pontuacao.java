package br.com.kenneth.jogodavelha.core.pontuacao;

import java.io.IOException;

import br.com.kenneth.jogodavelha.core.Jogador;

public interface Pontuacao {
    
	public Integer getPontos(Jogador jogador);
	
	public 	void salvaPontos(Jogador jogador) throws IOException;
}
