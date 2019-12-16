package models;

import controllers.Min_Liste_Controller;

public class MyListFilter implements Filter {
    @Override
    public boolean matches(Media m) {
        boolean foundMatch = false;
        for (String entry : Min_Liste_Controller.getMy_list()) {
            if (entry.equals(m.getImdbID())) {
                foundMatch = true;
                break;
            }

        }
        return !foundMatch;
    }

}
