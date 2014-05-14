package org.semanticwb.process.documentation.model.base;


public abstract class PolicyBase extends org.semanticwb.process.documentation.model.SectionElement implements org.semanticwb.process.documentation.model.Instantiable,org.semanticwb.model.Descriptiveable,org.semanticwb.process.documentation.model.Numerable,org.semanticwb.model.Sortable,org.semanticwb.model.Traceable,org.semanticwb.process.documentation.model.Prefixeable
{
    public static final org.semanticwb.platform.SemanticClass swpdoc_Policy=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Policy");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Policy");

    public static class ClassMgr
    {
       /**
       * Returns a list of Policy for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicies(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.Policy for all models
       * @return Iterator of org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicies()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy>(it, true);
        }

        public static org.semanticwb.process.documentation.model.Policy createPolicy(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.Policy.ClassMgr.createPolicy(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.Policy
       * @param id Identifier for org.semanticwb.process.documentation.model.Policy
       * @param model Model of the org.semanticwb.process.documentation.model.Policy
       * @return A org.semanticwb.process.documentation.model.Policy
       */
        public static org.semanticwb.process.documentation.model.Policy getPolicy(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Policy)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.Policy
       * @param id Identifier for org.semanticwb.process.documentation.model.Policy
       * @param model Model of the org.semanticwb.process.documentation.model.Policy
       * @return A org.semanticwb.process.documentation.model.Policy
       */
        public static org.semanticwb.process.documentation.model.Policy createPolicy(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Policy)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.Policy
       * @param id Identifier for org.semanticwb.process.documentation.model.Policy
       * @param model Model of the org.semanticwb.process.documentation.model.Policy
       */
        public static void removePolicy(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.Policy
       * @param id Identifier for org.semanticwb.process.documentation.model.Policy
       * @param model Model of the org.semanticwb.process.documentation.model.Policy
       * @return true if the org.semanticwb.process.documentation.model.Policy exists, false otherwise
       */

        public static boolean hasPolicy(String id, org.semanticwb.model.SWBModel model)
        {
            return (getPolicy(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Policy with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Policy
       * @return Iterator with all the org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicyByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Policy with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicyByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Policy with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.Policy
       * @return Iterator with all the org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicyByParentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Policy with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicyByParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Policy with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @param model Model of the org.semanticwb.process.documentation.model.Policy
       * @return Iterator with all the org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicyByProcessElementScope(org.semanticwb.process.model.ProcessElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Policy with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicyByProcessElementScope(org.semanticwb.process.model.ProcessElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Policy with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Policy
       * @return Iterator with all the org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicyByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Policy with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Policy
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Policy> listPolicyByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Policy> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static PolicyBase.ClassMgr getPolicyClassMgr()
    {
        return new PolicyBase.ClassMgr();
    }

   /**
   * Constructs a PolicyBase with a SemanticObject
   * @param base The SemanticObject with the properties for the Policy
   */
    public PolicyBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
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
