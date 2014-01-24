package org.semanticwb.process.documentation;

/**
 * SemanticWebBuilder es una plataforma para el desarrollo de portales y
 * aplicaciones de integración, colaboración y conocimiento, que gracias al uso
 * de tecnología semántica puede generar contextos de información alrededor de
 * algún tema de interés o bien integrar información y aplicaciones de
 * diferentes fuentes, donde a la información se le asigna un significado, de
 * forma que pueda ser interpretada y procesada por personas y/o sistemas, es
 * una creación original del Fondo de Información y Documentación para la
 * Industria INFOTEC, cuyo registro se encuentra actualmente en trámite.
 * 
* INFOTEC pone a su disposición la herramienta SemanticWebBuilder a través de
 * su licenciamiento abierto al público (‘open source’), en virtud del cual,
 * usted podrá usarlo en las mismas condiciones con que INFOTEC lo ha diseñado y
 * puesto a su disposición; aprender de él; distribuirlo a terceros; acceder a
 * su código fuente y modificarlo, y combinarlo o enlazarlo con otro software,
 * todo ello de conformidad con los términos y condiciones de la LICENCIA
 * ABIERTA AL PÚBLICO que otorga INFOTEC para la utilización del
 * SemanticWebBuilder 4.0.
 * 
* INFOTEC no otorga garantía sobre SemanticWebBuilder, de ninguna especie y
 * naturaleza, ni implícita ni explícita, siendo usted completamente responsable
 * de la utilización que le dé y asumiendo la totalidad de los riesgos que
 * puedan derivar de la misma.
 * 
* Si usted tiene cualquier duda o comentario sobre SemanticWebBuilder, INFOTEC
 * pone a su disposición la siguiente dirección electrónica:
 * http://www.semanticwebbuilder.org
 *
 */
import java.io.File;
import org.semanticwb.SWBPlatform;
import org.semanticwb.SWBUtils;
import org.semanticwb.codegen.CodeGenerator;
import org.semanticwb.codegen.CodeGeneratorException;

/**
 *
 * @author serch
 */
public class CodeGeneratorRural {

    public static void main(String[] args) {
        new CodeGeneratorRural().codeGen(args);
    }

    public void codeGen(String[] args) {
        String base = SWBUtils.getApplicationPath();
        SWBPlatform.createInstance();
        File f = new File(base + "/../../");
        SWBPlatform.getSemanticMgr().addBaseOntology(base + "/../../../../SWB4/swb/web/WEB-INF/owl/swb.owl");
        SWBPlatform.getSemanticMgr().addBaseOntology(base + "/../../../../SWB4/swbp/WEB-INF/owl/ext/swp.owl");
        SWBPlatform.getSemanticMgr().addBaseOntology(base + "/../../src/org/semanticwb/process/documentation/swpdoc.owl");
        SWBPlatform.getSemanticMgr().loadBaseVocabulary();
        SWBPlatform.getSemanticMgr().getOntology().rebind();

        try {
            String path = getClass().getResource("/").getPath().replaceAll("%20", " ");
            File dir = new File(path + "../../src");
            CodeGenerator codeGeneration = new CodeGenerator();
            codeGeneration.generateCode("swpdoc", false, dir);
            System.out.println("Generación de clases completa");
        } catch (CodeGeneratorException cge) {
            cge.printStackTrace();
        }
    }
}
