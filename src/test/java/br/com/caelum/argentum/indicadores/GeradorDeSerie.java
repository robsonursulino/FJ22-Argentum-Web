package br.com.caelum.argentum.indicadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorDeSerie {
	
	/**
	 * Serve para ajudar a fazer os testes.
	 * 
	 * Recebe uma sequência de valores e cria candles com abertura, fechamento, 
	 * minimo e maximo iguais, mil de volume e data de hoje. Finalmente, devolve
	 * tais candles encapsuladas em uma Serie Temporal.
	**/	
	public static SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<Candle>();
		for (double d : valores) {
			candles.add(new Candle.Builder()
				.abertura(d)
				.fechamento(d)
				.minimo(d)
				.maximo(d)
				.volume(d)
				.data(LocalDate.now())
				.Build());
		}
		return new SerieTemporal(candles);
	}

}
