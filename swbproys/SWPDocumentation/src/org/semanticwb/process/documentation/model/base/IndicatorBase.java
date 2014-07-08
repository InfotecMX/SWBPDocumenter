package org.semanticwb.process.documentation.model.base;


public abstract class IndicatorBase extends org.semanticwb.process.documentation.model.SectionElement implements org.semanticwb.process.documentation.model.Instantiable,org.semanticwb.model.Descriptiveable,org.semanticwb.process.documentation.model.Numerable,org.semanticwb.model.Traceable,org.semanticwb.model.Sortable
{
    public static final org.semanticwb.platform.SemanticProperty swpdoc_methodVerification=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#methodVerification");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_frequencyCalculation=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#frequencyCalculation");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_objetive=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#objetive");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_weightingIndicator=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#weightingIndicator");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_responsible=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#responsible");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_informationSource=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#informationSource");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_clasification=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#clasification");
    public static final org.semanticwb.platform.SemanticProperty swpdoc_formula=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticProperty("http://www.semanticwebbuilder.org/resources/documentation#formula");
    public static final org.semanticwb.platform.SemanticClass swpdoc_Indicator=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Indicator");
   /**
   * The semantic class that represents the currentObject
   */
    public static final org.semanticwb.platform.SemanticClass sclass=org.semanticwb.SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass("http://www.semanticwebbuilder.org/resources/documentation#Indicator");

    public static class ClassMgr
    {
       /**
       * Returns a list of Indicator for a model
       * @param model Model to find
       * @return Iterator of org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicators(org.semanticwb.model.SWBModel model)
        {
            java.util.Iterator it=model.getSemanticObject().getModel().listInstancesOfClass(sclass);
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator>(it, true);
        }
       /**
       * Returns a list of org.semanticwb.process.documentation.model.Indicator for all models
       * @return Iterator of org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicators()
        {
            java.util.Iterator it=sclass.listInstances();
            return new org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator>(it, true);
        }

        public static org.semanticwb.process.documentation.model.Indicator createIndicator(org.semanticwb.model.SWBModel model)
        {
            long id=model.getSemanticObject().getModel().getCounter(sclass);
            return org.semanticwb.process.documentation.model.Indicator.ClassMgr.createIndicator(String.valueOf(id), model);
        }
       /**
       * Gets a org.semanticwb.process.documentation.model.Indicator
       * @param id Identifier for org.semanticwb.process.documentation.model.Indicator
       * @param model Model of the org.semanticwb.process.documentation.model.Indicator
       * @return A org.semanticwb.process.documentation.model.Indicator
       */
        public static org.semanticwb.process.documentation.model.Indicator getIndicator(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Indicator)model.getSemanticObject().getModel().getGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Create a org.semanticwb.process.documentation.model.Indicator
       * @param id Identifier for org.semanticwb.process.documentation.model.Indicator
       * @param model Model of the org.semanticwb.process.documentation.model.Indicator
       * @return A org.semanticwb.process.documentation.model.Indicator
       */
        public static org.semanticwb.process.documentation.model.Indicator createIndicator(String id, org.semanticwb.model.SWBModel model)
        {
            return (org.semanticwb.process.documentation.model.Indicator)model.getSemanticObject().getModel().createGenericObject(model.getSemanticObject().getModel().getObjectUri(id,sclass),sclass);
        }
       /**
       * Remove a org.semanticwb.process.documentation.model.Indicator
       * @param id Identifier for org.semanticwb.process.documentation.model.Indicator
       * @param model Model of the org.semanticwb.process.documentation.model.Indicator
       */
        public static void removeIndicator(String id, org.semanticwb.model.SWBModel model)
        {
            model.getSemanticObject().getModel().removeSemanticObject(model.getSemanticObject().getModel().getObjectUri(id,sclass));
        }
       /**
       * Returns true if exists a org.semanticwb.process.documentation.model.Indicator
       * @param id Identifier for org.semanticwb.process.documentation.model.Indicator
       * @param model Model of the org.semanticwb.process.documentation.model.Indicator
       * @return true if the org.semanticwb.process.documentation.model.Indicator exists, false otherwise
       */

