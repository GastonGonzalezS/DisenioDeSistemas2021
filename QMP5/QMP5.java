import java.util.List;

class Prenda{

	List<Criterio> criterios;

	//implementacion de versiones anteriores

	boolean contieneEsteCriterio(Criterio unCriterio) {
		return criterios.contains(unCriterio);
	}
	
}

enum Criterio{
	viaje,
	entrecasa
	// etc etc 
}

// aqui la prenda contiene los criterios para los cuales es apta asi el guardaropa podra filtrar segun su necesidad 

class Guardaropa{
	List<Prenda> prendas;
	List<Usuario> usuarioPermitido;

	Guardaropa(List<Prenda> unasPrendas, List<Usuario> unosUsuarios,Criterio criterio){
		prendas = this.prendasSegunCriterio(unasPrendas,criterio);
		usuarioPermitido = unosUsuarios;
	}

	List<Prenda> prendasSegunCriterio(List<Prenda> prendas, Criterio unCriterio){
		return prendas.filter(prenda -> prenda.contieneEsteCriterio(unCriterio));
	}

	void agregarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}

	void quitarPrenda(Prenda unaPrenda){
		prendas.remove(unaPrenda);
	}
}

class Usuario {
	List<Sugerencia> sugerenciaPendiente;
	List<Sugerencia> sugerenciaAplicada;

	void aniadirSugerencia(Usuario unUser, Sugerencia unaSugerencia){
		unUser.insertarSugerencia(unaSugerencia);
	}

	void insertarSugerencia(Sugerencia unaSugerencia){
		sugerPend.add(unaSugerencia);
	}

	void aplicarSugerencia(Guardaropa unGuardaropa){
		Sugerencia unaSugerencia = sugerenciaPendiente.get(0);
		sugerenciaAplicada.add(unaSugerencia);
		unaSugerencia.aplicarEn(unGuardaropa); // aplica la primera sugerencia
	}

	void ignorarSugerencia(){
		sugerPend.remove(sugerPend.get(0)); // elimina el primer elemento de la lista 
	}

	void deshacerSugerencia(Sugerencia unaSugerencia,Guardaropa unGuardaropa){
		if(sugerAplicada.contains(unaSugerencia)){
			unaSugerencia.deshacerEn(unGuardaropa);
			sugerAplicada.remove(unaSugerencia);
		}
	}
}
// las sugerencias se iran aplicando o ignorando de una a la vez 

interface Sugerencia{
	void aplicarEn(Guardaropa unGuardaropa);
	void deshacerEn(Guardaropa unGuardaropa);
}

class AgregarPrenda implements Sugerencia{
	Prenda unaPrenda;

	AgregarPrenda(Prenda uPrenda){
		unaPrenda = uPrenda;
	}

	@Override
	public void aplicarEn(Guardaropa unGuardaropa) {
		unGuardaropa.agregarPrenda(unaPrenda);
	}

	@Override
	public void deshacerEn(Guardaropa unGuardaropa) {
		unGuardaropa.quitarPrenda(unaPrenda);
	}

}

class QuitarPrenda implements Sugerencia{
	Prenda unaPrenda;

	QuitarPrenda(Prenda uPrenda){
		unaPrenda = uPrenda;
	}

	@Override
	public void aplicarEn(Guardaropa unGuardaropa) {
		unGuardaropa.quitarPrenda(unaPrenda);
	}

	@Override
	public void deshacerEn(Guardaropa unGuardaropa) {
		unGuardaropa.agregarPrenda(unaPrenda);
	}

}