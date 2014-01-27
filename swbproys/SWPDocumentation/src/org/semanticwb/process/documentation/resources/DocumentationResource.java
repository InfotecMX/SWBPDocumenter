package org.semanticwb.process.documentation.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.semanticwb.Logger;
import org.semanticwb.SWBPlatform;
import org.semanticwb.SWBUtils;
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
import org.semanticwb.model.SWBComparator;
import org.semanticwb.platform.SemanticClass;
import org.semanticwb.platform.SemanticObject;
import org.semanticwb.platform.SemanticProperty;
import org.semanticwb.portal.SWBFormMgr;
import org.semanticwb.portal.api.GenericAdmResource;
import org.semanticwb.portal.api.SWBActionResponse;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;
import org.semanticwb.process.model.ProcessElement;
import org.semanticwb.process.model.ProcessSite;
import org.semanticwb.process.model.RepositoryDirectory;
import org.semanticwb.process.model.RepositoryFile;

/**
 * Recurso que gestiona la vista de la documentación de procesos.
 * @author carlos.alvarez
 */
public class DocumentationResource extends GenericAdmResource {

    private final Logger log = SWBUtils.getLogger(DocumentationResource.class);
    public final static String VIEW_DTB = "vdtb";
    public final static String ADD_SECTION = "as";
    public final static String ADD_SECTION_ACTIVITY = "asa";
    public final static String ADD_SECTION_WITH_FILE = "aswf";
    public final static String ADD_FILE = "af";
    public final static String UPDATE_SURI = "us";
    public final static String EDIT_SECTION_INSTANCE = "esi";
    public final static String EDIT_SECTION_WITH_FILE = "eswf";
    public final static String REMOVE_SECTION_INSTANCE = "rsi";
    public final static String SAVE_FREE_TEXT = "sft";
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
        if (action.equals(ADD_SECTION)) {
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
                    if (sp.getDisplayProperty() != null) {
                        forMgr.addProperty(sp);
                    }
                }
                itsp = forMgr.getProperties().iterator();
                while (itsp.hasNext()) {
                    SemanticProperty sp = itsp.next();
                    forMgr.addProperty(sp);
                }
                try {
                    SemanticObject semobj = forMgr.processForm(request);
                    SectionElement se = (SectionElement) semobj.createGenericInstance();
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
                        //Type Format
                        if (cls.getClassId().equals(Format.sclass.getClassId())) {
                            Format format = Format.ClassMgr.createFormat(model);
                            format.setTitle(title);
                            format.setDescription(description);
                            format.setDocumentType(documentType);
                            format.setKeyWords(keyWords);
                            format.setRelatedSubjects(relatedSubjects);
                            dsi.addDocuSectionElementInstance(format);
                            format.setParentSection(dsi.getSecTypeDefinition());
                            RepositoryDirectory rd = (RepositoryDirectory) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(dsi.getSecTypeDefinition().getConfigData());
                            if (rd != null && size > 0) {
                                RepositoryFile rf = RepositoryFile.ClassMgr.createRepositoryFile(rd.getProcessSite());
                                format.setRefRepository(rf);
                                rf.setRepositoryDirectory(rd);
                                rf.setTitle(!title.trim().equals("") ? title.trim() : name.substring(0, name.lastIndexOf(".")));
                                rf.storeFile(name, is, null, Boolean.TRUE, null);
                            }
                        }

                        //Type Reference
                        if (cls.getClassId().equals(Reference.sclass.getClassId())) {
                            Reference reference = Reference.ClassMgr.createReference(model);
                            reference.setTypeReference(typeReference);
                            reference.setNumber(number);
                            reference.setTitle(title);
                            reference.setPrefix(prefix);
                            reference.setDescription(description);
                            dsi.addDocuSectionElementInstance(reference);
                            reference.setParentSection(dsi.getSecTypeDefinition());
                            RepositoryDirectory rd = (RepositoryDirectory) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(dsi.getSecTypeDefinition().getConfigData());
                            if (rd != null && size > 0) {
                                RepositoryFile rf = RepositoryFile.ClassMgr.createRepositoryFile(rd.getProcessSite());
                                reference.setRefRepository(rf);
                                rf.setRepositoryDirectory(rd);
                                rf.setTitle(!title.trim().equals("") ? title.trim() : name.substring(0, name.lastIndexOf(".")));
                                rf.storeFile(name, is, null, Boolean.TRUE, null);
                            }
                        }
                        //Type Definition
                        if (cls.getClassId().equals(Definition.sclass.getClassId())) {
                            Definition definition = Definition.ClassMgr.createDefinition(model);
                            definition.setNumber(number);
                            definition.setPrefix(prefix);
                            definition.setTitle(title);
                            definition.setDescription(description);
                            dsi.addDocuSectionElementInstance(definition);
                            definition.setParentSection(dsi.getSecTypeDefinition());
                            RepositoryDirectory rd = (RepositoryDirectory) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(dsi.getSecTypeDefinition().getConfigData());
                            if (rd != null && size > 0) {
                                RepositoryFile rf = RepositoryFile.ClassMgr.createRepositoryFile(rd.getProcessSite());
                                definition.setRefRepository(rf);
                                rf.setRepositoryDirectory(rd);
                                rf.setTitle(!title.trim().equals("") ? title.trim() : name.substring(0, name.lastIndexOf(".")));
                                rf.storeFile(name, is, null, Boolean.TRUE, null);
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
                    Iterator<SectionElement> itse = SWBComparator.sortByCreated(dsit.listDocuSectionElementInstances());
                    while (itse.hasNext()) {
                        SectionElement set = itse.next();
                        String uriset = set.getURI();

                        SectionElementRef ser = null;

                        Iterator<SectionElementRef> itser = act.listSectionElementRefs();
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
                    if (sp.getDisplayProperty() != null) {
                        forMgr.addProperty(sp);
                    }
                }
                itsp = forMgr.getProperties().iterator();
                while (itsp.hasNext()) {
                    SemanticProperty sp = itsp.next();
                    forMgr.addProperty(sp);
                }
                try {
                    forMgr.processForm(request);
                } catch (FormValidateException ex) {
                    log.error("Error on processAction, " + action, ex);
                }
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("sptab", request.getParameter("uridsi"));
            response.setMode(UPDATE_SURI);
        } else if (action.equals(EDIT_SECTION_WITH_FILE)) {
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
                            if (item.getFieldName().equals("uridsi")) {
                                uridsi = item.getString();
                                dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridsi);
                            }
                        }
                    }
                    if (dsi != null) {
                        String urirf = "";
                        SemanticClass cls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(scls);
                        //Format
                        if (cls.getClassId().equals(Format.sclass.getClassId())) {
                            Format format = (Format) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urisei);
                            format.setTitle(title);
                            format.setDescription(description);
                            format.setDocumentType(documentType);
                            format.setKeyWords(keyWords);
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
                            if (definition.getRefRepository() != null) {
                                urirf = definition.getRefRepository().getURI();
                            }
                        }
                        RepositoryFile rf = (RepositoryFile) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urirf);
                        if (rf != null) {
                            rf.setTitle(!title.trim().equals("") ? title.trim() : rf.getTitle());
                        }
                    }
                    response.setRenderParameter("suri", suri);
                    response.setRenderParameter("sptab", uridsi);
                    response.setMode(UPDATE_SURI);
                } catch (Exception ex) {
                    log.error("Error on processAction, " + action, ex);
                }
            }
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
                Iterator<SectionElementRef> itser = SectionElementRef.ClassMgr.listSectionElementRefBySectionElement(sei);
                while(itser.hasNext()){
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
}