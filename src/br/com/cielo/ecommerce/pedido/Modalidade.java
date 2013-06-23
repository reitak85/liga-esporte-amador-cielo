package br.com.cielo.ecommerce.pedido;

public enum Modalidade {
	
	CREDITO_A_VISTA('1', "Cr�dito � Vista"),
	DEBITO('A', "D�bito"),
	PARCELADO_ADMINISTRADORA('3', "Parcelado"),
	PARCELADO_LOJA('2', "Parcelado");
	
	private char codigo;
	
	private String descricao;
	
	private Modalidade(char codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public char getCodigo() {
		return codigo;
	}

	public static Modalidade valueOf(char modalidade) {
		switch(modalidade) {
			case 'A': return DEBITO;
			case '1': return CREDITO_A_VISTA;
			case '2': return PARCELADO_LOJA;
			case '3': return PARCELADO_ADMINISTRADORA;
			default: throw new IllegalArgumentException("Modalidade '" + modalidade + "' n�o reconhecida.");
		}
	}
	
	@Override
	public String toString() {
		return descricao;
	}
	
}