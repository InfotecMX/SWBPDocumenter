package org.semanticwb.process.documentation.model.base;


public abstract class RiskBase extends org.semanticwb.process.documentation.model.SectionElement implements org.semanticwb.model.Descriptiveable,org.semanticwb.model.Sortable,org.semanticwb.model.Traceable,org.semanticwb.process.documentation.model.Instantiable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_Risk=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Risk");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Risk");

    public static class ClassMgr
    {
       /**
       * Returns a list of Risk for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRisks(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.Risk for all models
       * @return Iterator of org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRisks()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk>(it, true);
        }

        public static org.semanticwb.process.documentation.model.Risk createRisk(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.Risk.ClassMgr.createRisk(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.Risk
       * @param id Identifier for org.semanticwb.process.documentation.model.Risk
       * @param model Model of the org.semanticwb.process.documentation.model.Risk
       * @return A org.semanticwb.process.documentation.model.Risk
       */
        public static org.semanticwb.process.documentation.model.Risk getRisk(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Risk)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.Risk
       * @param id Identifier for org.semanticwb.process.documentation.model.Risk
       * @param model Model of the org.semanticwb.process.documentation.model.Risk
       * @return A org.semanticwb.process.documentation.model.Risk
       */
        public static org.semanticwb.process.documentation.model.Risk createRisk(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Risk)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.Risk
       * @param id Identifier for org.semanticwb.process.documentation.model.Risk
       * @param model Model of the org.semanticwb.process.documentation.model.Risk
       */
        public static void removeRisk(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.Risk
       * @param id Identifier for org.semanticwb.process.documentation.model.Risk
       * @param model Model of the org.semanticwb.process.documentation.model.Risk
       * @return true if the org.semanticwb.process.documentation.model.Risk exists, false otherwise
       */

        public static boolean hasRisk(String id, org.semanticwb.model.SWBModel model)
        {
            return (getRisk(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Risk with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Risk
       * @return Iterator with all the org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRiskByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Risk with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRiskByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Risk with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.Risk
       * @return Iterator with all the org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRiskByParentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Risk with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRiskByParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Risk with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @param model Model of the org.semanticwb.process.documentation.model.Risk
       * @return Iterator with all the org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRiskByProcessElementScope(org.semanticwb.process.model.ProcessElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Risk with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRiskByProcessElementScope(org.semanticwb.process.model.ProcessElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Risk with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Risk
       * @return Iterator with all the org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRiskByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Risk with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Risk
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Risk> listRiskByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Risk> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static RiskBase.ClassMgr getRiskClassMgr()
    {
        return new RiskBase.ClassMgr();
    }

   /**
   * Constructs a RiskBase with a SemanticObject
   * @param base The SemanticObject with the properties for the Risk
   */
    public RiskBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
    }
}
