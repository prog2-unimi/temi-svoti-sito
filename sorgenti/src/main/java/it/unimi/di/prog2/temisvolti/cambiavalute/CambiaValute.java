package it.unimi.di.prog2.temisvolti.cambiavalute;

import java.util.List;

public class CambiaValute {
  
  Cassa cassa = new Cassa();
  Cambi tassi = new Cambi();
  
  public CambiaValute(List<Importo> importi) {
    for (Importo importo : importi) cassa.versa(importo);
  }

  public boolean aggiorna(Cambi.Tasso tasso) {
    return tassi.aggiorna(tasso);
  }

  public Importo cambia(Importo da, Valuta aValuta) {
    if (da.valuta == aValuta) throw new IllegalArgumentException("Impossibile cambiare tra valute identiche");
    Cambi.Tasso t = tassi.cerca(da.valuta, aValuta);
    if (t == null) throw new IllegalArgumentException("Tasso non disponibile");
    Importo a = da.equivalente(t);
    if (cassa.totale(aValuta).compareTo(a) < 0) 
      throw new IllegalArgumentException("Fondi non sufficienti");
    cassa.versa(da);
    cassa.preleva(a);
    return a;
  }

  @Override
  public String toString() {
    return tassi.toString() + "\n" + cassa.toString();
  }
}
