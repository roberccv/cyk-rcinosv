package es.ceu.gisi.modcomp.cyk_algorithm.algorithm;

import es.ceu.gisi.modcomp.cyk_algorithm.algorithm.exceptions.CYKAlgorithmException;
import es.ceu.gisi.modcomp.cyk_algorithm.algorithm.interfaces.CYKAlgorithmInterface;
import java.util.ArrayList;

/**
 * Esta clase contiene la implementación de la interfaz CYKAlgorithmInterface
 * que establece los métodos necesarios para el correcto funcionamiento del
 * proyecto de programación de la asignatura Modelos de Computación.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class CYKAlgorithm implements CYKAlgorithmInterface {
char axioma;

ArrayList<Character> noTerminales = new ArrayList<>();
ArrayList<Character> terminales = new ArrayList<>();

//probar sin lo de dentro de los corchetes de el lado derecho del igual
ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
ArrayList<Character> noTerminalesAnnadidos = new ArrayList<>();

    @Override
    /**
     * Método que añade los elementos no terminales de la gramática.
     *
     * @param nonterminal Por ejemplo, 'S'
     * @throws CYKAlgorithmException Si el elemento no es una letra mayúscula.
     */
    public void addNonTerminal(char nonterminal) throws CYKAlgorithmException {
        //preguntar si  puedo comprobar que si ya esta en el arrayList no lo añada
        //y lance otra excepción -> ese elemento ya ha sido añadido previamente
        boolean condicion = Character.isDigit(nonterminal);
        if(Character.isUpperCase(nonterminal) && (isNonTAdded(nonterminal)== false) && (condicion == false)){
            noTerminales.add(nonterminal);
        }else{
            throw new CYKAlgorithmException("Not supported yet.");
        }
    }
    /*public boolean noNumero(char c){
        int comprobador = 0;
        for (int i = 0; i < 10; i++) {
            if (c == i) {
                comprobador++;
            }
        }
        return comprobador == 0;
    }
    */
    
    @Override
    /**
     * Método que añade los elementos terminales de la gramática.
     *
     * @param terminal Por ejemplo, 'a' 
     * @throws CYKAlgorithmException Si el elemento no es una letra minúscula.
     */
    public void addTerminal(char terminal) throws CYKAlgorithmException {
        
        boolean condicion = Character.isDigit(terminal);
        if((Character.isUpperCase(terminal)== false) && (condicion == false) && (isTAdded(String.valueOf(terminal)) == false) ){
            terminales.add(terminal);
        }else{
            throw new CYKAlgorithmException("Not supported yet.");
        }
    }

    @Override
    /**
     * Método que indica, de los elementos no terminales, cuál es el axioma de
     * la gramática.
     *
     * @param nonterminal Por ejemplo, 'S'
     * @throws CYKAlgorithmException Si el elemento insertado no forma parte del
     * conjunto de elementos no terminales.
     */
    public void setStartSymbol(char nonterminal) throws CYKAlgorithmException {
        //si el el nonterminal no esta en el array de no terminales, cambiar condición if
        //solo quiero que me funcione :)
        boolean dentro = false;
        for(int i = 0; i < noTerminales.size() ; i++){
            if(noTerminales.get(i) == nonterminal){
                dentro = true;
            }
        }
        if(dentro == false){
            throw new CYKAlgorithmException("Not supported yet.");
        }else{
            axioma = nonterminal;
       }
    }

    @Override
    /**
     * Método utilizado para construir la gramática. Admite producciones en FNC,
     * es decir de tipo A::=BC o A::=a
     *
     * @param nonterminal A
     * @param production "BC" o "a"
     * @throws CYKAlgorithmException Si la producción no se ajusta a FNC o está
     * compuesta por elementos (terminales o no terminales) no definidos
     * previamente.
     */
    public void addProduction(char nonterminal, String production) throws CYKAlgorithmException {
        //IMPORTANTE tengo que separar el String en chars para comprobar que este en No terminales
        //para así luego poder meterlo en la matriz
        //tm comprobar que si produccion es mayor que 2, no es valido
        int a = 0;
        int b = 0;
        boolean condicion1 = ((production.length() == 1) && (Character.isUpperCase(production.charAt(0)) == false) && isTAdded(production));
        boolean condicion2 = ((production.length()== 2) && (Character.isUpperCase(production.charAt(0))) && (Character.isUpperCase(production.charAt(1))) && isProdAdded(production));
        boolean condicion3 = (isNonTAdded(nonterminal));
        
        
        if((condicion1 || condicion2) && (condicion3)){
            
            for(int i = 0 ;  i< noTerminalesAnnadidos.size() ; i++){
                if(nonterminal == noTerminalesAnnadidos.get(i)){
                    a = a+1;
                }
            }
        
            if(a == 0){
                int posicionNuevo = matriz.size();
                matriz.add(new ArrayList<String>());
                matriz.get(posicionNuevo).add(String.valueOf(nonterminal));
                matriz.get(posicionNuevo).add(production);
                noTerminalesAnnadidos.add(nonterminal);
            }else{
                for(int i = 0; i < matriz.size() ; i++){
                    if((String.valueOf(nonterminal)).equals(matriz.get(i).get(0))){
                        
                        for(int j = 0 ; j< matriz.get(i).size() ; j++){
                            if(matriz.get(i).get(j).equals(production)){
                                b++;
                            }
                            if(b ==0){
                                matriz.get(i).add(production);
                            }else{
                                throw new CYKAlgorithmException("La producción ya había sido añadida");
                            }
                        }                                                
                    }
                }
            }
        }else{
            throw new CYKAlgorithmException("La producción no cumple los requisitos");    
        }  
    }
    
    public boolean isProdAdded(String noTerminal3){
        int comprobador = 0;
        
        for(int i = 0; i<noTerminales.size() ; i++){
            if((String.valueOf(noTerminales.get(i)).equals(noTerminal3.charAt(0)))||(String.valueOf(noTerminales.get(i)).equals(noTerminal3.charAt(1)))){
                comprobador++;
            }   
        }        
        return comprobador == 2;
    }
    public boolean isNonTAdded(char terminal3){
        int comprobador = 0;
        
        for(int i = 0; i<noTerminales.size() ; i++){
            if(noTerminales.get(i) == terminal3){
                comprobador++;
            }
        }
        return comprobador == 1;
    }
    public boolean isTAdded(String terminal3){
        int comprobador = 0;
        
        for(int i = 0; i<terminales.size() ; i++){
            if(String.valueOf(terminales.get(i)).equals(terminal3)){
                comprobador++;
            }
        }
        return comprobador == 1;
    }

    @Override
    /**
     * Método que indica si una palabra pertenece al lenguaje generado por la
     * gramática que se ha introducido.
     *
     * @param word La palabra a verificar, tiene que estar formada sólo por
     * elementos no terminales.
     * @return TRUE si la palabra pertenece, FALSE en caso contrario
     * @throws CYKAlgorithmException Si la palabra proporcionada no está formada
     * sólo por terminales, si está formada por terminales que no pertenecen al
     * conjunto de terminales definido para la gramática introducida, si la
     * gramática es vacía o si el autómata carece de axioma.
     */
    public boolean isDerived(String word) throws CYKAlgorithmException {
        
        throw new UnsupportedOperationException("Not supported yet.");
    
    }

    @Override
    /**
     * Método que, para una palabra, devuelve un String que contiene todas las
     * celdas calculadas por el algoritmo (la visualización debe ser similar al
     * ejemplo proporcionado en el PDF que contiene el paso a paso del
     * algoritmo).
     *
     * @param word La palabra a verificar, tiene que estar formada sólo por
     * elementos no terminales.
     * @return Un String donde se vea la tabla calculada de manera completa,
     * todas las celdas que ha calculado el algoritmo.
     * @throws CYKAlgorithmException Si la palabra proporcionada no está formada
     * sólo por terminales, si está formada por terminales que no pertenecen al
     * conjunto de terminales definido para la gramática introducida, si la
     * gramática es vacía o si el autómata carece de axioma.
     */
    public String algorithmStateToString(String word) throws CYKAlgorithmException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * Elimina todos los elementos que se han introducido hasta el momento en la
     * gramática (elementos terminales, no terminales, axioma y producciones),
     * dejando el algoritmo listo para volver a insertar una gramática nueva.
     */
    public void removeGrammar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * Devuelve un String que representa todas las producciones que han sido
     * agregadas a un elemento no terminal.
     *
     * @param nonterminal
     * @return Devuelve un String donde se indica, el elemento no terminal, el
     * símbolo de producción "::=" y las producciones agregadas separadas, única
     * y exclusivamente por una barra '|' (no incluya ningún espacio). Por
     * ejemplo, si se piden las producciones del elemento 'S', el String de
     * salida podría ser: "S::=AB|BC".
     */
    public String getProductions(char nonterminal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * Devuelve un String con la gramática completa.
     *
     * @return Devuelve el agregado de hacer getProductions sobre todos los
     * elementos no terminales.
     */
    public String getGrammar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
