package org.semanticwb.process.documentation.model.base;


public abstract class DocumentSectionBase extends org.semanticwb.model.SWBClass implements org.semanticwb.model.Activeable,org.semanticwb.model.Descriptiveable,org.semanticwb.model.Sortable,org.semanticwb.model.Traceable,org.semanticwb.process.documentation.model.Prefixeable
{
    public static final org.semanticwb.platform.SemanticProperty swpdoc_configData=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#configData");
    public static final org.semanticwb.platform.SemanticClass swpdoc_SectionElement=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#SectionElement");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_hasSectionElementInv=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#hasSectionElementInv");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_visibleProperties=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#visibleProperties");
    public static final org.semanticwb.platform.SemanticClass swb_Class=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/swb4/ontology#Class");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_sectionType=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#sectionType");
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentSection=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentSection");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentSection");

    public static class ClassMgr
    {
       /**
       * Returns a list of DocumentSection for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.DocumentSection
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSections(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.DocumentSection for all models
       * @return Iterator of org.semanticwb.process.documentation.model.DocumentSection
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSections()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection>(it, true);
        }

        public static org.semanticwb.process.documentation.model.DocumentSection createDocumentSection(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.DocumentSection.ClassMgr.createDocumentSection(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.DocumentSection
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSection
       * @return A org.semanticwb.process.documentation.model.DocumentSection
       */
        public static org.semanticwb.process.documentation.model.DocumentSection getDocumentSection(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.DocumentSection)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.DocumentSection
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSection
       * @return A org.semanticwb.process.documentation.model.DocumentSection
       */
        public static org.semanticwb.process.documentation.model.DocumentSection createDocumentSection(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.DocumentSection)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.DocumentSection
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSection
       */
        public static void removeDocumentSection(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.DocumentSection
       * @param id Identifier for org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSection
       * @return true if the org.semanticwb.process.documentation.model.DocumentSection exists, false otherwise
       */

        public static boolean hasDocumentSection(String id, org.semanticwb.model.SWBModel model)
        {
            return (getDocumentSection(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSection with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSection
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSectionByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSection with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSection
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSectionByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSection with a determined SectionElement
       * @param value SectionElement of the type org.semanticwb.process.documentation.model.SectionElement
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSection
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSectionBySectionElement(org.semanticwb.process.documentation.model.SectionElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasSectionElementInv, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSection with a determined SectionElement
       * @param value SectionElement of the type org.semanticwb.process.documentation.model.SectionElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSection
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSectionBySectionElement(org.semanticwb.process.documentation.model.SectionElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasSectionElementInv,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSection with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSection
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSectionByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.DocumentSection with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.DocumentSection
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.DocumentSection> listDocumentSectionByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.DocumentSection> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static DocumentSectionBase.ClassMgr getDocumentSectionClassMgr()
    {
        return new DocumentSectionBase.ClassMgr();
    }

   /**
   * Constructs a DocumentSectionBase with a SemanticObject
   * @param base The SemanticObject with the properties for the DocumentSection
   */
    public DocumentSectionBase(org.semanticwb.platform.SemanticObject base)
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
* Gets the ConfigData property
* @return String with the ConfigData
*/
    public String getConfigData()
    {
        return getSemanticObject().getProperty(swpdoc_configData);
    }

/**
* Sets the ConfigData property
* @param value long with the ConfigData
*/
    public void setConfigData(String value)
    {
        getSemanticObject().setProperty(swpdoc_configData, value);
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
* Gets the Index property
* @return int with the Index
*/
    public int getIndex()
    {
        return getSemanticObject().getIntProperty(swb_index);
    }

/**
* Sets the Index property
* @param value long with the Index
*/
    public void setIndex(int value)
    {
        getSemanticObject().setIntProperty(swb_index, value);
    }
   /**
   * Gets all the org.semanticwb.process.documentation.model.SectionElement
   * @return A GenericIterator with all the org.semanticwb.process.documentation.model.SectionElement
   */

    public org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElements()
    {
        return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement>(getSemanticObject().listObjectProperties(swpdoc_hasSectionElementInv));
    }

   /**
   * Gets true if has a SectionElement
   * @param value org.semanticwb.process.documentation.model.SectionElement to verify
   * @return true if the org.semanticwb.process.documentation.model.SectionElement exists, false otherwise
   */
    public boolean hasSectionElement(org.semanticwb.process.documentation.model.SectionElement value)
    {
        boolean ret=false;
        if(value!=null)
        {
           ret=getSemanticObject().hasObjectProperty(swpdoc_hasSectionElementInv,value.getSemanticObject());
        }
        return ret;
    }

   /**
   * Gets the SectionElement
   * @return a org.semanticwb.process.documentation.model.SectionElement
   */
    public org.semanticwb.process.documentation.model.SectionElement getSectionElement()
    {
         org.semanticwb.process.documentation.model.SectionElement ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_hasSectionElementInv);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.SectionElement)obj.createGenericInstance();
         }
         return ret;
    }

/**
* Gets the Prefix property
* @return String with the Prefix
*/
    public String getPrefix()
    {
        return getSemanticObject().getProperty(swpdoc_prefix);
    }

/**
* Sets the Prefix property
* @param value long with the Prefix
*/
    public void setPrefix(String value)
    {
        getSemanticObject().setProperty(swpdoc_prefix, value);
    }

    public String getPrefix(String lang)
    {
        return getSemanticObject().getProperty(swpdoc_prefix, null, lang);
    }

    public String getDisplayPrefix(String lang)
    {
        return getSemanticObject().getLocaleProperty(swpdoc_prefix, lang);
    }

    public void setPrefix(String prefix, String lang)
    {
        getSemanticObject().setProperty(swpdoc_prefix, prefix, lang);
    }

/**
* Gets the Active property
* @return boolean with the Active
*/
    public boolean isActive()
    {
        return getSemanticObject().getBooleanProperty(swb_active);
    }

/**
* Sets the Active property
* @param value long with the Active
*/
    public void setActive(boolean value)
    {
        getSemanticObject().setBooleanProperty(swb_active, value);
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
* Gets the VisibleProperties property
* @return String with the VisibleProperties
*/
    public String getVisibleProperties()
    {
        return getSemanticObject().getProperty(swpdoc_visibleProperties);
    }

/**
* Sets the VisibleProperties property
* @param value long with the VisibleProperties
*/
    public void setVisibleProperties(String value)
    {
        getSemanticObject().setProperty(swpdoc_visibleProperties, value);
    }

    public void setSectionType(org.semanticwb.platform.SemanticObject value)
    {
        getSemanticObject().setObjectProperty(swpdoc_sectionType, value);
    }

    public void removeSectionType()
    {
        getSemanticObject().removeProperty(swpdoc_sectionType);
    }

/**
* Gets the SectionType property
* @return the value for the property as org.semanticwb.platform.SemanticObject
*/
    public org.semanticwb.platform.SemanticObject getSectionType()
    {
         org.semanticwb.platform.SemanticObject ret=null;
         ret=getSemanticObject().getObjectProperty(swpdoc_sectionType);
         return ret;
    }
}
