package org.semanticwb.process.documentation.model.base;


public abstract class SectionElementBase extends org.semanticwb.model.SWBClass implements org.semanticwb.model.Descriptiveable,org.semanticwb.model.Traceable,org.semanticwb.model.Sortable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_DocumentSection=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#DocumentSection");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_parentSection=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#parentSection");
    public static final org.semanticwb.platform.SemanticClass swp_ProcessElement=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/swb4/process#ProcessElement");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_hasProcessElementScope=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#hasProcessElementScope");
    public static final org.semanticwb.platform.SemanticClass swpdoc_SectionElement=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#SectionElement");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#SectionElement");

    public static class ClassMgr
    {
       /**
       * Returns a list of SectionElement for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElements(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.SectionElement for all models
       * @return Iterator of org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElements()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement>(it, true);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.SectionElement
       * @param id Identifier for org.semanticwb.process.documentation.model.SectionElement
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElement
       * @return A org.semanticwb.process.documentation.model.SectionElement
       */
        public static org.semanticwb.process.documentation.model.SectionElement getSectionElement(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.SectionElement)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.SectionElement
       * @param id Identifier for org.semanticwb.process.documentation.model.SectionElement
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElement
       * @return A org.semanticwb.process.documentation.model.SectionElement
       */
        public static org.semanticwb.process.documentation.model.SectionElement createSectionElement(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.SectionElement)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.SectionElement
       * @param id Identifier for org.semanticwb.process.documentation.model.SectionElement
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElement
       */
        public static void removeSectionElement(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.SectionElement
       * @param id Identifier for org.semanticwb.process.documentation.model.SectionElement
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElement
       * @return true if the org.semanticwb.process.documentation.model.SectionElement exists, false otherwise
       */

        public static boolean hasSectionElement(String id, org.semanticwb.model.SWBModel model)
        {
            return (getSectionElement(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElement with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElementByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElement with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElementByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElement with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElementByParentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElement with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElementByParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElement with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElementByProcessElementScope(org.semanticwb.process.model.ProcessElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElement with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElementByProcessElementScope(org.semanticwb.process.model.ProcessElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElement with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.SectionElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElementByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.SectionElement with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.SectionElement
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.SectionElement> listSectionElementByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.SectionElement> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static SectionElementBase.ClassMgr getSectionElementClassMgr()
    {
        return new SectionElementBase.ClassMgr();
    }

   /**
   * Constructs a SectionElementBase with a SemanticObject
   * @param base The SemanticObject with the properties for the SectionElement
   */
    public SectionElementBase(org.semanticwb.platform.SemanticObject base)
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
   * Sets the value for the property ParentSection
   * @param value ParentSection to set
   */

    public void setParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_parentSection, value.getSemanticObject());
        }else
        {
            removeParentSection();
        }
    }
   /**
   * Remove the value for ParentSection property
   */

    public void removeParentSection()
    {
        getSemanticObject().removeProperty(swpdoc_parentSection);
    }

   /**
   * Gets the ParentSection
   * @return a org.semanticwb.process.documentation.model.DocumentSection
   */
    public org.semanticwb.process.documentation.model.DocumentSection getParentSection()
    {
         org.semanticwb.process.documentation.model.DocumentSection ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_parentSection);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.documentation.model.DocumentSection)obj.createGenericInstance();
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
   * Gets all the org.semanticwb.process.model.ProcessElement
   * @return A GenericIterator with all the org.semanticwb.process.model.ProcessElement
   */

    public org.semanticwb.model.GenericIterator<org.semanticwb.process.model.ProcessElement> listProcessElementScopes()
    {
        return new org.semanticwb.model.GenericIterator<org.semanticwb.process.model.ProcessElement>(getSemanticObject().listObjectProperties(swpdoc_hasProcessElementScope));
    }

   /**
   * Gets true if has a ProcessElementScope
   * @param value org.semanticwb.process.model.ProcessElement to verify
   * @return true if the org.semanticwb.process.model.ProcessElement exists, false otherwise
   */
    public boolean hasProcessElementScope(org.semanticwb.process.model.ProcessElement value)
    {
        boolean ret=false;
        if(value!=null)
        {
           ret=getSemanticObject().hasObjectProperty(swpdoc_hasProcessElementScope,value.getSemanticObject());
        }
        return ret;
    }
   /**
   * Adds a ProcessElementScope
   * @param value org.semanticwb.process.model.ProcessElement to add
   */

    public void addProcessElementScope(org.semanticwb.process.model.ProcessElement value)
    {
        getSemanticObject().addObjectProperty(swpdoc_hasProcessElementScope, value.getSemanticObject());
    }
   /**
   * Removes all the ProcessElementScope
   */

    public void removeAllProcessElementScope()
    {
        getSemanticObject().removeProperty(swpdoc_hasProcessElementScope);
    }
   /**
   * Removes a ProcessElementScope
   * @param value org.semanticwb.process.model.ProcessElement to remove
   */

    public void removeProcessElementScope(org.semanticwb.process.model.ProcessElement value)
    {
        getSemanticObject().removeObjectProperty(swpdoc_hasProcessElementScope,value.getSemanticObject());
    }

   /**
   * Gets the ProcessElementScope
   * @return a org.semanticwb.process.model.ProcessElement
   */
    public org.semanticwb.process.model.ProcessElement getProcessElementScope()
    {
         org.semanticwb.process.model.ProcessElement ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_hasProcessElementScope);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.model.ProcessElement)obj.createGenericInstance();
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
}
