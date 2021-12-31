$(function(){
    var detalleVenta = [];
    var montoTotalVenta = 0;

    //Formato Moneda
    var formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
      
        // These options are needed to round to whole numbers if that's what you want.
        //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
        //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
    });


    // Acciones Key Press

    $('#buscador').keypress(function(e) {
        var keycode = (e.keyCode ? e.keyCode : e.which);
        if (keycode == '32'){ //Barra espaciadora {
            var valor = document.getElementById("buscador").value;
            if(valor != null && valor != undefined && valor != ""){
                buscarProducto(valor);
            }else{
                alert("Debe ingresar un valor valido.");
            }
        }
    });


    //Funciones
    function buscarProducto(dato){ //El dato es el id del producto
        $.get("/producto/api/detalle/"+dato, function(data) {
            detalleVenta.push(data);
            agregarProductoATablaDetalleVenta(data);
            document.getElementById("buscador").value = null;
        });
    }
    
    function agregarProductoATablaDetalleVenta(data){
        var $tr = $("#fila-nuevo-detalle");
		var $trnew = $("#fila-nuevo-detalle").clone();
		var id = parseInt($tr.attr("data-id"));
		$tr.find("input.campo-detalles.campo-id").each(function(i, campo){
			var nombre = "detallesDeVenta[" + id + "]." + $(campo).attr('name').split('.')[1];
			$(campo).attr('name', nombre + ".id");
            $(campo).attr('value', data.id);
		});

        $tr.find("input.campo-detalles.campo-nombre").each(function(i, campo){
			var nombre = "detallesDeVenta[" + id + "]." + $(campo).attr('name').split('.')[1];
			$(campo).attr('name', nombre + ".nombre");
            $(campo).attr('value', data.nombre);
		});

        $tr.find("input.campo-detalles.campo-precio").each(function(i, campo){
			var nombre = "detallesDeVenta[" + id + "]." + $(campo).attr('name').split('.')[1];
			$(campo).attr('name', nombre + ".precio");
            $(campo).attr('value', data.precio);
		});

        montoTotalVenta = montoTotalVenta + data.precio;
        document.getElementById("totalVenta").innerHTML = "Total Venta: " + formatter.format(montoTotalVenta);
		
		$tr.find("td").each(function(i, campo){
			$(campo).attr('data-id', id);
		});
		
        $tr.attr("id", "detallesDeVenta-" + id).removeClass("d-none");
		$trnew.attr("data-id", id + 1);
		$(document.getElementById("detallesDeVenta-" + id)).after($trnew);
    }

});