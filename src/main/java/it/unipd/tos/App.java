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

         long contaPanini = itemsOrdered.stream()
                   .filter(m -> m.getType() == MenuItem.ItemType.Panino).count();

         if(contaPanini>5) {

             double paninoMenoCaro = itemsOrdered.stream()
                     .filter(m-> m.getType() == MenuItem.ItemType.Panino)
                     .mapToDouble(MenuItem::getPrice).min().getAsDouble();

             totale -= (paninoMenoCaro*0.5);
         }

         return totale;
     }
}