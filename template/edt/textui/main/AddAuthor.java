package edt.textui.main;

import edt.core.App;
import edt.core.Document;
import edt.core.Author;

import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;

/**
 * Command for adding an author to the current document in the editor.
 */
public class AddAuthor extends Command<App> {

    /**
     * Constructor.
     * 
     * @param app the target entity.
     */
    public AddAuthor(App app) {
        super(MenuEntry.ADD_AUTHOR, app);
    }

    /**
     * Execute the command.
     */
    @Override
    @SuppressWarnings("nls")
    public final void execute() {
        String name;
        String email;
        Form f = new Form();
        InputString inS = new InputString(f, Message.requestAuthorName());
        InputString inE = new InputString(f, Message.requestEmail());
        f.parse();

        Author author = new Author(inS.value(), inE.value());

        entity().getDocument().addAuthor(author);
    }
}
