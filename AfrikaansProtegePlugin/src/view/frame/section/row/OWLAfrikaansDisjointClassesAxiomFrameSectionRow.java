package view.frame.section.row;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.*;

import java.util.*;

/**
 * Created by Adam on 19-Mar-17.
 */
public class OWLAfrikaansDisjointClassesAxiomFrameSectionRow extends AbstractOWLAfrikaansFrameSectionRow<OWLClassExpression, OWLDisjointClassesAxiom, OWLClassExpression> {

    public OWLAfrikaansDisjointClassesAxiomFrameSectionRow(OWLEditorKit editorKit, OWLFrameSection<OWLClassExpression, OWLDisjointClassesAxiom,
            OWLClassExpression> section, OWLOntology ontology, OWLClassExpression rootObject, OWLDisjointClassesAxiom axiom) {
        super(editorKit, section, ontology, rootObject, axiom);
    }

    @Override
    protected OWLObjectEditor getObjectEditor() {
        return null;
    }

    @Override
    protected OWLDisjointClassesAxiom createAxiom(OWLClassExpression editedObject) {
        return null;
    }

    protected List<OWLClassExpression> getObjects() {
        Set<OWLClassExpression> clses = new HashSet<>(getAxiom().getClassExpressions());
        clses.remove(getRoot());
        return new ArrayList<>(clses);
    }

    /**
     * Gets a list of objects contained in this row.  These objects
     * could be placed on the clip board during a copy operation,
     * or navigated to etc.
     */
    public List<OWLClassExpression> getManipulatableObjects() {
        return getObjects();
    }
}
