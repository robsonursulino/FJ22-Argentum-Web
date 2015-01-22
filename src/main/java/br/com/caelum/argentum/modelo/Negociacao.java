package br.com.caelum.argentum.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Negociacao {
	private final double preco;
	private final int quantidade;
	private final LocalDate data;
	
	public Negociacao(double preco, int quantidade, LocalDate data) {
		if (data == null) {
			throw new IllegalArgumentException("Data não pode ser nula");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDate getData() {
		return data;
	}
	
	public String getDataFormatada() {
		return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public double getVolume() {
		return preco * quantidade;
	}

	public boolean isMesmoDia(LocalDate data) {
		return this.data.equals(data);
	}
	
}
