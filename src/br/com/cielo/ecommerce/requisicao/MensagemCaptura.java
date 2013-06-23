package br.com.cielo.ecommerce.requisicao;

import br.com.cielo.ecommerce.contexto.Loja;
import br.com.cielo.ecommerce.resposta.Transacao;

public class MensagemCaptura extends Mensagem {
	
	private Transacao transacao;
	
	private long valor;
	
	public MensagemCaptura(Loja loja, Transacao transacao, long valor) {
		super(loja);
		this.transacao = transacao;
		this.valor = valor;
	}

	@Override	
	String getTemplate() {
		String template = "" +
		"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + 
		"<requisicao-captura id=\"%1$s\" versao=\"%2$s\">" +
		"	<tid>%5$s</tid>" +		
		"	<dados-ec>" + 
		"		<numero>%3$d</numero>" + 
		"		<chave>%4$s</chave>" + 
		"	</dados-ec>";
		
		if (valor != 0) {
			template = template + "	<valor>%6$d</valor>";
		}
		
		template = template +  "</requisicao-captura>";		
		
		return template;
	}

	@Override
	Object[] getArgumentos() {
		Object [] argumentos = { 
				getId(),
				getVersao(),				
				getLoja().getNumero(),
				getLoja().getChave(),
				transacao.getTid(),
				valor				
			};
		return argumentos;
	}

}