import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.frame.AbstractOWLFrame;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * Created by Casey on 17-Mar-17.
 */
public class OWLAfrikaansClassDescriptionFrame extends AbstractOWLFrame<OWLClass>{

    public OWLAfrikaansClassDescriptionFrame(OWLEditorKit editorKit) {
        super(editorKit.getModelManager().getOWLOntologyManager());
        addSection(new OWLAfikaansEquivalentClassesAxiomFrameSection(editorKit, this));
        addSection(new OWLAfrikaansSubClassesAxiomFrameSection(editorKit, this));
        addSection(new OWLAfrikaansGeneralClassAxiomFrameSection(editorKit, this));
        addSection(new OWLAfrikaansInheritedAnonymousClassesFrameSection(editorKit, this));
        addSection(new OWLAfrikaansDisjointClassesAxiomFrameSection(editorKit, this));
        addSection(new OWLAfrikaansDisjointUnionAxiomFrameSection(editorKit, this));
    }
}
