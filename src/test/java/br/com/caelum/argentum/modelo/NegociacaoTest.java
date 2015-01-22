package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoImutavel() {
		
		LocalDate data = LocalDate.of(2014, Month.JULY, 15);
		
		Negociacao negociacao = new Negociacao(10, 5, data);
		
		negociacao.getData().plusDays(10);
		
		assertEquals(15, data.get(ChronoField.DAY_OF_MONTH));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 10, null);
	}
	
	@Test
	public void mesmoDia() {
		LocalDate data = LocalDate.of(2014, 1, 3);
		
		Negociacao negociacao = new Negociacao(43.5, 1000, LocalDate.now());
		
		assertFalse(negociacao.isMesmoDia(data));
		
	}

}
