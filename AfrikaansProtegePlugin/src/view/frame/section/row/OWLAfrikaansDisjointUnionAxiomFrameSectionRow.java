package view.frame.section.row;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.semanticweb.owlapi.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Casey on 23-Mar-17.
 */
public class OWLAfrikaansDisjointUnionAxiomFrameSectionRow extends AbstractOWLAfrikaansFrameSectionRow<OWLClass, OWLDisjointUnionAxiom, Set<OWLClassExpression>> {

    @Override
    protected OWLDisjointUnionAxiom createAxiom(Set<OWLClassExpression> owlClassExpressions) {
        return null;
    }

    public OWLAfrikaansDisjointUnionAxiomFrameSectionRow(OWLEditorKit owlEditorKit,
                                                         OWLFrameSection<OWLClass, OWLDisjointUnionAxiom, Set<OWLClassExpression>> section,
                                                         OWLOntology ontology, OWLClass rootObject,
                                                         OWLDisjointUnionAxiom axiom) {
        super(owlEditorKit, section, ontology, rootObject, axiom);
    }

    public List<OWLClassExpression> getManipulatableObjects() {
        return new ArrayList<>(getAxiom().getClassExpressions());
    }
}
