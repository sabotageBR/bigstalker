package com.bigstalker.service.combos;

import javax.inject.Named;

import com.bigstalker.enums.AvaliacaoEnum;

@Named
public class CombosBean {

	public AvaliacaoEnum[] getListaAvaliacao() {
		return AvaliacaoEnum.values();
	}
}
