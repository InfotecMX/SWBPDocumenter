package org.semanticwb.process.documentation.model.base;


public abstract class SectionElementRefBase extends org.semanticwb.process.documentation.model.ReferenceObject implements org.semanticwb.model.Traceable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_SectionElement=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#SectionElement");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_sectionElement=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#sectionElement");
    public static final org.semanticwb.platform.SemanticClass swpdoc_Activity=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Activity");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_activity=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#activity");
    public static final org.semanticwb.platform.SemanticClass swpdoc_SectionElementRef=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#SectionElementRef");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#SectionElementRef");

    public static class ClassMgr
    {
       /**
       * Returns a list of SectionElementRef for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefs(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.SectionElementRef for all models
       * @return Iterator of org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefs()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef>(it, true);
        }

        public static org.semanticwb.process.documentation.model.SectionElementRef createSectionElementRef(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.SectionElementRef.ClassMgr.createSectionElementRef(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.SectionElementRef
       * @param id Identifier for org.semanticwb.process.documentation.model.SectionElementRef
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElementRef
       * @return A org.semanticwb.process.documentation.model.SectionElementRef
       */
        public static org.semanticwb.process.documentation.model.SectionElementRef getSectionElementRef(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.SectionElementRef)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.SectionElementRef
       * @param id Identifier for org.semanticwb.process.documentation.model.SectionElementRef
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElementRef
       * @return A org.semanticwb.process.documentation.model.SectionElementRef
       */
        public static org.semanticwb.process.documentation.model.SectionElementRef createSectionElementRef(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.SectionElementRef)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.SectionElementRef
       * @param id Identifier for org.semanticwb.process.documentation.model.SectionElementRef
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElementRef
       */
        public static void removeSectionElementRef(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.SectionElementRef
       * @param id Identifier for org.semanticwb.process.documentation.model.SectionElementRef
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElementRef
       * @return true if the org.semanticwb.process.documentation.model.SectionElementRef exists, false otherwise
       */

        public static boolean hasSectionElementRef(String id, org.semanticwb.model.SWBModel model)
        {
            return (getSectionElementRef(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElementRef with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElementRef
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElementRef with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElementRef with a determined SectionElement
       * @param value SectionElement of the type org.semanticwb.process.documentation.model.SectionElement
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElementRef
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefBySectionElement(org.semanticwb.process.documentation.model.SectionElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_sectionElement, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElementRef with a determined SectionElement
       * @param value SectionElement of the type org.semanticwb.process.documentation.model.SectionElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefBySectionElement(org.semanticwb.process.documentation.model.SectionElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_sectionElement,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElementRef with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElementRef
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElementRef with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElementRef with a determined Activity
       * @param value Activity of the type org.semanticwb.process.documentation.model.Activity
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElementRef
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefByActivity(org.semanticwb.process.documentation.model.Activity value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_activity, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElementRef with a determined Activity
       * @param value Activity of the type org.semanticwb.process.documentation.model.Activity
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElementRef
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefByActivity(org.semanticwb.process.documentation.model.Activity value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_activity,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static SectionElementRefBase.ClassMgr getSectionElementRefClassMgr()
    {
        return new SectionElementRefBase.ClassMgr();
    }

   /**
   * Constructs a SectionElementRefBase with a SemanticObject
   * @param base The SemanticObject with the properties for the SectionElementRef
   */
    public SectionElementRefBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
    }
   /**
   * Sets the value for the property SectionElement
   * @param value SectionElement to set
   */

    public void setSectionElement(org.semanticwb.process.documentation.model.SectionElement value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_sectionElement, value.getSemanticObject());
        }else
        {
            removeSectionElement();
        }
    }
   /**
   * Remove the value for SectionElement property
   */

    public void removeSectionElement()
    {
        getSemanticObject().removeProperty(swpdoc_sectionElement);
    }

   /**
   * Gets the SectionElement
   * @return a org.semanticwb.process.documentation.model.SectionElement
   */
    public org.semanticwb.process.documentation.model.SectionElement getSectionElement()
    {
         org.semanticwb.process.documentation.model.SectionElement ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_sectionElement);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.SectionElement)obj.createGenericInstance();
         }
         return ret;
    }
   /**
   * Sets the value for the property Activity
   * @param value Activity to set
   */

    public void setActivity(org.semanticwb.process.documentation.model.Activity value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_activity, value.getSemanticObject());
        }else
        {
            removeActivity();
        }
    }
   /**
   * Remove the value for Activity property
   */

    public void removeActivity()
    {
        getSemanticObject().removeProperty(swpdoc_activity);
    }

   /**
   * Gets the Activity
   * @return a org.semanticwb.process.documentation.model.Activity
   */
    public org.semanticwb.process.documentation.model.Activity getActivity()
    {
         org.semanticwb.process.documentation.model.Activity ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_activity);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.Activity)obj.createGenericInstance();
         }
         return ret;
    }
}
