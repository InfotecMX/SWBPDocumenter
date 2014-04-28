package org.semanticwb.process.documentation.model.base;


public abstract class DocumentationBase extends org.semanticwb.model.SWBClass implements org.semanticwb.model.Descriptiveable,org.semanticwb.model.Traceable
{
    public static final org.semanticwb.platform.SemanticProperty swpdoc_actualVersion=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#actualVersion");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_nameVersion=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#nameVersion");
    public static final org.semanticwb.platform.SemanticClass swp_Process=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/swb4/process#Process");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_process=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#process");
    public static final org.semanticwb.platform.SemanticClass swpdoc_Documentation=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Documentation");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Documentation");

    public static class ClassMgr
    {
       /**
       * Returns a list of Documentation for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.Documentation
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Documentation> listDocumentations(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Documentation>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.Documentation for all models
       * @return Iterator of org.semanticwb.process.documentation.model.Documentation
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Documentation> listDocumentations()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Documentation>(it, true);
        }

        public static org.semanticwb.process.documentation.model.Documentation createDocumentation(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.Documentation.ClassMgr.createDocumentation(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.Documentation
       * @param id Identifier for org.semanticwb.process.documentation.model.Documentation
       * @param model Model of the org.semanticwb.process.documentation.model.Documentation
       * @return A org.semanticwb.process.documentation.model.Documentation
       */
        public static org.semanticwb.process.documentation.model.Documentation getDocumentation(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Documentation)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.Documentation
       * @param id Identifier for org.semanticwb.process.documentation.model.Documentation
       * @param model Model of the org.semanticwb.process.documentation.model.Documentation
       * @return A org.semanticwb.process.documentation.model.Documentation
       */
        public static org.semanticwb.process.documentation.model.Documentation createDocumentation(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Documentation)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.Documentation
       * @param id Identifier for org.semanticwb.process.documentation.model.Documentation
       * @param model Model of the org.semanticwb.process.documentation.model.Documentation
       */
        public static void removeDocumentation(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.Documentation
       * @param id Identifier for org.semanticwb.process.documentation.model.Documentation
       * @param model Model of the org.semanticwb.process.documentation.model.Documentation
       * @return true if the org.semanticwb.process.documentation.model.Documentation exists, false otherwise
       */

        public static boolean hasDocumentation(String id, org.semanticwb.model.SWBModel model)
        {
            return (getDocumentation(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Documentation with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Documentation
       * @return Iterator with all the org.semanticwb.process.documentation.model.Documentation
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Documentation> listDocumentationByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Documentation> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Documentation with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Documentation
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Documentation> listDocumentationByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Documentation> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Documentation with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Documentation
       * @return Iterator with all the org.semanticwb.process.documentation.model.Documentation
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Documentation> listDocumentationByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Documentation> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Documentation with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Documentation
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Documentation> listDocumentationByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Documentation> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Documentation with a determined Process
       * @param value Process of the type org.semanticwb.process.model.Process
       * @param model Model of the org.semanticwb.process.documentation.model.Documentation
       * @return Iterator with all the org.semanticwb.process.documentation.model.Documentation
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Documentation> listDocumentationByProcess(org.semanticwb.process.model.Process value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Documentation> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_process, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Documentation with a determined Process
       * @param value Process of the type org.semanticwb.process.model.Process
       * @return Iterator with all the org.semanticwb.process.documentation.model.Documentation
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Documentation> listDocumentationByProcess(org.semanticwb.process.model.Process value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Documentation> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_process,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static DocumentationBase.ClassMgr getDocumentationClassMgr()
    {
        return new DocumentationBase.ClassMgr();
    }

   /**
   * Constructs a DocumentationBase with a SemanticObject
   * @param base The SemanticObject with the properties for the Documentation
   */
    public DocumentationBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
    }
   /**
   * Sets the value for the property ModifiedBy
   * @param value ModifiedBy to set
   */

    public void setModifiedBy(org.semanticwb.model.User value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swb_modifiedBy, value.getSemanticObject());
        }else
        {
            removeModifiedBy();
        }
    }
   /**
   * Remove the value for ModifiedBy property
   */

    public void removeModifiedBy()
    {
        getSemanticObject().removeProperty(swb_modifiedBy);
    }

   /**
   * Gets the ModifiedBy
   * @return a org.semanticwb.model.User
   */
    public org.semanticwb.model.User getModifiedBy()
    {
         org.semanticwb.model.User ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swb_modifiedBy);
         if(obj!=null)
         {
             ret=(org.semanticwb.model.User)obj.createGenericInstance();
         }
         return ret;
    }

/**
* Gets the Updated property
* @return java.util.Date with the Updated
*/
    public java.util.Date getUpdated()
    {
        return getSemanticObject().getDateProperty(swb_updated);
    }

/**
* Sets the Updated property
* @param value long with the Updated
*/
    public void setUpdated(java.util.Date value)
    {
        getSemanticObject().setDateProperty(swb_updated, value);
    }

/**
* Gets the Created property
* @return java.util.Date with the Created
*/
    public java.util.Date getCreated()
    {
        return getSemanticObject().getDateProperty(swb_created);
    }

/**
* Sets the Created property
* @param value long with the Created
*/
    public void setCreated(java.util.Date value)
    {
        getSemanticObject().setDateProperty(swb_created, value);
    }

/**
* Gets the ActualVersion property
* @return boolean with the ActualVersion
*/
    public boolean isActualVersion()
    {
        return getSemanticObject().getBooleanProperty(swpdoc_actualVersion);
    }

/**
* Sets the ActualVersion property
* @param value long with the ActualVersion
*/
    public void setActualVersion(boolean value)
    {
        getSemanticObject().setBooleanProperty(swpdoc_actualVersion, value);
    }

/**
* Gets the Description property
* @return String with the Description
*/
    public String getDescription()
    {
        return getSemanticObject().getProperty(swb_description);
    }

/**
* Sets the Description property
* @param value long with the Description
*/
    public void setDescription(String value)
    {
        getSemanticObject().setProperty(swb_description, value);
    }

    public String getDescription(String lang)
    {
        return getSemanticObject().getProperty(swb_description, null, lang);
    }

    public String getDisplayDescription(String lang)
    {
        return getSemanticObject().getLocaleProperty(swb_description, lang);
    }

    public void setDescription(String description, String lang)
    {
        getSemanticObject().setProperty(swb_description, description, lang);
    }
   /**
   * Sets the value for the property Creator
   * @param value Creator to set
   */

    public void setCreator(org.semanticwb.model.User value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swb_creator, value.getSemanticObject());
        }else
        {
            removeCreator();
        }
    }
   /**
   * Remove the value for Creator property
   */

    public void removeCreator()
    {
        getSemanticObject().removeProperty(swb_creator);
    }

   /**
   * Gets the Creator
   * @return a org.semanticwb.model.User
   */
    public org.semanticwb.model.User getCreator()
    {
         org.semanticwb.model.User ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swb_creator);
         if(obj!=null)
         {
             ret=(org.semanticwb.model.User)obj.createGenericInstance();
         }
         return ret;
    }

