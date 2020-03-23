package com.bigstalker.session;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@URLMappings(mappings={
		  @URLMapping(id = "login", pattern = "/login", viewId = "/login.xhtml"),
		  @URLMapping(id = "index", pattern = "/index", viewId = "/index.xhtml"),
		  @URLMapping(id = "home", pattern = "/home", viewId = "/index.xhtml"),
		  @URLMapping(id = "perfil_list", pattern = "/perfil", viewId = "/pages/perfil/perfil_list.xhtml"),
		  @URLMapping(id = "perfil_view", pattern = "/perfil/#{usuario}", viewId = "/pages/perfil/perfil_view.xhtml")
		})
public class BigStalkerView {
	
}
