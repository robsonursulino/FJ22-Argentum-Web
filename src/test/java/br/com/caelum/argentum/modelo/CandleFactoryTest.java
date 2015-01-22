package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CandleFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		LocalDate hoje = LocalDate.now();
		  
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
	    Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);
	 
	    List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
	 
	    CandleFactory fabrica = new CandleFactory();
	    Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);
	    
	    assertEquals(40.5, candle.getAbertura(), 0.00001);
	    assertEquals(42.3, candle.getFechamento(), 0.00001);
	    assertEquals(39.8, candle.getMinimo(), 0.00001);
	    assertEquals(45.0, candle.getMaximo(), 0.00001);
	    assertEquals(16760.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void semNegociacoesGeraCandleComZeros() {
		LocalDate hoje = LocalDate.now();
		  
	    List<Negociacao> negociacoes = Arrays.asList();
	 
	    CandleFactory fabrica = new CandleFactory();
	    Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);
	    
	    assertEquals(0.0, candle.getAbertura(), 0.00001);
	    assertEquals(0.0, candle.getFechamento(), 0.00001);
	    assertEquals(0.0, candle.getMinimo(), 0.00001);
	    assertEquals(0.0, candle.getMaximo(), 0.00001);
	    assertEquals(0.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
		LocalDate hoje = LocalDate.now();
		  
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1);
	 
	    CandleFactory fabrica = new CandleFactory();
	    Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);
	    
	    assertEquals(40.5, candle.getAbertura(), 0.00001);
	    assertEquals(40.5, candle.getFechamento(), 0.00001);
	    assertEquals(40.5, candle.getMinimo(), 0.00001);
	    assertEquals(40.5, candle.getMaximo(), 0.00001);
	    assertEquals(4050.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void negociacoesEmOrdemCrescenteDeValor() {
		LocalDate hoje = LocalDate.now();
		  
		Negociacao negociacao1 = new Negociacao(40, 100, hoje);
		Negociacao negociacao2 = new Negociacao(50, 100, hoje);
		Negociacao negociacao3 = new Negociacao(60, 100, hoje);
	    Negociacao negociacao4 = new Negociacao(70, 100, hoje);
	 
	    List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
	 
	    CandleFactory fabrica = new CandleFactory();
	    Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);
	    
	    assertEquals(40, candle.getAbertura(), 0.00001);
	    assertEquals(70, candle.getFechamento(), 0.00001);
	    assertEquals(40, candle.getMinimo(), 0.00001);
	    assertEquals(70, candle.getMaximo(), 0.00001);
	    assertEquals(22000, candle.getVolume(), 0.00001);
	}

	@Test
	public void negociacoesEmOrdemDecrescenteDeValor() {
		LocalDate hoje = LocalDate.now();
		  
		Negociacao negociacao1 = new Negociacao(70, 100, hoje);
		Negociacao negociacao2 = new Negociacao(60, 100, hoje);
		Negociacao negociacao3 = new Negociacao(50, 100, hoje);
	    Negociacao negociacao4 = new Negociacao(40, 100, hoje);
	 
	    List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
	 
	    CandleFactory fabrica = new CandleFactory();
	    Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);
	    
	    assertEquals(70, candle.getAbertura(), 0.00001);
	    assertEquals(40, candle.getFechamento(), 0.00001);
	    assertEquals(40, candle.getMinimo(), 0.00001);
	    assertEquals(70, candle.getMaximo(), 0.00001);
	    assertEquals(22000, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
		LocalDate hoje = LocalDate.now();
		
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
	    Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);
	    
	    LocalDate amanha = hoje.plusDays(1);
	    
   		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
	    Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);
		
	    LocalDate depois = amanha.plusDays(1);
	    
   		Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
	    Negociacao negociacao8 = new Negociacao(52.3, 100, depois);
	    
	    List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4,
	    		negociacao5, negociacao6, negociacao7, negociacao8);
	    
	    CandleFactory factory = new CandleFactory();
	    
	    List<Candle> candles = factory.constroiCandles(negociacoes);
	    
	    assertEquals(3, candles.size());
	    assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
	    assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
	    assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
	    assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
	    assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
	    assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);	    
	}
	
	@Test(expected=IllegalStateException.class)
	public void naoPermiteConstruirCandlesComNegociacoesForaDeOrdem() {
		LocalDate hoje = LocalDate.now();
		LocalDate amanha = hoje.plusDays(1);
		LocalDate depois = amanha.plusDays(1);
		
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
	    Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);
	    
   		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
	    Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);
	    
   		Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
	    Negociacao negociacao8 = new Negociacao(52.3, 100, depois);
	    
	    List<Negociacao> negociacoes = Arrays.asList(negociacao8, negociacao7, negociacao6, negociacao5,
	    		negociacao4, negociacao3, negociacao2, negociacao1);
	    
	    CandleFactory factory = new CandleFactory();
	    
	    List<Candle> candles = factory.constroiCandles(negociacoes);
	    
	}
	

}
