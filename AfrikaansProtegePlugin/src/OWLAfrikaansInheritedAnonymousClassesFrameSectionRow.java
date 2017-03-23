import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.CollectionFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Casey on 23-Mar-17.
 */
public class OWLAfrikaansInheritedAnonymousClassesFrameSectionRow extends AbstractOWLAfrikaansFrameSectionRow<OWLClass,
        OWLClassAxiom, OWLClassExpression> {

    public OWLAfrikaansInheritedAnonymousClassesFrameSectionRow(OWLEditorKit owlEditorKit, OWLFrameSection<OWLClass,
            OWLClassAxiom, OWLClassExpression> section, OWLOntology ontology, OWLClass rootObject, OWLClassAxiom axiom) {
        super(owlEditorKit, section, ontology, rootObject, axiom);
    }

    @Override
    protected OWLClassAxiom createAxiom(OWLClassExpression owlClassExpression) {
        return null;
    }

    @Override
    public List<? extends OWLObject> getManipulatableObjects() {
        if (getAxiom() instanceof OWLSubClassOfAxiom) {
            return Arrays.asList(((OWLSubClassOfAxiom) getAxiom()).getSuperClass());
        } else {
            Set<OWLClassExpression> descs = new HashSet<>(((OWLEquivalentClassesAxiom) getAxiom()).getClassExpressions());
            descs.remove(getRootObject());
            if (descs.isEmpty()) {
                // in the weird case that something is asserted equiv to itself
                OWLClassExpression cls = getRootObject();
                return Arrays.asList(cls);
            }
            return Arrays.asList(descs.iterator().next());
        }
    }
}
