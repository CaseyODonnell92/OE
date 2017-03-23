package view.frame.section.row;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.*;

import java.util.*;

/**
 * Created by Adam on 19-Mar-17.
 */
public class OWLAfrikaansSubClassesAxiomFrameSectionRow extends AbstractOWLAfrikaansFrameSectionRow<OWLClassExpression, OWLSubClassOfAxiom, OWLClassExpression> {

    public OWLAfrikaansSubClassesAxiomFrameSectionRow(OWLEditorKit editorKit, OWLFrameSection<OWLClassExpression, OWLSubClassOfAxiom,
            OWLClassExpression> section, OWLOntology ontology, OWLClassExpression rootObject, OWLSubClassOfAxiom axiom) {
        super(editorKit, section, ontology, rootObject, axiom);
    }

    @Override
    protected OWLObjectEditor getObjectEditor() {
        return null;
    }

    @Override
    protected OWLSubClassOfAxiom createAxiom(OWLClassExpression editedObject) {
        return getOWLDataFactory().getOWLSubClassOfAxiom(getRootObject(), editedObject);
    }


    /**
     * Gets a list of objects contained in this row.  These objects
     * could be placed on the clip board during a copy operation,
     * or navigated to etc.
     */
    public List<OWLClassExpression> getManipulatableObjects() {
        return Arrays.asList(getAxiom().getSuperClass());
    }
}