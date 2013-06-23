package br.com.cielo.ecommerce.pedido;

import br.com.cielo.ecommerce.contexto.Destino;
import br.com.cielo.ecommerce.contexto.Loja;
import br.com.cielo.ecommerce.requisicao.Mensagem;
import br.com.cielo.ecommerce.requisicao.MensagemAutorizacao;
import br.com.cielo.ecommerce.requisicao.MensagemAutorizacaoDireta;
import br.com.cielo.ecommerce.requisicao.MensagemCancelamento;
import br.com.cielo.ecommerce.requisicao.MensagemCaptura;
import br.com.cielo.ecommerce.requisicao.MensagemConsulta;
import br.com.cielo.ecommerce.requisicao.MensagemNovaTransacao;
import br.com.cielo.ecommerce.requisicao.MensagemTid;
import br.com.cielo.ecommerce.requisicao.Requisicao;
import br.com.cielo.ecommerce.resposta.FalhaComunicaoException;
import br.com.cielo.ecommerce.resposta.Transacao;

public class TransacaoService {
	
	private Loja loja;
	
	private Destino destino = new Destino();
	
	public TransacaoService(final Loja loja) {
		this.loja = loja;
	}	
	
	public Transacao criarTransacao(Pedido pedido) throws FalhaComunicaoException {
		Mensagem mensagem = new MensagemNovaTransacao(loja, pedido);
		Requisicao requisicao = new Requisicao(mensagem);
		return requisicao.enviarPara(destino);
	}

	public Transacao capturar(Transacao transacao, long valor) throws FalhaComunicaoException {
		Mensagem mensagem = new MensagemCaptura(loja, transacao, valor);
		Requisicao requisicao = new Requisicao(mensagem);
		return requisicao.enviarPara(destino);
	}
	
	public Transacao cancelar(Transacao transacao) throws FalhaComunicaoException {
		Mensagem mensagem = new MensagemCancelamento(loja, transacao);
		Requisicao requisicao = new Requisicao(mensagem);
		return requisicao.enviarPara(destino);
	}

	
	public Transacao autorizarDireto(Pedido pedido) throws FalhaComunicaoException {
		MensagemTid mensagemTid = new MensagemTid(loja, pedido.getFormaPagamento());
		Requisicao requisicaoTid = new Requisicao(mensagemTid);
		String tid = requisicaoTid.enviarPara(destino).getTid();
		
		MensagemAutorizacaoDireta mensagemAutorizacaoDireta = 
			new MensagemAutorizacaoDireta(loja)
				.setPedido(pedido)
				.setTid(tid);
		Requisicao requisicaoAutorizacaoDireta = new Requisicao(mensagemAutorizacaoDireta);
		
		return requisicaoAutorizacaoDireta.enviarPara(destino);
	}
	
	
	public Transacao autorizar(Transacao transacao) throws FalhaComunicaoException {
		Mensagem mensagem = new MensagemAutorizacao(loja, transacao);
		Requisicao requisicao = new Requisicao(mensagem);
		return requisicao.enviarPara(destino);
	}

	public Transacao consultar(Transacao transacao) throws FalhaComunicaoException {
		Mensagem mensagem = new MensagemConsulta(loja, transacao);
		Requisicao requisicao = new Requisicao(mensagem);
		return requisicao.enviarPara(destino);
	}
	
}