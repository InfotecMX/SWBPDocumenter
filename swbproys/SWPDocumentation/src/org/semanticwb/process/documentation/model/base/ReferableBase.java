package org.semanticwb.process.documentation.model.base;

public interface ReferableBase extends org.semanticwb.model.GenericObject
{
    public static final org.semanticwb.platform.SemanticProperty swpdoc_file=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#file");
    public static final org.semanticwb.platform.SemanticClass swp_RepositoryElement=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/swb4/process#RepositoryElement");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_refRepository=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#refRepository");
    public static final org.semanticwb.platform.SemanticClass swpdoc_Referable=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Referable");

    public String getFile();

    public void setFile(String value);

   /**
   * Sets a value from the property RefRepository
   * @param value An instance of org.semanticwb.process.model.RepositoryElement
   */
    public void setRefRepository(org.semanticwb.process.model.RepositoryElement value);

   /**
   * Remove the value from the property RefRepository
   */
    public void removeRefRepository();

    public org.semanticwb.process.model.RepositoryElement getRefRepository();
}
