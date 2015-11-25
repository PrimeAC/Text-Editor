package edt.textui.section;

import edt.core.Section;
import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;

/* FIXME: import core classes here */

/**
 * Command for listing all subsections of the current section.
 */
public class ListSections extends Command<Section> {

    /**
     * Constructor.
     * 
     * @param ent the target entity.
     */
    public ListSections(Section section) {
        super(MenuEntry.LIST_SECTIONS, section);
    }

    /**
     * Execute the command.
     */
    @Override
    @SuppressWarnings("nls")
    public final void execute() {
        Display display = new Display();

        display.add(entity().getAllTitles());
        display.display();
        
    }
}
