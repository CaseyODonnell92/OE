package view.frame.section.row;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2017/03/22.
 */
public class OWLAfrikaansGeneralClassAxiomFrameSectionRow extends AbstractOWLAfrikaansFrameSectionRow<OWLClass, OWLClassAxiom, OWLClassAxiom> {

    public OWLAfrikaansGeneralClassAxiomFrameSectionRow(OWLEditorKit owlEditorKit, OWLFrameSection<OWLClass, OWLClassAxiom, OWLClassAxiom> section, OWLOntology ontology, OWLClass rootObject, OWLClassAxiom axiom) {
        super(owlEditorKit, section, ontology, rootObject, axiom);
    }

    @Override
    protected OWLObjectEditor<OWLClassAxiom> getObjectEditor() {
        return null;
    }

    public boolean isEditable() {return false;}

    @Override
    protected OWLClassAxiom createAxiom(OWLClassAxiom editedObject) {
        return editedObject;
    }

    @Override
    public List<? extends OWLObject> getManipulatableObjects() {
        ArrayList<OWLClassAxiom> list = new ArrayList<>();
        list.add(getAxiom());
        return list;
    }
}
