package edt.textui.main;

import edt.core.App;
import edt.core.Document;

import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputInteger;

/**
 * Command for creating a new document in the editor.
 */
public class NewDocument extends Command<App> {

    /**
     * Constructor.
     * 
     * @param app the target entity.
     */
    public NewDocument(App app) {
        super(MenuEntry.NEW, app);
    }

    /**
     * Execute the command.
     */
    @Override
    @SuppressWarnings("nls")
    public final void execute() {
        
        Document doc = new Document();

        entity().setDocument(doc);
        
    }
}
