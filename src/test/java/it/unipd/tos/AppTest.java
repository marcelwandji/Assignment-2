////////////////////////////////////////////////////////////////////
// MARCEL JUNIOR WANDJI 1171044
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private ArrayList<MenuItem> list;
    private App myApp;

    public AppTest() {
        myApp = new App();
    }
    
    @Before
    public void before() {
        list = new ArrayList<MenuItem>();
    }
    /**
     * Dato un elenco di ordinazioni (Panini e Fritti e Bevande) calcolare il totale
     */
    @Test
    public void test_totaleElencoOrdineDiPanini_Fritti_e_Bevande() throws TakeAwayBillException {

         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",3.5));
         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",3.5));
         list.add(new MenuItem(MenuItem.ItemType.Fritto,"arancino",2.5));
         list.add(new MenuItem(MenuItem.ItemType.Bevande,"fanta",1.5));

         assertEquals(11,myApp.getOrderPrice(list),0);
    }
    
    /**
     *  Se vengono ordinati più di 5 Panini viene fatto uno sconto del 50% sul prezzo 
     *  del panino meno caro
     */
    @Test
    public void test_OrdineConPiuDi5PaniniScontoDi50pcSuPaninoMenoCaro() throws TakeAwayBillException {

        for(int i=0; i<6; i++) {
            list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",3.5));
        }

        assertEquals(21-1.75,myApp.getOrderPrice(list),0);
    }

    /**
     *  Se l’importo totale delle ordinazioni (Panini e Fritti) supera i 50 euro viene fatto 
     *  il 10% di sconto
     */
    @Test
    public void test_scontoDi10pcSeLimportoTotaleDiPanini_e_FrittiSuperaI50() throws TakeAwayBillException {

         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",20));
         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",20));
         list.add(new MenuItem(MenuItem.ItemType.Fritto,"arancino",15));
         list.add(new MenuItem(MenuItem.ItemType.Fritto,"arancino",15));

         assertEquals(70-7,myApp.getOrderPrice(list),0);
    }
    
    /**
     * Non è possibile avere un’ordinazione con più di 30 elementi (se accade prevedere un 
     * messaggio d’errore
     */
    @Test(expected = TakeAwayBillException.class)
    public void test_OrdineConPiuDi30ElementiLanciaUnEccezione() throws TakeAwayBillException {

        for(int i=0; i<31; i++)
            list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",20));

        myApp.getOrderPrice(list);

    }
    
    /**
     *  Se l’importo totale è inferiore a 10 € viene aggiunta una commissione di 0,50 €
     */
    @Test
    public void test_aggiuntaCommisione_0_5_seImportoTotaleminoreDi10() throws TakeAwayBillException{

         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",2.5));
         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",2.5));
         list.add(new MenuItem(MenuItem.ItemType.Bevande,"Coca-Cola",1.5));
         list.add(new MenuItem(MenuItem.ItemType.Bevande,"Pepsi",1.5));

         assertEquals(8+0.5,myApp.getOrderPrice(list),0);
    }

    /**
     *  Se non c'e' nessun ordine viene segnalato un errore 
     */
    @Test(expected = TakeAwayBillException.class)
    public void test_OrdineConNessunElemento() throws TakeAwayBillException {
    	myApp.getOrderPrice(list);
    }

    /**
     *  Ordine con più di 5 Panini  e l'importo totale (Panini e Fritti) supera i 50 euro 
     *   
     */
    @Test
    public void test_ordineConPiuDi5Panini_e_importoTotalePaniniFrittiSuperaI50() throws TakeAwayBillException{

        for(int i=0; i<8; i++) {
            list.add(new MenuItem(MenuItem.ItemType.Panino,"primavera",5));
            list.add(new MenuItem(MenuItem.ItemType.Fritto,"arancino",4));
        }

        double totale = 5*8 + 4*8; // totale e' 72
        totale -= 2.5; // sconto di 50% sul prezzo del panino meno caro quindi uguale a 69.5
        totale -= 0.1*totale; //sconto di 10% perche' totale e' maggiore di 50 quindi 69,5-6,95= 62,55

        assertEquals(totale,myApp.getOrderPrice(list),0);
    }

    /**
     *  Ordine con più di 5 Panini  e l'importo totale minore di 10 euro 
     *   
     */
    @Test
    public void test_ordineConPiuDi5Panini_e_importoTotaleMinoreDi10() throws TakeAwayBillException{

        for(int i=0; i<5; i++) {
            list.add(new MenuItem(MenuItem.ItemType.Panino,"primavera",1.75));
        }

        list.add(new MenuItem(MenuItem.ItemType.Panino,"primavera",2));

        double totale = 5*1.75 + 2; // totale e' 10,75
        totale -= 0.875; // sconto di 50% sul prezzo del panino meno caro quindi uguale a 9,875
        totale += 0.5 ;  // commissione di 0,5 perche' totale e' inferiore a 10 euro quindi 9,875+0,5= 10,375

        assertEquals(totale,myApp.getOrderPrice(list),0);
    }
}