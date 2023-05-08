package es.ceu.gisi.modcomp.cyk_algorithm.algorithm;

import es.ceu.gisi.modcomp.cyk_algorithm.algorithm.exceptions.CYKAlgorithmException;
import es.ceu.gisi.modcomp.cyk_algorithm.algorithm.interfaces.CYKAlgorithmInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Esta clase contiene la implementación de la interfaz CYKAlgorithmInterface
 * que establece los métodos necesarios para el correcto funcionamiento del
 * proyecto de programación de la asignatura Modelos de Computación.
 *
 * @author Roberto Cinos Vega <roberto.cinosvega@usp.ceu.es>
 */
public class CYKAlgorithm implements CYKAlgorithmInterface {
char axioma;

ArrayList<Character> noTerminales = new ArrayList<>();
ArrayList<Character> terminales = new ArrayList<>();

ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
ArrayList<Character> noTerminalesAnnadidos = new ArrayList<>();
ArrayList<ArrayList<String>> comprobar = new ArrayList<ArrayList<String>>();

    @Override
    /**
     * Método que añade los elementos no terminales de la gramática.
     *
     * @param nonterminal Por ejemplo, 'S'
     * @throws CYKAlgorithmException Si el elemento no es una letra mayúscula.
     */
    public void addNonTerminal(char nonterminal) throws CYKAlgorithmException {
        boolean condicion = Character.isDigit(nonterminal);
        if(Character.isUpperCase(nonterminal) && (isNonTAdded(nonterminal)== false) && (condicion == false)){
            noTerminales.add(nonterminal);
        }else if(isNonTAdded(nonterminal)== true){
            throw new CYKAlgorithmException("El elemento no terminal ya había sido añadido");
        }else{
            throw new CYKAlgorithmException("El elemento no cumple con los requisitos para ser un no terminal."
            + " Compruebe que sea una letra mayúscula");
        }
            
    }
    

    
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
        }else if(isTAdded(String.valueOf(terminal))== true){
            throw new CYKAlgorithmException("El elemento terminal ya había sido añadido");
        }else{
            throw new CYKAlgorithmException("El elemento no cumple con los requisitos para ser un terminal."
            + " Compruebe que sea una letra minúscula");
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
            throw new CYKAlgorithmException("El elemento no forma parte de los elementos terminales añadidos, debe introducirlo antes.");
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
                System.out.println("La prod " + production + " ha sido añadida al terminal " + String.valueOf(nonterminal) );
            }else{
                for(int i = 0; i < matriz.size() ; i++){
                    if((String.valueOf(nonterminal)).equals(matriz.get(i).get(0))){
                        
                        for(int j = 0 ; j< matriz.get(i).size() ; j++){
                            if(matriz.get(i).get(j).equals(production)){
                                b++;
                            }
                        }
                        if(b ==0){
                            matriz.get(i).add(production);
                            System.out.println("La prod " + production + " ha sido añadida al terminal " + String.valueOf(nonterminal) );
                        }else{
                            throw new CYKAlgorithmException("La producción ya había sido añadida");
                        }
                                                                        
                    }
                }
            }
        }else{
            throw new CYKAlgorithmException("La producción " + production +" con cabeza "+ String.valueOf(nonterminal)+" no cumple los requisitos");    
        }  
    }
    
    /**
     * Método que comprueba si los elementos de la producción de un No terminal de la gramática 
     * están añadidos a la gramática. Este método esta destinado a comprobar las 
     * producciones con la forma "BA" o "CC", para ello usa un contador donde va 
     * comparando cada char del String noTerminal3 con los elementos en el 
     * ArrayList noTerminales.
     *
     * @param noTerminal3 String compuesto por dos elementos no terminales; 
     * @return devuelve true si los elementos de la producción habían sido añadidos 
     * a la gramática previamente, y false en  el caso contrario.
     * 
     */
    public boolean isProdAdded(String noTerminal3){
        int comprobador = 0;
        
        for(int i = 0; i<noTerminales.size() ; i++){
            if((noTerminales.get(i) == noTerminal3.charAt(1)) && (noTerminales.get(i) == noTerminal3.charAt(0))){
                
                comprobador = 2;
            } else if(noTerminales.get(i) == noTerminal3.charAt(1)){
                
                comprobador++;
            }else if(noTerminales.get(i) == noTerminal3.charAt(0)){
                comprobador++;
            }
        }
        
        return comprobador == 2;
    }
    
    /**
     *Método que, comprueba si el elemento no terminal que le es pasado como 
     * parámetro había sido añadido previamente a la gramática. Comprueba el 
     * ArrayList donde se guardan todos los no terminales ya añadidos.
     *
     * @param nonTerminal3 Char correspondiente a un no terminal a comprobar.
     * @return true si el no terminal había sido añadido, false en el caso 
     * contrario.
     */
    public boolean isNonTAdded(char nonTerminal3){
        int comprobador = 0;
        
        for(int i = 0; i<noTerminales.size() ; i++){
            if(noTerminales.get(i) == nonTerminal3){
                comprobador++;
            }
        }
        return comprobador == 1;
    }
    /**
     *Método que, comprueba si el elemento terminal que le es pasado como 
     * parámetro había sido añadido previamente a la gramática. Comprueba el 
     * ArrayList donde se guardan todos los terminales ya añadidos.
     *
     * @param terminal3 Char correspondiente a un terminal a comprobar.
     * @return true si el terminal había sido añadido, false en el caso 
     * contrario.
     */
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
        if (rejectDerived(word)) {
            throw new CYKAlgorithmException("Se descarta la palabra, no cumple los requisitos");
        }
        
        //añade la palabra a la primera posición del arrayList
        for (int i = 0; i < word.length(); i++) {
            comprobar.add(new ArrayList<String>());
            comprobar.get(i).add(""+word.charAt(i));
            
        }           
        //añadir posición 1
        for (int m = 0; m < word.length(); m++) {
            comprobar.get(m).add(obtenerProductores(comprobar.get(m).get(0)));
        }       
        //cada vez busca una posición menos
        int x = word.length()-1;
        for (int i = 2; i <= word.length(); i++) {
            for (int j = 0; j < x; j++) {
                if(comprobar.get(j).get(i-1) != null && comprobar.get(j+1).get(i-1) != null ){
                    comprobar.get(j).add(obtenerCelda(j, i));
                }
            }
            x--;
        }               
        System.out.println(algorithmStateToString(word));
        
        return comprobar.get(0).get(word.length()).contains(""+axioma) == true;
        //throw new CYKAlgorithmException("La palabra no pertenece al lenguaje definido por la gramática");
    
    }
    /**
     * Método que, comprueba que la palabra que ha sido pasada como parámetro
     * cumple con los requisitos para poderhaber sido generada por una gramática.
     * Comprueba si esta no contiene ningun caracter en mayúscula, que todos 
     * los elementos de la palabra hayan sido añadidos como terminales 
     * previamente, que la gramática no este vacía y que el autómata tenga un 
     * axioma definido.
     *
     * @param word , stringa a comprobar.
     * @return true si la palabra pasada como parámetro debería ser rechazada.
     */
    public boolean rejectDerived(String word){
        int a = 0;
        for(int i = 0 ; i < word.length() ; i++){
            if(Character.isUpperCase(word.charAt(i))){
                a ++;
            }else if(!terminales.contains(word.charAt(i))){
                a++;
            }
        }
        
        
        return (axioma == ('\n')) || (matriz.isEmpty()) || (a > 0);
    }

    /**
     * Método que, obtiene la celda cuya posición 'x' e 'y' de la matriz se le pasa 
     * como parámetro, siguiendo el algoritmo propuesto, Este comprara las filas
     * superiores y las diagonales mezclando todos los elementos que contiene y 
     * obtiene sus productores llamando al método ObtenerProductores.
     *
     * @param i posición del ArrayList de Strings.
     * @param j posición del String dentro de los ArrayLists
     * @return Un String con los.
     * 
     */
    public String obtenerCelda(int i, int j){
        int r = i;
        int s = j;
        int o = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("");
        Set<Character> product3 = new HashSet<>();
        
        for (int k = 0 ; k < (j-1) ; k++ ){
            ArrayList<Character> product1 = new ArrayList<>();
            ArrayList<Character> product2 = new ArrayList<>();
                   
            String arriba = comprobar.get(i).get(o);
            String diagonal = comprobar.get(r+1).get(s-1);
            for(int contador = 0 ; contador < arriba.length() ; contador++){
                product1.add(arriba.charAt(contador));
            }
            for(int contador2 = 0 ; contador2 < diagonal.length() ; contador2++){
                product2.add(diagonal.charAt(contador2));
            }
        
            for (Character producto : product1) {
                for(Character producto2 : product2){
                    String productores = obtenerProductores(""+producto + producto2);         
                    for(int z = 0 ; z < productores.length() ; z++){
                        product3.add(productores.charAt(z));
                    }                    
                }
            }
                                                
            r++;
            s--;
            o++;
        }
        for(Character elemento : product3){
                sb.append(elemento);
        }
        return sb.toString();
    }
    /**
     * Método que, paraa cada combinación de no terminales, obtiene sus
     * productures para así agregarlos a la tabla.
     *
     * @param combi es un string formado por dos elementos no terminales.
     * @return Un String con los productores de la combinación pasada como 
     * parámetro.
     */
    public String obtenerProductores(String combi){
        StringBuilder sb = new StringBuilder();
        
            for (int i = 0; i < matriz.size(); i++) {
                for (int j = 1; j < matriz.get(i).size(); j++) {
                    if(combi.equals(matriz.get(i).get(j))){
                        sb.append(matriz.get(i).get(0));
                    }
                }            
            }

        return sb.toString();
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
       
        if (rejectDerived(word)) {
            throw new CYKAlgorithmException("Se descarta la palabra, no cumple los requisitos");
        }
        
       int columnas = comprobar.get(0).size();
       int filas = comprobar.size();
       StringBuilder sb = new StringBuilder();
       
    for (ArrayList<String> comprobar1 : comprobar) {
        sb.append("-------------");
    }
       sb.append("\n");
       for (int i = 0; i <  columnas; i++) {
                      
            sb.append("|");
            for (int j = 0 ; j < filas ; j++) {
                sb.append(String.format("%-10s  |" , comprobar.get(j).get(i)));
            }
            sb.append("\n");
            for(int j =0; j< filas; j++){
                sb.append("-------------");
            }
            sb.append("\n");
            
            if (i > 0){
                filas--;
            }
       }
       return sb.toString();
       
    }

    @Override
    /**
     * Elimina todos los elementos que se han introducido hasta el momento en la
     * gramática (elementos terminales, no terminales, axioma y producciones),
     * dejando el algoritmo listo para volver a insertar una gramática nueva.
     */
    public void removeGrammar() {
            
        axioma = '\n';
        matriz.clear();
        noTerminales.clear();
        terminales.clear();
        removeDeriveds();
    }
    /**
     * Vacía las estructuras de datos usadas para comprobar si una palabra 
     * pertenece a un lenguaje determinado.
     */
    public void removeDeriveds(){
        noTerminalesAnnadidos.clear();
        comprobar.clear();
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
        StringBuilder sb = new StringBuilder();
        
        int a = -1;
        /*
        for (int i = 0; i < matriz.size(); i++) {
            if(matriz.get(i).get(0).equals(String.valueOf(nonterminal))){
                a = i;
            }
        }*/
        for(ArrayList<String> prod : matriz){
            if(prod.get(0).equals(String.valueOf(nonterminal))){
                a = matriz.indexOf(prod);
            }
        }
        
        if(a==-1){
            return "";
        }
        
        sb.append(nonterminal);
        sb.append("::=");
        sb.append(matriz.get(a).get(1));
        for (int i = 2; i < matriz.get(a).size(); i++) {
            sb.append("|");
            sb.append(matriz.get(a).get(i));
        }
        String resultado = sb.toString();
        System.out.println(resultado);
        //System.out.println(String.valueOf(nonterminal) + "::=" + matriz.get(a).get(0) + resto);
        
        return resultado;        
        //String.valueOf(nonterminal).concat("::=").concat(matriz.get(a).get(0).concat(resto));
        //throw new CYKAlgorithmException("Not supported yet.");
    }
    
    @Override
    /**
     * Devuelve un String con la gramática completa.
     *
     * @return Devuelve el agregado de hacer getProductions sobre todos los
     * elementos no terminales.
     */
    public String getGrammar() {
        String gramatica = "";
        for(int i = 0; i < matriz.size() ; i++){
            gramatica = gramatica.concat(getProductions(matriz.get(i).get(0).charAt(0)).concat("\n"));
        }
        System.out.println(gramatica);
        return gramatica;
    }

}
