package br.com.kenneth.jogodavelha.core;

import br.com.kenneth.jogodavelha.Constantes;
import br.com.kenneth.jogodavelha.fc.Face;

public class Tabuleiro {

	private char[][] matriz;

	public Tabuleiro() {
		matriz = new char[Constantes.TABULEIRO_SIZE][Constantes.TABULEIRO_SIZE];
		limpar();
	}

	public void limpar() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = ' ';
			}
		}
	}

	public void imprime() {
		Face.pulaLinha();

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				Face.imprimeMesmaLinha(String.valueOf(matriz[i][j]));

				if (j < matriz[i].length - 1) {
					Face.imprimeMesmaLinha(" | ");
				}
			}
			Face.pulaLinha();

			if (i < matriz.length - 1) {
				Face.imprimeTexto("---------");
			}
		}
	}

	public boolean cheio() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == ' ') {
					return false;
				}
			}
		}

		return true;
	}

	public boolean play(Jogador jogador, Move move) throws InvalidaMoveException {
		int i = move.getI();
		int j = move.getJ();

		if (i < 0 || j < 0 || i >= Constantes.TABULEIRO_SIZE || j >= Constantes.TABULEIRO_SIZE ) {
			throw new InvalidaMoveException("A jogada não condiz com o tamanho do tabuleiro.\nJogue novamente!");
		}
		
		if (matriz[i][j] != ' ') {
			throw new InvalidaMoveException("O oponente já realizou esta jogada!\n\n Tente outra jogada! ");
		}
		
		matriz[i][j] = jogador.getSimbolo();
		return checarLinhas(jogador) || checarColunas(jogador) || checarDiagonal1(jogador) || chegarDiagonal2(jogador);

	}

	private boolean checarLinhas(Jogador jogador) {
		for (int i = 0; i < Constantes.TABULEIRO_SIZE; i++) {
			if (checarLinha(i, jogador)) {
				return true;
			}
		}

		return false;
	}

	private boolean checarLinha(int i, Jogador jogador) {
		char simbolo = jogador.getSimbolo();

		for (int j = 0; j < Constantes.TABULEIRO_SIZE; j++) {
			if (matriz[i][j] != simbolo) {
				return false;
			}
		}

		return true;
	}

	private boolean checarColunas(Jogador jogador) {
		for (int j = 0; j < Constantes.TABULEIRO_SIZE; j++) {
			if (checarLinha(j, jogador)) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unused")
	private boolean checarColuna(int j, Jogador jogador) {
		char simbolo = jogador.getSimbolo();

		for (int i = 0; i < Constantes.TABULEIRO_SIZE; i++) {
			if (matriz[i][j] != simbolo) {
				return false;
			}
		}

		return true;
	}

	private boolean checarDiagonal1(Jogador jogador) {
		char simbolo = jogador.getSimbolo();

		for (int i = 0; i < Constantes.TABULEIRO_SIZE; i++) {
			if (matriz[i][i] != simbolo) {
				return false;
			}
		}

		return true;
	}

	private boolean chegarDiagonal2(Jogador jogador) {
		char simbolo = jogador.getSimbolo();
		int ultimaLinha = Constantes.TABULEIRO_SIZE - 1;

		for (int i = ultimaLinha, j = 0; i >= 0; i--, j++) {
			if (matriz[i][j] != simbolo) {
				return false;
			}

		}

		return true;

	}
}
