/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.semanticwb.process.documentation.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.semanticwb.Logger;
import org.semanticwb.SWBPlatform;
import org.semanticwb.SWBPortal;
import org.semanticwb.SWBUtils;
import org.semanticwb.process.documentation.model.Definition;
import org.semanticwb.process.documentation.model.DocumentSectionInstance;
import org.semanticwb.process.documentation.model.Format;
import org.semanticwb.process.documentation.model.FreeText;
import org.semanticwb.process.documentation.model.Reference;
import org.semanticwb.process.documentation.model.SectionElement;
import org.semanticwb.model.FormValidateException;
import org.semanticwb.model.SWBContext;
import org.semanticwb.platform.SemanticClass;
import org.semanticwb.platform.SemanticObject;
import org.semanticwb.platform.SemanticProperty;
import org.semanticwb.portal.SWBFormMgr;
import org.semanticwb.portal.api.GenericResource;
import org.semanticwb.portal.api.SWBActionResponse;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;
import org.semanticwb.portal.api.SWBResourceURL;
import org.semanticwb.process.model.ProcessElement;
import org.semanticwb.process.model.ProcessSite;
import org.semanticwb.process.model.RepositoryDirectory;
import org.semanticwb.process.model.RepositoryFile;

/**
 *
 * @author carlos.alvarez
 */
public class DocumentTemplateBehavior extends GenericResource {

    private final static Logger log = SWBUtils.getLogger(DocumentTemplateBehavior.class);
    public final static String VIEW_DTB = "vdtb";
    public final static String ADD_SECTION = "as";
    public final static String ADD_SECTION_WITH_FILE = "aswf";
    public final static String ADD_FILE = "af";
    public final static String UPDATE_SURI = "us";
    public final static String EDIT_SECTION_INSTANCE = "esi";
    public final static String EDIT_SECTION_WITH_FILE = "eswf";
    public final static String REMOVE_SECTION_INSTANCE = "rsi";
    public final static String SAVE_FREE_TEXT = "sft";

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        boolean isAdminSite = paramRequest.getWebPage().getWebSiteId().equals(SWBContext.getAdminWebSite().getId());
        if (isAdminSite) {
            PrintWriter out = response.getWriter();
            SWBResourceURL url = paramRequest.getRenderUrl().setCallMethod(SWBResourceURL.Call_DIRECT).setMode(VIEW_DTB);
            url.setParameter("suri", request.getParameter("suri"));
            out.println("<iframe src=\"" + url + "\" style=\"width:100%; height:100%;\" frameborder=\"0\"></iframe>");
        } else {
            String path = SWBPortal.getContextPath() + "/swbadmin/jsp/process/financiera/documentTemplateBehavior.jsp";
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("suri", request.getParameter("suri"));
            RequestDispatcher rd = request.getRequestDispatcher(path);
            try {
                rd.include(request, response);
            } catch (ServletException ex) {
                log.error("Error on doView, " + path + ", " + ex.getMessage());
            }
        }
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        String mode = paramRequest.getMode();
        if (mode.equals(VIEW_DTB)) {
            doViewDTB(request, response, paramRequest);
        } else if (mode.equals(ADD_SECTION)) {
            doAddSection(request, response, paramRequest);
        } else if (mode.equals(EDIT_SECTION_INSTANCE)) {
            doEditSection(request, response, paramRequest);
        } else if (mode.equals(UPDATE_SURI)) {
            doUpdateSuri(request, response, paramRequest);
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
                    dsi.addDocuSectionElementInstance(se);
                } catch (FormValidateException ex) {
                    log.error("Error on processAction, " + action + ", " + ex.getMessage());
                }
            }
            //ProcessFileRepositiry - Resources
            response.setRenderParameter("suri", suri);
            response.setMode(UPDATE_SURI);
        } else if (action.equals("iframe")) {
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    for (FileItem item : multiparts) {
                        if (!item.isFormField()) {
                            String name = item.getName();
                            //name = new File(item.getName()).getName();
//                            System.out.println("write file: " + name);
//                            System.out.println("getInputStream: " + item.getInputStream());
//                            item.write(new File("c:\\curso\\" + name));
                        } else {
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();
//                            System.out.println("fieldName: " + fieldName);
//                            System.out.println("fieldValue: " + fieldValue);
                        }
                    }
                } catch (Exception e) {
                }
            }
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
                    response.setMode(UPDATE_SURI);
                } catch (Exception ex) {
                    log.error("Error on processAction, " + action + ", " + ex.getMessage());
                }
            }
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
                    log.error("Error on processAction, " + action + ", " + ex.getMessage());
                }
            }
            response.setRenderParameter("suri", suri);
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
                    response.setMode(UPDATE_SURI);
                } catch (Exception ex) {
                    log.error("Error on processAction, " + action + ", " + ex.getMessage());
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
                sei.remove();
            }
            response.setRenderParameter("suri", suri);
            response.setMode(UPDATE_SURI);
        } else if (action.equals(SAVE_FREE_TEXT)) {
            suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            String urift = request.getParameter("urift") != null ? request.getParameter("urift") : "";
            String data = request.getParameter("data") != null ? request.getParameter("data") : "";
            if (!suri.equals("") && !urift.equals("")) {
                FreeText ft = (FreeText) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urift);
                if (ft != null) {
                    ft.setText(data);
                }
            }
            response.setRenderParameter("suri", suri);
            response.setMode(UPDATE_SURI);
        } else {
            super.processAction(request, response); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public void doViewDTB(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = SWBPortal.getContextPath() + "/swbadmin/jsp/process/financiera/documentTemplateBehavior.jsp";
        request.setAttribute("paramRequest", paramRequest);
        request.setAttribute("suri", request.getParameter("suri"));
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doViewDTB, " + path + ", " + ex.getMessage());
        }
    }

    public void doAddSection(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = SWBPortal.getContextPath() + "/swbadmin/jsp/process/financiera/editSectionTemplateBehavior.jsp";
        request.setAttribute("paramRequest", paramRequest);
        request.setAttribute("suri", request.getParameter("suri"));
        request.setAttribute("uridsi", request.getParameter("uridsi"));
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doAddSection, " + path + ", " + ex.getMessage());
        }
    }

    public void doEditSection(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = SWBPortal.getContextPath() + "/swbadmin/jsp/process/financiera/editSectionTemplateBehavior.jsp";
        request.setAttribute("paramRequest", paramRequest);
        request.setAttribute("suri", request.getParameter("suri"));
        request.setAttribute("urisei", request.getParameter("urisei"));
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doEditSection, " + path + ", " + ex.getMessage());
        }
    }

    public void doUpdateSuri(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.getWriter().print(request.getParameter("suri"));
    }
}
