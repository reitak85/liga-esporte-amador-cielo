package br.com.cielo.ecommerce.pedido;

public enum Bandeira {
	
	VISA("visa"),
	MASTERCARD("mastercard"),
	ELO("elo"),
	DINERS("diners"),
	DISCOVER("discover")
	;
	
	private String nome;
	
	private Bandeira(String nome) {
		this.nome = nome;
	}
	
	public static Bandeira valueOf(int codigo) {
		
		switch (codigo) {

		case 1:
			return VISA;
		case 2:
			return MASTERCARD;
		case 3:
			return ELO;
		case 5:
			return DINERS;
		case 6:
			return DISCOVER;
		default:
			throw new IllegalArgumentException("Código '" + codigo
					+ "' de bandeira não suportado.");
		}
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
	

}