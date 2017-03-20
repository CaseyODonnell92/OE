import javax.annotation.Nonnull;

/**
 * Created by Casey on 20-Mar-17.
 */
public enum AfrikaansManchesterOWLSyntax {

    /*AND   */      EN ("en", false, false, false, false, true, "en"),
    /*SOME  */      TEN_MINSTE_EEN("ten minste een", false, false, false, true, false, "ten misnte een"),
    /*ONLY  */      SLEGS("slegs", false, false, false, true, false, "slegs");

    private final boolean frameKeyword;
    private final boolean sectionKeyword;
    private final boolean axiomKeyword;
    private final boolean classExpressionQuantiferKeyword;
    private final boolean classExpressionConnectiveKeyword;
    @Nonnull
    private final String rendering;
    @Nonnull
    private final String keyword;

    AfrikaansManchesterOWLSyntax(@Nonnull String rendering, boolean frameKeyword,
                                 boolean sectionKeyword, boolean axiomKeyword,
                                 boolean classExpressionQuantifierKeyword,
                                 boolean classExpressionConnectiveKeyword, @Nonnull String keyword) {
        this.rendering = rendering;
        this.frameKeyword = frameKeyword;
        this.sectionKeyword = sectionKeyword;
        this.axiomKeyword = axiomKeyword;
        this.classExpressionConnectiveKeyword = classExpressionConnectiveKeyword;
        classExpressionQuantiferKeyword = classExpressionQuantifierKeyword;
        this.keyword = keyword;
    }

    /*
    AfrikaansManchesterOWLSyntax(@Nonnull String rendering, boolean frameKeyword,
                        boolean sectionKeyword, boolean axiomKeyword,
                        boolean classExpressionQuantifierKeyword,
                        boolean classExpressionConnectiveKeyword) {
        this(rendering, frameKeyword, sectionKeyword, axiomKeyword,
                classExpressionQuantifierKeyword,
                classExpressionConnectiveKeyword, rendering + ':');
    }
    */

    public String toString() {return rendering;}
}
