package org.semanticwb.process.documentation.resources;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticwb.Logger;
import org.semanticwb.SWBPlatform;
import org.semanticwb.SWBUtils;
import org.semanticwb.process.documentation.model.DocumentSection;
import org.semanticwb.process.documentation.model.DocumentTemplate;
import org.semanticwb.process.documentation.model.DocumentationInstance;
import org.semanticwb.process.documentation.model.Referable;
import org.semanticwb.model.SWBComparator;
import org.semanticwb.model.User;
import org.semanticwb.model.WebSite;
import org.semanticwb.platform.SemanticClass;
import org.semanticwb.platform.SemanticProperty;
import org.semanticwb.portal.SWBFormMgr;
import org.semanticwb.portal.api.GenericResource;
import org.semanticwb.portal.api.SWBActionResponse;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;

/**
 *
 * @author carlos.alvarez
 */
public class DocumentTemplateResource extends GenericResource {

    private final Logger log = SWBUtils.getLogger(DocumentTemplateResource.class);
    public final static String ADD_TEMPLATE = "at";
    public final static String EDIT_TEMPLATE = "et";
    public final static String REMOVE_TEMPLATE = "rt";
    public final static String UPDATE_ID_TEMPLATE = "ui";
    public final static String ADD_DOCUMENT_SECTION = "ads";
    public final static String UPDATE_DOCUMENT_SECTION = "uds";
    public final static String EDIT_DOCUMENT_SECTION = "eds";
    public final static String REMOVE_DOCUMENT_SECTION = "rds";
    public final static String VIEW_PROPERTIES = "vp";
    public final static String VIEW_LOG = "vloc";
    public final static String ATT_TEMPLATES = "templates";

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentTemplate.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(path);
        
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("uridt", request.getParameter("uridt"));
            request.setAttribute(ATT_TEMPLATES, listTemplates(request, paramRequest));
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doView, " + path + ", " + ex.getMessage());
        }
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        String mode = paramRequest.getMode();
        if (mode.equals(ADD_TEMPLATE)) {
            doAddDT(request, response, paramRequest);
        } else if (mode.equals(EDIT_TEMPLATE)) {
            doEdit(request, response, paramRequest);
        } else if (mode.equals(UPDATE_ID_TEMPLATE)) {
            doUpdateID(request, response, paramRequest);
        } else if (mode.equals(ADD_DOCUMENT_SECTION)) {
            doAddDS(request, response, paramRequest);
        } else if (mode.equals(EDIT_DOCUMENT_SECTION)) {
            doEditDS(request, response, paramRequest);
        } else if (mode.equals(VIEW_PROPERTIES)) {
            doViewProperties(request, response, paramRequest);
        } else if (mode.equals(VIEW_LOG)) {
            doViewLog(request, response, paramRequest);
        } else {
            super.processRequest(request, response, paramRequest); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void processAction(HttpServletRequest request, SWBActionResponse response) throws SWBResourceException, IOException {
        String action = response.getAction();
        WebSite model = response.getWebPage().getWebSite();
        if (action.equals(ADD_TEMPLATE) && model != null) {
            String titleTemplate = request.getParameter("titleTemplate") != null ? request.getParameter("titleTemplate").toString().trim() : "";
            String[] processes = request.getParameterValues("applytop");
            String uridt = "";
            if (!titleTemplate.equals("")) {
                DocumentTemplate dt = DocumentTemplate.ClassMgr.createDocumentTemplate(model);
                dt.setTitle(titleTemplate);
                uridt = dt.getURI();
                if (processes != null) {
                    for (int i = 0; i < processes.length; i++) {
                        String idp = processes[i];
                        org.semanticwb.process.model.Process process = (org.semanticwb.process.model.Process) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(idp);
                        if (process != null) {
                            dt.addProcess(process);
                        }
                    }
                }
            }
            response.setRenderParameter("uridt", uridt);
            response.setMode(UPDATE_ID_TEMPLATE);
        } else if (action.equals(EDIT_TEMPLATE) && model != null) {
            String titleTemplate = request.getParameter("titleTemplate") != null ? request.getParameter("titleTemplate").toString().trim() : "";
            String uridt = request.getParameter("uridt") != null ? request.getParameter("uridt").toString().trim() : "";
            String[] processes = request.getParameterValues("applytop");
            if (!titleTemplate.equals("") && !uridt.equals("")) {
                DocumentTemplate dt = (DocumentTemplate) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridt);
                dt.setTitle(titleTemplate);
                dt.removeAllProcess();
                if (processes != null) {
                    for (int i = 0; i < processes.length; i++) {
                        String idp = processes[i];
                        org.semanticwb.process.model.Process process = (org.semanticwb.process.model.Process) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(idp);
                        if (process != null) {
                            dt.addProcess(process);
                        }
                    }
                }
                
                Iterator<DocumentSection> itds = dt.listDocumentSections();
                while (itds.hasNext()) {
                    DocumentSection ds = itds.next();
                    try {
                        Integer index = Integer.parseInt(request.getParameter("ind" + ds.getURI()).toString());
                        ds.setIndex(index);
                    } catch (NumberFormatException e) {
                        log.error("NumberFormatException, on EDIT_TEMPLATE, " + e.getMessage());
                    }
                }
            }
            response.setRenderParameter("uridt", uridt);
            response.setMode(UPDATE_ID_TEMPLATE);
        } else if (action.equals(REMOVE_TEMPLATE)) {
            String uridt = request.getParameter("uridt") != null ? request.getParameter("uridt") : "";
            if (!uridt.equals("")) {
                DocumentTemplate dt = (DocumentTemplate) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridt);
                if (dt != null) {
                    Iterator<DocumentationInstance> itdi = dt.listDocumentationInstances();
                    while (itdi.hasNext()) {
                        DocumentationInstance di = itdi.next();
//                        if(di.getDocTypeDefinition().equals(dt)){
                        di.remove();
//                        }
                    }
                    dt.removeAllDocumentSection();
                    dt.remove();
                }
                response.setRenderParameter("uridt", "");
                response.setMode(UPDATE_ID_TEMPLATE);
            }
        } else if (action.equals(ADD_DOCUMENT_SECTION) && model != null) {
            String titleSection = request.getParameter("titleSection") != null ? request.getParameter("titleSection").toString().trim() : "";
            String uridt = request.getParameter("uridt") != null ? request.getParameter("uridt").toString().trim() : "";
            String dstype = request.getParameter("dstype") != null ? request.getParameter("dstype").toString().trim() : "";
            SemanticClass semCls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(URLDecoder.decode(dstype));
            String configdata = "configdata";
            if (semCls != null) {
                if (semCls.isSubClass(Referable.swpdoc_Referable, false)) {
                    configdata = request.getParameter("configdata") != null ? request.getParameter("configdata").toString().trim() : "";
                }
                if (!titleSection.equals("") && !uridt.equals("") && !dstype.equals("") && !configdata.equals("")) {
                    DocumentTemplate dt = (DocumentTemplate) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridt);
                    DocumentSection ds = DocumentSection.ClassMgr.createDocumentSection(model);
                    ds.setConfigData(configdata);
                    ds.setTitle(titleSection);
                    int i = (Integer.parseInt(SWBUtils.Collections.sizeOf(dt.listDocumentSections()) + "") + 1);
                    ds.setIndex(i);
                    ds.setSectionType(semCls.getSemanticObject());
                    ds.setActive(Boolean.TRUE);
                    dt.addDocumentSection(ds);
                    SWBFormMgr formMgr = new SWBFormMgr(semCls, model.getSemanticObject(), SWBFormMgr.MODE_EDIT);
                    formMgr.clearProperties();
                    Iterator<SemanticProperty> itsp = semCls.listProperties();
                    while (itsp.hasNext()) {
                        SemanticProperty sp = itsp.next();
                        if (sp.getDisplayProperty() != null) {
                            formMgr.addProperty(sp);
                        }
                    }
                    
                    ds.setVisibleProperties("");
                    String newprop = "";
                    itsp = formMgr.getProperties().iterator();
                    while (itsp.hasNext()) {
                        SemanticProperty sp = itsp.next();
                        if (request.getParameter(sp.getPropId()) != null) {
                            String label = request.getParameter("label" + sp.getPropId());
                            newprop += label + ";" + sp.getPropId() + "|";
                        }
                    }
                    ds.setVisibleProperties(newprop);
                }
            }
            response.setRenderParameter("uridt", uridt);
            response.setMode(UPDATE_ID_TEMPLATE);
        } else if (action.equals(EDIT_DOCUMENT_SECTION) && model != null) {
            String titleSection = request.getParameter("titleSection") != null ? request.getParameter("titleSection").toString().trim() : "";
            String uridt = request.getParameter("uridt") != null ? request.getParameter("uridt").toString().trim() : "";
            String urids = request.getParameter("urids") != null ? request.getParameter("urids").toString().trim() : "";
            if (!titleSection.equals("") && !uridt.equals("") && !urids.equals("")) {
                DocumentSection ds = (DocumentSection) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urids);
                ds.setTitle(titleSection);
                SemanticClass semCls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(ds.getSectionType().getURI());
                ds.setSectionType(semCls.getSemanticObject());
                SWBFormMgr formMgr = new SWBFormMgr(semCls, model.getSemanticObject(), SWBFormMgr.MODE_EDIT);
                formMgr.clearProperties();
                Iterator<SemanticProperty> itsp = semCls.listProperties();
                while (itsp.hasNext()) {
                    SemanticProperty sp = itsp.next();
                    if (sp.getDisplayProperty() != null) {
                        formMgr.addProperty(sp);
                    }
                }
                ds.setVisibleProperties("");
                String newprop = "";
                itsp = formMgr.getProperties().iterator();
                while (itsp.hasNext()) {
                    SemanticProperty sp = itsp.next();
                    if (request.getParameter(sp.getPropId()) != null) {
                        String label = request.getParameter("label" + sp.getPropId());
                        newprop += label + ";" + sp.getPropId() + "|";
                    }
                }
                ds.setVisibleProperties(newprop);
            }
            response.setRenderParameter("uridt", uridt);
            response.setMode(UPDATE_ID_TEMPLATE);
        } else if (action.equals(UPDATE_DOCUMENT_SECTION) && model != null) {
            String uridt = request.getParameter("uridt") != null ? request.getParameter("uridt").toString().trim() : "";
            String urids = request.getParameter("urids") != null ? request.getParameter("urids").toString().trim() : "";
            DocumentSection ds = (DocumentSection) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urids);
            if (ds != null) {
                if (Boolean.parseBoolean(request.getParameter("active"))) {
                    ds.setActive(Boolean.TRUE);
                } else {
                    ds.setActive(Boolean.FALSE);
                }
            }
            response.setRenderParameter("uridt", uridt);
            response.setMode(UPDATE_ID_TEMPLATE);
        } else if (action.equals(REMOVE_DOCUMENT_SECTION)) {
            String uridt = request.getParameter("uridt") != null ? request.getParameter("uridt").toString().trim() : "";
            String urids = request.getParameter("urids") != null ? request.getParameter("urids").toString().trim() : "";
            if (!uridt.equals("") && !urids.equals("")) {
                DocumentTemplate dt = (DocumentTemplate) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridt);
                DocumentSection ds = (DocumentSection) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urids);
                if (dt != null && ds != null) {
                    dt.removeDocumentSection(ds);
                    ds.remove();
                }
            }
            response.setRenderParameter("uridt", uridt);
            response.setMode(UPDATE_ID_TEMPLATE);
        } else {
            super.processAction(request, response); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public void doAddDT(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentTemplateEdit.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("uridt", "");
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doAddDT, " + path + ", " + ex.getMessage());
        }
    }

    @Override
    public void doEdit(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentTemplateEdit.jsp";
        
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("uridt", request.getParameter("uridt"));
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doEdit, " + path + ", " + ex.getMessage());
        }
    }

    public void doUpdateID(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.getWriter().print(request.getParameter("uridt"));
    }

    public void doAddDS(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentTemplateEditSection.jsp";
        
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("uridt", request.getParameter("uridt"));
            request.setAttribute("iddS", "");
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doAddDS, " + path + ", " + ex.getMessage());
        }
    }

    public void doEditDS(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentTemplateEditSection.jsp";
        
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("uridt", request.getParameter("uridt"));
            request.setAttribute("urids", request.getParameter("urids"));
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doEditDS, " + path + ", " + ex.getMessage());
        }
    }

    public void doViewProperties(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentTemplateProperties.jsp";
        
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("classuri", request.getParameter("classuri"));
            request.setAttribute("urids", request.getParameter("urids"));
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doViewProperties, " + path + ", " + ex.getMessage());
        }
    }

    public void doViewLog(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/logView.jsp";
        
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("uridt", request.getParameter("uridt"));
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doViewLog, " + path + ", " + ex.getMessage());
        }
    }

    /**
     * Obtiene la lista de plantillas, ordenadas y paginadas.
     *
     * @param request Objeto HTTPServletRequest.
     * @param paramRequest Objeto SWBParamRequest.
     * @return Lista con las plantillas ordenadas y paginadas.
     */
    private List<DocumentTemplate> listTemplates(HttpServletRequest request, SWBParamRequest paramRequest) {
        ArrayList<DocumentTemplate> unpaged = new ArrayList<DocumentTemplate>();
        WebSite model = paramRequest.getWebPage().getWebSite();
        String lang = "es";
        User user = paramRequest.getUser();
        if (user != null && user.getLanguage() != null) {
            lang = user.getLanguage();
        }
        int page = 1;
        int itemsPerPage = 10;

        Iterator<DocumentTemplate> it = SWBComparator.sortByDisplayName(DocumentTemplate.ClassMgr.listDocumentTemplates(model), lang);

        while (it.hasNext()) {
            DocumentTemplate dt = it.next();
            unpaged.add(dt);
        }

        //Realizar paginado de instancias
        int maxPages = 1;
        if (request.getParameter("p") != null && !request.getParameter("p").trim().equals("")) {
            page = Integer.valueOf(request.getParameter("p"));
            if (page < 0) {
                page = 1;
            }
        }

        if (itemsPerPage < 10) {
            itemsPerPage = 10;
        }

        if (unpaged.size() >= itemsPerPage) {
            maxPages = (int) Math.ceil((double) unpaged.size() / itemsPerPage);
        }
        if (page > maxPages) {
            page = maxPages;
        }

        int sIndex = (page - 1) * itemsPerPage;
        if (unpaged.size() > itemsPerPage && sIndex > unpaged.size() - 1) {
            sIndex = unpaged.size() - itemsPerPage;
        }

        int eIndex = sIndex + itemsPerPage;
        if (eIndex >= unpaged.size()) {
            eIndex = unpaged.size();
        }

        request.setAttribute("maxPages", maxPages);
        ArrayList<DocumentTemplate> ret = new ArrayList<DocumentTemplate>();
        for (int i = sIndex; i < eIndex; i++) {
            DocumentTemplate dt = unpaged.get(i);
            ret.add(dt);
        }
        return ret;
    }
}
