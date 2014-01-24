package org.semanticwb.process.documentation.model.base;


public abstract class DocumentationInstanceBase extends org.semanticwb.model.SWBClass implements org.semanticwb.model.Traceable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentTemplate=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentTemplate");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_docTypeDefinition=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#docTypeDefinition");
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentSectionInstance=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentSectionInstance");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_hasDocumnetSectionInstance=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#hasDocumnetSectionInstance");
    public static final org.semanticwb.platform.SemanticClass swp_Process=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/swb4/process#Process");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_processRef=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#processRef");
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentationInstance=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentationInstance");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentationInstance");

    public static class ClassMgr
    {
       /**
       * Returns a list of DocumentationInstance for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstances(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.DocumentationInstance for all models
       * @return Iterator of org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstances()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance>(it, true);
        }

        public static org.semanticwb.process.documentation.model.DocumentationInstance createDocumentationInstance(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.DocumentationInstance.ClassMgr.createDocumentationInstance(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.DocumentationInstance
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentationInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       * @return A org.semanticwb.process.documentation.model.DocumentationInstance
       */
        public static org.semanticwb.process.documentation.model.DocumentationInstance getDocumentationInstance(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.DocumentationInstance)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.DocumentationInstance
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentationInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       * @return A org.semanticwb.process.documentation.model.DocumentationInstance
       */
        public static org.semanticwb.process.documentation.model.DocumentationInstance createDocumentationInstance(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.DocumentationInstance)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.DocumentationInstance
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentationInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       */
        public static void removeDocumentationInstance(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.DocumentationInstance
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentationInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       * @return true if the org.semanticwb.process.documentation.model.DocumentationInstance exists, false otherwise
       */

        public static boolean hasDocumentationInstance(String id, org.semanticwb.model.SWBModel model)
        {
            return (getDocumentationInstance(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined DocTypeDefinition
       * @param value DocTypeDefinition of the type org.semanticwb.process.documentation.model.DocumentTemplate
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByDocTypeDefinition(org.semanticwb.process.documentation.model.DocumentTemplate value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_docTypeDefinition, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined DocTypeDefinition
       * @param value DocTypeDefinition of the type org.semanticwb.process.documentation.model.DocumentTemplate
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByDocTypeDefinition(org.semanticwb.process.documentation.model.DocumentTemplate value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_docTypeDefinition,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined DocumnetSectionInstance
       * @param value DocumnetSectionInstance of the type org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByDocumnetSectionInstance(org.semanticwb.process.documentation.model.DocumentSectionInstance value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasDocumnetSectionInstance, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined DocumnetSectionInstance
       * @param value DocumnetSectionInstance of the type org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByDocumnetSectionInstance(org.semanticwb.process.documentation.model.DocumentSectionInstance value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasDocumnetSectionInstance,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined ProcessRef
       * @param value ProcessRef of the type org.semanticwb.process.model.Process
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentationInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByProcessRef(org.semanticwb.process.model.Process value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_processRef, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentationInstance with a determined ProcessRef
       * @param value ProcessRef of the type org.semanticwb.process.model.Process
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentationInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentationInstance> listDocumentationInstanceByProcessRef(org.semanticwb.process.model.Process value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentationInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_processRef,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static DocumentationInstanceBase.ClassMgr getDocumentationInstanceClassMgr()
    {
        return new DocumentationInstanceBase.ClassMgr();
    }

   /**
   * Constructs a DocumentationInstanceBase with a SemanticObject
   * @param base The SemanticObject with the properties for the DocumentationInstance
   */
    public DocumentationInstanceBase(org.semanticwb.platform.SemanticObject base)
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
   * Sets the value for the property DocTypeDefinition
   * @param value DocTypeDefinition to set
   */

    public void setDocTypeDefinition(org.semanticwb.process.documentation.model.DocumentTemplate value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_docTypeDefinition, value.getSemanticObject());
        }else
        {
            removeDocTypeDefinition();
        }
    }
   /**
   * Remove the value for DocTypeDefinition property
   */

    public void removeDocTypeDefinition()
    {
        getSemanticObject().removeProperty(swpdoc_docTypeDefinition);
    }

   /**
   * Gets the DocTypeDefinition
   * @return a org.semanticwb.process.documentation.model.DocumentTemplate
   */
    public org.semanticwb.process.documentation.model.DocumentTemplate getDocTypeDefinition()
    {
         org.semanticwb.process.documentation.model.DocumentTemplate ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_docTypeDefinition);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.DocumentTemplate)obj.createGenericInstance();
         }
         return ret;
    }
   /**
   * Gets all the org.semanticwb.process.documentation.model.DocumentSectionInstance
   * @return A GenericIterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
   */

    public org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumnetSectionInstances()
    {
        return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance>(getSemanticObject().listObjectProperties(swpdoc_hasDocumnetSectionInstance));
    }

   /**
   * Gets true if has a DocumnetSectionInstance
   * @param value org.semanticwb.process.documentation.model.DocumentSectionInstance to verify
   * @return true if the org.semanticwb.process.documentation.model.DocumentSectionInstance exists, false otherwise
   */
    public boolean hasDocumnetSectionInstance(org.semanticwb.process.documentation.model.DocumentSectionInstance value)
    {
        boolean ret=false;
        if(value!=null)
        {
           ret=getSemanticObject().hasObjectProperty(swpdoc_hasDocumnetSectionInstance,value.getSemanticObject());
        }
        return ret;
    }
   /**
   * Adds a DocumnetSectionInstance
   * @param value org.semanticwb.process.documentation.model.DocumentSectionInstance to add
   */

    public void addDocumnetSectionInstance(org.semanticwb.process.documentation.model.DocumentSectionInstance value)
    {
        getSemanticObject().addObjectProperty(swpdoc_hasDocumnetSectionInstance, value.getSemanticObject());
    }
   /**
   * Removes all the DocumnetSectionInstance
   */

    public void removeAllDocumnetSectionInstance()
    {
        getSemanticObject().removeProperty(swpdoc_hasDocumnetSectionInstance);
    }
   /**
   * Removes a DocumnetSectionInstance
   * @param value org.semanticwb.process.documentation.model.DocumentSectionInstance to remove
   */

    public void removeDocumnetSectionInstance(org.semanticwb.process.documentation.model.DocumentSectionInstance value)
    {
        getSemanticObject().removeObjectProperty(swpdoc_hasDocumnetSectionInstance,value.getSemanticObject());
    }

   /**
   * Gets the DocumnetSectionInstance
   * @return a org.semanticwb.process.documentation.model.DocumentSectionInstance
   */
    public org.semanticwb.process.documentation.model.DocumentSectionInstance getDocumnetSectionInstance()
    {
         org.semanticwb.process.documentation.model.DocumentSectionInstance ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_hasDocumnetSectionInstance);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.DocumentSectionInstance)obj.createGenericInstance();
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
   * Sets the value for the property ProcessRef
   * @param value ProcessRef to set
   */

    public void setProcessRef(org.semanticwb.process.model.Process value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_processRef, value.getSemanticObject());
        }else
        {
            removeProcessRef();
        }
    }
   /**
   * Remove the value for ProcessRef property
   */

    public void removeProcessRef()
    {
        getSemanticObject().removeProperty(swpdoc_processRef);
    }

   /**
   * Gets the ProcessRef
   * @return a org.semanticwb.process.model.Process
   */
    public org.semanticwb.process.model.Process getProcessRef()
    {
         org.semanticwb.process.model.Process ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_processRef);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.model.Process)obj.createGenericInstance();
         }
         return ret;
    }
}
