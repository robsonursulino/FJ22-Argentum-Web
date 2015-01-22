package br.com.caelum.argentum.modelo;

import java.util.List;

public class SerieTemporal {

	private List<Candle> candles;

	public SerieTemporal(List<Candle> candles) {
		if (candles == null)
			throw new IllegalArgumentException("Não cria sem lista de candles");
		this.candles = candles;
	}

	public Candle getCandle(int posicao) {
		return this.candles.get(posicao);
	}

	public int getUltimaPosicao() {
		return this.candles.size() - 1;
	}

}
