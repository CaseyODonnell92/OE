package view.frame.section;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.OWLFrame;
import org.protege.editor.owl.ui.frame.OWLFrameSectionRow;
import org.protege.editor.owl.ui.frame.cls.AbstractOWLClassAxiomFrameSection;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.CollectionFactory;
import view.frame.section.row.OWLAfrikaansDisjointClassesAxiomFrameSectionRow;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam on 2017/03/20.
 */
public class OWLAfrikaansDisjointClassesAxiomFrameSection extends AbstractOWLClassAxiomFrameSection<OWLDisjointClassesAxiom, OWLClassExpression> {

    private static final String LABEL = "Disjunkte van";

    private Set<OWLClassExpression> added = new HashSet<>();


    public OWLAfrikaansDisjointClassesAxiomFrameSection(OWLEditorKit editorKit, OWLFrame<OWLClass> frame) {
        super(editorKit, LABEL, "Disjunkte", frame);
    }

    public boolean canAdd() {
        return false;
    }

    @Override
    protected void addAxiom(OWLDisjointClassesAxiom ax, OWLOntology ontology) {
        addRow(new OWLAfrikaansDisjointClassesAxiomFrameSectionRow(getOWLEditorKit(),
                this,
                ontology,
                getRootObject(),
                ax));
        for (OWLClassExpression desc : ax.getClassExpressions()) {
            added.add(desc);
        }
    }

    @Override
    protected Set<OWLDisjointClassesAxiom> getClassAxioms(OWLClassExpression owlClassExpression, OWLOntology owlOntology) {
        if (!owlClassExpression.isAnonymous()) {
            return owlOntology.getDisjointClassesAxioms(owlClassExpression.asOWLClass());
        } else {
            Set<OWLDisjointClassesAxiom> axioms = new HashSet<>();
            for (OWLAxiom ax : owlOntology.getGeneralClassAxioms()) {
                if (ax instanceof OWLDisjointClassesAxiom &&
                        ((OWLDisjointClassesAxiom) ax).getClassExpressions().contains(owlClassExpression)) {
                    axioms.add((OWLDisjointClassesAxiom) ax);
                }
            }
            return axioms;
        }
    }

    @Override
    protected boolean isResettingChange(OWLOntologyChange change) {
        return change.isAxiomChange() &&
                change.getAxiom() instanceof OWLDisjointClassesAxiom &&
                ((OWLDisjointClassesAxiom) change.getAxiom()).getClassExpressions().contains(getRootObject());
    }

    @Override
    protected OWLDisjointClassesAxiom createAxiom(OWLClassExpression owlClassExpression) {
        return getOWLDataFactory().getOWLDisjointClassesAxiom(CollectionFactory.createSet(getRootObject(), owlClassExpression));
    }

    @Override
    public OWLObjectEditor<OWLClassExpression> getObjectEditor() {
        return null;
    }

    @Override
    protected void clear() {
        added.clear();
    }

    @Override
    public Comparator<OWLFrameSectionRow<OWLClassExpression, OWLDisjointClassesAxiom, OWLClassExpression>> getRowComparator() {
        return null;
    }
}