        public static boolean hasIndicator(String id, org.semanticwb.model.SWBModel model)
        {
            return (getIndicator(id, model)!=null);
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Indicator with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Indicator
       * @return Iterator with all the org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicatorByModifiedBy(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Indicator with a determined ModifiedBy
       * @param value ModifiedBy of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicatorByModifiedBy(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_modifiedBy,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Indicator with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @param model Model of the org.semanticwb.process.documentation.model.Indicator
       * @return Iterator with all the org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicatorByParentSection(org.semanticwb.process.documentation.model.DocumentSection value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Indicator with a determined ParentSection
       * @param value ParentSection of the type org.semanticwb.process.documentation.model.DocumentSection
       * @return Iterator with all the org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicatorByParentSection(org.semanticwb.process.documentation.model.DocumentSection value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_parentSection,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Indicator with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @param model Model of the org.semanticwb.process.documentation.model.Indicator
       * @return Iterator with all the org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicatorByProcessElementScope(org.semanticwb.process.model.ProcessElement value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Indicator with a determined ProcessElementScope
       * @param value ProcessElementScope of the type org.semanticwb.process.model.ProcessElement
       * @return Iterator with all the org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicatorByProcessElementScope(org.semanticwb.process.model.ProcessElement value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swpdoc_hasProcessElementScope,value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Indicator with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @param model Model of the org.semanticwb.process.documentation.model.Indicator
       * @return Iterator with all the org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicatorByCreator(org.semanticwb.model.User value,org.semanticwb.model.SWBModel model)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator> it=new org.semanticwb.model.GenericIterator(model.getSemanticObject().getModel().listSubjectsByClass(swb_creator, value.getSemanticObject(),sclass));
            return it;
        }
       /**
       * Gets all org.semanticwb.process.documentation.model.Indicator with a determined Creator
       * @param value Creator of the type org.semanticwb.model.User
       * @return Iterator with all the org.semanticwb.process.documentation.model.Indicator
       */

        public static java.util.Iterator<org.semanticwb.process.documentation.model.Indicator> listIndicatorByCreator(org.semanticwb.model.User value)
        {
            org.semanticwb.model.GenericIterator<org.semanticwb.process.documentation.model.Indicator> it=new org.semanticwb.model.GenericIterator(value.getSemanticObject().getModel().listSubjectsByClass(swb_creator,value.getSemanticObject(),sclass));
            return it;
        }
    }

    public static IndicatorBase.ClassMgr getIndicatorClassMgr()
    {
        return new IndicatorBase.ClassMgr();
    }

   /**
   * Constructs a IndicatorBase with a SemanticObject
   * @param base The SemanticObject with the properties for the Indicator
   */
    public IndicatorBase(org.semanticwb.platform.SemanticObject base)
    {
        super(base);
    }

/**
* Gets the MethodVerification property
* @return String with the MethodVerification
*/
    public String getMethodVerification()
    {
        return getSemanticObject().getProperty(swpdoc_methodVerification);
    }

/**
* Sets the MethodVerification property
* @param value long with the MethodVerification
*/
    public void setMethodVerification(String value)
    {
        getSemanticObject().setProperty(swpdoc_methodVerification, value);
    }

/**
* Gets the FrequencyCalculation property
* @return String with the FrequencyCalculation
*/
    public String getFrequencyCalculation()
    {
        return getSemanticObject().getProperty(swpdoc_frequencyCalculation);
    }

/**
* Sets the FrequencyCalculation property
* @param value long with the FrequencyCalculation
*/
    public void setFrequencyCalculation(String value)
    {
        getSemanticObject().setProperty(swpdoc_frequencyCalculation, value);
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
* Gets the Objetive property
* @return String with the Objetive
*/
    public String getObjetive()
    {
        return getSemanticObject().getProperty(swpdoc_objetive);
    }

/**
* Sets the Objetive property
* @param value long with the Objetive
*/
    public void setObjetive(String value)
    {
        getSemanticObject().setProperty(swpdoc_objetive, value);
    }

    public String getObjetive(String lang)
    {
        return getSemanticObject().getProperty(swpdoc_objetive, null, lang);
    }

    public String getDisplayObjetive(String lang)
    {
        return getSemanticObject().getLocaleProperty(swpdoc_objetive, lang);
    }

    public void setObjetive(String objetive, String lang)
    {
        getSemanticObject().setProperty(swpdoc_objetive, objetive, lang);
    }

/**
* Gets the WeightingIndicator property
* @return String with the WeightingIndicator
*/
    public String getWeightingIndicator()
    {
        return getSemanticObject().getProperty(swpdoc_weightingIndicator);
    }

/**
* Sets the WeightingIndicator property
* @param value long with the WeightingIndicator
*/
    public void setWeightingIndicator(String value)
    {
        getSemanticObject().setProperty(swpdoc_weightingIndicator, value);
    }

/**
* Gets the Responsible property
* @return String with the Responsible
*/
    public String getResponsible()
    {
        return getSemanticObject().getProperty(swpdoc_responsible);
    }

/**
* Sets the Responsible property
* @param value long with the Responsible
*/
    public void setResponsible(String value)
    {
        getSemanticObject().setProperty(swpdoc_responsible, value);
    }

/**
* Gets the InformationSource property
* @return String with the InformationSource
*/
    public String getInformationSource()
    {
        return getSemanticObject().getProperty(swpdoc_informationSource);
    }

/**
* Sets the InformationSource property
* @param value long with the InformationSource
*/
    public void setInformationSource(String value)
    {
        getSemanticObject().setProperty(swpdoc_informationSource, value);
    }

/**
* Gets the Clasification property
* @return String with the Clasification
*/
    public String getClasification()
    {
        return getSemanticObject().getProperty(swpdoc_clasification);
    }

/**
* Sets the Clasification property
* @param value long with the Clasification
*/
    public void setClasification(String value)
    {
        getSemanticObject().setProperty(swpdoc_clasification, value);
    }

    public String getClasification(String lang)
    {
        return getSemanticObject().getProperty(swpdoc_clasification, null, lang);
    }

    public String getDisplayClasification(String lang)
    {
        return getSemanticObject().getLocaleProperty(swpdoc_clasification, lang);
    }

    public void setClasification(String clasification, String lang)
    {
        getSemanticObject().setProperty(swpdoc_clasification, clasification, lang);
    }

/**
* Gets the Formula property
* @return String with the Formula
*/
    public String getFormula()
    {
        return getSemanticObject().getProperty(swpdoc_formula);
    }

/**
* Sets the Formula property
* @param value long with the Formula
*/
    public void setFormula(String value)
    {
        getSemanticObject().setProperty(swpdoc_formula, value);
    }
}
