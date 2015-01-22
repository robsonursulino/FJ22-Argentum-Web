package br.com.caelum.argentum.indicadores;

import java.lang.reflect.Constructor;

public final class IndicadorFactory {
	
	private final String nomeIndicadorBase;
	private final String nomeMedia;

	public IndicadorFactory(String nomeIndicadorBase, String nomeMedia) {
		this.nomeIndicadorBase = nomeIndicadorBase;
		this.nomeMedia = nomeMedia;
	}
	
	public Indicador defineIndicador() {
		if (nomeIndicadorBase.equals("") || nomeMedia.equals("")) {
			return new MediaMovelSimples(new IndicadorFechamento());
		}
		String pacote = "br.com.caelum.argentum.indicadores.";
		try {
			Class<?> classeIndicadorBase = Class.forName(pacote + nomeIndicadorBase);
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();
			
			Class<?> classeMedia = Class.forName(pacote + nomeMedia);
			Constructor<?> constructorMedia = classeMedia.getConstructor(Indicador.class);
			Indicador indicador = (Indicador) constructorMedia.newInstance(indicadorBase);
			
			return indicador;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
