/**
 * Created by Casey on 15-Mar-17.
 *
 * View Class for replicating class description view but providing Afrikaans
 * language descriptions
 */

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.frame.OWLFrame;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.protege.editor.owl.ui.frame.OWLFrameSectionRow;
import org.protege.editor.owl.ui.frame.cls.OWLClassDescriptionFrame;
import org.semanticweb.owlapi.model.*;
import org.protege.editor.owl.ui.framelist.OWLFrameList;
import org.protege.editor.owl.ui.view.cls.AbstractOWLClassViewComponent;
import java.util.List;

import javax.swing.*;
import java.awt.*;


public class AfrikaansViewComponent extends AbstractOWLClassViewComponent{

    private OWLFrameList<OWLClass> list;
    private TextArea textArea;

    @Override
    public void initialiseClassView() {
        OWLEditorKit ek = getOWLEditorKit();

        list = new OWLFrameList<>(ek, new OWLClassDescriptionFrame(ek));
        setLayout(new BorderLayout());
        JScrollPane sp = new JScrollPane(list);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea = new TextArea();
        //add(sp);
        add(textArea);
        textArea.setText("Welkom by die Afrikaanse Protege plugin!");

    }

    @Override
    public OWLClass updateView(OWLClass selectedClass) {
        list.setRootObject(selectedClass);


        textArea.setText(textArea.getText());

        OWLFrame frame = list.getFrame();
        //textArea.setText(frame.toString());
        for (OWLFrameSection section: (List<OWLFrameSection>)frame.getFrameSections()) {
            for (OWLFrameSectionRow row: (List<OWLFrameSectionRow>) section.getRows()) {
                textArea.setText(textArea.getText() + "\n" + (row.getAxiom().toString().replace("some", "ten minste").replace("only", "net"))); // + " : " + row.getAxiom().getAxiomWithoutAnnotations()));
            }
        }
        return selectedClass;
    }

    @Override
    public void disposeView(){list.dispose();
    }
}
