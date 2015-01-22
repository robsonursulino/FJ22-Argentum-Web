package br.com.caelum.argentum.reader;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.ws.ClienteWebService;

import com.thoughtworks.xstream.converters.ConversionException;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste =
				"<list>" +
				"  <negociacao>" +
			    "    <preco>43.5</preco>" +
			    "    <quantidade>1000</quantidade>" +
			    "	 <data>18/07/2014</data>" +
				"  </negociacao>" +
			    "</list>";
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		LeitorXML leitor = new LeitorXML();
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(1, negociacoes.size());
		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(1000, negociacoes.get(0).getQuantidade());
		assertEquals(LocalDate.of(2014, 7, 18), negociacoes.get(0).getData());
	}

	@Test
	public void carregaXmlComTresNegociacaoEmListaUnitaria() {
		String xmlDeTeste =
				"<list>" +
				"  <negociacao>" +
			    "    <preco>43.5</preco>" +
			    "    <quantidade>1000</quantidade>" +
			    "	 <data>18/07/2014</data>" +
				"  </negociacao>" +
				"  <negociacao>" +
			    "    <preco>70.5</preco>" +
			    "    <quantidade>2000</quantidade>" +
			    "	 <data>17/07/2014</data>" +
				"  </negociacao>" +
				"  <negociacao>" +
			    "    <preco>785.7</preco>" +
			    "    <quantidade>500</quantidade>" +
			    "	 <data>16/07/2014</data>" +
				"  </negociacao>" +
			    "</list>";
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		LeitorXML leitor = new LeitorXML();
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(3, negociacoes.size());

		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(1000, negociacoes.get(0).getQuantidade());
		assertEquals(LocalDate.of(2014, 7, 18), negociacoes.get(0).getData());
		
		assertEquals(70.5, negociacoes.get(1).getPreco(), 0.00001);
		assertEquals(2000, negociacoes.get(1).getQuantidade());
		assertEquals(LocalDate.of(2014, 7, 17), negociacoes.get(1).getData());
		
		assertEquals(785.7, negociacoes.get(2).getPreco(), 0.00001);
		assertEquals(500, negociacoes.get(2).getQuantidade());
		assertEquals(LocalDate.of(2014, 7, 16), negociacoes.get(2).getData());
	}

	@Test
	public void carregaXmlSemNegociacoes() {
		String xmlDeTeste =
				"<list>" +
			    "</list>";
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		LeitorXML leitor = new LeitorXML();
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(0, negociacoes.size());
	}
	
	@Test
	public void carregaXmlComNegociacaoSemPreco() {
		String xmlDeTeste =
				"<list>" +
				"  <negociacao>" +
			    "    <preco></preco>" +
			    "    <quantidade>1000</quantidade>" +
			    "	 <data>18/07/2014</data>" +
				"  </negociacao>" +
			    "</list>";
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		LeitorXML leitor = new LeitorXML();
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(1, negociacoes.size());
		assertEquals(0, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(1000, negociacoes.get(0).getQuantidade());
		assertEquals(LocalDate.of(2014, 7, 18), negociacoes.get(0).getData());
	}
	
	@Test(expected=ConversionException.class)
	public void carregaXmlComNegociacaoSemData() {
		String xmlDeTeste =
				"<list>" +
				"  <negociacao>" +
			    "    <preco>43.5</preco>" +
			    "    <quantidade>1000</quantidade>" +
			    "	 <data></data>" +
				"  </negociacao>" +
			    "</list>";
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		LeitorXML leitor = new LeitorXML();
		
		leitor.carrega(xml);
	}

	@Test
	public void carregaXmlComUmaNegociacaoSemNodePrecoEmListaUnitaria() {
		String xmlDeTeste =
				"<list>" +
				"  <negociacao>" +
			    "    <quantidade>1000</quantidade>" +
			    "	 <data>18/07/2014</data>" +
				"  </negociacao>" +
			    "</list>";
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		LeitorXML leitor = new LeitorXML();
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(1, negociacoes.size());
		assertEquals(0, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(1000, negociacoes.get(0).getQuantidade());
		assertEquals(LocalDate.of(2014, 7, 18), negociacoes.get(0).getData());
	}
	
	@Test
	public void carregaXMLDoArquivo() {
		List<Negociacao> negociacoes = new ClienteWebService().getNegociacoes();
		
		assertEquals(39.5, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(263454, negociacoes.get(negociacoes.size()-1).getQuantidade());
			
	}
	
}
