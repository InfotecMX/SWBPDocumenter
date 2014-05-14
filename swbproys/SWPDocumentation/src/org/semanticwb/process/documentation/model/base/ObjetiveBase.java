package org.semanticwb.process.documentation.model.base;


public abstract class ObjetiveBase extends org.semanticwb.process.documentation.model.SectionElement implements org.semanticwb.process.documentation.model.Instantiable,org.semanticwb.model.Descriptiveable,org.semanticwb.model.Sortable,org.semanticwb.model.Traceable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_Objetive=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Objetive");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Objetive");

    public static class ClassMgr
    {
       /**
       * Returns a list of Objetive for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetives(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.Objetive for all models
       * @return Iterator of org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetives()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive>(it, true);
        }

        public static org.semanticwb.process.documentation.model.Objetive createObjetive(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.Objetive.ClassMgr.createObjetive(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.Objetive
       * @param id Identifier for org.semanticwb.process.documentation.model.Objetive
       * @param model Model of the org.semanticwb.process.documentation.model.Objetive
       * @return A org.semanticwb.process.documentation.model.Objetive
       */
        public static org.semanticwb.process.documentation.model.Objetive getObjetive(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Objetive)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.Objetive
       * @param id Identifier for org.semanticwb.process.documentation.model.Objetive
       * @param model Model of the org.semanticwb.process.documentation.model.Objetive
       * @return A org.semanticwb.process.documentation.model.Objetive
       */
        public static org.semanticwb.process.documentation.model.Objetive createObjetive(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Objetive)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.Objetive
       * @param id Identifier for org.semanticwb.process.documentation.model.Objetive
       * @param model Model of the org.semanticwb.process.documentation.model.Objetive
       */
        public static void removeObjetive(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.Objetive
       * @param id Identifier for org.semanticwb.process.documentation.model.Objetive
       * @param model Model of the org.semanticwb.process.documentation.model.Objetive
       * @return true if the org.semanticwb.process.documentation.model.Objetive exists, false otherwise
       */

        public static boolean hasObjetive(String id, org.semanticwb.model.SWBModel model)
        {
            return (getObjetive(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Objetive with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Objetive
       * @return Iterator with all the org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetiveByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Objetive with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetiveByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Objetive with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.Objetive
       * @return Iterator with all the org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetiveByParentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Objetive with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetiveByParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Objetive with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @param model Model of the org.semanticwb.process.documentation.model.Objetive
       * @return Iterator with all the org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetiveByProcessElementScope(org.semanticwb.process.model.ProcessElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Objetive with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetiveByProcessElementScope(org.semanticwb.process.model.ProcessElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Objetive with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Objetive
       * @return Iterator with all the org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetiveByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Objetive with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Objetive
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Objetive> listObjetiveByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Objetive> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static ObjetiveBase.ClassMgr getObjetiveClassMgr()
    {
        return new ObjetiveBase.ClassMgr();
    }

   /**
   * Constructs a ObjetiveBase with a SemanticObject
   * @param base The SemanticObject with the properties for the Objetive
   */
    public ObjetiveBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
    }
}
