import org.semanticweb.owlapi.manchestersyntax.renderer.ManchesterOWLSyntaxObjectRenderer;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.ShortFormProvider;

import javax.annotation.Nonnull;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by Casey on 20-Mar-17.
 */
public class AfrikaansManchesterOWLSyntaxObjectRenderer extends ManchesterOWLSyntaxObjectRenderer{

    public AfrikaansManchesterOWLSyntaxObjectRenderer(Writer writer, ShortFormProvider provider) {
        super(writer, provider);
    }

    /****** WRITE METHODS ******/

    // The following methods are overridden here becaause either
    // 1) the method in super only accepts ManchesterOWLSyntax delimeters and we need to support delimeters from
    //    AfrikaansManchesterOWLSyntax
    // OR
    // 2) the method in super uses a delimeter in its body from ManchesterOWLSyntax that we wish to replace with one
    //    from AfrikaansManchesterOWLSyntax

    protected void write(@Nonnull Set<? extends OWLObject> objects,
                         @Nonnull AfrikaansManchesterOWLSyntax delimeter, boolean newline) {
        int tab = getIndent();
        pushTab(tab);
        for (Iterator<? extends OWLObject> it = sort(objects).iterator(); it
                .hasNext();) {
            it.next().accept(this);
            if (it.hasNext()) {
                if (newline && isUseWrapping()) {
                    writeNewLine();
                }
                write(delimeter);
            }
        }
        popTab();
    }

    protected void write(@Nonnull Set<? extends OWLClassExpression> objects, boolean newline) {
        boolean first = true;
        for (Iterator<? extends OWLObject> it = sort(objects).iterator(); it
                .hasNext();) {
            OWLObject desc = it.next();
            if (!first) {
                if (newline && isUseWrapping()) {
                    writeNewLine();
                }
                write(" ", AfrikaansManchesterOWLSyntax.EN, " ");
            }
            first = false;
            if (desc instanceof OWLAnonymousClassExpression) {
                write("(");
            }
            desc.accept(this);
            if (desc instanceof OWLAnonymousClassExpression) {
                write(")");
            }
        }
    }

    private void writeRestriction(
            @Nonnull OWLQuantifiedObjectRestriction restriction,
            @Nonnull AfrikaansManchesterOWLSyntax keyword) {
        restriction.getProperty().accept(this);
        write(keyword);
        boolean conjunctionOrDisjunction = false;
        if (restriction.getFiller() instanceof OWLAnonymousClassExpression) {
            if (restriction.getFiller() instanceof OWLObjectIntersectionOf
                    || restriction.getFiller() instanceof OWLObjectUnionOf) {
                conjunctionOrDisjunction = true;
                incrementTab(4);
                writeNewLine();
            }
            write("(");
        }
        restriction.getFiller().accept(this);
        if (restriction.getFiller() instanceof OWLAnonymousClassExpression) {
            write(")");
            if (conjunctionOrDisjunction) {
                popTab();
            }
        }
    }

    /*
    private void writeRestriction(
            @Nonnull OWLQuantifiedDataRestriction restriction,
            @Nonnull AfrikaansManchesterOWLSyntax keyword) {
        restriction.getProperty().accept(this);
        write(keyword);
        restriction.getFiller().accept(this);
    }
    */

    protected void write(String prefix, @Nonnull AfrikaansManchesterOWLSyntax keyword,
                         String suffix) {
        write(prefix);
        write(keyword.toString());
        write(suffix);
    }

    protected void write(@Nonnull AfrikaansManchesterOWLSyntax keyword) {
        write(" ", keyword, " ");
    }

    /****** END OF WRITE METHODS******/

    /****** VISIT METHODS ******/

    //The folliwing methods are overridden versions of those in super, rewritten so that they use keywords from
    //AfrikaansManchesterOWLSyntax rather than ManchesterOWLSyntax

    //AND -> EN
    @Override
    public void visit(@Nonnull OWLDataIntersectionOf node) {
        write("(");
        write(node.getOperands(), AfrikaansManchesterOWLSyntax.EN, false);
        write(")");
    }

    //SOME -> TEN MINSTE EEN
    @Override
    public void visit(OWLObjectSomeValuesFrom node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.TEN_MINSTE_EEN);
    }

    //ONLY -> SLEGS
    @Override
    public void visit(OWLObjectAllValuesFrom node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.SLEGS);
    }

    /****** END OF VISIT METHODS ******/
}
