package org.semanticwb.process.documentation.model.base;


public abstract class DocumentSectionInstanceBase extends org.semanticwb.model.SWBClass implements org.semanticwb.model.Traceable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_SectionElement=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#SectionElement");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_hasDocuSectionElementInstance=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#hasDocuSectionElementInstance");
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentSection=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentSection");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_secTypeDefinition=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#secTypeDefinition");
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentSectionInstance=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentSectionInstance");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentSectionInstance");

    public static class ClassMgr
    {
       /**
       * Returns a list of DocumentSectionInstance for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstances(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.DocumentSectionInstance for all models
       * @return Iterator of org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstances()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance>(it, true);
        }

        public static org.semanticwb.process.documentation.model.DocumentSectionInstance createDocumentSectionInstance(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.DocumentSectionInstance.ClassMgr.createDocumentSectionInstance(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @return A org.semanticwb.process.documentation.model.DocumentSectionInstance
       */
        public static org.semanticwb.process.documentation.model.DocumentSectionInstance getDocumentSectionInstance(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.DocumentSectionInstance)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @return A org.semanticwb.process.documentation.model.DocumentSectionInstance
       */
        public static org.semanticwb.process.documentation.model.DocumentSectionInstance createDocumentSectionInstance(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.DocumentSectionInstance)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */
        public static void removeDocumentSectionInstance(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @return true if the org.semanticwb.process.documentation.model.DocumentSectionInstance exists, false otherwise
       */

        public static boolean hasDocumentSectionInstance(String id, org.semanticwb.model.SWBModel model)
        {
            return (getDocumentSectionInstance(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSectionInstance with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstanceByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSectionInstance with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstanceByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSectionInstance with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstanceByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSectionInstance with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstanceByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSectionInstance with a determined DocuSectionElementInstance
       * @param value DocuSectionElementInstance of the type org.semanticwb.process.documentation.model.SectionElement
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstanceByDocuSectionElementInstance(org.semanticwb.process.documentation.model.SectionElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasDocuSectionElementInstance, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSectionInstance with a determined DocuSectionElementInstance
       * @param value DocuSectionElementInstance of the type org.semanticwb.process.documentation.model.SectionElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstanceByDocuSectionElementInstance(org.semanticwb.process.documentation.model.SectionElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasDocuSectionElementInstance,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSectionInstance with a determined SecTypeDefinition
       * @param value SecTypeDefinition of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSectionInstance
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstanceBySecTypeDefinition(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_secTypeDefinition, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSectionInstance with a determined SecTypeDefinition
       * @param value SecTypeDefinition of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSectionInstance
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> listDocumentSectionInstanceBySecTypeDefinition(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSectionInstance> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_secTypeDefinition,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static DocumentSectionInstanceBase.ClassMgr getDocumentSectionInstanceClassMgr()
    {
        return new DocumentSectionInstanceBase.ClassMgr();
    }

   /**
   * Constructs a DocumentSectionInstanceBase with a SemanticObject
   * @param base The SemanticObject with the properties for the DocumentSectionInstance
   */
    public DocumentSectionInstanceBase(org.semanticwb.platform.SemanticObject base)
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
   * Gets all the org.semanticwb.process.documentation.model.SectionElement
   * @return A GenericIterator with all the org.semanticwb.process.documentation.model.SectionElement
   */

    public org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> listDocuSectionElementInstances()
    {
        return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement>(getSemanticObject().listObjectProperties(swpdoc_hasDocuSectionElementInstance));
    }

   /**
   * Gets true if has a DocuSectionElementInstance
   * @param value org.semanticwb.process.documentation.model.SectionElement to verify
   * @return true if the org.semanticwb.process.documentation.model.SectionElement exists, false otherwise
   */
    public boolean hasDocuSectionElementInstance(org.semanticwb.process.documentation.model.SectionElement value)
    {
        boolean ret=false;
        if(value!=null)
        {
           ret=getSemanticObject().hasObjectProperty(swpdoc_hasDocuSectionElementInstance,value.getSemanticObject());
        }
        return ret;
    }
   /**
   * Adds a DocuSectionElementInstance
   * @param value org.semanticwb.process.documentation.model.SectionElement to add
   */

    public void addDocuSectionElementInstance(org.semanticwb.process.documentation.model.SectionElement value)
    {
        getSemanticObject().addObjectProperty(swpdoc_hasDocuSectionElementInstance, value.getSemanticObject());
    }
   /**
   * Removes all the DocuSectionElementInstance
   */

    public void removeAllDocuSectionElementInstance()
    {
        getSemanticObject().removeProperty(swpdoc_hasDocuSectionElementInstance);
    }
   /**
   * Removes a DocuSectionElementInstance
   * @param value org.semanticwb.process.documentation.model.SectionElement to remove
   */

    public void removeDocuSectionElementInstance(org.semanticwb.process.documentation.model.SectionElement value)
    {
        getSemanticObject().removeObjectProperty(swpdoc_hasDocuSectionElementInstance,value.getSemanticObject());
    }

   /**
   * Gets the DocuSectionElementInstance
   * @return a org.semanticwb.process.documentation.model.SectionElement
   */
    public org.semanticwb.process.documentation.model.SectionElement getDocuSectionElementInstance()
    {
         org.semanticwb.process.documentation.model.SectionElement ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_hasDocuSectionElementInstance);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.SectionElement)obj.createGenericInstance();
         }
         return ret;
    }
   /**
   * Sets the value for the property SecTypeDefinition
   * @param value SecTypeDefinition to set
   */

    public void setSecTypeDefinition(org.semanticwb.process.documentation.model.DocumentSection value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_secTypeDefinition, value.getSemanticObject());
        }else
        {
            removeSecTypeDefinition();
        }
    }
   /**
   * Remove the value for SecTypeDefinition property
   */

    public void removeSecTypeDefinition()
    {
        getSemanticObject().removeProperty(swpdoc_secTypeDefinition);
    }

   /**
   * Gets the SecTypeDefinition
   * @return a org.semanticwb.process.documentation.model.DocumentSection
   */
    public org.semanticwb.process.documentation.model.DocumentSection getSecTypeDefinition()
    {
         org.semanticwb.process.documentation.model.DocumentSection ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_secTypeDefinition);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.DocumentSection)obj.createGenericInstance();
         }
         return ret;
    }
}
