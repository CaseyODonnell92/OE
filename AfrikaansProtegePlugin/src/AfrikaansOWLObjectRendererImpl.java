import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.renderer.OWLObjectRenderer;
import org.protege.editor.owl.ui.renderer.OWLObjectRendererImpl;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.ShortFormProvider;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Casey on 20-Mar-17.
 */
public class AfrikaansOWLObjectRendererImpl implements OWLObjectRenderer {

    private final OWLModelManager manager;
    private WriterDelegate writerDelegate;
    private AfrikaansManchesterOWLSyntaxObjectRenderer renderer;

    public AfrikaansOWLObjectRendererImpl(OWLModelManager manager) {
        this.manager = manager;
        writerDelegate = new WriterDelegate();

        //create an anonymous ShortFormProvider for use by the renderer
        ShortFormProvider provider = new ShortFormProvider(){
            public String getShortForm(OWLEntity owlEntity) {
                return AfrikaansOWLObjectRendererImpl.this.manager.getRendering(owlEntity);
            }

            public void dispose() {
                // do nothing
            }
        };

        renderer = new AfrikaansManchesterOWLSyntaxObjectRenderer(writerDelegate, provider);
    }

    @Override
    public String render(OWLObject owlObject) {
        if (owlObject instanceof OWLOntology)
            return null;
        writerDelegate.reset();
        owlObject.accept(renderer);
        return writerDelegate.toString();
    }

    private class WriterDelegate extends Writer {

        private StringWriter delegate;

        private void reset() {
            delegate = new StringWriter();
        }


        public String toString() {
            return delegate.getBuffer().toString();
        }


        public void close() throws IOException {
            delegate.close();
        }


        public void flush() throws IOException {
            delegate.flush();
        }


        public void write(char cbuf[], int off, int len) throws IOException {
            delegate.write(cbuf, off, len);
        }
    }
}
