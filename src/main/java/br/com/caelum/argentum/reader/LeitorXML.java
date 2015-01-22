package br.com.caelum.argentum.reader;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXML {
	
	public List<Negociacao> carrega(InputStream input) {
		XStream stream = new XStream(new DomDriver());
//		stream.registerConverter(new NegociacaoConverter());
		stream.alias("negociacao", Negociacao.class);
		List<Negociacao> XML = (List<Negociacao>) stream.fromXML(input);
		return XML;
	}

}
