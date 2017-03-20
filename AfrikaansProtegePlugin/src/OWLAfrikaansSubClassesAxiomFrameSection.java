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
public class OWLAfrikaansSubClassesAxiomFrameSection extends AbstractOWLClassAxiomFrameSection<OWLSubClassOfAxiom, OWLClassExpression> {

    private static final String LABEL = "Subklas van";

    private Set<OWLClassExpression> added = new HashSet<>();


    public OWLAfrikaansSubClassesAxiomFrameSection(OWLEditorKit editorKit, OWLFrame<OWLClass> frame) {
        super(editorKit, LABEL, "Subklas", frame);
    }

    @Override
    protected void addAxiom(OWLSubClassOfAxiom ax, OWLOntology ontology) {
        addRow(new OWLAfrikaansSubClassesAxiomFrameSectionRow(getOWLEditorKit(),
                this,
                ontology,
                getRootObject(),
                ax));
        for (OWLClassExpression desc : ax.getNestedClassExpressions()) {
            added.add(desc);
        }
    }

    @Override
    protected Set<OWLSubClassOfAxiom> getClassAxioms(OWLClassExpression owlClassExpression, OWLOntology owlOntology) {
        if (!owlClassExpression.isAnonymous()) {
            return owlOntology.getSubClassAxiomsForSubClass(owlClassExpression.asOWLClass());
        } else {
            Set<OWLSubClassOfAxiom> axioms = new HashSet<>();
            for (OWLAxiom ax : owlOntology.getGeneralClassAxioms()) {
                if (ax instanceof OWLSubClassOfAxiom && ((OWLSubClassOfAxiom)ax).getSubClass().equals(owlClassExpression)){
                    axioms.add((OWLSubClassOfAxiom)ax);
                }
            }
            return axioms;
        }
    }

    @Override
    protected OWLSubClassOfAxiom createAxiom(OWLClassExpression owlClassExpression) {
        return (OWLSubClassOfAxiom)owlClassExpression;
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
    public Comparator<OWLFrameSectionRow<OWLClassExpression, OWLSubClassOfAxiom, OWLClassExpression>> getRowComparator() {
        return null;
    }
}
