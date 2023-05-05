package es.ceu.gisi.modcomp.cyk_algorithm.algorithm.test;

import es.ceu.gisi.modcomp.cyk_algorithm.algorithm.CYKAlgorithm;
import es.ceu.gisi.modcomp.cyk_algorithm.algorithm.exceptions.CYKAlgorithmException;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Clase que testea el correcto funcionamiento de la implementación del
 * algoritmo CYK haciendo uso de la clase CYKAlgorithm.
 *
 * El objetivo de estos tests es comprobar si la implementación del alumno en la
 * realización de su clase CYKAlgorithm cumple con los requisitos básicos.
 *
 * El código del alumno, no obstante, será comprobado con tests adicionales.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class BasicTest {

    private CYKAlgorithm cyk;

    public BasicTest() throws IOException, FileNotFoundException, CYKAlgorithmException {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void comprobarAniadirTerminalValido() throws CYKAlgorithmException {
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');
    }

    @Test
    public void comprobarAniadirTerminalNoValido1() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('0');
    }

    @Test
    public void comprobarAniadirTerminalNoValido2() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('A');
    }

    @Test
    public void comprobarAniadirTerminalNoValido3() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');
        cyk.addTerminal('a');
    }

    @Test
    public void comprobarAniadirNoTerminalValido() throws CYKAlgorithmException {
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('S');
    }

    @Test
    public void comprobarAniadirNoTerminalNoValido1() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('a');
    }

    @Test
    public void comprobarAniadirNoTerminalNoValido2() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('0');
    }

    @Test
    public void comprobarAniadirNoTerminalNoValido3() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('S');
        cyk.addNonTerminal('S');
    }

    @Test
    public void comprobarEstablecerAxiomaValido() throws CYKAlgorithmException {
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('S');
        cyk.setStartSymbol('S');
    }

    @Test
    public void comprobarEstablecerAxiomaNoValido1() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.setStartSymbol('S');
    }

    @Test
    public void comprobarEstablecerAxiomaNoValido2() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('A');
        cyk.addTerminal('a');
        cyk.setStartSymbol('S');
    }

    @Test
    public void comprobarEstablecerAxiomaNoValido3() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('A');
        cyk.addTerminal('a');
        cyk.setStartSymbol('a');
    }

    @Test
    public void comprobarAniadirProduccionValida() throws CYKAlgorithmException {
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');
        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addProduction('S', "AS");
        //cyk.addProduction('S', "a");
    }

    @Test
    public void comprobarAniadirProduccionNoValida1() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');

        cyk.addProduction('S', "b");
    }

    @Test
    public void comprobarAniadirProduccionNoValida2() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');

        cyk.addProduction('C', "a");
    }

    @Test
    public void comprobarAniadirProduccionNoValida3() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addProduction('S', "AB");
    }

    @Test
    public void comprobarAniadirProduccionNoValida4() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');

        cyk.addProduction('S', "A");
    }

    @Test
    public void comprobarAniadirProduccionNoValida5() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addProduction('A', "aA");
    }

    @Test
    public void comprobarAniadirProduccionNoValida6() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');

        cyk.addProduction('A', "aa");
    }

    @Test
    public void comprobarAniadirProduccionNoValida7() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');

        cyk.addProduction('A', "SAA");
    }

    @Test
    public void comprobarAniadirProduccionNoValida8() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);
        cyk = new CYKAlgorithm();
        cyk.addTerminal('a');

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');

        cyk.addProduction('A', "SA");
        cyk.addProduction('A', "SA");
    }

    @Test
    public void comprobarRecuperarProducciones() throws CYKAlgorithmException {
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BC");

        cyk.addProduction('A', "BA");
        cyk.addProduction('A', "a");

        cyk.addProduction('B', "CC");
        cyk.addProduction('B', "b");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "a");
                
        assertEquals("S::=AB|BC", cyk.getProductions('S'));
        assertEquals("A::=BA|a", cyk.getProductions('A'));
        assertEquals("B::=CC|b", cyk.getProductions('B'));
        assertEquals("C::=AB|a", cyk.getProductions('C'));

    }

    @Test
    public void comprobarEliminarGramaticaValido() throws CYKAlgorithmException {
        cyk = new CYKAlgorithm();
        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BC");

        cyk.addProduction('A', "BA");
        cyk.addProduction('A', "a");

        cyk.addProduction('B', "CC");
        cyk.addProduction('B', "b");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "a");

        cyk.removeGrammar();

        assertEquals("", cyk.getProductions('S'));
        assertEquals("", cyk.getProductions('A'));
        assertEquals("", cyk.getProductions('B'));
        assertEquals("", cyk.getProductions('C'));

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');
    }
    @Test
    public void comprobarImprimirGram() throws CYKAlgorithmException {
        cyk = new CYKAlgorithm();
        
        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BC");

        cyk.addProduction('A', "BA");
        cyk.addProduction('A', "a");

        cyk.addProduction('B', "CC");
        cyk.addProduction('B', "b");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "a");
        
        cyk.getGrammar();
    }
    @Test
    public void comprobarImprimir() throws CYKAlgorithmException{
        cyk = new CYKAlgorithm();
        
        
        
    }

    @Test
    public void comprobarDerivacionNoValido1() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BC");

        cyk.addProduction('A', "BA");
        cyk.addProduction('A', "a");

        cyk.addProduction('B', "CC");
        cyk.addProduction('B', "b");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "a");

        cyk.isDerived("bbBbb");
    }

    @Test
    public void comprobarDerivacionNoValido2() throws CYKAlgorithmException {
        thrown.expect(CYKAlgorithmException.class);

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BC");

        cyk.addProduction('A', "BA");
        cyk.addProduction('A', "a");

        cyk.addProduction('B', "CC");
        cyk.addProduction('B', "b");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "a");

        cyk.isDerived("caabb");
    }

    @Test
    public void comprobarDerivacionValido1() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BC");

        cyk.addProduction('A', "BA");
        cyk.addProduction('A', "a");

        cyk.addProduction('B', "CC");
        cyk.addProduction('B', "b");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "a");

        assertTrue(cyk.isDerived("baaba"));
    }

    @Test
    public void comprobarDerivacionValido2() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BC");

        cyk.addProduction('A', "BA");
        cyk.addProduction('A', "a");

        cyk.addProduction('B', "CC");
        cyk.addProduction('B', "b");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "a");

        assertFalse(cyk.isDerived("bbb"));
    }
     @Test
    public void comprobarDerivacionCorrectoG1() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('A');

        cyk.addProduction('A', "BC");
        
        cyk.addProduction('B', "CA");
        cyk.addProduction('B', "a");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "b");

        assertTrue(cyk.isDerived("bbabbaba"));
    }
     @Test
    public void comprobarDerivacionIncorrectoG1() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('A');

        cyk.addProduction('A', "BC");
        
        cyk.addProduction('B', "CA");
        cyk.addProduction('B', "a");

        cyk.addProduction('C', "AB");
        cyk.addProduction('C', "b");

        assertFalse(cyk.isDerived("bbabaaba"));
    }
     @Test
    public void comprobarDerivacionCorrectoG2() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');
        cyk.addNonTerminal('D');

        cyk.addTerminal('a');
        cyk.addTerminal('b');
        cyk.addTerminal('c');

        cyk.setStartSymbol('A');

        cyk.addProduction('A', "BC");       
        cyk.addProduction('A', "a");
        
        cyk.addProduction('B', "CD");

        cyk.addProduction('C', "BA");
        cyk.addProduction('C', "b");
        
        cyk.addProduction('D', "c");

        assertTrue(cyk.isDerived("bcbcbcacb"));
    }
     @Test
    public void comprobarDerivacionIncorrectoG2() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');
        cyk.addNonTerminal('D');

        cyk.addTerminal('a');
        cyk.addTerminal('b');
        cyk.addTerminal('c');

        cyk.setStartSymbol('A');

        cyk.addProduction('A', "BC");       
        cyk.addProduction('A', "a");
        
        cyk.addProduction('B', "CD");

        cyk.addProduction('C', "BA");
        cyk.addProduction('C', "b");
        
        cyk.addProduction('D', "c");

        assertFalse(cyk.isDerived("aabcaabc"));
    }
     @Test
    public void comprobarDerivacionCorrectoG3() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');
        cyk.addNonTerminal('D');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        
        cyk.addProduction('A', "BS");       
        cyk.addProduction('A', "a");
        
        cyk.addProduction('B', "SA");
        cyk.addProduction('B', "b");
        cyk.addProduction('B', "DC");
        
        cyk.addProduction('C', "a");
        
        cyk.addProduction('D', "b");

        assertTrue(cyk.isDerived("babaaba"));
    }
     @Test
    public void comprobarDerivacionIncorrectoG3() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');
        cyk.addNonTerminal('C');
        cyk.addNonTerminal('D');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        
        cyk.addProduction('A', "BS");       
        cyk.addProduction('A', "a");
        
        cyk.addProduction('B', "SA");
        cyk.addProduction('B', "b");
        cyk.addProduction('B', "DC");
        
        cyk.addProduction('C', "a");
        
        cyk.addProduction('D', "b");

        assertFalse(cyk.isDerived("bbabb"));
    }
     @Test
    public void comprobarDerivacionCorrectoG4() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BA");
        cyk.addProduction('S', "a");
        
        cyk.addProduction('A', "AA");       
        cyk.addProduction('A', "BB");
        
        cyk.addProduction('B', "BB");
        cyk.addProduction('B', "SB");
        cyk.addProduction('B', "b");              

        assertTrue(cyk.isDerived(""));
    }
    
     @Test
    public void comprobarDerivacionInorrectoG4() throws CYKAlgorithmException {

        cyk = new CYKAlgorithm();

        cyk.addNonTerminal('S');
        cyk.addNonTerminal('A');
        cyk.addNonTerminal('B');

        cyk.addTerminal('a');
        cyk.addTerminal('b');

        cyk.setStartSymbol('S');

        cyk.addProduction('S', "AB");
        cyk.addProduction('S', "BA");
        cyk.addProduction('S', "a");
        
        cyk.addProduction('A', "AA");       
        cyk.addProduction('A', "BB");
        
        cyk.addProduction('B', "BB");
        cyk.addProduction('B', "SB");
        cyk.addProduction('B', "b");              

        assertTrue(cyk.isDerived(""));
    }
}
