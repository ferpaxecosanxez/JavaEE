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
	var numErr = 0;

	for (i = 0; i < form.childNodes.length; i++) {
		var itemForm = form.childNodes[i];

		// Capturar etiqueta: <input>
		if (itemForm.tagName != null && itemForm.tagName == "INPUT") {
			var value = itemForm.value;

			if (itemForm.type == "text"
					&& (value == null || value.length == 0 || /^\s+$/.test(value))) {
				numErr = 1;
				msg += "Los campos de texto no pueden quedar vac\u00EDos.\n\n";
			}
		}
	}
	// Verificar se han dado errores.
	if (numErr > 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}
