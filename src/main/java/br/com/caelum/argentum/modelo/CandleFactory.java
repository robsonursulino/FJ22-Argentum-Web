package br.com.caelum.argentum.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CandleFactory {

	public Candle constroiCandleParaData(LocalDate data,
			List<Negociacao> negociacoes) {

		double minimo = negociacoes.isEmpty() ? 0 : Double.MAX_VALUE;
		double maximo = 0;
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();
		double volume = 0;

		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();
			double preco = negociacao.getPreco();
			if (preco > maximo) {
				maximo = preco;
			}	
			if (preco < minimo) {
				minimo = preco;
			}
		}

		return new Candle.Builder()
			.abertura(abertura)
			.fechamento(fechamento)
			.minimo(minimo)
			.maximo(maximo)
			.volume(volume)
			.data(data)
			.Build();
	}

	public List<Candle> constroiCandles(List<Negociacao> negociacoes) {

		Collections.sort(negociacoes, (n1, n2) -> n1.getData().compareTo(n2.getData()));
		
		List<Candle> candles = new ArrayList<Candle>();
		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		LocalDate dataAtual = negociacoes.get(0).getData();
		
		for (Negociacao negociacao : negociacoes) {
			// Verifica a ordem das negociações
			if (negociacao.getData().isBefore(dataAtual)) {
				throw new IllegalStateException("Negociações em ordem errada");
			}
			// Se não for do mesmo dia, fecha candle e reinicia variáveis
			if (!negociacao.isMesmoDia(dataAtual)) {
				criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);
		}
		
		criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
		
		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles,
			List<Negociacao> negociacoesDoDia, LocalDate dataAtual) {
		candles.add(constroiCandleParaData(dataAtual, negociacoesDoDia));
	}

}
