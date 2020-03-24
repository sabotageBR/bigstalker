package com.bigstalker.enums;

public enum AvaliacaoEnum {

	MUITO_RUIM(1,"label.global.muitoruim"),
	RUIM(2,"label.global.ruim"),
	MEDIO(3,"label.global.medio"),
	ALTO(4,"label.global.alto"),
	MUITO_ALTO(5,"label.global.muitoalto");
	
	private AvaliacaoEnum(Integer valor,String key) {
		setValor(valor);
		setKey(key);
	}
	private Integer valor;
	private String key;
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
