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
         if( itemsOrdered.size()>30 || itemsOrdered.size() == 0){
             throw  new TakeAwayBillException("ORDINE NON VALIDO");
         }

         double totale = itemsOrdered.stream().mapToDouble(MenuItem::getPrice).sum();

         long contaPanini = itemsOrdered.stream()
                   .filter(m -> m.getType() == MenuItem.ItemType.Panino).count();

         if(contaPanini>5) {

             double paninoMenoCaro = itemsOrdered.stream()
                     .filter(m-> m.getType() == MenuItem.ItemType.Panino)
                     .mapToDouble(MenuItem::getPrice).min().getAsDouble();

             totale -= (paninoMenoCaro*0.5);
         }

         double importoPaniniFritti = itemsOrdered.stream()
                 .filter(x-> x.getType() == MenuItem.ItemType.Panino || x.getType() == MenuItem.ItemType.Fritto)
                 .mapToDouble(MenuItem::getPrice).sum();

         if(importoPaniniFritti > 50) {
               totale -= totale*0.1;
         }

         if(totale < 10) {
             totale += 0.5;
         }

         return totale;
     }
}