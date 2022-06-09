package br.com.kenneth.jogodavelha.core.pontuacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.kenneth.jogodavelha.core.Jogador;

public class ArquivoPontuacao implements Pontuacao {

	private static final Path PONTUACAO_ARQUIVO = Path.of("ponto.txt");
	private Map<String, Integer> pontoMap = new HashMap<>();

	public ArquivoPontuacao() throws IOException {
		setup();
	}

	private void setup() throws IOException {

		if (!Files.exists(PONTUACAO_ARQUIVO)) {
			Files.createFile(PONTUACAO_ARQUIVO);
		}

		try (BufferedReader reader = Files.newBufferedReader(PONTUACAO_ARQUIVO)) {
			String linha;

			while ((linha = reader.readLine()) != null) {
				String[] tokens = linha.split("\\|");

				pontoMap.put(tokens[0], Integer.parseInt(tokens[1]));
			}
		}
	}

	@Override
	public Integer getPontos(Jogador jogador) {
		return pontoMap.get(jogador.getNome().toUpperCase());
	}

	@Override
	public void salvaPontos(Jogador jogador) throws IOException {
		Integer ponto = getPontos(jogador);

		if (ponto == null) {
			ponto = 0;
		}
		
		pontoMap.put(jogador.getNome().toUpperCase(), ponto + 1);

		try (BufferedWriter writer = Files.newBufferedWriter(PONTUACAO_ARQUIVO)) {
			Set<Map.Entry<String, Integer>> entries = pontoMap.entrySet();

			for (Map.Entry<String, Integer> entry : entries) {
				String nome = entry.getKey().toUpperCase();
				Integer p = entry.getValue();
				writer.write(nome + "|" + p);
				writer.newLine();
			}

		}
	}

}
