import org.protege.editor.owl.OWLEditorKit;
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

    protected Object getObjectRendering(OWLObject ob) {
        OWLObjectRendererImpl renderer = new OWLObjectRendererImpl(getOWLModelManager());   //Hey Adam... Psst! I've got this set to manchester syntax cause if Larry
                                                                                            //and Michael see our progress, I don't know if we should let them know there's
                                                                                            //already a renderer for their whole project lol
        return renderer.render(ob);
    }


    /**
     * Gets the rendering of the value of a particular column.
     *
     * @return The <code>String</code> representation of the column
     * value.
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
