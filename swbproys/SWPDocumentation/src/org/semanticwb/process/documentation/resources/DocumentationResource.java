package org.semanticwb.process.documentation.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.semanticwb.Logger;
import org.semanticwb.SWBPlatform;
import org.semanticwb.SWBPortal;
import org.semanticwb.SWBUtils;
import org.semanticwb.model.Descriptiveable;
import org.semanticwb.process.documentation.model.Activity;
import org.semanticwb.process.documentation.model.Definition;
import org.semanticwb.process.documentation.model.DocumentSectionInstance;
import org.semanticwb.process.documentation.model.DocumentationInstance;
import org.semanticwb.process.documentation.model.Format;
import org.semanticwb.process.documentation.model.FreeText;
import org.semanticwb.process.documentation.model.Model;
import org.semanticwb.process.documentation.model.Reference;
import org.semanticwb.process.documentation.model.SectionElement;
import org.semanticwb.process.documentation.model.SectionElementRef;
import org.semanticwb.model.FormValidateException;
import org.semanticwb.model.Resource;
import org.semanticwb.model.SWBComparator;
import org.semanticwb.model.Sortable;
import org.semanticwb.model.User;
import org.semanticwb.model.VersionInfo;
import org.semanticwb.model.WebPage;
import org.semanticwb.platform.SemanticClass;
import org.semanticwb.platform.SemanticObject;
import org.semanticwb.platform.SemanticProperty;
import org.semanticwb.portal.SWBFormMgr;
import org.semanticwb.portal.api.GenericAdmResource;
import org.semanticwb.portal.api.SWBActionResponse;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;
import org.semanticwb.portal.api.SWBResourceModes;
import org.semanticwb.portal.api.SWBResourceURL;
import org.semanticwb.portal.api.SWBResourceURLImp;
import org.semanticwb.process.documentation.model.DocumentSection;
import org.semanticwb.process.documentation.model.Documentation;
import org.semanticwb.process.documentation.model.Referable;
import static org.semanticwb.process.documentation.resources.UserDocumentationResource.getProcessElementModel;
import org.semanticwb.process.model.GraphicalElement;
import org.semanticwb.process.model.ProcessElement;
import org.semanticwb.process.model.ProcessSite;
import org.semanticwb.process.model.RepositoryDirectory;
import org.semanticwb.process.model.RepositoryElement;
import org.semanticwb.process.model.RepositoryFile;
import org.semanticwb.process.model.RepositoryURL;
import org.semanticwb.process.resources.ProcessFileRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Recurso que gestiona la vista de la documentación de procesos.
 *
 * @author carlos.alvarez
 */
public class DocumentationResource extends GenericAdmResource {

    private final Logger log = SWBUtils.getLogger(DocumentationResource.class);
    public final static String VIEW_DTB = "vdtb";
    public final static String ADD_SECTION = "as";
    public final static String GENERATE_VERSION = "gv";
    public final static String SAVE_VERSION = "sv";
    public final static String ADD_SECTION_ACTIVITY = "asa";
    public final static String ADD_SECTION_WITH_FILE = "aswf";
    public final static String ADD_FILE = "af";
    public final static String UPDATE_SURI = "us";
    public final static String EDIT_SECTION_INSTANCE = "esi";
    public final static String EDIT_SECTION_WITH_FILE = "eswf";
    public final static String REMOVE_SECTION_INSTANCE = "rsi";
    public final static String SAVE_FREE_TEXT = "sft";
    public final static String SAVE_FREE_TEXT_DESCRIPTION = "sftd";
    public final static String VIEW_LOG = "vloc";

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String suri = request.getParameter("suri");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentation.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(path);

