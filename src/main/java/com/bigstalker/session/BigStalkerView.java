package com.bigstalker.session;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@URLMappings(mappings={
		  @URLMapping(id = "login", pattern = "/login", viewId = "/index.xhtml")
		})
public class BigStalkerView {
	
}
