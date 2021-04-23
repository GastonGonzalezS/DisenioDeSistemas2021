class Prenda{
    TipoPrenda tipo;
    Tela tela;
    Color colorPrincipal;
    Color colorSecundario;

    constructor(TipoPrenda untipo, Tela unaTela, Color unColor){
        if(untipo = null){
            throw new FaltaDatoDeLaPrendaException("Tipo")
        }
        tipo = untipo;
        if(unaTela = null){
            throw new FaltaDatoDeLaPrendaException("Tela")
        }
        tela = unaTela;
        if(unColor = null){
            throw new FaltaDatoDeLaPrendaException("Color")
        }
        colorPrincipal = unColor;

    }

    void setColorSecundario(Color unColor){
        colorSecundario = unColor;
    }
}

enum Categoria{
    Superior,Inferior,Calzado; // Etc ...
}

enum TipoPrenda{
    Categoria categoria;
    TipoPrenda(categoria)

    Pantalon(Categoria.Inferior),
    Zapato(Categoria.Calzado),
    Camisa(Categoria.Superior),
    //Etc...

    }

enum Tela {
    algodon,nailon; // etc ...
}

class Color{
    int red;
    int green;
    int blue;

    constructor(int rojo, int verde, int azul){
        red = rojo;
        green = verde;
        blue = azul;
    }

    void setColores(int rojo, int verde, int azul){
        red = rojo;
        green = verde;
        blue = azul;
    }
}

public class FaltaDatoDeLaPrendaException extends RuntimeException {
public FaltaDatoDeLaPrendaException(String mensaje){
        super("No se puede crear la prenda porque falta el dato: " + mensaje);
        }