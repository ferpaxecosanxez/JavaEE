var optionName = "";

/**
 * Valida todos los campos de un formulario.
 * 
 * @param form
 *            Objeto DOM correspondiente a "this".
 * @returns true, si el formulario es válido.<br>
 *          false, en caso contrario.
 */
function validaFormulario(form) {
	var msg = "";
	var error = false;

	for (i = 0; i < form.childNodes.length; i++) {
		var itemForm = form.childNodes[i];
		var value;
		var field
		
		// Capturar etiqueta: <input>
		if (itemForm.tagName != null && itemForm.tagName == "INPUT") {
			value = itemForm.value;
			field = itemForm.name;

			if (itemForm.type == "text"
					&& (value == null || value.length == 0 || /^\s+$/.test(value))) {
				error = true;
				msg += "El campo " + field +" est\u00E1 vac\u00CDo.\n\n";
			}
			if (itemForm.type == "password"
				&& (value == null || value.length == 0 || /^\s+$/.test(value))) {
				error = true;
				msg += "El campo " + field +" debe contener una contrase\u00F1a.\n\n";
			}
			if (itemForm.type == "number"
					&& (value == null || value.length == 0 || isNaN(value))) {
				error = true;
				msg += "El campo " + field + " debe contener n\u00FAmeros.\n\n";
			}
		// Capturar etiqueta <div> que dentro tendrá opciones (Checks, Radios,
		// Etc).
		} else if (itemForm.tagName != null && itemForm.tagName == "DIV") {
			var classCss = itemForm.attributes.class.nodeValue;

			// Botones Radio.
			if (classCss.indexOf("opcionesRadio") != -1
					&& !validaRadioButton(itemForm.children)) {
				error = true;
				msg += "Debe seleccionar una opci\u00F3n correspondiente a "
						+ optionName + ".\n\n";
			}
		}
	}
	// Verificar se han dado errores.
	if (error) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

/**
 * Valida una sección que contien opciones de tipo radio. La validación consiste
 * en ver si el usuario ha seleccionado un opción.
 * 
 * @param items
 *            Todos los hijos que componen el contenedor DIV.
 * @returns true, si existe una opción seleccionada.<br>
 *          false, en caso contrario.
 */
function validaRadioButton(items) {
	radioSeleccionado = false;
	
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		
		// Capturar etiqueta: <input>
		if (item.tagName != null && item.tagName == "INPUT") {
			if (item.checked) {
				radioSeleccionado = true;
			}
			optionName = item.name;
		}
	}
	
	return radioSeleccionado;
}
