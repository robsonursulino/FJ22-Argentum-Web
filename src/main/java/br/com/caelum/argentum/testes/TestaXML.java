package br.com.caelum.argentum.testes;

import java.time.LocalDate;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.NegociacaoConverter;

public class TestaXML {
	
	public static void main(String[] args) {
		
		Negociacao negociacao = new Negociacao(42.3, 100, LocalDate.now());
		
		XStream stream = new XStream(new DomDriver());
		stream.registerConverter(new NegociacaoConverter());
		stream.alias("Negociacao", Negociacao.class);
		
		System.out.println(stream.toXML(negociacao));
	}	
}
