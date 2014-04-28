package org.semanticwb.process.documentation.model.base;


public abstract class DefinitionBase extends org.semanticwb.process.documentation.model.SectionElement implements org.semanticwb.model.Descriptiveable,org.semanticwb.process.documentation.model.Referable,org.semanticwb.process.documentation.model.Numerable,org.semanticwb.model.Sortable,org.semanticwb.model.Traceable,org.semanticwb.process.documentation.model.Prefixeable,org.semanticwb.process.documentation.model.Instantiable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_Definition=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Definition");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Definition");

    public static class ClassMgr
    {
       /**
       * Returns a list of Definition for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitions(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.Definition for all models
       * @return Iterator of org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitions()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition>(it, true);
        }

        public static org.semanticwb.process.documentation.model.Definition createDefinition(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.Definition.ClassMgr.createDefinition(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.Definition
       * @param id Identifier for org.semanticwb.process.documentation.model.Definition
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       * @return A org.semanticwb.process.documentation.model.Definition
       */
        public static org.semanticwb.process.documentation.model.Definition getDefinition(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Definition)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.Definition
       * @param id Identifier for org.semanticwb.process.documentation.model.Definition
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       * @return A org.semanticwb.process.documentation.model.Definition
       */
        public static org.semanticwb.process.documentation.model.Definition createDefinition(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Definition)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.Definition
       * @param id Identifier for org.semanticwb.process.documentation.model.Definition
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       */
        public static void removeDefinition(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.Definition
       * @param id Identifier for org.semanticwb.process.documentation.model.Definition
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       * @return true if the org.semanticwb.process.documentation.model.Definition exists, false otherwise
       */

        public static boolean hasDefinition(String id, org.semanticwb.model.SWBModel model)
        {
            return (getDefinition(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByParentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined RefRepository
       * @param value RefRepository of the type org.semanticwb.process.model.RepositoryElement
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByRefRepository(org.semanticwb.process.model.RepositoryElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_refRepository, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined RefRepository
       * @param value RefRepository of the type org.semanticwb.process.model.RepositoryElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByRefRepository(org.semanticwb.process.model.RepositoryElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_refRepository,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByProcessElementScope(org.semanticwb.process.model.ProcessElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByProcessElementScope(org.semanticwb.process.model.ProcessElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Definition
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Definition with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Definition
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Definition> listDefinitionByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Definition> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static DefinitionBase.ClassMgr getDefinitionClassMgr()
    {
        return new DefinitionBase.ClassMgr();
    }

   /**
   * Constructs a DefinitionBase with a SemanticObject
   * @param base The SemanticObject with the properties for the Definition
   */
    public DefinitionBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
    }

/**
* Gets the File property
* @return String with the File
*/
    public String getFile()
    {
        return getSemanticObject().getProperty(swpdoc_file);
    }

/**
* Sets the File property
* @param value long with the File
*/
    public void setFile(String value)
    {
        getSemanticObject().setProperty(swpdoc_file, value);
    }
   /**
   * Sets the value for the property RefRepository
   * @param value RefRepository to set
   */

    public void setRefRepository(org.semanticwb.process.model.RepositoryElement value)
    {
        if(value!=null)
        {
            getSemanticObject().setObjectProperty(swpdoc_refRepository, value.getSemanticObject());
        }else
        {
            removeRefRepository();
        }
    }
   /**
   * Remove the value for RefRepository property
   */

    public void removeRefRepository()
    {
        getSemanticObject().removeProperty(swpdoc_refRepository);
    }

   /**
   * Gets the RefRepository
   * @return a org.semanticwb.process.model.RepositoryElement
   */
    public org.semanticwb.process.model.RepositoryElement getRefRepository()
    {
         org.semanticwb.process.model.RepositoryElement ret=null;
         org.semanticwb.platform.SemanticObject obj=getSemanticObject().getObjectProperty(swpdoc_refRepository);
         if(obj!=null)
         {
             ret=(org.semanticwb.process.model.RepositoryElement)obj.createGenericInstance();
         }
         return ret;
    }

/**
* Gets the Number property
* @return String with the Number
*/
    public String getNumber()
    {
        return getSemanticObject().getProperty(swpdoc_number);
    }

/**
* Sets the Number property
* @param value long with the Number
*/
    public void setNumber(String value)
    {
        getSemanticObject().setProperty(swpdoc_number, value);
    }

    public String getNumber(String lang)
    {
        return getSemanticObject().getProperty(swpdoc_number, null, lang);
    }

    public String getDisplayNumber(String lang)
    {
        return getSemanticObject().getLocaleProperty(swpdoc_number, lang);
    }

    public void setNumber(String number, String lang)
    {
        getSemanticObject().setProperty(swpdoc_number, number, lang);
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
}
