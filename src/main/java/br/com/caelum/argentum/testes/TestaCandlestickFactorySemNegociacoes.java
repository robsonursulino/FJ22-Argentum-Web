package br.com.caelum.argentum.testes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandleFactory;
import br.com.caelum.argentum.modelo.Negociacao;

public class TestaCandlestickFactorySemNegociacoes {
	
	public static void main(String[] args) {
	
		LocalDate hoje = LocalDate.now();
	  
	    List<Negociacao> negociacoes = Arrays.asList();
	 
	     CandleFactory fabrica = new CandleFactory();
	     Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);
	
	     System.out.println(candle);
	     
	}

}
