package br.com.cielo.ecommerce.util.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import br.com.cielo.ecommerce.contexto.Destino;
import br.com.cielo.ecommerce.contexto.Loja;
import br.com.cielo.ecommerce.requisicao.Mensagem;
import br.com.cielo.ecommerce.requisicao.MensagemAutorizacao;
import br.com.cielo.ecommerce.requisicao.MensagemCancelamento;
import br.com.cielo.ecommerce.requisicao.MensagemCaptura;
import br.com.cielo.ecommerce.requisicao.MensagemConsulta;
import br.com.cielo.ecommerce.requisicao.Operacao;
import br.com.cielo.ecommerce.requisicao.Requisicao;
import br.com.cielo.ecommerce.resposta.FalhaComunicaoException;
import br.com.cielo.ecommerce.resposta.Transacao;

public class GerenciadorTransacao {

	private HttpServletRequest request;
	
	public GerenciadorTransacao(final HttpServletRequest request) {
		this.request = request;
	}
	
	public GerenciadorTransacao() {
	}
	
	public String executar() throws FalhaComunicaoException {
		
		Transacao transacao = getTransacao();
		Loja loja = getLoja();
		Mensagem mensagem = contruirMensagem(transacao, loja);
		
		return requisitar(mensagem);
	}

	String requisitar(Mensagem mensagem) throws FalhaComunicaoException {
		Requisicao requisicao = new Requisicao(mensagem);
		
		return requisicao.enviarPara(new Destino()).getConteudo();
	}

	private Transacao getTransacao() {
		String tid = getParameter("tid");
		Transacao transacao = new Transacao(tid);
		return transacao;
	}

	private Loja getLoja() {
		String numeroLojaString = getParameter("numeroLoja");
		Loja loja = Loja.valueOf(Long.valueOf(numeroLojaString));
		return loja;
	}

	private Mensagem contruirMensagem(Transacao transacao, Loja loja) {
		Operacao operacao = getOperacao();
		
		switch (operacao) {
			case AUTORIZACAO:
				return new MensagemAutorizacao(loja, transacao);
			case CAPTURA:
				String valorString = getParameter("valor");
				
				if (StringUtils.isEmpty(valorString)) {
					valorString = "0";
				}
				
				long valor = Long.valueOf(valorString);
				return new MensagemCaptura(loja, transacao, valor);
			case CANCELAMENTO:
				return new MensagemCancelamento(loja, transacao);
			case CONSULTA:
				return new MensagemConsulta(loja, transacao);
			default:
				throw new IllegalArgumentException();
		}
	}

	private Operacao getOperacao() {
		String acao = getParameter("acao");
		Operacao operacao = Operacao.valueOf(acao);
		return operacao;
	}
	
	String getParameter(String parameter) {
		return request.getParameter(parameter);
	}
}