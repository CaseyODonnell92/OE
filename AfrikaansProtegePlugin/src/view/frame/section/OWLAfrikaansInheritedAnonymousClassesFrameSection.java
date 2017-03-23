package view.frame.section;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.AbstractOWLFrameSection;
import org.protege.editor.owl.ui.frame.OWLFrame;
import org.protege.editor.owl.ui.frame.OWLFrameSectionRow;
import org.protege.editor.owl.ui.frame.cls.InheritedAnonymousClassesFrameSectionRow;
import org.semanticweb.owlapi.model.*;
import view.frame.section.row.OWLAfrikaansInheritedAnonymousClassesFrameSectionRow;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Casey on 23-Mar-17.
 */
public class OWLAfrikaansInheritedAnonymousClassesFrameSection extends AbstractOWLFrameSection<OWLClass, OWLClassAxiom, OWLClassExpression> {

    private Set<OWLClass> processedClasses = new HashSet<>();

    public OWLAfrikaansInheritedAnonymousClassesFrameSection(OWLEditorKit editorKit, OWLFrame<? extends OWLClass> frame) {
        super(editorKit, "Subklas van (Naamlose Voorvader)", "Naamloos Voorvader Klas", frame);
    }

    public boolean canAdd() {return false;}

    @Override
    protected OWLClassAxiom createAxiom(OWLClassExpression owlClassExpression) {
        return null;
    }

    @Override
    public OWLObjectEditor<OWLClassExpression> getObjectEditor() {
        return null;
    }

    @Override
    protected void refill(OWLOntology owlOntology) {
        Set<OWLClass> clses = getOWLModelManager().getOWLHierarchyManager().getOWLClassHierarchyProvider().getAncestors(getRootObject());
        clses.remove(getRootObject());
        for (OWLClass cls : clses) {
            for (OWLSubClassOfAxiom ax : owlOntology.getSubClassAxiomsForSubClass(cls)) {
                if (ax.getSuperClass().isAnonymous()) {
                    addRow(new OWLAfrikaansInheritedAnonymousClassesFrameSectionRow(getOWLEditorKit(), this, owlOntology, cls, ax));
                }
            }
            for (OWLEquivalentClassesAxiom ax : owlOntology.getEquivalentClassesAxioms(cls)) {
                addRow(new OWLAfrikaansInheritedAnonymousClassesFrameSectionRow(getOWLEditorKit(), this, owlOntology, cls, ax));
            }
            processedClasses.add(cls);
        }
    }

    @Override
    protected void clear() {

    }

    @Override
    protected boolean isResettingChange(OWLOntologyChange change) {
        return change.isAxiomChange() && (change.getAxiom() instanceof OWLSubClassOfAxiom || change.getAxiom() instanceof OWLEquivalentClassesAxiom);
    }

    @Override
    public Comparator<OWLFrameSectionRow<OWLClass, OWLClassAxiom, OWLClassExpression>> getRowComparator() {
        return null;
    }
}
