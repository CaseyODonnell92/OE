import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.AbstractOWLFrameSectionRow;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.protege.editor.owl.ui.renderer.OWLObjectRendererDLSyntax;
import org.protege.editor.owl.ui.renderer.OWLObjectRendererImpl;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.Iterator;

/**
 * Created by Casey on 19-Mar-17.
 */
public abstract class AbstractOWLAfrikaansFrameSectionRow<R extends Object, A extends OWLAxiom, E> extends AbstractOWLFrameSectionRow<R, A, E> {

    protected AbstractOWLAfrikaansFrameSectionRow(OWLEditorKit owlEditorKit, OWLFrameSection<R,A,E> section, OWLOntology ontology,
                                                  R rootObject, A axiom) {
        super(owlEditorKit, section, ontology, rootObject, axiom);
    }

    public boolean isEditable() {
        return false;
    }

    public boolean isDeleteable() {
        return true;
    }

    @Override
    protected OWLObjectEditor getObjectEditor() {
        return null;
    }

    protected Object getObjectRendering(OWLObject ob) {
        AfrikaansOWLObjectRendererImpl renderer = new AfrikaansOWLObjectRendererImpl(getOWLModelManager());
        return renderer.render(ob);
    }


    /**
     * Overridden to return the Afrikaans Manchester Rendering
     */
    public String getRendering() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPrefix());
        for (Iterator<? extends OWLObject> it = getManipulatableObjects().iterator(); it.hasNext(); ) {
            OWLObject obj = it.next();
            sb.append(getObjectRendering(obj));
            if (it.hasNext()) {
                sb.append(getDelimeter());
            }
        }
        sb.append(getSuffix());
        return sb.toString();
    }
}
