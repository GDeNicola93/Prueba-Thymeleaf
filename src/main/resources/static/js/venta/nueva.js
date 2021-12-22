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
        // var tabla = document.getElementById("detallesDeVenta");

        // //Se crea el <tr>
        // var nuevaFilaDeTabla = tabla.insertRow(0);
        
        // //Se crean los <td>
        // var col1 = nuevaFilaDeTabla.insertCell(0);
        // var col2 = nuevaFilaDeTabla.insertCell(1);
        // var col3 = nuevaFilaDeTabla.insertCell(2);


        // col1.innerHTML = data.id;
        // col2.innerHTML = data.nombre;
        // col3.innerHTML = formatter.format(data.precio);

        // montoTotalVenta = montoTotalVenta + data.precio;

        // document.getElementById("totalVenta").innerHTML = "Total Venta: " + formatter.format(montoTotalVenta);
        var $tr = $("#fila-nuevo-detalle");
		var $trnew = $("#fila-nuevo-detalle").clone();
        var id = parseInt($tr.attr("data-id"));

        $tr.find("td.idProducto").text(data.id);
        $tr.find("td.nombreProducto").text(data.nombre);
        $tr.find("td.precioProducto").text(formatter.format(data.precio));
        montoTotalVenta = montoTotalVenta + data.precio;
        
        debugger;
        $tr.attr("id", "detalles-" + id).removeClass("d-none");
        $trnew.attr("data-id", id + 1);
		$(document.getElementById("detalles-" + id)).after($trnew);
        document.getElementById("totalVenta").innerHTML = "Total Venta: " + formatter.format(montoTotalVenta);
    }

});