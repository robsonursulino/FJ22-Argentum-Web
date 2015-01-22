package br.com.caelum.argentum.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.IndicadorFactory;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandleFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;

@ManagedBean
@ViewScoped
public class ArgentumBean {
	
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String nomeMedia = "";
	private String nomeIndicadorBase = "";
	
	public ArgentumBean() {
		this.negociacoes = new ClienteWebService().getNegociacoes();
		geraGrafico();
	}

	public void geraGrafico() {
		System.out.println("PLOTANDO: " + nomeMedia + " de " + nomeIndicadorBase);
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());
		geradorGrafico.plotaIndicador(new IndicadorFactory(nomeIndicadorBase, nomeMedia).defineIndicador());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
	}
	
	public List<Negociacao> getNegociacao() {
		return negociacoes;
	}
	
	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}
	
   public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

	@PostConstruct
    public void test() {
       System.out.println("bean criado!!");
    }
   
}
