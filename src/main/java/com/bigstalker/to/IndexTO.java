package com.bigstalker.to;

import java.util.ArrayList;
import java.util.List;

import com.bigstalker.entity.Perfil;

public class IndexTO {
	
	private List<Perfil> perfisTopNota;
	private List<Perfil> perfisTopClick;
	
	public List<Perfil> getPerfisTopNota() {
		if (perfisTopNota == null) {
			perfisTopNota = new ArrayList<Perfil>();
		}
		return perfisTopNota;
	}
	
	public void setPerfisTopNota(List<Perfil> perfisTopNota) {
		this.perfisTopNota = perfisTopNota;
	}
	
	public List<Perfil> getPerfisTopClick() {
		if (perfisTopClick == null) {
			perfisTopClick = new ArrayList<Perfil>();
		}
		return perfisTopClick;
	}
	
	public void setPerfisTopClick(List<Perfil> perfisTopClick) {
		this.perfisTopClick = perfisTopClick;
	}
	
}
