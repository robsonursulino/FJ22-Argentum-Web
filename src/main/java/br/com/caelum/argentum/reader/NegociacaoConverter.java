package br.com.caelum.argentum.reader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.caelum.argentum.modelo.Negociacao;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class NegociacaoConverter implements Converter {

	@Override
	public boolean canConvert(Class clazz) {
		return clazz.equals(Negociacao.class);
	}

	@Override
	public void marshal(Object obj, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		Negociacao negociacao = (Negociacao) obj;
		
		writer.startNode("preco");
		writer.setValue(String.valueOf(negociacao.getPreco()));
		writer.endNode();
		
		writer.startNode("quantidade");
		writer.setValue(String.valueOf(negociacao.getQuantidade()));
		writer.endNode();
		
		writer.startNode("data");
		writer.setValue(negociacao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		double preco = 0;
		int quantidade = 0;
		LocalDate data = null;
		
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if (reader.getNodeName().equals("preco")) {
				preco = reader.getValue().equals("") ? 0 : Double.parseDouble(reader.getValue());
			} else if (reader.getNodeName().equals("quantidade")) {
				quantidade = reader.getValue().equals("") ? 0 : Integer.parseInt(reader.getValue());
			} else if (reader.getNodeName().equals("time")) {
				data = reader.getValue().equals("") ? null : LocalDate.parse(reader.getValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			}	
			reader.moveUp();
		}	

		return new Negociacao(preco, quantidade, data);
	}

}
