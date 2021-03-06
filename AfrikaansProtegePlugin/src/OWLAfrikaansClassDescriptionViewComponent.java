/**
 * Created by Casey on 15-Mar-17.
 *
 * View Class for replicating class description view but providing Afrikaans
 * language descriptions
 */

import org.protege.editor.owl.OWLEditorKit;
import org.semanticweb.owlapi.model.*;
import org.protege.editor.owl.ui.framelist.OWLFrameList;
import org.protege.editor.owl.ui.view.cls.AbstractOWLClassViewComponent;
import view.frame.OWLAfrikaansClassDescriptionFrame;

import javax.swing.*;
import java.awt.*;


public class OWLAfrikaansClassDescriptionViewComponent extends AbstractOWLClassViewComponent{

    private OWLFrameList<OWLClass> list;

    @Override
    public void initialiseClassView() {
        OWLEditorKit ek = getOWLEditorKit();

        list = new OWLFrameList<>(ek, new OWLAfrikaansClassDescriptionFrame(ek));
        setLayout(new BorderLayout());
        JScrollPane sp = new JScrollPane(list);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(sp);
    }

    @Override
    public OWLClass updateView(OWLClass selectedClass) {
        list.setRootObject(selectedClass);
        return selectedClass;
    }

    @Override
    public void disposeView() {list.dispose();}
}
