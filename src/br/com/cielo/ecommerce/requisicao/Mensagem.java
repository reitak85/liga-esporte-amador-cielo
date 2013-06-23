package br.com.cielo.ecommerce.requisicao;

import java.util.Formatter;
import java.util.Locale;
import java.util.UUID;

import br.com.cielo.ecommerce.contexto.Loja;

public abstract class Mensagem {
	
	private Loja loja;
	
	private String id;
	
	private String versao;

	public Mensagem(Loja loja) {
		this.loja = loja;
		//this.id = "" + System.currentTimeMillis();
		this.id = UUID.randomUUID().toString();
		this.versao = "1.1.1";
	}

	public Loja getLoja() {
		return loja;
	}

	public String toXml() {
		Object[] argumentos = getArgumentos();
		
		Formatter formatter = getFormatter();
		
		return formatter.format(getTemplate(), argumentos).toString();
	}
	
	abstract String getTemplate();

	abstract Object[] getArgumentos();	

	public String getId() {
		return id;
	}
	
	Formatter getFormatter() {
		Formatter formatter = new Formatter(new Locale("pt", "BR"));
		return formatter;
	}

	String getVersao() {
		return versao;
	}	
}