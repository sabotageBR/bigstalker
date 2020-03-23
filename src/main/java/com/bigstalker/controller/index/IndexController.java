package com.bigstalker.controller.index;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bigstalker.controller.AbstractController;
import com.bigstalker.service.perfil.PerfilService;
import com.bigstalker.to.IndexTO;


@Named
@RequestScoped
public class IndexController extends AbstractController<IndexTO> {

	
	private @Inject PerfilService perfilService;
	
	@PostConstruct
	private void init() {
		getTo().setPerfisTopClick(perfilService.listarTopClick());
		getTo().setPerfisTopNota(perfilService.listarTopNota());
	}
	
	
}
