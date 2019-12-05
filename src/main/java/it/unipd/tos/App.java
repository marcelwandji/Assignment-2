////////////////////////////////////////////////////////////////////
// MARCEL JUNIOR WANDJI 1171044
////////////////////////////////////////////////////////////////////

package it.unipd.tos;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

import java.util.List;


/**
 * Hello world!
 *
 */
public class App implements TakeAwayBill
{
     @Override
     public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
         double totale = 0;

         totale = itemsOrdered.stream().mapToDouble(MenuItem::getPrice).sum();

         return totale;
     }
}