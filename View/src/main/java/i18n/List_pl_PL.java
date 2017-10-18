package i18n;

import java.util.ListResourceBundle;

/**
 * Created by jusia on 27.05.2017.
 */
public class List_pl_PL extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"uni", "Politechnika Lodzka"},
            {"subject", "Programowanie komponentowe"},
            {"year", "Rok akademicki 2016/2017"},
            {"copyright", "Prawa autorskie"},
            {"authors", "Joanna Gorczak & Justyna Kowalczyk"},
            {"back", "Powrot"},

    };
}
