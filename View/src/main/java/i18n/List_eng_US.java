package i18n;

import java.util.ListResourceBundle;

/**
 * Created by jusia on 27.05.2017.
 */
public class List_eng_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"uni", "Lodz Univeristy of Technology"},
            {"subject", "Component Programming"},
            {"year", "Academic year 2016/2017"},
            {"copyright", "Copyright"},
            {"authors", "Joanna Gorczak & Justyna Kowalczyk"},
            {"back", "Back"},

    };
}