/**
* Gets the Title property
* @return String with the Title
*/
    public String getTitle()
    {
        return getSemanticObject().getProperty(swb_title);
    }

/**
* Sets the Title property
* @param value long with the Title
*/
    public void setTitle(String value)
    {
        getSemanticObject().setProperty(swb_title, value);
    }

    public String getTitle(String lang)
    {
        return getSemanticObject().getProperty(swb_title, null, lang);
    }

    public String getDisplayTitle(String lang)
    {
        return getSemanticObject().getLocaleProperty(swb_title, lang);
    }

    public void setTitle(String title, String lang)
    {
        getSemanticObject().setProperty(swb_title, title, lang);
    }

/**
* Gets the NameVersion property
* @return String with the NameVersion
*/
    public String getNameVersion()
    {
        return getSemanticObject().getProperty(swpdoc_nameVersion);
    }

/**
* Sets the NameVersion property
* @param value long with the NameVersion
*/
    public void setNameVersion(String value)
    {
        getSemanticObject().setProperty(swpdoc_nameVersion, value);
    }
   /**
   * Sets the value for the property Process
   * @param value Process to set
   */

    public void setProcess(org.semanticwb.process.model.Process value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_process, value.getSemanticObject());
        }else
        {
            removeProcess();
        }
    }
   /**
   * Remove the value for Process property
   */

    public void removeProcess()
    {
        getSemanticObject().removeProperty(swpdoc_process);
    }

   /**
   * Gets the Process
   * @return a org.semanticwb.process.model.Process
   */
    public org.semanticwb.process.model.Process getProcess()
    {
         org.semanticwb.process.model.Process ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_process);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.model.Process)obj.createGenericInstance();
         }
         return ret;
    }
}
