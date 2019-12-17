package models;

import controllers.Min_Liste_Controller;

public class MyListFilter implements Filter {
    @Override
    public boolean matches(Media m) {
        boolean foundMatch = false;

        //et foreach loop af typen string der hedder 'entry', der kører igenem alle elementer i my_list som tilgåes
        //igennem metoden getMy_list() osv..
        for (String entry : Min_Liste_Controller.getMy_list()) {
            //hvis m matcher med et ImbdID, så sættes foundMatch til true, og foreach loopet stoppes.
            if (entry.equals(m.getImdbID())) {
                foundMatch = true;
                break;
            }

        }
        return foundMatch;
    }

}
