package br.com.caelum.argentum.modelo;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Candle {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final LocalDate data;
	
	public static class Builder {
		private double abertura;
		private double fechamento;
		private double minimo;
		private double maximo;
		private double volume;
		private LocalDate data;
		
		private boolean setouAbertura = false;
		private boolean setouFechamento = false;
		private boolean setouMinimo = false;
		private boolean setouMaximo = false;
		private boolean setouVolume = false;
		private boolean setouData = false;
		
		public Builder abertura(double abertura) {
			this.abertura = abertura;
			setouAbertura = true;
			return this;
		}
		
		public Builder fechamento(double fechamento) {
			this.fechamento = fechamento;
			setouFechamento = true;
			return this;
		}

		public Builder minimo(double minimo) {
			this.minimo = minimo;
			setouMinimo = true;
			return this;
		}
		
		public Builder maximo(double maximo) {
			this.maximo = maximo;
			setouMaximo = true;
			return this;
		}

		public Builder volume(double volume) {
			this.volume = volume;
			setouVolume = true;
			return this;
		}
	
		public Builder data(LocalDate data) {
			this.data = data;
			setouData = true;
			return this;
		}
		
		public Candle Build() {
			if (this.maximo < this.minimo) {
				throw new IllegalArgumentException("Preço máximo não pode ser menor que o preço mínimo");
			}
			if (this.data == null) {
				throw new IllegalArgumentException("A data não pode ser nula");
			}
			if (this.abertura < 0 || this.fechamento < 0 || this.minimo < 0 || this.maximo < 0 || this.volume < 0) {
				throw new IllegalArgumentException("Não pode ter valor negativo");
			}
			if (!setouAbertura || !setouFechamento || !setouMinimo || !setouMaximo || !setouVolume || !setouData) {
				throw new IllegalStateException("Todos os dados devem ser preenchidos");
			}
			return new Candle(this);
		}
	}

	private Candle(Builder builder) {
		abertura = builder.abertura;
		fechamento = builder.fechamento;
		minimo = builder.minimo;
		maximo = builder.maximo;
		volume = builder.volume;
		data = builder.data;
	}
	
	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public LocalDate getData() {
		return data;
	}
	
	public boolean isAlta() {
		return (abertura < fechamento || abertura == fechamento);
	}
	
	public boolean isBaixa() {
		return abertura > fechamento;
	}
	
	@Override
	public String toString() {
		DecimalFormat format = new DecimalFormat("#,##0.00");
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String output = String.format("[Abertura %s, Fechamento %s, Mínima %s, Máxima %s, Volume %s, Data %s", 
				format.format(this.abertura),
				format.format(this.fechamento),
				format.format(this.minimo),
				format.format(this.maximo),
				format.format(this.volume),
				this.data.format(formatoData) + "]");
		
		return output;
	}
	
}
