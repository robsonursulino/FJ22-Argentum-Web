package br.com.caelum.argentum.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OlaMundoBean {
	
	private String msg = "Quem é você?";
	private String nome;

	public String getMsg() {
		System.out.println("getMsg");
		return msg;
	}

	public String getNome() {
		System.out.println("getNome");
		return nome;
	}

	public void setNome(String nome) {
		System.out.println("setNome");
		this.nome = nome;
	}
	
	public void nomeFoiDigitado() {
		System.out.println("\nChamou o botão");
	}

}