        try {
            request.setAttribute("paramRequest", paramRequest);
            if (suri != null && suri.length() > 0) {
                request.setAttribute("suri", suri);
            }
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doView, " + path, ex);
        }
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        String mode = paramRequest.getMode();
        if (mode.equals(ADD_SECTION)) {
            doAddSection(request, response, paramRequest);
        } else if (mode.equals(EDIT_SECTION_INSTANCE)) {
            doEditSection(request, response, paramRequest);
        } else if (mode.equals(UPDATE_SURI)) {
            doUpdateSuri(request, response, paramRequest);
        } else if (mode.equals(VIEW_LOG)) {
            doViewLog(request, response, paramRequest);
        } else if (mode.equals(SAVE_VERSION)) {
            doSaveVersion(request, response, paramRequest);
        } else if (mode.equals(SAVE_FREE_TEXT_DESCRIPTION)) {
            doEditDescription(request, response, paramRequest);
        } else {
            super.processRequest(request, response, paramRequest);
        }
    }

    @Override
    public void processAction(HttpServletRequest request, SWBActionResponse response) throws SWBResourceException, IOException {
        String action = response.getAction();
        String suri = request.getParameter("surio") != null ? request.getParameter("surio") : "";
        ProcessElement processElement = (ProcessElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(suri);
        ProcessSite model = null;
        if (processElement != null) {
            model = processElement.getProcessSite();
        }
        if (action.equals(GENERATE_VERSION)) {
            String uridi = request.getParameter("uridi") != null ? request.getParameter("uridi") : "";
            suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            String uridsi = "";
            if (!suri.equals("")) {
                DocumentationInstance di = (DocumentationInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridi);
                uridsi = di.getDocumnetSectionInstance().getURI();
                Iterator<Documentation> itdoc = SWBComparator.sortSortableObject(Documentation.ClassMgr.listDocumentationByProcess(di.getProcessRef()));
                while (itdoc.hasNext()) {
                    Documentation documentation = itdoc.next();
                    documentation.setActualVersion(false);
                }
                Documentation doc = Documentation.ClassMgr.createDocumentation(response.getWebPage().getWebSite());
                String basePath = SWBPortal.getWorkPath() + "/models/" + response.getWebPage().getWebSiteId() + "/Resource/" + di.getProcessRef().getId() + "/" + doc.getId() + "/";
                File modeler = new File(basePath);
                if (!modeler.exists()) {
                    modeler.mkdirs();
                }
                doc.setProcess(di.getProcessRef());
                doc.setActualVersion(true);
                String title = request.getParameter("titlee") != null ? request.getParameter("titlee") : "";
                String description = request.getParameter("descriptionn") != null ? request.getParameter("descriptionn") : "";
                doc.setTitle(!title.equals("") ? title : "" + di.getProcessRef().getTitle() + " - " + doc.getId());
                doc.setDescription(description);
                File index = new File(basePath + di.getProcessRef().getTitle() + ".html");
                FileOutputStream out = new FileOutputStream(index);
                try {
                    org.w3c.dom.Document dom = getDom(suri, response.getUser().getLanguage(), response, request, doc);
                    if (dom != null) {
//                        String tlpPath = "/swbadmin/jsp/process/documentation/documentation.xsl";
                        String tlpPath = "/work/models/" + response.getWebPage().getWebSiteId() + "/jsp/documentation/documentation.xsl";
                        javax.xml.transform.Templates tpl = SWBUtils.XML.loadTemplateXSLT(new FileInputStream(SWBUtils.getApplicationPath() + tlpPath));
                        out.write(SWBUtils.XML.transformDom(tpl, dom).getBytes());
                    }
                    out.write(modals.getBytes());
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    log.error(e);
                }
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", uridsi);
            response.setRenderParameter("newVersion", "true");
            response.setMode(UPDATE_SURI);
////            response.setRenderParameter("suri", suri);
//            response.setRenderParameter("suri", suri);
////            response.setRenderParameter("sptab", uridsi);
////            response.setMode(UPDATE_SURI);

        } else if (action.equals(ADD_SECTION)) {
            String uridsi = request.getParameter("uridsi") != null ? request.getParameter("uridsi") : "";
            String scls = request.getParameter("scls") != null ? request.getParameter("scls") : "";
            DocumentSectionInstance dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridsi);
            SemanticClass cls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(scls);
            if (cls != null && dsi != null) {
                SWBFormMgr forMgr = new SWBFormMgr(cls, model.getSemanticObject(), null);
                forMgr.clearProperties();
                Iterator<SemanticProperty> itsp = cls.listProperties();
                while (itsp.hasNext()) {
                    SemanticProperty sp = itsp.next();
                    if (sp.getDisplayProperty() != null && !sp.getPropId().equals(Sortable.swb_index.getPropId())) {
                        forMgr.addProperty(sp);
                    }
                }
                try {
                    SemanticObject semobj = forMgr.processForm(request);
                    SectionElement se = (SectionElement) semobj.createGenericInstance();
                    String index = request.getParameter("index") != null ? request.getParameter("index") : "";
                    if (!index.equals("")) {
                        try {
                            se.setIndex(Integer.parseInt(index));
                        } catch (NumberFormatException ex) {
                            se.setIndex(0);
                        }
                    } else {
                        se.setIndex(0);
                    }
                    se.setParentSection(dsi.getSecTypeDefinition());
                    dsi.addDocuSectionElementInstance(se);
                } catch (FormValidateException ex) {
                    log.error("Error on processAction, " + action, ex);
                }
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", uridsi);
            response.setMode(UPDATE_SURI);
        } else if (action.equals(ADD_SECTION_WITH_FILE)) {
            String configData = "";
            String uridsi = "";
            String scls = "";
            DocumentSectionInstance dsi = null;
            InputStream is = null;
            String name = "";
            //Used by Fortmat 
            String title = "";
            String description = "";
            String documentType = "";
            String keyWords = "";
            String relatedSubjects = "";
            //Used by Reference
            String typeReference = "";
            String number = "";
            String prefix = "";
            String index = "";
            String lfile = "";
            String hftype = "";
            long size = 0;
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    for (FileItem item : multiparts) {
                        if (!item.isFormField()) {
                            is = item.getInputStream();
                            name = SWBUtils.TEXT.decode(item.getName(), "UTF-8").trim();
                            size = item.getSize();
                        } else {
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();
                            //Format
                            if (fieldName.equals("title")) {
                                title = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("description")) {
                                description = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("documentType")) {
                                documentType = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("keyWords")) {
                                keyWords = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("relatedSubjects")) {
                                relatedSubjects = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("typeReference")) {
                                typeReference = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("number")) {
                                number = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("prefix")) {
                                prefix = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (item.getFieldName().equals("surio")) {
                                suri = item.getString();
                            }
                            if (item.getFieldName().equals("scls")) {
                                scls = item.getString();
                            }
                            if (item.getFieldName().equals("index")) {
                                index = item.getString();
                            }
                            if (item.getFieldName().equals("hftype")) {
                                hftype = item.getString();
                            }
                            if (item.getFieldName().equals("lfile")) {
                                lfile = item.getString();
                            }
                            if (item.getFieldName().equals("configData")) {
                                configData = item.getString();
                            }
                            if (item.getFieldName().equals("uridsi")) {
                                uridsi = item.getString();
                                dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridsi);
                            }
                        }
                    }
                    if (dsi != null) {
                        SemanticClass cls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(scls);
                        processElement = (ProcessElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(suri);
                        model = processElement.getProcessSite();
                        Referable referable = null;
                        //Type Format
                        if (cls.getClassId().equals(Format.sclass.getClassId())) {
                            Format format = Format.ClassMgr.createFormat(model);
                            format.setTitle(title);
                            format.setDescription(description);
                            format.setDocumentType(documentType);
                            format.setKeyWords(keyWords);
                            format.setRelatedSubjects(relatedSubjects);
                            if (!index.equals("")) {
                                try {
                                    format.setIndex(Integer.parseInt(index));
                                } catch (NumberFormatException ex) {
                                    format.setIndex(0);
                                }
                            } else {
                                format.setIndex(0);
                            }
                            dsi.addDocuSectionElementInstance(format);
                            format.setParentSection(dsi.getSecTypeDefinition());
                            referable = (Referable) format;
                        }
                        //Type Reference
                        if (cls.getClassId().equals(Reference.sclass.getClassId())) {
                            Reference reference = Reference.ClassMgr.createReference(model);
                            reference.setTypeReference(typeReference);
                            reference.setNumber(number);
                            reference.setTitle(title);
                            reference.setPrefix(prefix);
                            reference.setDescription(description);
                            if (!index.equals("")) {
                                try {
                                    reference.setIndex(Integer.parseInt(index));
                                } catch (NumberFormatException ex) {
                                    reference.setIndex(0);
                                }
                            } else {
                                reference.setIndex(0);
                            }
                            dsi.addDocuSectionElementInstance(reference);
                            reference.setParentSection(dsi.getSecTypeDefinition());
                            referable = (Referable) reference;
                        }
                        //Type Definition
                        if (cls.getClassId().equals(Definition.sclass.getClassId())) {
                            Definition definition = Definition.ClassMgr.createDefinition(model);
                            definition.setNumber(number);
                            definition.setPrefix(prefix);
                            definition.setTitle(title);
                            definition.setDescription(description);
                            if (!index.equals("")) {
                                try {
                                    definition.setIndex(Integer.parseInt(index));
                                } catch (NumberFormatException ex) {
                                    definition.setIndex(0);
                                }
                            } else {
                                definition.setIndex(0);
                            }
                            dsi.addDocuSectionElementInstance(definition);
                            definition.setParentSection(dsi.getSecTypeDefinition());
                            referable = (Referable) definition;
                        }
                        RepositoryDirectory rd = (RepositoryDirectory) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(configData);
//                            if (rd != null && size > 0) {
                        if (rd != null) {
                            if (hftype.equals("file")) {
                                RepositoryFile rf = RepositoryFile.ClassMgr.createRepositoryFile(rd.getProcessSite());
                                referable.setRefRepository(rf);
                                rf.setRepositoryDirectory(rd);
                                rf.setTitle(!title.trim().equals("") ? title.trim() : name.substring(0, name.lastIndexOf(".")));
                                rf.storeFile(name, is, null, Boolean.TRUE, null);
                                rf.setDescription(description);
                            } else { // is url
                                RepositoryURL repoUrl = RepositoryURL.ClassMgr.createRepositoryURL(rd.getProcessSite());
                                repoUrl.setRepositoryDirectory(rd);
                                referable.setRefRepository(repoUrl);
                                repoUrl.setTitle(!title.trim().equals("") ? title.trim() : name.substring(0, name.lastIndexOf(".")));
                                repoUrl.setDescription(description);
                                User usr = response.getUser();
                                repoUrl.setOwnerUserGroup(usr.getUserGroup());
                                repoUrl.storeFile(lfile.startsWith("http://") ? lfile : "http://" + lfile, null, Boolean.TRUE, null);
                            }
                        }
                    }
                    response.setRenderParameter("suri", suri);
                    response.setRenderParameter("sptab", uridsi);
                    response.setMode(UPDATE_SURI);
                } catch (Exception ex) {
                    log.error("Error on processAction, " + action, ex);
                }
            }
        } else if (action.equals(ADD_SECTION_ACTIVITY)) {
            suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            String uridi = request.getParameter("uridi") != null ? request.getParameter("uridi") : "";
            String uridsi = request.getParameter("uridsi") != null ? request.getParameter("uridsi") : "";
            String urise = request.getParameter("urise") != null ? request.getParameter("urise") : "";
            org.semanticwb.process.model.Process process = (org.semanticwb.process.model.Process) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(suri);
            DocumentationInstance di = (DocumentationInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridi);
            SectionElement se = (SectionElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urise);
            if (di != null && se != null && process != null) {
                Activity act = (Activity) se.getSemanticObject().createGenericInstance();
                model = process.getProcessSite();
                Map map = DocumentationInstance.getDocumentSectionInstance(di);
                ArrayList arr = DocumentationInstance.listDocumentSections(di.getDocTypeDefinition(), map, di, process.getProcessSite(), (Activity.sclass.getClassId() + " " + Model.sclass.getClassId() + " " + FreeText.sclass.getClassId()));
                for (int j = 0; j < arr.size(); j++) {
                    DocumentSectionInstance dsit = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(arr.get(j).toString());
                    Iterator<SectionElement> itse = SWBComparator.sortSortableObject(dsit.listDocuSectionElementInstances());
                    while (itse.hasNext()) {
                        SectionElement set = itse.next();
                        String uriset = set.getURI();

                        SectionElementRef ser = null;

                        Iterator<SectionElementRef> itser = SWBComparator.sortSortableObject(act.listSectionElementRefs());
                        while (itser.hasNext()) {
                            SectionElementRef sert = itser.next();
                            if (sert.getSectionElement().equals(set)) {
                                ser = sert;
                                break;
                            }
                        }
                        if (ser == null && request.getParameter(uriset) != null) {
                            ser = SectionElementRef.ClassMgr.createSectionElementRef(model);
                            ser.setSectionElement(set);
                            ser.setActivity(act);
                            act.addSectionElementRef(ser);
                        }
                        if (ser != null && request.getParameter(uriset) == null) {
                            act.removeSectionElementRef(ser);
                            ser.remove();
                        }
                    }
                }
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", uridsi);
            response.setMode(UPDATE_SURI);
        } else if (action.equals(EDIT_SECTION_INSTANCE)) {
            String urisei = request.getParameter("urisei") != null ? request.getParameter("urisei") : "";
            SectionElement sei = (SectionElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urisei);
            SemanticClass cls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(request.getParameter("scls"));
            if (sei != null && cls != null) {
                SWBFormMgr forMgr = new SWBFormMgr(sei.getSemanticObject(), null, SWBFormMgr.MODE_EDIT);
                forMgr.clearProperties();
                Iterator<SemanticProperty> itsp = cls.listProperties();
                while (itsp.hasNext()) {
                    SemanticProperty sp = itsp.next();
                    if (sp.getDisplayProperty() != null && !sp.getPropId().equals(Sortable.swb_index.getPropId())) {
                        forMgr.addProperty(sp);
                    }
                }
                try {
                    SemanticObject semobj = forMgr.processForm(request);
                    SectionElement se = (SectionElement) semobj.createGenericInstance();
                    String index = request.getParameter("index") != null ? request.getParameter("index") : "";
                    if (!index.equals("")) {
                        try {
                            se.setIndex(Integer.parseInt(index));
                        } catch (NumberFormatException ex) {
                        }
                    } else {
                        se.setIndex(0);
                    }
                } catch (FormValidateException ex) {
                    log.error("Error on processAction, " + action, ex);
                }
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", request.getParameter("uridsi"));
            response.setMode(UPDATE_SURI);
        } else if (action.equals(EDIT_SECTION_WITH_FILE)) {
            String repElement = "";
            String configData = "";
            String urisei = "";
            String uridsi = "";
            String scls = "";
            DocumentSectionInstance dsi = null;
            //Used by Fortmat 
            String title = "";
            String description = "";
            String documentType = "";
            String keyWords = "";
            String relatedSubjects = "";
            //Used by Reference
            String typeReference = "";
            String number = "";
            String prefix = "";
            String index = "";
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    for (FileItem item : multiparts) {
                        if (item.isFormField()) {
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();
                            //Format
                            if (fieldName.equals("title")) {
                                title = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("description")) {
                                description = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("documentType")) {
                                documentType = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("keyWords")) {
                                keyWords = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("relatedSubjects")) {
                                relatedSubjects = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("typeReference")) {
                                typeReference = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("number")) {
                                number = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (fieldName.equals("prefix")) {
                                prefix = SWBUtils.TEXT.decode(fieldValue, "UTF-8").trim();
                            }
                            if (item.getFieldName().equals("surio")) {
                                suri = item.getString();
                            }
                            if (item.getFieldName().equals("scls")) {
                                scls = item.getString();
                            }
                            if (item.getFieldName().equals("urisei")) {
                                urisei = item.getString();
                            }
                            if (item.getFieldName().equals("index")) {
                                index = item.getString();
                            }
                            if (item.getFieldName().equals("configData")) {
                                configData = item.getString();
                            }
                            if (item.getFieldName().equals("repElement")) {
                                repElement = item.getString();
                            }
                            if (item.getFieldName().equals("uridsi")) {
                                uridsi = item.getString();
                                dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridsi);
                            }
                        }
                    }
                    if (dsi != null) {
                        String urirf = "";
                        SemanticClass cls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(scls);
                        Referable ref = (Referable) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urisei);
                        RepositoryElement repositoryElement = (RepositoryElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(repElement);
                        RepositoryDirectory rd = (RepositoryDirectory) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(configData);
                        if (repositoryElement != null && rd != null) {
                            repositoryElement.setRepositoryDirectory(rd);
//                            ref.setRefRepository(repositoryElement);
                        }
                        //Format
                        if (cls.getClassId().equals(Format.sclass.getClassId())) {
                            Format format = (Format) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urisei);
                            format.setTitle(title);
                            format.setDescription(description);
                            format.setDocumentType(documentType);
                            format.setKeyWords(keyWords);
                            if (!index.equals("")) {
                                try {
                                    format.setIndex(Integer.parseInt(index));
                                } catch (NumberFormatException ex) {
                                }
                            } else {
                                format.setIndex(0);
                            }
                            format.setRelatedSubjects(relatedSubjects);
                            if (format.getRefRepository() != null) {
                                urirf = format.getRefRepository().getURI();
                            }
                        }
                        //Format
                        if (cls.getClassId().equals(Reference.sclass.getClassId())) {
                            Reference reference = (Reference) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urisei);
                            reference.setTypeReference(typeReference);
                            reference.setNumber(number);
                            reference.setTitle(title);
                            reference.setPrefix(prefix);
                            reference.setDescription(description);
                            if (!index.equals("")) {
                                try {
                                    reference.setIndex(Integer.parseInt(index));
                                } catch (NumberFormatException ex) {
                                }
                            } else {
                                reference.setIndex(0);
                            }
                            if (reference.getRefRepository() != null) {
                                urirf = reference.getRefRepository().getURI();

                            }
                        }
                        //Definition
                        if (cls.getClassId().equals(Definition.sclass.getClassId())) {
                            Definition definition = (Definition) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urisei);
                            definition.setNumber(number);
                            definition.setPrefix(prefix);
                            definition.setTitle(title);
                            definition.setDescription(description);
                            if (!index.equals("")) {
                                try {
                                    definition.setIndex(Integer.parseInt(index));
                                } catch (NumberFormatException ex) {
                                }
                            } else {
                                definition.setIndex(0);
                            }
                            if (definition.getRefRepository() != null) {
                                urirf = definition.getRefRepository().getURI();
                            }
                        }
                        RepositoryElement rf = (RepositoryElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urirf);
                        if (rf != null) {
                            rf.setTitle(!title.trim().equals("") ? title.trim() : rf.getTitle());
                            rf.setDescription(description);
                        }
                    }
                } catch (Exception ex) {
                    log.error("Error on processAction, " + action, ex);
                    ex.printStackTrace();
                }
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", uridsi);
            response.setMode(UPDATE_SURI);
        } else if (action.equals(REMOVE_SECTION_INSTANCE)) {
            suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            String urisei = request.getParameter("urisei") != null ? request.getParameter("urisei") : "";
            urisei = urisei.substring(0, urisei.indexOf("|"));
            String uridsi = request.getParameter("urisei") != null ? request.getParameter("urisei") : "";
            uridsi = uridsi.substring(uridsi.lastIndexOf("|") + 1, uridsi.length());
            SectionElement sei = (SectionElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urisei);
            DocumentSectionInstance dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridsi);
            if (sei != null && dsi != null) {
                dsi.removeDocuSectionElementInstance(sei);
                Iterator<SectionElementRef> itser = SWBComparator.sortSortableObject(SectionElementRef.ClassMgr.listSectionElementRefBySectionElement(sei));
                while (itser.hasNext()) {
                    SectionElementRef ser = itser.next();
                    ser.remove();
                }
                sei.remove();
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", uridsi);
            response.setMode(UPDATE_SURI);
        } else if (action.equals(SAVE_FREE_TEXT)) {
            suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            String urift = request.getParameter("urift") != null ? request.getParameter("urift") : "";
            String data = request.getParameter("data") != null ? request.getParameter("data") : "";
            String uridsi = request.getParameter("uridsi") != null ? request.getParameter("uridsi") : "";

            if (!suri.equals("") && !urift.equals("")) {
                FreeText ft = (FreeText) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urift);
                if (ft != null) {
                    ft.setText(data);
                }
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", uridsi);
            response.setMode(UPDATE_SURI);
        } else if (action.equals(SAVE_FREE_TEXT_DESCRIPTION)) {
            suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            String uriact = request.getParameter("urift") != null ? request.getParameter("urift") : "";
            String data = request.getParameter("data") != null ? request.getParameter("data") : "";
            String uridsi = request.getParameter("uridsi") != null ? request.getParameter("uridsi") : "";
            Activity activity = (Activity) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uriact);
            if (activity != null) {
                activity.setDescription(data);
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", uridsi);
            response.setMode(UPDATE_SURI);
        } else {
            super.processAction(request, response); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public void doAddSection(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String suri = request.getParameter("suri");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentationEdit.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            if (suri != null && suri.length() > 0) {
                request.setAttribute("suri", suri);
            }
            if (request.getParameter("uridi") != null) {
                request.setAttribute("uridi", request.getParameter("uridi"));
            }
            if (request.getParameter("uridsi") != null) {
                request.setAttribute("uridsi", request.getParameter("uridsi"));
            }
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doAddSection, " + path, ex);
        }
    }

    public void doEditSection(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String suri = request.getParameter("suri");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentationEdit.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            if (suri != null && suri.length() > 0) {
                request.setAttribute("suri", suri);
            }
            if (request.getParameter("urisei") != null) {
                request.setAttribute("urisei", request.getParameter("urisei"));
            }
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doEditSection, " + path, ex);
        }
    }

    public void doUpdateSuri(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        DocumentSectionInstance dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(request.getParameter("sptab"));
        response.getWriter().print(request.getParameter("suri") + "|" + dsi.getId());
    }

    public void doViewLog(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String suri = request.getParameter("suri");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/logView.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            if (suri != null && suri.length() > 0) {
                request.setAttribute("suri", suri);
            }
            if (request.getParameter("uridt") != null) {
                request.setAttribute("uridt", request.getParameter("uridt"));
            }
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doViewLog, " + path, ex);
        }
    }

    public void doSaveVersion(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/saveVersion.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("suri", request.getParameter("suri"));
            request.setAttribute("uridi", request.getParameter("uridi"));
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doSaveVersion, " + path, ex);
        }
    }

    public void doEditDescription(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String suri = request.getParameter("suri");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/editDescription.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            if (suri != null && suri.length() > 0) {
                request.setAttribute("suri", suri);
            }
            if (request.getParameter("urisei") != null) {
                request.setAttribute("urisei", request.getParameter("urisei"));
            }
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doEditDescription, " + path, ex);
        }
    }
    public static Comparator activityComparator = new Comparator() {
        @Override
        public int compare(Object t, Object t1) {
            int it = 0;
            int it1 = 0;
            GraphicalElement ge = ((Activity) t).getActivityRef().getProcessActivity();
            it = ge.getIndex();
            int ret = 0;
            GraphicalElement ge1 = ((Activity) t1).getActivityRef().getProcessActivity();
            it1 = ge1.getIndex();
            if (it > it1) {
                ret = 1;
            } else {
                ret = -1;
            }
            return ret;
        }
    };
    public static Comparator typeSectionComparator = new Comparator() {
        @Override
        public int compare(Object t, Object t1) {
            int it = 0;
            int it1 = 0;
            DocumentSection ds = ((DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(t.toString())).getSecTypeDefinition();
            it = ds.getIndex();
            int ret = 0;
            DocumentSection ds1 = ((DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(t1.toString())).getSecTypeDefinition();
            it1 = ds1.getIndex();
            if (it > it1) {
                ret = 1;
            } else {
                ret = -1;
            }
            return ret;
        }
    };
    String modals = "";

    public Document getDom(String suri, String lang, SWBActionResponse paramRequest, HttpServletRequest request, Documentation documentation) throws TransformerConfigurationException, TransformerException, SWBResourceException, FileNotFoundException, IOException {
        org.w3c.dom.Document document = SWBUtils.XML.getNewDocument();
        org.semanticwb.process.model.Process process = (org.semanticwb.process.model.Process) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(suri);
        modals = "";
        if (process != null) {
            boolean hasModel = false;
            Element root = document.createElement("root");
            root.setAttribute("title", process.getTitle());
            document.appendChild(root);
            DocumentationInstance docInstance = (DocumentationInstance) SWBComparator.sortByCreated(DocumentationInstance.ClassMgr.listDocumentationInstanceByProcessRef(process), true).next();
            Map map = DocumentationInstance.getDocumentSectionInstance(docInstance);
            ArrayList arr = DocumentationInstance.listDocumentSections(docInstance.getDocTypeDefinition(), map, docInstance, docInstance.getProcessRef().getProcessSite(), "");
            for (int j = 0; j < arr.size(); j++) {
                DocumentSectionInstance dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(arr.get(j).toString());
                SemanticClass scls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(dsi.getSecTypeDefinition().getSectionType().getURI());

                if (!scls.getClassId().equals(Model.sclass.getClassId())) {
                    root.appendChild(document.createTextNode("\n\t"));
                    Element section = document.createElement("section");
                    section.setAttribute("uri", dsi.getURI());
                    section.setAttribute("url", scls.getName() + dsi.getId());
                    section.setAttribute("className", scls.getName());
                    section.setAttribute("title", dsi.getSecTypeDefinition().getTitle());
                    root.appendChild(section);
                    root.appendChild(document.createTextNode("\n\t"));

                    String[] visibleProps = dsi.getSecTypeDefinition().getVisibleProperties().split("\\|");
                    Iterator<SectionElement> iterator = SWBComparator.sortSortableObject(dsi.listDocuSectionElementInstances());
                    int count = 1;
                    while (iterator.hasNext()) {
                        SectionElement se = iterator.next();
                        section.appendChild(document.createTextNode("\n\t\t"));
                        Element instance = document.createElement("instance");
                        instance.setAttribute("id", se.getId());
                        instance.setAttribute("uri", se.getURI());
                        instance.setAttribute("className", scls.getName());
                        instance.setAttribute("count", count + "");
                        count++;
                        instance.setAttribute("type", dsi.getSecTypeDefinition().getTitle());
                        section.appendChild(instance);
                        if (!scls.getClassId().equals(FreeText.sclass.getClassId())
                                && !scls.getClassId().equals(org.semanticwb.process.documentation.model.Activity.sclass.getClassId())) {
                            for (int i = 0; i < visibleProps.length; i++) {
                                String props = visibleProps[i];
                                instance.appendChild(document.createTextNode("\n\t\t\t"));
                                Element property = document.createElement("property");
                                String title = props.substring(0, props.indexOf(";"));
                                String propid = props.substring((props.indexOf(";") + 1), props.length());
                                SemanticProperty prop = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticPropertyById(propid);
                                property.setAttribute("propid", propid);
                                property.setAttribute("type", dsi.getSecTypeDefinition().getTitle());
                                String value = "";
                                if (prop.getPropId().equals(Referable.swpdoc_file.getPropId())) {
                                    Referable referable = (Referable) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(se.getURI());
                                    if (referable != null && referable.getRefRepository() != null && referable.getRefRepository().getRepositoryDirectory() != null) {
                                        RepositoryDirectory repositoryDirectory = referable.getRefRepository().getRepositoryDirectory();
                                        if (repositoryDirectory != null) {
                                            Resource resource = null;
                                            Iterator<Resource> ire = repositoryDirectory.listResources();
                                            while (ire.hasNext()) {
                                                resource = ire.next();
                                                if (resource.getResourceType().getResourceClassName().equals(ProcessFileRepository.class.getName())) {
                                                    break;
                                                }
                                            }
                                            if (resource != null) {
                                                SWBResourceURL urlDownload = new SWBResourceURLImp(request, resource, (WebPage) repositoryDirectory, SWBResourceModes.UrlType_RENDER);
                                                urlDownload.setMode(ProcessFileRepository.MODE_GETFILE);
                                                urlDownload.setCallMethod(SWBResourceURL.Call_DIRECT);
                                                urlDownload.setParameter("fid", referable.getRefRepository().getId());
                                                urlDownload.setParameter("verNum", referable.getRefRepository().getLastVersion().getVersionNumber() + "");
                                                RepositoryElement re = (RepositoryElement) referable.getRefRepository();
                                                VersionInfo vi = re.getLastVersion();
                                                if (re instanceof RepositoryFile) {
                                                    value = "<a href=\"" + urlDownload + "\">" + referable.getRefRepository().getTitle() + " <i class=\"fa fa-download\"></i></a>";
                                                } else if (re instanceof RepositoryURL) {
                                                    value = "<a target=\"_blank\" href=\"" + vi.getVersionFile() + "\">" + referable.getRefRepository().getTitle() + " <i class=\"fa fa-external-link\"></i></a>";
                                                }
                                                property.appendChild(document.createTextNode("\n\t\t\t" + value + "\n\t\t\t"));
                                            }
                                        }
                                    }
                                } else {
                                    value = se.getSemanticObject().getProperty(prop);
                                    property.appendChild(document.createTextNode("\n\t\t\t" + (value != null ? value + "\n" : "") + "\t\t\t"));
                                }
                                property.setAttribute("title", title);
                                instance.appendChild(property);
                                if (i < visibleProps.length) {
                                    instance.appendChild(document.createTextNode("\t\t"));
                                }
                            }
                            instance.appendChild(document.createTextNode("\n\t\t"));
                        } else {//Si es Activity o FreeText

                            //SWBResourceURL urlDialog = paramRequest.getRenderUrl().setCallMethod(SWBResourceURL.Call_DIRECT).setParameter("suri", suri);
                            instance.appendChild(document.createTextNode("\n\t\t\t"));
                            Element property = document.createElement("property");
                            if (scls.getClassId().equals(Activity.sclass.getClassId())) {
                                property.setAttribute("propid", Descriptiveable.swb_title.getPropId());
                                property.setAttribute("type", dsi.getSecTypeDefinition().getTitle());
                                Activity act = (Activity) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(se.getURI());
                                Iterator<SectionElementRef> itser = SWBComparator.sortSortableObject(act.listSectionElementRefs());
                                Iterator<SemanticObject> itso = SWBComparator.sortSortableObject(paramRequest.getWebPage().getWebSite().getSemanticModel().listSubjects(SectionElementRef.swpdoc_activity, act.getSemanticObject()));
                                List<SemanticObject> list = new ArrayList<SemanticObject>();
                                while (itso.hasNext()) {
                                    SemanticObject semObj = itso.next();
                                    list.add(semObj);
                                }
                                itso = list.iterator();
                                Map mapST = new HashMap();
                                while (itso.hasNext()) {
                                    SemanticObject semObj = itso.next();
                                    SectionElementRef serSemObj = (SectionElementRef) semObj.createGenericInstance();
                                    if (serSemObj.getSectionElement() != null && serSemObj.getSectionElement().getParentSection() != null) {//Verificar registros null
                                        if (mapST.containsKey(serSemObj.getSectionElement().getParentSection())) {
                                            mapST.put(serSemObj.getSectionElement().getParentSection(), mapST.get(serSemObj.getSectionElement().getParentSection()) + "|" + semObj);
                                        } else {
                                            mapST.put(serSemObj.getSectionElement().getParentSection(), semObj);
                                        }
                                    }
                                }
                                Iterator it = mapST.entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry e = (Map.Entry) it.next();
                                    SemanticObject semObj = SemanticObject.createSemanticObject(e.getKey().toString());
                                    list.add(semObj);
                                }
                                String value = ""
                                        + "<div class=\"row\">\n"
                                        + "     <div class=\"col-lg-9 col-md-9 col-sm-9\">\n"
                                        + "         <h4 class=\"list-group-item-heading\">" + act.getActivityRef().getProcessActivity().getTitle() + "</h4>\n"
                                        + "         <p>" + act.getDescription() + "</p>\n"
                                        + "     </div>\n"
                                        + "     <div class=\"col-lg-3 col-md-3 col-sm-3\">\n";
                                if (itser.hasNext()) {

                                    value += "         <button class=\"btn btn-success pull-right collapsed\" data-toggle=\"collapse\" data-parent=\"#panel" + se.getId() + "\" href=\"#seact" + se.getId() + "\">\n"
                                            + "             <span class=\"fa fa-caret-down fa-fw\"></span>\n"
                                            //                                            + "             " + paramRequest.getLocaleString("lblRelations") + "\n"
                                            + "             Relacionados\n"
                                            + "         </button>\n";
                                }
                                value += "     </div>\n"
                                        + "</div>\n"
                                        + "<div id=\"seact" + se.getId() + "\" class=\"panel-collapse collapse\">\n"
                                        + "     <div class=\"row swbp-background\">\n"
                                        + "         <div class=\"col-lg-2\">\n"//Begin Menu
                                        + "             <ul class=\"nav nav-pills nav-stacked\">\n";
                                List<DocumentSection> listds = new ArrayList<DocumentSection>();
                                while (itser.hasNext()) {
                                    SectionElementRef sectionElementRef = itser.next();
                                    if (sectionElementRef.getSectionElement() != null && sectionElementRef.getSectionElement().getParentSection() != null) {//Verificar registros null
                                        if (!listds.contains(sectionElementRef.getSectionElement().getParentSection())) {
                                            listds.add(sectionElementRef.getSectionElement().getParentSection());
                                        }
                                    }
                                }
                                Iterator<DocumentSection> itds = SWBComparator.sortSortableObject(listds.iterator());
                                while (itds.hasNext()) {
                                    DocumentSection documentSection = itds.next();
                                    value += "              <li><a href=\"#sptabact" + act.getId() + documentSection.getId() + "\" data-toggle=\"tab\">" + documentSection.getTitle() + "</a></li>";
                                }
                                value += "             </ul>\n"
                                        + "         </div>\n" //End Menu
                                        + "          <div class=\"col-lg-10\">\n"//Begin Content
                                        + "                  <div class=\"tab-content\">\n";
                                it = mapST.entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry e = (Map.Entry) it.next();
                                    DocumentSection docSect = (DocumentSection) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(e.getKey().toString());
                                    String[] properties = docSect.getVisibleProperties().split("\\|");
                                    String[] uris = e.getValue().toString().split("\\|");
                                    List<String> lists = new ArrayList<String>();
                                    for (String valuet : uris) {
                                        lists.add(valuet);
                                    }
                                    Collections.sort(lists, UserDocumentationResource.sectionElementComparator);
                                    value += "                  <div class=\"tab-pane\" id=\"sptabact" + act.getId() + docSect.getId() + "\">\n"
                                            + "                     <div class=\"table-responsive\">\n"//Begin div table responsive
                                            + "                         <table class=\"table table-hover swbp-table swbp-table-relacionados\"><tr><thead>\n";//Begin table
                                    for (int a = 0; a < properties.length; a++) {
                                        value += "                          <th>" + properties[a].substring(0, properties[a].indexOf(";")) + "</th><th>Ver</th></thead></tr>\n";
                                        break;
                                    }
                                    for (String valuet : lists) {
                                        SectionElementRef serMap = (SectionElementRef) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(valuet);
                                        SectionElement seMap = (SectionElement) serMap.getSectionElement().getSemanticObject().createGenericInstance();
                                        SWBFormMgr forMgrMap = new SWBFormMgr(seMap.getSemanticObject(), null, SWBFormMgr.MODE_VIEW);
                                        value += "                          <tr><td>\n";
                                        for (int c = 0; c < properties.length; c++) {
                                            SemanticProperty semPropMap = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticPropertyById(properties[c].substring((properties[c].indexOf(";") + 1), properties[c].length()));
                                            value += (forMgrMap.renderElement(request, semPropMap, SWBFormMgr.MODE_VIEW));
                                            break;
                                        }
                                        String uridsi = DocumentSectionInstance.ClassMgr.listDocumentSectionInstanceByDocuSectionElementInstance(seMap).next().getURI();
                                        String urisei = seMap.getURI();
//                                        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/Resource/" + process.getId() + "/" + documentation.getId() + "/modals/";
//                                        String modal = path + "modal" + seMap.getId() + ".html";
                                        modals += createModal(suri, uridsi, urisei, request, paramRequest, documentation);

//                                        value += "                              </td><td class=\"swbp-icon-center\"><a class=\"btn btn-default fa fa-eye\" onclick=\"showModal('" + urlDialog.setMode(UserDocumentationResource.MODE_VIEW_INSTANCE).setParameter("urisei", urisei + "|" + uridsi) + "' , '" + paramRequest.getLocaleString("btnEdit") + "', '" + paramRequest.getLocaleString("msgLoadingElement") + "', '" + paramRequest.getLocaleString("msgLoadError") + "');\"></a></td></tr>";
//                                        value += "                              </td><td class=\"swbp-icon-center\"><a class=\"btn btn-default fa fa-eye\" onclick=\"showModal('" + modal + "' , '" + paramRequest.getLocaleString("btnEdit") + "', '" + paramRequest.getLocaleString("msgLoadingElement") + "', '" + paramRequest.getLocaleString("msgLoadError") + "');\"></a></td></tr>";
                                        value += "                              </td><td class=\"swbp-icon-center\"><a class=\"btn btn-default fa fa-eye\" data-toggle=\"modal\" data-target=\"#modal" + seMap.getId() + "\"></a></td></tr>";
                                    }
                                    value += "                          </table>\n"//End table
                                            + "                     </div>"//End div table responsive
                                            + "                 </div>\n";
                                }
                                value += "                 </div>\n"
                                        + "          </div>\n"//End Content
                                        + "     </div>\n"
                                        + "</div>\n";
                                property.appendChild(document.createTextNode("\n\t\t\t" + value + "\n\t\t\t"));
                            } else {
                                FreeText freeText = (FreeText) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(se.getURI());
                                property.setAttribute("propid", FreeText.swpdoc_text.getPropId());
                                property.setAttribute("type", dsi.getSecTypeDefinition().getTitle());
                                property.appendChild(document.createTextNode("\n\t\t\t" + freeText.getText() + "\n\t\t\t"));
                            }
                            instance.appendChild(property);
                            instance.appendChild(document.createTextNode("\t\t"));
                        }
                    }
                    section.appendChild(document.createTextNode("\n\t"));
                } else {
                    root.appendChild(document.createTextNode("\n\t"));
                    Element section = document.createElement("section");
                    section.setAttribute("uri", dsi.getURI());
                    section.setAttribute("url", scls.getName() + dsi.getId());
                    section.setAttribute("className", scls.getName());
                    section.setAttribute("title", dsi.getSecTypeDefinition().getTitle());
                    root.appendChild(section);
                    root.appendChild(document.createTextNode("\n\t"));

                    section.appendChild(document.createTextNode("\n\t\t"));
                    Element instance = document.createElement("instance");
                    instance.setAttribute("id", dsi.getId());
                    instance.setAttribute("uri", dsi.getURI());
                    instance.setAttribute("className", scls.getName());
                    instance.setAttribute("count", "1");
                    instance.setAttribute("type", dsi.getSecTypeDefinition().getTitle());
                    section.appendChild(instance);
                    instance.appendChild(document.createTextNode("\n\t\t\t"));

                    String mode = paramRequest.getMode();

                    Element property = document.createElement("property");
                    property.setAttribute("propid", Descriptiveable.swb_title.getPropId());
                    property.setAttribute("type", dsi.getSecTypeDefinition().getTitle());
                    property.setAttribute("propid", Model.swb_title.getPropId());
                    property.setAttribute("type", dsi.getSecTypeDefinition().getTitle());
                    property.appendChild(document.createTextNode("\n\t\t\t" + getProcessElementModel(process.getData(), suri, mode) + "\n\t\t\t"));

                    instance.appendChild(property);
                    instance.appendChild(document.createTextNode("\t\t"));
                    hasModel = true;
                }
            }
//            //Model funciona sin problema
            if (hasModel) {
                Element model = document.createElement("model");
                root.appendChild(document.createTextNode("\n\t"));
                String mode = paramRequest.getMode();
                model.appendChild(document.createTextNode(getProcessElementModel(process.getData(), suri, mode) + "\n\t\t"));
                root.appendChild(model);
                root.appendChild(document.createTextNode("\n\t"));
            }

            String path = SWBPortal.getWorkPath() + "/models/" + paramRequest.getWebPage().getWebSiteId() + "/Resource/" + process.getTitle() + "/";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(path + process.getTitle() + ".xml"));
            Source input = new DOMSource(document);
            transformer.transform(input, output);
        }
        return document;
    }

    public String createModal(String suri, String uridsi, String urisei, HttpServletRequest request, SWBActionResponse paramRequest, Documentation documentation) throws SWBResourceException, FileNotFoundException, IOException {
        org.semanticwb.process.model.Process process = (org.semanticwb.process.model.Process) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(suri);
        DocumentSectionInstance dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridsi);

        SemanticClass cls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(dsi.getSecTypeDefinition().getSectionType().getURI());
        SectionElement sei = (SectionElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urisei);
        SWBFormMgr forMgr = new SWBFormMgr(sei.getSemanticObject(), null, SWBFormMgr.MODE_EDIT);
        forMgr.clearProperties();
        Iterator<SemanticProperty> itsp = cls.listProperties();
        while (itsp.hasNext()) {
            SemanticProperty sp = itsp.next();
            if (sp.getDisplayProperty() != null) {
                forMgr.addProperty(sp);
            }
        }
