package org.semanticwb.process.documentation.model.base;

public interface PrefixeableBase extends org.semanticwb.model.GenericObject
{
    public static final org.semanticwb.platform.SemanticProperty swpdoc_prefix=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#prefix");
    public static final org.semanticwb.platform.SemanticClass swpdoc_Prefixeable=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Prefixeable");

    public String getPrefix();

    public void setPrefix(String value);

    public String getPrefix(String lang);

    public String getDisplayPrefix(String lang);

    public void setPrefix(String prefix, String lang);
}
