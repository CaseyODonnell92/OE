package view.frame.section;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLWorkspace;
import org.protege.editor.owl.ui.editor.OWLGeneralAxiomEditor;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.AbstractOWLFrameSection;
import org.protege.editor.owl.ui.frame.OWLFrame;
import org.protege.editor.owl.ui.frame.OWLFrameSectionRow;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.OWLAxiomVisitorExAdapter;

import javax.swing.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Adam on 2017/03/22.
 */
public class OWLAfrikaansGeneralClassAxiomFrameSection extends AbstractOWLFrameSection<
        OWLClass,
        OWLClassAxiom,
        OWLClassAxiom> {

    public OWLAfrikaansGeneralClassAxiomFrameSection(OWLEditorKit editorKit, OWLFrame<? extends OWLClass> frame) {
        super(editorKit, "Algemene Klasaksiomas", "Algemene Klasaksiomas", frame);
    }

    @Override
    protected OWLClassAxiom createAxiom(OWLClassAxiom object) {
        return object;
    }

    @Override
    public OWLObjectEditor<OWLClassAxiom> getObjectEditor() {
        return null;
    }

    public boolean canAdd() {
        return false;
    }

    @Override
    protected void refill(OWLOntology ontology) {
        OWLWorkspace workspace = getOWLEditorKit().getOWLWorkspace();
        OWLClass cls = workspace.getOWLSelectionModel().getLastSelectedClass();
        if(cls == null) {
            return;
        }
        for(OWLClassAxiom ax : ontology.getGeneralClassAxioms()) {
            if (ax.containsEntityInSignature(cls)) {
                addRow(new OWLAfrikaansGeneralClassAxiomFrameSectionRow(getOWLEditorKit(), this, ontology, getRootObject(), ax));
            }
        }
    }

    @Override
    protected void clear() {

    }

    @Override
    protected boolean isResettingChange(OWLOntologyChange change) {
        if(!change.isAxiomChange()) {
            return false;
        }
        if(!change.getSignature().contains(getRootObject())) {
            return false;
        }
        OWLAxiom axiom = change.getAxiom();
        return axiom.accept(new OWLAxiomVisitorExAdapter<Boolean>(false) {

            @Override
            public Boolean visit(OWLSubClassOfAxiom axiom) {
                return axiom.isGCI();
            }

            @Override
            public Boolean visit(OWLEquivalentClassesAxiom axiom) {
                return !axiom.contains(getRootObject());
            }

            @Override
            public Boolean visit(OWLDisjointClassesAxiom axiom) {
                return !axiom.contains(getRootObject());
            }
        });
    }

    @Override
    public Comparator<OWLFrameSectionRow<OWLClass, OWLClassAxiom, OWLClassAxiom>> getRowComparator() {
        return null;
    }

}