//        System.out.println("the sei : " + sei.getId());
        String html = "";
        html += "<div class=\"modal fade\" id=\"modal" + sei.getId() + "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">";
        html += "<script src=\"" + SWBPlatform.getContextPath() + "/swbadmin/jsp/process/repository/fileRepositoryUtils.js\" charset=\"utf-8\"></script>\n"
                + "<div class=\"modal-dialog\">\n"
                + "    <div class=\"modal-content swbp-content\">\n"
                + "        <div class=\"modal-header\">\n"
                + "            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"
                + "            <h4 class=\"modal-title\"><span class=\"fa fa-check-square-o fa-fw\"></span>" + sei.getTitle() + "</h4>\n"
                + "        </div>\n"
                + "         <form class=\"form-horizontal\">"
                + "             <div class=\"modal-body\">\n";//Begin body
        Map map = new HashMap();
        if (dsi.getSecTypeDefinition().getVisibleProperties().length() > 0) {
            String[] values = dsi.getSecTypeDefinition().getVisibleProperties().split("\\|");
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                String title = value.substring(0, value.indexOf(";"));
                String propid = value.substring((value.indexOf(";") + 1), value.length());
                map.put(propid, title);
            }
        }
        itsp = forMgr.getProperties().iterator();
        while (itsp.hasNext()) {
            SemanticProperty sp = itsp.next();
            String titlesp = map.containsKey(sp.getPropId()) ? map.get(sp.getPropId()).toString() : sp.getDisplayName("es");
            if (map.containsKey(sp.getPropId())) {
                if (!sp.getPropId().equals(Referable.swpdoc_file.getPropId())) {
                    html += "<div class=\"form-group\">\n"
                            + "                    <label for=\"\" class=\"col-lg-4 control-label\">" + titlesp + "</label>\n"
                            + "                    <div class=\"col-lg-7\">\n";

                    String inputfm = forMgr.renderElement(request, sp, SWBFormMgr.MODE_CREATE);
                    inputfm = inputfm.replaceFirst(">", " disabled  class=\"form-control\">");
//            inputfm = inputfm.replace(inputfm.substring(inputfm.indexOf("style"), (inputfm.indexOf("px;") + 4)), "");
                    html += inputfm;
                    html += "                    </div>\n"
                            + "                </div>\n";
                } else {//Si es archivo
                    Referable referable = (Referable) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(sei.getURI());
                    if (referable != null && referable.getRefRepository() != null && referable.getRefRepository().getRepositoryDirectory() != null) {
                        RepositoryDirectory rd = referable.getRefRepository().getRepositoryDirectory();
                        String filename = referable.getRefRepository().getTitle();
                        String fid = referable.getRefRepository().getId();
                        String verNum = referable.getRefRepository().getLastVersion().getVersionNumber() + "";

                        Resource res = null;
                        Iterator<Resource> ire = rd.listResources();
                        while (ire.hasNext()) {
                            res = ire.next();
                            if (res.getResourceType().getResourceClassName().equals("org.semanticwb.process.resources.ProcessFileRepository")) {
                                break;
                            }
                        }
                        if (res != null) {
                            SWBResourceURL urlDownload = new SWBResourceURLImp(request, res, (WebPage) rd, SWBResourceModes.UrlType_RENDER);
                            urlDownload.setMode(ProcessFileRepository.MODE_GETFILE);
                            urlDownload.setCallMethod(SWBResourceURL.Call_DIRECT);
                            urlDownload.setParameter("fid", fid);
                            urlDownload.setParameter("verNum", verNum);

                            RepositoryElement re = (RepositoryElement) referable.getRefRepository();
                            VersionInfo vi = re.getLastVersion();

                            html += "<div class=\"form-group\" id=\"div" + sp.getName() + "\">\n"
                                    + "                    <label for=\"\" class=\"col-lg-4 control-label\">" + titlesp + "</label>\n"
                                    + "                    <div class=\"col-lg-7 input-group\">\n"
                                    + "                        <input type=\"text\" value=\"" + filename + "\" class=\"form-control\" disabled=\"true\">\n"
                                    + "                        <span class=\"input-group-btn\">\n";
                            if (re instanceof RepositoryFile) {
                                html += "                            <a class=\"btn btn-default\" href=\"" + urlDownload + "\"><li class=\"fa fa-download\"></li></a>\n";
                            } else if (re instanceof RepositoryURL) {
                                html += "                            <a target=\"_blank\" class=\"btn btn-default\" href=\"" + vi.getVersionFile() + "\"><li class=\"fa fa-external-link\"></li></a>\n";
                            }
                            html += "                        </span>\n"
                                    + "\n"
                                    + "                    </div>\n"
                                    + "                </div>  ";
                        }
                    }
                }
            }
        }
        html += "               </div>\n"
                + "         </form>\n";//End body
        html += "</div>";
        html += "</div>";
        html += "</div>";
        return html;
