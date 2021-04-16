class Prenda {
    private TipoPrenda tipoPrenda;
    private double precioBase;
    private EstadoPrenda estado;

    constructor(TipoPrenda unTipo,double unPrecio, EstadoPrenda unEstado){
        tipoPrenda = unTipo;
        precioBase = unPrecio;
        estado = unEstado;
    }

    double precio(){
        return estado.modificarPrecioBase(precioBase);
    }

    TipoPrenda getTipo(){
        return tipoPrenda;
    }
};

enum TipoPrenda {
    saco,pantalon,camisa;
};

interface EstadoPrenda{
    double modificarPrecioBase(double precio);
}

class NuevaPrenda extends EstadoPrenda{
    double modificarPrecioBase(double precio){
        return precio;
    };
}

class PromocionPrenda extends EstadoPrenda{
    double valorDescuento;

    constructor(double valorDescuento){
        valorDescuento = valorDescuento;
    }

    double modificarPrecioBase(double precio){
        return max(0,precio - valorDescuento);
    }

}

class LiquidacionPrenda extends EstadoPrenda{
    double porcentaje;

    constructor(double porcen){
        porcentaje = porcen;
    }

    double modificarPrecioBase(double precio){
        return (precio * porcentaje) / 100;
    }
}

class VentaPrenda {
    List<Prenda> prendas;
    Date fecha;
    MedioDePago medioPago;

    constructor(List<Prenda> unasPrendas,Date unaFecha, MedioDePago unMedio){
        prendas = unasPrendas;
        fecha = unaFecha;
        medioPago = unMedio;
    }

    double precioTotalPrendas(){
        return prendas.sum(prenda -> prenda.precio());
    }

    double precioDeVenta(){
        return precioTotalPrendas() + medioPago.calcularRecargo(precioTotalPrendas());
    }

}


interface MedioDePago{
    double calcularRecargo(double precio);
}


class Efectivo extends MedioDePago{
    double calcularRecargo(double precio){
        return 0;
    }
}

class Tarjeta extends MedioDePago{
    int cuotas;
    double coeficiente;
    
    constructor(int unasCuotas, double unCoef){
        cuotas = unasCuotas;
        coeficiente = unCoef;
    }

    double calcularRecargo(double precio){
        return (cuotas * coeficiente) + (0.01 * precio);
    }

}

class DiaDeTrabajo {
	Date fecha;
	List<VentaPrenda> ventas;
	
	constructor (Date unaFecha, List<VentaPrenda> unasVentas){
		fecha = unaFecha;
		ventas = unasVentas;	
	}
	
	void agregarVenta(VentaPrenda unaVenta){
		ventas.add(unaVenta);
	} // se podria corroborar que la fecha coincida con la fecha de la venta 
	  // para que no haya ventas de otro dia 
	  
	double ganancias(){
		return ventas.sum(venta -> venta.precioDeVenta())
	}
}
