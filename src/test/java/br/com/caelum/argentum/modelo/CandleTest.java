package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class CandleTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		new Candle.Builder()
			.abertura(10)
			.fechamento(20)
			.minimo(20)
			.maximo(10)
			.volume(1000)
			.data(LocalDate.now())
			.Build();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeTerDataNula() {
		new Candle.Builder()
			.abertura(10)
			.fechamento(20)
			.minimo(10)
			.maximo(20)
			.volume(1000)
			.data(null)
			.Build();
	}

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeTerValorNegativo() {
		new Candle.Builder()
			.abertura(-10)
			.fechamento(-20)
			.minimo(-20)
			.maximo(-10)
			.volume(-1000)
			.data(LocalDate.now())
			.Build();
	}

	@Test
	public void quantoAberturaIgualFechamentoEhAlta() {
		Candle c = new Candle.Builder()
			.abertura(10)
			.fechamento(10)
			.minimo(10)
			.maximo(20)
			.volume(1000)
			.data(LocalDate.now())
			.Build();
		
		assertTrue(c.isAlta());
	}
	
	@Test(expected=IllegalStateException.class)
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
		new Candle.Builder()
			.abertura(10)
			.minimo(10)
			.maximo(20)
			.volume(1000)
			.data(LocalDate.now())
			.Build();
	}
	

}