//        String path = SWBPortal.getWorkPath() + "/models/" + paramRequest.getWebPage().getWebSiteId() + "/Resource/" + process.getId() + "/" + documentation.getId() + "/modals/";
//        File folder = new File(path);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//        File modal = new File(path + "modal" + sei.getId() + ".html");
//        FileOutputStream out = new FileOutputStream(modal);
//        out.write(html.getBytes());
//        out.flush();
//        out.close();
    }
    static List<RepositoryDirectory> list = new ArrayList<RepositoryDirectory>();

    public static List<RepositoryDirectory> listRepositoryDerectoryByParent(WebPage webpage, boolean clear) {
        if (clear) {
            list.clear();
        }
        Iterator<RepositoryDirectory> it = SWBComparator.sortByCreated(RepositoryDirectory.ClassMgr.listRepositoryDirectoryByParent(webpage), true);
        while (it.hasNext()) {
            RepositoryDirectory rep = it.next();
            Iterator<RepositoryDirectory> itRep = SWBComparator.sortByCreated(RepositoryDirectory.ClassMgr.listRepositoryDirectoryByParent(rep), true);
            while (itRep.hasNext()) {
                RepositoryDirectory repDir = itRep.next();
                list.add(repDir);
                listRepositoryDerectoryByParent(repDir, false);
            }
            list.add(rep);
        }
        return list;
    }
}