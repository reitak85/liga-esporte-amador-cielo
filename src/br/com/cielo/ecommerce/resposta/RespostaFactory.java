package br.com.cielo.ecommerce.resposta;

import br.com.cielo.ecommerce.resposta.Transacao.DadosPedido;
import br.com.cielo.ecommerce.resposta.Transacao.Processamento;

import com.thoughtworks.xstream.XStream;

public class RespostaFactory {
	
	private static final RespostaFactory INSTANCE = new RespostaFactory();
	
	private XStream xStream;
	
	private RespostaFactory() {
		
		xStream = new XStream();
		xStream.alias("erro", Erro.class);
		xStream.alias("transacao", Transacao.class);
		xStream.alias("retorno-tid", Transacao.class);
		
		xStream.aliasAttribute(Resposta.class, "id", "id");
		
		xStream.aliasAttribute(Transacao.class, "dadosPedido", "dados-pedido");
		xStream.aliasAttribute(DadosPedido.class, "dataHora", "data-hora");
		xStream.aliasAttribute(Processamento.class, "dataHora", "data-hora");
		xStream.aliasAttribute(Transacao.class, "formaPagamento", "forma-pagamento");
		xStream.aliasAttribute(Transacao.class, "urlAutenticacao", "url-autenticacao");		
	}
	
	public static RespostaFactory getInstance() {
		return INSTANCE;
	}
	
	public Resposta criar(String xml) {
		Resposta resposta = (Resposta) xStream.fromXML(xml);
		resposta.setConteudo(xml);
		return resposta;
	}

}