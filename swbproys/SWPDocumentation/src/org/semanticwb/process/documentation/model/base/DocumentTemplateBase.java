package org.semanticwb.process.documentation.model.base;


public abstract class DocumentTemplateBase extends org.semanticwb.model.SWBClass implements org.semanticwb.model.Traceable,org.semanticwb.model.Descriptiveable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentationInstance=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentationInstance");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_hasDocumentationInstanceInv=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#hasDocumentationInstanceInv");
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentSection=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentSection");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_hasDocumentSection=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#hasDocumentSection");
    public static final org.semanticwb.platform.SemanticClass swp_Process=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/swb4/process#Process");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_hasProcess=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#hasProcess");
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentTemplate=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentTemplate");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentTemplate");

    public static class ClassMgr
    {
       /**
       * Returns a list of DocumentTemplate for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplates(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.DocumentTemplate for all models
       * @return Iterator of org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplates()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate>(it, true);
        }

        public static org.semanticwb.process.documentation.model.DocumentTemplate createDocumentTemplate(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.DocumentTemplate.ClassMgr.createDocumentTemplate(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.DocumentTemplate
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentTemplate
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       * @return A org.semanticwb.process.documentation.model.DocumentTemplate
       */
        public static org.semanticwb.process.documentation.model.DocumentTemplate getDocumentTemplate(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.DocumentTemplate)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.DocumentTemplate
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentTemplate
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       * @return A org.semanticwb.process.documentation.model.DocumentTemplate
       */
        public static org.semanticwb.process.documentation.model.DocumentTemplate createDocumentTemplate(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.DocumentTemplate)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.DocumentTemplate
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentTemplate
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       */
        public static void removeDocumentTemplate(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.DocumentTemplate
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentTemplate
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       * @return true if the org.semanticwb.process.documentation.model.DocumentTemplate exists, false otherwise
       */

        public static boolean hasDocumentTemplate(String id, org.semanticwb.model.SWBModel model)
        {
            return (getDocumentTemplate(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined DocumentationInstance
       * @param value DocumentationInstance of the type org.semanticwb.process.documentation.model.DocumentationInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByDocumentationInstance(org.semanticwb.process.documentation.model.DocumentationInstance value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasDocumentationInstanceInv, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined DocumentationInstance
       * @param value DocumentationInstance of the type org.semanticwb.process.documentation.model.DocumentationInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByDocumentationInstance(org.semanticwb.process.documentation.model.DocumentationInstance value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasDocumentationInstanceInv,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined DocumentSection
       * @param value DocumentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByDocumentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasDocumentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined DocumentSection
       * @param value DocumentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByDocumentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasDocumentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined Process
       * @param value Process of the type org.semanticwb.process.model.Process
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentTemplate
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByProcess(org.semanticwb.process.model.Process value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcess, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentTemplate with a determined Process
       * @param value Process of the type org.semanticwb.process.model.Process
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentTemplate
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentTemplate> listDocumentTemplateByProcess(org.semanticwb.process.model.Process value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentTemplate> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcess,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static DocumentTemplateBase.ClassMgr getDocumentTemplateClassMgr()
    {
        return new DocumentTemplateBase.ClassMgr();
    }

   /**
   * Constructs a DocumentTemplateBase with a SemanticObject
   * @param base The SemanticObject with the properties for the DocumentTemplate
   */
    public DocumentTemplateBase(org.semanticwb.platform.SemanticObject base)
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
   * Gets all the org.semanticwb.process.documentation.model.DocumentationInstance
   * @return A GenericIterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
   */

    public org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstances()
    {
        return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance>(getSemanticObject().listObjectProperties(swpdoc_hasDocumentationInstanceInv));
    }

   /**
   * Gets true if has a DocumentationInstance
   * @param value org.semanticwb.process.documentation.model.DocumentationInstance to verify
   * @return true if the org.semanticwb.process.documentation.model.DocumentationInstance exists, false otherwise
   */
    public boolean hasDocumentationInstance(org.semanticwb.process.documentation.model.DocumentationInstance value)
    {
        boolean ret=false;
        if(value!=null)
        {
           ret=getSemanticObject().hasObjectProperty(swpdoc_hasDocumentationInstanceInv,value.getSemanticObject());
        }
        return ret;
    }

   /**
   * Gets the DocumentationInstance
   * @return a org.semanticwb.process.documentation.model.DocumentationInstance
   */
    public org.semanticwb.process.documentation.model.DocumentationInstance getDocumentationInstance()
    {
         org.semanticwb.process.documentation.model.DocumentationInstance ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_hasDocumentationInstanceInv);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.DocumentationInstance)obj.createGenericInstance();
         }
         return ret;
    }
   /**
   * Gets all the org.semanticwb.process.documentation.model.DocumentSection
   * @return A GenericIterator with all the org.semanticwb.process.documentation.model.DocumentSection
   */

    public org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSections()
    {
        return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection>(getSemanticObject().listObjectProperties(swpdoc_hasDocumentSection));
    }

   /**
   * Gets true if has a DocumentSection
   * @param value org.semanticwb.process.documentation.model.DocumentSection to verify
   * @return true if the org.semanticwb.process.documentation.model.DocumentSection exists, false otherwise
   */
    public boolean hasDocumentSection(org.semanticwb.process.documentation.model.DocumentSection value)
    {
        boolean ret=false;
        if(value!=null)
        {
           ret=getSemanticObject().hasObjectProperty(swpdoc_hasDocumentSection,value.getSemanticObject());
        }
        return ret;
    }
   /**
   * Adds a DocumentSection
   * @param value org.semanticwb.process.documentation.model.DocumentSection to add
   */

    public void addDocumentSection(org.semanticwb.process.documentation.model.DocumentSection value)
    {
        getSemanticObject().addObjectProperty(swpdoc_hasDocumentSection, value.getSemanticObject());
    }
   /**
   * Removes all the DocumentSection
   */

    public void removeAllDocumentSection()
    {
        getSemanticObject().removeProperty(swpdoc_hasDocumentSection);
    }
   /**
   * Removes a DocumentSection
   * @param value org.semanticwb.process.documentation.model.DocumentSection to remove
   */

    public void removeDocumentSection(org.semanticwb.process.documentation.model.DocumentSection value)
    {
        getSemanticObject().removeObjectProperty(swpdoc_hasDocumentSection,value.getSemanticObject());
    }

   /**
   * Gets the DocumentSection
   * @return a org.semanticwb.process.documentation.model.DocumentSection
   */
    public org.semanticwb.process.documentation.model.DocumentSection getDocumentSection()
    {
         org.semanticwb.process.documentation.model.DocumentSection ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_hasDocumentSection);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.DocumentSection)obj.createGenericInstance();
         }
         return ret;
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
   * Gets all the org.semanticwb.process.model.Process
   * @return A GenericIterator with all the org.semanticwb.process.model.Process
   */

    public org.semanticwb.model.GenericIterator<org.semanticwb.process.model.Process> listProcesses()
    {
        return new org.semanticwb.model.GenericIterator<org.semanticwb.process.model.Process>(getSemanticObject().listObjectProperties(swpdoc_hasProcess));
    }

   /**
   * Gets true if has a Process
   * @param value org.semanticwb.process.model.Process to verify
   * @return true if the org.semanticwb.process.model.Process exists, false otherwise
   */
    public boolean hasProcess(org.semanticwb.process.model.Process value)
    {
        boolean ret=false;
        if(value!=null)
        {
           ret=getSemanticObject().hasObjectProperty(swpdoc_hasProcess,value.getSemanticObject());
        }
        return ret;
    }
   /**
   * Adds a Process
   * @param value org.semanticwb.process.model.Process to add
   */

    public void addProcess(org.semanticwb.process.model.Process value)
    {
        getSemanticObject().addObjectProperty(swpdoc_hasProcess, value.getSemanticObject());
    }
   /**
   * Removes all the Process
   */

    public void removeAllProcess()
    {
        getSemanticObject().removeProperty(swpdoc_hasProcess);
    }
   /**
   * Removes a Process
   * @param value org.semanticwb.process.model.Process to remove
   */

    public void removeProcess(org.semanticwb.process.model.Process value)
    {
        getSemanticObject().removeObjectProperty(swpdoc_hasProcess,value.getSemanticObject());
    }

   /**
   * Gets the Process
   * @return a org.semanticwb.process.model.Process
   */
    public org.semanticwb.process.model.Process getProcess()
    {
         org.semanticwb.process.model.Process ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_hasProcess);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.model.Process)obj.createGenericInstance();
         }
         return ret;
    }
}
