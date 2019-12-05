////////////////////////////////////////////////////////////////////
// MARCEL JUNIOR WANDJI 1171044
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
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
    /**
     * Dato un elenco di ordinazioni (Panini e Fritti e Bevande) calcolare il totale
     */
        @Test
        public void test_totaleElencoOrdineDiPaniniFrittiBevande() throws TakeAwayBillException {
            list = new ArrayList<MenuItem>();
            myApp = new App();

            list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",3.5));
            list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",3.5));
            list.add(new MenuItem(MenuItem.ItemType.Fritto,"arancino",2.5));
            list.add(new MenuItem(MenuItem.ItemType.Bevande,"fanta",1.5));

            assertEquals(11,myApp.getOrderPrice(list),0);
        }
}