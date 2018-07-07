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
				msg += "Los campos de texto no pueden quedar vacíos.\n\n";
			}
			if (itemForm.type == "number"
					&& (value == null || value.length == 0 || isNaN(value))) {
				numErr = 1;
				msg += "Lo campos num\u00E9ricos deben contener n\u00FAmeros.";
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
