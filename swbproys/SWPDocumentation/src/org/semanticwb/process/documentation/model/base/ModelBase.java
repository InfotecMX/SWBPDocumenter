package org.semanticwb.process.documentation.model.base;


public abstract class ModelBase extends org.semanticwb.process.documentation.model.SectionElement implements org.semanticwb.model.Descriptiveable,org.semanticwb.model.Traceable,org.semanticwb.model.Sortable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_Model=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Model");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Model");

    public static class ClassMgr
    {
       /**
       * Returns a list of Model for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModels(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.Model for all models
       * @return Iterator of org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModels()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model>(it, true);
        }

        public static org.semanticwb.process.documentation.model.Model createModel(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.Model.ClassMgr.createModel(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.Model
       * @param id Identifier for org.semanticwb.process.documentation.model.Model
       * @param model Model of the org.semanticwb.process.documentation.model.Model
       * @return A org.semanticwb.process.documentation.model.Model
       */
        public static org.semanticwb.process.documentation.model.Model getModel(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Model)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.Model
       * @param id Identifier for org.semanticwb.process.documentation.model.Model
       * @param model Model of the org.semanticwb.process.documentation.model.Model
       * @return A org.semanticwb.process.documentation.model.Model
       */
        public static org.semanticwb.process.documentation.model.Model createModel(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Model)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.Model
       * @param id Identifier for org.semanticwb.process.documentation.model.Model
       * @param model Model of the org.semanticwb.process.documentation.model.Model
       */
        public static void removeModel(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.Model
       * @param id Identifier for org.semanticwb.process.documentation.model.Model
       * @param model Model of the org.semanticwb.process.documentation.model.Model
       * @return true if the org.semanticwb.process.documentation.model.Model exists, false otherwise
       */

        public static boolean hasModel(String id, org.semanticwb.model.SWBModel model)
        {
            return (getModel(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Model with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Model
       * @return Iterator with all the org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModelByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Model with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModelByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Model with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.Model
       * @return Iterator with all the org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModelByParentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Model with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModelByParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Model with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @param model Model of the org.semanticwb.process.documentation.model.Model
       * @return Iterator with all the org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModelByProcessElementScope(org.semanticwb.process.model.ProcessElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Model with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModelByProcessElementScope(org.semanticwb.process.model.ProcessElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Model with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Model
       * @return Iterator with all the org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModelByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Model with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Model
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Model> listModelByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Model> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static ModelBase.ClassMgr getModelClassMgr()
    {
        return new ModelBase.ClassMgr();
    }

   /**
   * Constructs a ModelBase with a SemanticObject
   * @param base The SemanticObject with the properties for the Model
   */
    public ModelBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
    }
}
