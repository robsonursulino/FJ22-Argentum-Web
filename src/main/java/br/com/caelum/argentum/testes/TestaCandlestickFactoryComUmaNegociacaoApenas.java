package br.com.caelum.argentum.testes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandleFactory;
import br.com.caelum.argentum.modelo.Negociacao;

public class TestaCandlestickFactoryComUmaNegociacaoApenas {
	
	public static void main(String[] args) {
	
		LocalDate hoje = LocalDate.now();
	  
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
	 
	    List<Negociacao> negociacoes = Arrays.asList(negociacao1);
	 
	     CandleFactory fabrica = new CandleFactory();
	     Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);
	
	     System.out.println(candle);
	     
	}

}
