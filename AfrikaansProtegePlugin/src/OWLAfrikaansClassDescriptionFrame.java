import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.frame.AbstractOWLFrame;
import org.semanticweb.owlapi.model.OWLClass;

/**
 * Created by Casey on 17-Mar-17.
 */
public class OWLAfrikaansClassDescriptionFrame extends AbstractOWLFrame<OWLClass>{

    public OWLAfrikaansClassDescriptionFrame(OWLEditorKit editorKit) {
        super(editorKit.getModelManager().getOWLOntologyManager());
        addSection(new OWLAfikaansEquivalentClassesAxiomFrameSection(editorKit, this));
    }
}
