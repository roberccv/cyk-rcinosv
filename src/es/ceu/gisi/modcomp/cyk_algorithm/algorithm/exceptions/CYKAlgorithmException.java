package es.ceu.gisi.modcomp.cyk_algorithm.algorithm.exceptions;

/**
 *
 * @author Roberto Cinos Vega.
 */
public class CYKAlgorithmException extends Exception {

    /**
    *Imprime el contenido de la excepción pasado como parámetro, para facilitar 
    * la visualización del error.
    * @param excepción, String expecificando el error.
    */
    public CYKAlgorithmException(String excepción) {
        System.out.println(excepción);
    }

}
