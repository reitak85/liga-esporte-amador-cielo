package br.com.cielo.ecommerce.pedido;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Cartao {
	
	private String numero;
	
	private String codigoSeguranca;
	
	private String validade;
	
	private IndicadorCodigoSeguranca indicadorCodigoSeguranca;
	
	public Cartao(String numero, String validade, String codigoSeguranca) {
		super();
		this.numero = numero;
		this.validade = validade;
		setCodigoSeguranca(codigoSeguranca);
	}

	private void setCodigoSeguranca(String codigoSeguranca) {
		if (StringUtils.isEmpty(codigoSeguranca)) {
			this.indicadorCodigoSeguranca = IndicadorCodigoSeguranca.NAO_INFORMADO;
		}
		else {
			this.indicadorCodigoSeguranca = IndicadorCodigoSeguranca.INFORMADO;
		}
		
		this.codigoSeguranca = codigoSeguranca;
	}
	
	public Cartao(String numero, String validade, IndicadorCodigoSeguranca indicadorCodigoSeguranca) {
		super();
		this.numero = numero;
		this.validade = validade;
		this.indicadorCodigoSeguranca = indicadorCodigoSeguranca;
	}	
	
	public enum IndicadorCodigoSeguranca {
		INFORMADO(1),
		NAO_INFORMADO(0),
		ILEGIVEL(2),
		INEXISTENTE(9);
		
		private short codigo;

		private IndicadorCodigoSeguranca(int codigo) {
			this.codigo = (short) codigo;
		}

		public short getCodigo() {
			return codigo;
		}		
	}

	public String getNumero() {
		return numero;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public IndicadorCodigoSeguranca getIndicadorCodigoSeguranca() {
		return indicadorCodigoSeguranca;
	}

	public String getValidade() {
		return validade;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}