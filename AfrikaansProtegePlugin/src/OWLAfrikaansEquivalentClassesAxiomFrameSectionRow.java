import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.util.CollectionFactory;
import org.semanticweb.owlapi.model.*;

import java.util.*;

/**
 * Created by Casey on 19-Mar-17.
 */
public class OWLAfrikaansEquivalentClassesAxiomFrameSectionRow extends AbstractOWLAfrikaansFrameSectionRow<OWLClassExpression, OWLEquivalentClassesAxiom, OWLClassExpression> {

    public OWLAfrikaansEquivalentClassesAxiomFrameSectionRow(OWLEditorKit editorKit, OWLFrameSection<OWLClassExpression, OWLEquivalentClassesAxiom,
            OWLClassExpression> section, OWLOntology ontology, OWLClassExpression rootObject, OWLEquivalentClassesAxiom axiom) {
        super(editorKit, section, ontology, rootObject, axiom);
    }

    @Override
    protected OWLEquivalentClassesAxiom createAxiom(OWLClassExpression editedObject) {
        return getOWLDataFactory().getOWLEquivalentClassesAxiom(CollectionFactory.createSet(getRoot(), editedObject));
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
