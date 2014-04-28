package org.semanticwb.process.documentation.model.base;


public abstract class ActivityBase extends org.semanticwb.process.documentation.model.SectionElement implements org.semanticwb.model.Descriptiveable,org.semanticwb.model.Sortable,org.semanticwb.model.Traceable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_ActivityRef=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#ActivityRef");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_activityRef=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#activityRef");
    public static final org.semanticwb.platform.SemanticClass swpdoc_SectionElementRef=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#SectionElementRef");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_hasSectionElementRef=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#hasSectionElementRef");
    public static final org.semanticwb.platform.SemanticClass swpdoc_Activity=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Activity");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Activity");

    public static class ClassMgr
    {
       /**
       * Returns a list of Activity for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivities(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.Activity for all models
       * @return Iterator of org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivities()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity>(it, true);
        }

        public static org.semanticwb.process.documentation.model.Activity createActivity(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.Activity.ClassMgr.createActivity(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.Activity
       * @param id Identifier for org.semanticwb.process.documentation.model.Activity
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return A org.semanticwb.process.documentation.model.Activity
       */
        public static org.semanticwb.process.documentation.model.Activity getActivity(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Activity)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.Activity
       * @param id Identifier for org.semanticwb.process.documentation.model.Activity
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return A org.semanticwb.process.documentation.model.Activity
       */
        public static org.semanticwb.process.documentation.model.Activity createActivity(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Activity)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.Activity
       * @param id Identifier for org.semanticwb.process.documentation.model.Activity
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       */
        public static void removeActivity(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.Activity
       * @param id Identifier for org.semanticwb.process.documentation.model.Activity
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return true if the org.semanticwb.process.documentation.model.Activity exists, false otherwise
       */

        public static boolean hasActivity(String id, org.semanticwb.model.SWBModel model)
        {
            return (getActivity(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined ActivityRef
       * @param value ActivityRef of the type org.semanticwb.process.documentation.model.ActivityRef
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByActivityRef(org.semanticwb.process.documentation.model.ActivityRef value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_activityRef, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined ActivityRef
       * @param value ActivityRef of the type org.semanticwb.process.documentation.model.ActivityRef
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByActivityRef(org.semanticwb.process.documentation.model.ActivityRef value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_activityRef,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByParentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined SectionElementRef
       * @param value SectionElementRef of the type org.semanticwb.process.documentation.model.SectionElementRef
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityBySectionElementRef(org.semanticwb.process.documentation.model.SectionElementRef value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasSectionElementRef, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined SectionElementRef
       * @param value SectionElementRef of the type org.semanticwb.process.documentation.model.SectionElementRef
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityBySectionElementRef(org.semanticwb.process.documentation.model.SectionElementRef value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasSectionElementRef,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByProcessElementScope(org.semanticwb.process.model.ProcessElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByProcessElementScope(org.semanticwb.process.model.ProcessElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Activity
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Activity with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Activity
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Activity> listActivityByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Activity> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static ActivityBase.ClassMgr getActivityClassMgr()
    {
        return new ActivityBase.ClassMgr();
    }

   /**
   * Constructs a ActivityBase with a SemanticObject
   * @param base The SemanticObject with the properties for the Activity
   */
    public ActivityBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
    }
   /**
   * Sets the value for the property ActivityRef
   * @param value ActivityRef to set
   */

    public void setActivityRef(org.semanticwb.process.documentation.model.ActivityRef value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_activityRef, value.getSemanticObject());
        }else
        {
            removeActivityRef();
        }
    }
   /**
   * Remove the value for ActivityRef property
   */

    public void removeActivityRef()
    {
        getSemanticObject().removeProperty(swpdoc_activityRef);
    }

   /**
   * Gets the ActivityRef
   * @return a org.semanticwb.process.documentation.model.ActivityRef
   */
    public org.semanticwb.process.documentation.model.ActivityRef getActivityRef()
    {
         org.semanticwb.process.documentation.model.ActivityRef ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_activityRef);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.ActivityRef)obj.createGenericInstance();
         }
         return ret;
    }
   /**
   * Gets all the org.semanticwb.process.documentation.model.SectionElementRef
   * @return A GenericIterator with all the org.semanticwb.process.documentation.model.SectionElementRef
   */

    public org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef> listSectionElementRefs()
    {
        return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElementRef>(getSemanticObject().listObjectProperties(swpdoc_hasSectionElementRef));
    }

   /**
   * Gets true if has a SectionElementRef
   * @param value org.semanticwb.process.documentation.model.SectionElementRef to verify
   * @return true if the org.semanticwb.process.documentation.model.SectionElementRef exists, false otherwise
   */
    public boolean hasSectionElementRef(org.semanticwb.process.documentation.model.SectionElementRef value)
    {
        boolean ret=false;
        if(value!=null)
        {
           ret=getSemanticObject().hasObjectProperty(swpdoc_hasSectionElementRef,value.getSemanticObject());
        }
        return ret;
    }
   /**
   * Adds a SectionElementRef
   * @param value org.semanticwb.process.documentation.model.SectionElementRef to add
   */

    public void addSectionElementRef(org.semanticwb.process.documentation.model.SectionElementRef value)
    {
        getSemanticObject().addObjectProperty(swpdoc_hasSectionElementRef, value.getSemanticObject());
    }
   /**
   * Removes all the SectionElementRef
   */

    public void removeAllSectionElementRef()
    {
        getSemanticObject().removeProperty(swpdoc_hasSectionElementRef);
    }
   /**
   * Removes a SectionElementRef
   * @param value org.semanticwb.process.documentation.model.SectionElementRef to remove
   */

    public void removeSectionElementRef(org.semanticwb.process.documentation.model.SectionElementRef value)
    {
        getSemanticObject().removeObjectProperty(swpdoc_hasSectionElementRef,value.getSemanticObject());
    }

   /**
   * Gets the SectionElementRef
   * @return a org.semanticwb.process.documentation.model.SectionElementRef
   */
    public org.semanticwb.process.documentation.model.SectionElementRef getSectionElementRef()
    {
         org.semanticwb.process.documentation.model.SectionElementRef ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_hasSectionElementRef);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.SectionElementRef)obj.createGenericInstance();
         }
         return ret;
    }
}
