/**
 * Valida si una etiqueta de tipo "input" contiene valor.
 * 
 * @param idForm
 *            Identificador del formulario definido.
 * @returns true, si todo a ido bien,<br>
 *          false, en caso contrario.
 */
function validaFormulario(idForm) {
	var form = document.getElementById(idForm);
	var msg = "";
	var error = false;

	for (i = 0; i < form.childNodes.length; i++) {
		var itemForm = form.childNodes[i];

		// Capturar etiqueta: <input>
		if (itemForm.tagName != null && itemForm.tagName == "INPUT") {
			var value = itemForm.value;
			var field = itemForm.name;

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
