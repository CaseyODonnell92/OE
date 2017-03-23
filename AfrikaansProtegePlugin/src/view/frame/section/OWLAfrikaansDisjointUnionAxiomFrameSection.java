package view.frame.section;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.AbstractOWLFrameSection;
import org.protege.editor.owl.ui.frame.OWLFrame;
import org.protege.editor.owl.ui.frame.OWLFrameSectionRow;
import org.semanticweb.owlapi.model.*;
import view.frame.section.row.OWLAfrikaansDisjointUnionAxiomFrameSectionRow;

import java.util.Comparator;
import java.util.Set;

/**
 * Created by Casey on 23-Mar-17.
 */
public class OWLAfrikaansDisjointUnionAxiomFrameSection extends AbstractOWLFrameSection<OWLClass, OWLDisjointUnionAxiom, Set<OWLClassExpression>> {

    public OWLAfrikaansDisjointUnionAxiomFrameSection(OWLEditorKit editorKit, OWLFrame<OWLClass> frame) {
        super(editorKit, "Disjunkte Unie Van", "Disjunkte Unie Van", frame);
    }

    @Override
    protected OWLDisjointUnionAxiom createAxiom(Set<OWLClassExpression> owlClassExpressions) {
        return null;
    }

    @Override
    public OWLObjectEditor<Set<OWLClassExpression>> getObjectEditor() {
        return null;
    }

    @Override
    protected void refill(OWLOntology owlOntology) {
        for (OWLDisjointUnionAxiom axiom : owlOntology.getDisjointUnionAxioms(getRootObject())) {
            addRow(new OWLAfrikaansDisjointUnionAxiomFrameSectionRow(getOWLEditorKit(), this, owlOntology, getRootObject(), axiom));
        }
    }

    @Override
    protected void clear() {

    }

    @Override
    public boolean canAdd() {
        return false;
    }

    @Override
    protected boolean isResettingChange(OWLOntologyChange change) {
        return change.isAxiomChange() &&
                change.getAxiom() instanceof OWLDisjointUnionAxiom &&
                ((OWLDisjointUnionAxiom) change.getAxiom()).getOWLClass().equals(getRootObject());
    }

    @Override
    public Comparator<OWLFrameSectionRow<OWLClass, OWLDisjointUnionAxiom, Set<OWLClassExpression>>> getRowComparator() {
        return null;
    }
}
