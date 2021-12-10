$(function(){
	
	// Acciones On-Load
	$("#subCategoriaFormGroup").hide();
	
		
	// Acciones On-Change
	$("#categoria").change(function(){
		cargarSelectSubCategorias();		
	});
	
	
	//Funciones
	function cargarSelectSubCategorias() {
			
		categoriaId = $("#categoria").val();

		if(categoriaId != null && categoriaId != undefined && categoriaId != ""){
			$("#subCategoriaFormGroup").show();
			var comboSubCategorias = document.getElementById("subCategoria");
			borrarOpcionesSelect(comboSubCategorias);
			$.get("/categorias/"+categoriaId+"/sub_categorias", function(data) {
				for(var i=0;i<data.length;i++){
					var opcion = document.createElement("option");
					opcion.value = data[i].id;
					opcion.text = data[i].nombre;
					comboSubCategorias.add(opcion);
				}
			});
		}else{
			$("#subCategoriaFormGroup").hide();
		}
	};

	function borrarOpcionesSelect(select){
		var i; 
		for(i=select.options.length-1;i>=0;i--) {
			select.remove(i);
		} 
	}
});


