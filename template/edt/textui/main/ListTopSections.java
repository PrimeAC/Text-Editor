package edt.textui.main;

import java.util.SortedSet;
import edt.core.Document;
import edt.core.Section;

import edt.core.App;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;

/**
 * Command for showing the top sections of the current document in the editor.
 */
public class ListTopSections extends Command<App> {

    /**
     * Constructor.
     * 
     * @param app the target entity.
     */
    public ListTopSections(App app) {
        super (MenuEntry.SHOW_INDEX, app);
    }

    /**
     * Execute the command.
     */
    @Override
    @SuppressWarnings("nls")
    public final void execute() {
        Display display = new Display();

        display.add(entity().getDocument().getHeadLine());

        for (Section i : entity().getDocument().getSubsections()) {
            if (i.getKey() == null) {
               display.addNewLine(Message.sectionIndexEntry("", i.getTitle())); 
            }
            else {
                display.addNewLine(Message.sectionIndexEntry(i.getKey(), i.getTitle()));
            }
        }
        display.display();
    }
}
