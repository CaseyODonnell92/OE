package syntax;

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
    //    syntax.AfrikaansManchesterOWLSyntax
    // OR
    // 2) the method in super uses a delimeter in its body from ManchesterOWLSyntax that we wish to replace with one
    //    from syntax.AfrikaansManchesterOWLSyntax

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
            @Nonnull OWLQuantifiedDataRestriction restriction,
            @Nonnull AfrikaansManchesterOWLSyntax keyword) {
        restriction.getProperty().accept(this);
        write(keyword);
        restriction.getFiller().accept(this);
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

    private <V extends OWLObject> void writeRestriction(
            @Nonnull OWLHasValueRestriction<V> restriction,
            @Nonnull OWLPropertyExpression p) {
        p.accept(this);
        write(AfrikaansManchesterOWLSyntax.WAARDE);
        restriction.getFiller().accept(this);
    }

    private <F extends OWLPropertyRange> void writeRestriction(
            @Nonnull OWLCardinalityRestriction<F> restriction,
            @Nonnull AfrikaansManchesterOWLSyntax keyword,
            @Nonnull OWLPropertyExpression p) {
        p.accept(this);
        write(keyword);
        write(Integer.toString(restriction.getCardinality()));
        writeSpace();
        if (restriction.getFiller() instanceof OWLAnonymousClassExpression) {
            write("(");
        }
        restriction.getFiller().accept(this);
        if (restriction.getFiller() instanceof OWLAnonymousClassExpression) {
            write(")");
        }
    }

    protected void write(String prefix, @Nonnull AfrikaansManchesterOWLSyntax keyword,
                         String suffix) {
        write(prefix);
        write(keyword.toString());
        write(suffix);
    }

    protected void write(@Nonnull AfrikaansManchesterOWLSyntax keyword) {
        write(" ", keyword, " ");
    }

    protected void writeSectionKeyword(@Nonnull AfrikaansManchesterOWLSyntax keyword) {
        write(" ", keyword, ": ");
    }

    private void writeBinaryOrNaryList(
            @Nonnull AfrikaansManchesterOWLSyntax binaryKeyword,
            @Nonnull Set<? extends OWLObject> objects,
            @Nonnull AfrikaansManchesterOWLSyntax naryKeyword) {
        if (objects.size() == 2) {
            Iterator<? extends OWLObject> it = objects.iterator();
            it.next().accept(this);
            write(binaryKeyword);
            it.next().accept(this);
        } else {
            writeSectionKeyword(naryKeyword);
            writeCommaSeparatedList(objects);
        }
    }

    /****** END OF WRITE METHODS******/

    /****** VISIT METHODS ******/

    //The folliwing methods are overridden versions of those in super, rewritten so that they use keywords from
    //syntax.AfrikaansManchesterOWLSyntax rather than ManchesterOWLSyntax

    /*** Class Expression support ***/
    
    //AND -> EN
    @Override
    public void visit(@Nonnull OWLObjectIntersectionOf ce) {
        write(ce.getOperands(), true);
    }

    //OR -> OF
    @Override
    public void visit(@Nonnull OWLObjectUnionOf node) {
        boolean first = true;
        for (Iterator<? extends OWLClassExpression> it = node.getOperands()
                .iterator(); it.hasNext();) {
            OWLClassExpression op = it.next();
            if (!first) {
                write(" ", AfrikaansManchesterOWLSyntax.OF, " ");
            }
            first = false;
            if (op.isAnonymous()) {
                write("(");
            }
            op.accept(this);
            if (op.isAnonymous()) {
                write(")");
            }
        }
    }

    //NOT -> NIE
    @Override
    public void visit(@Nonnull OWLObjectComplementOf node) {
        write("", AfrikaansManchesterOWLSyntax.NIE, node.isAnonymous() ? " " : "");
        if (node.isAnonymous()) {
            write("(");
        }
        node.getOperand().accept(this);
        if (node.isAnonymous()) {
            write(")");
        }
        write(AfrikaansManchesterOWLSyntax.NIE); //To implement the Afrikaans double negative
    }

    //SOME -> SOMMIGE
    @Override
    public void visit(OWLObjectSomeValuesFrom node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.SOMMIGE);
    }

    //ONLY -> SLEGS
    @Override
    public void visit(OWLObjectAllValuesFrom node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.SLEGS);
    }

    //VALUE -> WAARDE
    @Override
    public void visit(@Nonnull OWLObjectHasValue node) {
        writeRestriction(node, node.getProperty());
    }

    //MIN -> TEN MINSTE
    @Override
    public void visit(@Nonnull OWLObjectMinCardinality node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.TEN_MINSTE, node.getProperty());
    }

    //EXACTLY -> PRESIES
    @Override
    public void visit(@Nonnull OWLObjectExactCardinality node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.PRESIES, node.getProperty());
    }

    //MAX -> BY DIE MEESTE
    @Override
    public void visit(@Nonnull OWLObjectMaxCardinality node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.BY_DIE_MEESTE, node.getProperty());
    }

    /*** Data expression support ***/

    //AND -> EN
    @Override
    public void visit(@Nonnull OWLDataIntersectionOf node) {
        write("(");
        write(node.getOperands(), AfrikaansManchesterOWLSyntax.EN, false);
        write(")");
    }

    //OR -> OF
    @Override
    public void visit(@Nonnull OWLDataUnionOf node) {
        write("(");
        write(node.getOperands(), AfrikaansManchesterOWLSyntax.OF, false);
        write(")");
    }

    //SOME -> SOMMIGE
    @Override
    public void visit(OWLDataSomeValuesFrom node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.SOMMIGE);
    }

    @Override
    public void visit(OWLDataAllValuesFrom node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.SLEGS);
    }

    @Override
    public void visit(@Nonnull OWLDataHasValue node) {
        writeRestriction(node, node.getProperty());
    }

    @Override
    public void visit(@Nonnull OWLDataMinCardinality node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.TEN_MINSTE, node.getProperty());
    }

    @Override
    public void visit(@Nonnull OWLDataExactCardinality node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.PRESIES, node.getProperty());
    }

    @Override
    public void visit(@Nonnull OWLDataMaxCardinality node) {
        writeRestriction(node, AfrikaansManchesterOWLSyntax.BY_DIE_MEESTE, node.getProperty());
    }

    /*** SUPPORT FOR GENERAL CLASS AXIOMS***/

    private boolean wrapSave;
    private boolean tabSave;

    private void setAxiomWriting() {
        wrapSave = isUseWrapping();
        tabSave = isUseTabbing();
        setUseWrapping(false);
        setUseTabbing(false);
    }

    private void restore() {
        setUseTabbing(tabSave);
        setUseWrapping(wrapSave);
    }

    @Override
    public void visit(@Nonnull OWLSubClassOfAxiom axiom) {
        setAxiomWriting();
        axiom.getSubClass().accept(this);
        write(AfrikaansManchesterOWLSyntax.SUBKLAS_VAN);
        axiom.getSuperClass().accept(this);
        restore();
    }

    @Override
    public void visit(@Nonnull OWLEquivalentClassesAxiom axiom) {
        setAxiomWriting();
        writeBinaryOrNaryList(AfrikaansManchesterOWLSyntax.DIESELFDE_AS, axiom.getClassExpressions(),
                AfrikaansManchesterOWLSyntax.DIESELFDE_KLASSE);
        restore();
    }

    @Override
    public void visit(@Nonnull OWLDisjointClassesAxiom axiom) {
        setAxiomWriting();
        writeBinaryOrNaryList(AfrikaansManchesterOWLSyntax.DISJUNKTE_VAN, axiom.getClassExpressions(),
                AfrikaansManchesterOWLSyntax.DISJUNKTE_KLASSE);
        restore();
    }


    /****** END OF VISIT METHODS ******/
}
