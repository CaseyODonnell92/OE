import javax.annotation.Nonnull;

/**
 * Created by Casey on 20-Mar-17.
 */
public enum AfrikaansManchesterOWLSyntax {

    /*AND           */      EN("en", false, false, false, false, true, "en"),
    /*OR            */      OF("of", false, false, false, false, true, "of"),
    /*SOME          */      SOMMIGE("sommige", false, false, false, true, false, "sommige"),
    /*ONLY          */      SLEGS("slegs", false, false, false, true, false, "slegs"),
    /*NOT           */      NIE("nie", false, false, false, false, true, "nie"),
    /*VALUE         */      WAARDE("waarde", false, false, false, true, false, "value"),
    /*MIN           */      TEN_MINSTE("ten minste", false, false, false, true, false, "ten minste"),
    /*MAX           */      BY_DIE_MEESTE("by die meeste", false, false, false, true,  false, "by die meeste"),
    /*EXACTLY       */      PRESIES("presies", false, false, false, true,  false, "presies"),
    /*DISJOINT WITH */      DISJUNKTE_VAN("DisjunkteVan", false, true,  true,  false, false),
    /*SUBCLASS_OF   */      SUBKLAS_VAN("SubKlasVan", false, true,  true,  false, false),
    /*EQUIVALENT_TO */      DIESELFDE_AS("DieselfdeAs", false, true,  true,  false, false),
    /*EQUIVALENT_CLASSES*/  DIESELFDE_KLASSE("DieselfdeKlasse", false, true,  true,  false, false),
    /*DISJOINT_CLASSES*/    DISJUNKTE_KLASSE("DisjointClasses", true,  true,  true,  false, false);


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

    AfrikaansManchesterOWLSyntax(@Nonnull String rendering, boolean frameKeyword,
                        boolean sectionKeyword, boolean axiomKeyword,
                        boolean classExpressionQuantifierKeyword,
                        boolean classExpressionConnectiveKeyword) {
        this(rendering, frameKeyword, sectionKeyword, axiomKeyword,
                classExpressionQuantifierKeyword,
                classExpressionConnectiveKeyword, rendering + ':');
    }

    public String toString() {return rendering;}
}
