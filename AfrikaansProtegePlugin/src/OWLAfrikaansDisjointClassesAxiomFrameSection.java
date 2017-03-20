import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.OWLFrame;
import org.protege.editor.owl.ui.frame.OWLFrameSectionRow;
import org.protege.editor.owl.ui.frame.cls.AbstractOWLClassAxiomFrameSection;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.CollectionFactory;

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