package org.semanticwb.process.documentation.model.base;

public interface NumerableBase extends org.semanticwb.model.GenericObject
{
    public static final org.semanticwb.platform.SemanticProperty swpdoc_number=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#number");
    public static final org.semanticwb.platform.SemanticClass swpdoc_Numerable=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Numerable");

    public String getNumber();

    public void setNumber(String value);

    public String getNumber(String lang);

    public String getDisplayNumber(String lang);

    public void setNumber(String number, String lang);
}
