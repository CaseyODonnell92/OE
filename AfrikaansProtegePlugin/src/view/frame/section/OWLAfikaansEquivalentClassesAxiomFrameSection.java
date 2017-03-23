package view.frame.section;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.OWLFrame;
import org.protege.editor.owl.ui.frame.OWLFrameSectionRow;
import org.protege.editor.owl.ui.frame.cls.AbstractOWLClassAxiomFrameSection;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.CollectionFactory;
import view.frame.section.row.OWLAfrikaansEquivalentClassesAxiomFrameSectionRow;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Casey on 19-Mar-17.
 */
public class OWLAfikaansEquivalentClassesAxiomFrameSection extends AbstractOWLClassAxiomFrameSection<OWLEquivalentClassesAxiom, OWLClassExpression> {

    private static final String LABEL = "Dieselfde as";

    private Set<OWLClassExpression> added = new HashSet<>();

    private boolean inferredEquivalentClasses = true;

    public OWLAfikaansEquivalentClassesAxiomFrameSection(OWLEditorKit editorKit, OWLFrame<OWLClass> frame) {
        super(editorKit, LABEL, "Dieselfde klas", frame);
    }

    public boolean canAdd() {
        return false;
    }

    @Override
    protected void addAxiom(OWLEquivalentClassesAxiom ax, OWLOntology ontology) {
        addRow(new OWLAfrikaansEquivalentClassesAxiomFrameSectionRow(getOWLEditorKit(),
                this,
                ontology,
                getRootObject(),
                ax));
        for (OWLClassExpression desc : ax.getClassExpressions()) {
            added.add(desc);
        }
    }

    @Override
    protected Set<OWLEquivalentClassesAxiom> getClassAxioms(OWLClassExpression owlClassExpression, OWLOntology owlOntology) {
        if (!owlClassExpression.isAnonymous()) {
            return owlOntology.getEquivalentClassesAxioms(owlClassExpression.asOWLClass());
        } else {
            Set<OWLEquivalentClassesAxiom> axioms = new HashSet<>();
            for (OWLAxiom ax : owlOntology.getGeneralClassAxioms()) {
                if (ax instanceof OWLEquivalentClassesAxiom &&
                        ((OWLEquivalentClassesAxiom) ax).getClassExpressions().contains(owlClassExpression)) {
                    axioms.add((OWLEquivalentClassesAxiom) ax);
                }
            }
            return axioms;
        }
    }

    @Override
    protected OWLEquivalentClassesAxiom createAxiom(OWLClassExpression owlClassExpression) {
        return getOWLDataFactory().getOWLEquivalentClassesAxiom(CollectionFactory.createSet(getRootObject(), owlClassExpression));
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
    protected boolean isResettingChange(OWLOntologyChange change) {
        return change.isAxiomChange() &&
                change.getAxiom() instanceof OWLEquivalentClassesAxiom &&
                ((OWLEquivalentClassesAxiom) change.getAxiom()).getClassExpressions().contains(getRootObject());
    }

    @Override
    public Comparator<OWLFrameSectionRow<OWLClassExpression, OWLEquivalentClassesAxiom, OWLClassExpression>> getRowComparator() {
        return null;
    }
}
