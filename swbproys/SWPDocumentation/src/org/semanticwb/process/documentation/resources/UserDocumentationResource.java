package org.semanticwb.process.documentation.resources;

import java.io.File;
import java.io.IOException;
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
import org.semanticwb.Logger;
import org.semanticwb.SWBPlatform;
import org.semanticwb.SWBPortal;
import org.semanticwb.SWBUtils;
import org.semanticwb.model.Descriptiveable;
import org.semanticwb.model.Resource;
import org.semanticwb.model.SWBComparator;
import org.semanticwb.model.User;
import org.semanticwb.model.WebPage;
import org.semanticwb.model.WebSite;
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
import org.semanticwb.process.documentation.model.Activity;
import org.semanticwb.process.documentation.model.DocumentSection;
import org.semanticwb.process.documentation.model.DocumentSectionInstance;
import org.semanticwb.process.documentation.model.Documentation;
import org.semanticwb.process.documentation.model.DocumentationInstance;
import org.semanticwb.process.documentation.model.FreeText;
import org.semanticwb.process.documentation.model.Model;
import org.semanticwb.process.documentation.model.Referable;
import org.semanticwb.process.documentation.model.SectionElement;
import org.semanticwb.process.documentation.model.SectionElementRef;
import org.semanticwb.process.model.Process;
import org.semanticwb.process.model.ProcessElement;
import org.semanticwb.process.model.RepositoryDirectory;
import org.semanticwb.process.model.SubProcess;
import static org.semanticwb.process.resources.DocumentationResource.deleteDerectory;
import org.semanticwb.process.resources.ProcessFileRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author hasdai
 */
public class UserDocumentationResource extends GenericAdmResource {

    private static final Logger log = SWBUtils.getLogger(UserDocumentationResource.class);
    public static final String ATT_PARAMREQUEST = "paramRequest";
    public static final String ATT_PROCESSLIST = "processes";
    public static final String MODE_VIEWDOC = "viewdoc";
    public static final String MODE_VIEW_VERSION = "viewver";
    public static final String PARAM_PROCESSID = "suri";
    public static final String MODE_VIEW_INSTANCE = "viewins";
    public static final String EDIT_VERSION = "editver";
    public static final String UPDATE_SURI = "updsuri";

    @Override
    public void processAction(HttpServletRequest request, SWBActionResponse response) throws SWBResourceException, IOException {
        String action = response.getAction();
        if (action.equals(SWBResourceURL.Action_EDIT)) {
            String suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            if (!suri.equals("")) {
                Process process = (Process) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(suri);
                if (process != null) {
                    Iterator<Documentation> iterator = Documentation.ClassMgr.listDocumentationByProcess(process);
                    while (iterator.hasNext()) {
                        Documentation documentation = iterator.next();
                        String title = request.getParameter("titledoc" + documentation.getEncodedURI()) != null ? request.getParameter("titledoc" + documentation.getEncodedURI()) : "";
                        String description = request.getParameter("descriptiondoc" + documentation.getEncodedURI()) != null ? request.getParameter("descriptiondoc" + documentation.getEncodedURI()) : "";
                        String actual = request.getParameter("veractual") != null ? request.getParameter("veractual") : "";
                        if (actual.equals(documentation.getEncodedURI())) {
                            documentation.setActualVersion(true);
                        } else {
                            documentation.setActualVersion(false);
                        }
                        documentation.setTitle(title);
                        documentation.setDescription(description);
                    }
                }
            }
            response.setRenderParameter("suri", suri);
        } else if (action.equals(EDIT_VERSION)) {
            String suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            String uridoc = request.getParameter("uridoc") != null ? request.getParameter("uridoc") : "";
            String currentVersion = request.getParameter("currentVersion") != null ? request.getParameter("currentVersion") : "";
            String title = request.getParameter("titlee") != null ? request.getParameter("titlee") : "";
            String descriptionn = request.getParameter("descriptionn") != null ? request.getParameter("descriptionn") : "";

            Documentation doc = (Documentation) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridoc);
            if (doc != null) {
                doc.setTitle(title);
                doc.setDescription(descriptionn);
                if (!currentVersion.equals("") && !doc.isActualVersion()) {
                    Iterator<Documentation> itdoc = Documentation.ClassMgr.listDocumentationByProcess((Process) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(suri));
                    while (itdoc.hasNext()) {
                        Documentation documentation = itdoc.next();
                        if (documentation.isActualVersion()) {
                            documentation.setActualVersion(false);
                            break;
                        }
                    }
                    doc.setActualVersion(true);
                }
                if (currentVersion.equals("") && doc.isActualVersion()) {
                    doc.setActualVersion(false);
                }
            }
            response.setRenderParameter("suri", suri);
            response.setMode(UPDATE_SURI);
        } else if (action.equals(SWBResourceURL.Action_REMOVE)) {
            String suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
            String uridoc = request.getParameter("uridoc") != null ? request.getParameter("uridoc") : "";
            if (!uridoc.equals("")) {
                Documentation doc = (Documentation) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uridoc);
                if (doc != null) {
                    String basePath = SWBPortal.getWorkPath() + "/models/" + response.getWebPage().getWebSiteId() + "/Resource/" + doc.getProcess().getId() + "/" + doc.getId() + "/";
                    File version = new File(basePath);
                    if (version.exists()) {
                        deleteDerectory(version);
                    }
                    doc.remove();
                }
            }
            response.setRenderParameter("suri", suri);
            response.setRenderParameter("remove", "true");
        } else {
            super.processAction(request, response); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        String mode = paramRequest.getMode();
        if (mode.equals(MODE_VIEWDOC)) {
            doViewDoc(request, response, paramRequest);
        } else if (mode.equals(MODE_VIEW_INSTANCE)) {
            doViewInst(request, response, paramRequest);
        } else if (mode.equals(MODE_VIEW_VERSION)) {
            doViewVersion(request, response, paramRequest);
        } else if (mode.equals(SWBResourceURL.Mode_EDIT)) {
            doEdit(request, response, paramRequest);
        } else if (mode.equals(SWBResourceURL.Mode_EDIT)) {
            doEdit(request, response, paramRequest);
        } else if (mode.equals(UPDATE_SURI)) {
            doUpdateSuri(request, response, paramRequest);
        } else {
            super.processRequest(request, response, paramRequest); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String jsp = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/userDocumentation.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(jsp);

        try {
            request.setAttribute(ATT_PARAMREQUEST, paramRequest);
            request.setAttribute(ATT_PROCESSLIST, listUserProcesses(paramRequest.getUser(), paramRequest.getWebPage().getWebSite(), request));
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("UserDocumentationResource - problema al incluir userDocumentation.jsp", ex);
        }
    }

    public void doViewDoc(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
        String jsp = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/userDocumentationView.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        try {
            request.setAttribute(ATT_PARAMREQUEST, paramRequest);
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("UserDocumentationResource - problema al incluir userDocumentationView.jsp" + ", " + jsp, ex);
        }




        response.setContentType("text/html; charset=UTF-8");

//        PrintWriter out = response.getWriter();
//        String suri = request.getParameter("suri") != null ? request.getParameter("suri") : "";
//        String lang = paramRequest.getUser().getLanguage();
//        try {
//            org.w3c.dom.Document dom = getDom(suri, lang, paramRequest, request);
//            if (dom != null) {
//                String tlpPath = "/swbadmin/jsp/process/documentation/documentation.xsl";
//                javax.xml.transform.Templates tpl = SWBUtils.XML.loadTemplateXSLT(new FileInputStream(SWBUtils.getApplicationPath() + tlpPath));
//                out.print(SWBUtils.XML.transformDom(tpl, dom));
//            }
//        } catch (Exception e) {
//            log.error(e);
//        }
    }

    public void doViewInst(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String suri = request.getParameter("suri") != null ? request.getParameter("suri").toString() : "";
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentationEdit.jsp";
        request.setAttribute("paramRequest", paramRequest);
        request.setAttribute("disabled", "disabled");
        request.setAttribute("suri", suri);
        request.setAttribute("urisei", request.getParameter("urisei"));
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doViewInst, " + path + ", " + ex.getMessage());
        }
    }

    public void doViewVersion(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/documentationVersion.jsp";
        request.setAttribute("paramRequest", paramRequest);
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doViewVersion, " + path + ", " + ex.getMessage());
        }
    }

    @Override
    public void doEdit(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/saveVersion.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            request.setAttribute("paramRequest", paramRequest);
            request.setAttribute("suri", request.getParameter("suri"));
            request.setAttribute("uridoc", request.getParameter("uridoc"));
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doSaveVersion, " + path, ex);
        }
    }

    /**
     * Obtiene la lista de procesos a los cuales tiene acceso el usuario.
     *
     * @param user Usuario de SWB en sesión
     * @param site Sitio de procesos
     * @return Lista de procesos a los que el usuario tiene acceso
     */
    private List<Process> listUserProcesses(User user, WebSite site, HttpServletRequest request) {
        ArrayList<Process> unpaged = new ArrayList();
        String pg = request.getParameter("pg") != null ? request.getParameter("pg") : "";
        int page = 1;
        int itemsPerPage = 10;

        if (getResourceBase().getAttribute("itemsPerPage") != null) {
            try {
                itemsPerPage = Integer.parseInt(getResourceBase().getAttribute("itemsPerPage"));
            } catch (NumberFormatException ex) {
                itemsPerPage = 10;
            }
        }
        //boolean isDocumenter = false;

        /*Role docRole = site.getUserRepository().getRole(getResourceBase().getAttribute("docRole"));
         Role adminRole = site.getUserRepository().getRole(getResourceBase().getAttribute("admin"));
         if (user.hasRole(docRole) || user.hasRole(adminRole)) {
         isDocumenter = true;
         }*/

        /*Iterator<StartEvent> startEvents = StartEvent.ClassMgr.listStartEvents(site);
         while (startEvents.hasNext()) {
         StartEvent sevt = startEvents.next();
         if (sevt.getProcess() != null && sevt.getContainer() != null && sevt.getContainer() instanceof Process && (user.haveAccess(sevt) || isDocumenter)) {
         Process itp = sevt.getProcess();
         if (itp != null && itp.isValid() && (user.haveAccess(itp) || isDocumenter)) {//El proceso del evento es válido y el usuario tiene acceso al proceso
         if (DocumentationInstance.ClassMgr.listDocumentationInstanceByProcessRef(itp, itp.getProcessSite()).hasNext() || isDocumenter) {
         if (!unpaged.contains(itp)) {
         unpaged.add(itp);
         }
         }
         }
         }
         }*/

        Iterator<Process> processes = SWBComparator.sortByDisplayName(Process.ClassMgr.listProcesses(site), user.getLanguage() != null ? user.getLanguage() : "es");
        while (processes.hasNext()) {
            Process process = processes.next();
            if (process.isValid() && !unpaged.contains(process)) {
                if (!pg.equals("")) {
                    if (pg.equals(process.getProcessGroup().getId())) {
                        unpaged.add(process);
                    }
                } else {
                    unpaged.add(process);
                }
            }
        }

        //Realizar paginado de instancias
        int maxPages = 1;
        if (request.getParameter("p") != null && !request.getParameter("p").trim().equals("")) {
            page = Integer.valueOf(request.getParameter("p"));
            if (page < 0) {
                page = 1;
            }
        }

        if (itemsPerPage < 5) {
            itemsPerPage = 5;
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

        ArrayList<Process> ret = new ArrayList();
        for (int i = sIndex; i < eIndex; i++) {
            Process p = unpaged.get(i);
            ret.add(p);
        }

        return ret;
    }
    public static Comparator sectionElementComparator = new Comparator() {
        @Override
        public int compare(Object t, Object t1) {
            int it = 0;
            int it1 = 0;
            SectionElement ds = ((SectionElementRef) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(t.toString())).getSectionElement();
            it = ds.getIndex();
            int ret = 0;
            SectionElement ds1 = ((SectionElementRef) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(t1.toString())).getSectionElement();
            it1 = ds1.getIndex();
            if (it > it1) {
                ret = 1;
            } else {
                ret = -1;
            }
            return ret;
        }
    };

    public Document getDom(String suri, String lang, SWBParamRequest paramRequest, HttpServletRequest request) throws TransformerConfigurationException, TransformerException, SWBResourceException {
        org.w3c.dom.Document document = SWBUtils.XML.getNewDocument();
        Process process = (Process) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(suri);
        if (process != null) {

            Element root = document.createElement("root");
            root.setAttribute("title", process.getTitle());
            document.appendChild(root);

            DocumentationInstance docInstance = DocumentationInstance.ClassMgr.listDocumentationInstanceByProcessRef(process).next();
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
                    Iterator<SectionElement> iterator = dsi.listDocuSectionElementInstances();
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
                                String value;
                                if (prop.getPropId().equals(Referable.swpdoc_file.getPropId())) {
                                    Referable referable = (Referable) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(se.getURI());
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
                                            value = "<a href=\"" + urlDownload + "\">" + referable.getRefRepository().getTitle() + " <i class=\"fa fa-download\"></i></a>";
                                            property.appendChild(document.createTextNode("\n\t\t\t" + value + "\n\t\t\t"));
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
                            SWBResourceURL urlDialogInstance = paramRequest.getRenderUrl().setCallMethod(SWBResourceURL.Call_DIRECT).setParameter("suri", suri);
                            instance.appendChild(document.createTextNode("\n\t\t\t"));
                            Element property = document.createElement("property");
                            if (scls.getClassId().equals(Activity.sclass.getClassId())) {
                                property.setAttribute("propid", Descriptiveable.swb_title.getPropId());
                                property.setAttribute("type", dsi.getSecTypeDefinition().getTitle());
                                Activity act = (Activity) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(se.getURI());
                                Iterator<SectionElementRef> itser = SWBComparator.sortSortableObject(act.listSectionElementRefs());
                                Iterator<SemanticObject> itso = paramRequest.getWebPage().getWebSite().getSemanticModel().listSubjects(SectionElementRef.swpdoc_activity, act.getSemanticObject());
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
                                    if (mapST.containsKey(serSemObj.getSectionElement().getParentSection())) {
                                        mapST.put(serSemObj.getSectionElement().getParentSection(), mapST.get(serSemObj.getSectionElement().getParentSection()) + "|" + semObj);
                                    } else {
                                        mapST.put(serSemObj.getSectionElement().getParentSection(), semObj);
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
                                        + "         <p>" + act.getActivityRef().getProcessActivity().getDescription() + "</p>\n"
                                        + "     </div>\n"
                                        + "     <div class=\"col-lg-3 col-md-3 col-sm-3\">\n";
                                if (itser.hasNext()) {

                                    value += "         <button class=\"btn btn-success pull-right collapsed\" data-toggle=\"collapse\" data-parent=\"#panel" + se.getId() + "\" href=\"#seact" + se.getId() + "\">\n"
                                            + "             <span class=\"fa fa-caret-down fa-fw\"></span>\n"
                                            + "             " + paramRequest.getLocaleString("lblRelations") + "\n"
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
                                    if (!listds.contains(sectionElementRef.getSectionElement().getParentSection())) {
                                        listds.add(sectionElementRef.getSectionElement().getParentSection());
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
                                        value += "                          <th>" + properties[a].substring(0, properties[a].indexOf(";")) + "</th><th>Ver</th></thead></tr>";
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
                                        value += "                              </td><td class=\"swbp-icon-center\"><a class=\"btn btn-default fa fa-eye\" onclick=\"showModal('" + urlDialogInstance.setMode(UserDocumentationResource.MODE_VIEW_INSTANCE).setParameter("urisei", urisei + "|" + uridsi) + "' , '" + paramRequest.getLocaleString("btnEdit") + "', '" + paramRequest.getLocaleString("msgLoadingElement") + "', '" + paramRequest.getLocaleString("msgLoadError") + "');\"></a></td></tr>";
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
                }
            }




//            Element root = document.createElement("root");
//            root.setAttribute("title", process.getTitle());
//            root.setAttribute("uri", process.getURI());
//            document.appendChild(root);
//
//
////            si es sitio de administración colocar el header
////            if (!paramRequest.getWebPage().getWebSiteId().equals(SWBContext.getAdminWebSite().getId())) {
//
//            //Model funciona sin problema
            Element model = document.createElement("model");
            root.appendChild(document.createTextNode("\n\t"));
            String mode = paramRequest.getMode();
            model.appendChild(document.createTextNode(getProcessElementModel(process.getData(), suri, mode) + "\n\t\t"));
            root.appendChild(model);
            root.appendChild(document.createTextNode("\n\t"));
//            }
//
//
//            DocumentationInstance docInstance = DocumentationInstance.ClassMgr.listDocumentationInstanceByProcessRef(process).next();
//            Map map = DocumentationInstance.getDocumentSectionInstance(docInstance);
//            ArrayList arr = DocumentationInstance.listDocumentSections(docInstance.getDocTypeDefinition(), map, docInstance, docInstance.getProcessRef().getProcessSite(), "");
//            for (int j = 0; j < arr.size(); j++) {
//                
//                DocumentSectionInstance dsi = (DocumentSectionInstance) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(arr.get(j).toString());
//                SemanticClass scls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(dsi.getSecTypeDefinition().getSectionType().getURI());
//                if (!scls.getClassId().equals(Model.sclass.getClassId())) {
//                    
//                    root.appendChild(document.createTextNode("\n\t"));
//                    Element section = document.createElement("section");
//                    section.setAttribute("uri", dsi.getURI());
//                    section.setAttribute("url", scls.getName() + dsi.getId());
//                    section.setAttribute("className", scls.getName());
//                    section.setAttribute("title", dsi.getSecTypeDefinition().getTitle());
//                    root.appendChild(section);
//                    root.appendChild(document.createTextNode("\n\t"));
//
//                    String[] visibleProps = dsi.getSecTypeDefinition().getVisibleProperties().split("\\|");
//                    Iterator<SectionElement> iterator = dsi.listDocuSectionElementInstances();
//                    while (iterator.hasNext()) {
//                        SectionElement se = iterator.next();
//                        section.appendChild(document.createTextNode("\n\t\t"));
//                        Element instance = document.createElement("instance");
//                        instance.setAttribute("uri", se.getURI());
//                        instance.setAttribute("className", scls.getName());
//                        section.appendChild(instance);
//
//                        if (!scls.getClassId().equals(FreeText.sclass.getClassId())
//                                && !scls.getClassId().equals(org.semanticwb.process.documentation.model.Activity.sclass.getClassId())) {
//                            for (int i = 0; i < visibleProps.length; i++) {
//                                String props = visibleProps[i];
//                                instance.appendChild(document.createTextNode("\n\t\t\t"));
//                                Element property = document.createElement("property");
//                                String title = props.substring(0, props.indexOf(";"));
//                                String propid = props.substring((props.indexOf(";") + 1), props.length());
//                                SemanticProperty prop = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticPropertyById(propid);
//                                property.setAttribute("propid", propid);
////                                if (se.getSemanticObject().getProperty(prop) != null && se.getSemanticObject().getProperty(prop).length() > 0) {
//                                    property.appendChild(document.createTextNode("\n\t\t\t\t" + se.getSemanticObject().getProperty(prop) + "\n\t\t\t"));
////                                }
//                                property.setAttribute("title", title);
//                                instance.appendChild(property);
//                                if (i < visibleProps.length) {
//                                    instance.appendChild(document.createTextNode("\t\t"));
//                                }
//                            }
//                            instance.appendChild(document.createTextNode("\n\t\t"));
//                        }
//                        section.appendChild(document.createTextNode("\n\t"));
//                    }
//                }
//            }



//////            Iterator<GraphicalElement> iterator = process.listAllContaineds();
//////            // get Types
//////            Map types = new HashMap();
//////            while (iterator.hasNext()) {
//////                GraphicalElement ge = iterator.next();
//////                if (ge instanceof Lane || ge instanceof Activity || ge instanceof Gateway
//////                        || ge instanceof Event || ge instanceof DataObject) {
//////                    String cls = ge.getSemanticObject().getSemanticClass().getURI();
//////                    if (types.containsKey(cls)) {
//////                        types.put(cls, types.get(cls) + "|" + ge.getURI());
//////                    } else {
//////                        types.put(cls, ge.getURI());
//////                    }
//////                }
//////            }
//////            //Add elements types
//////            Iterator it = types.entrySet().iterator();
//////            while (it.hasNext()) {
//////                Map.Entry e = (Map.Entry) it.next();
//////                SemanticClass semCls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(e.getKey().toString());
//////                Element section = document.createElement("section");
//////                root.appendChild(document.createTextNode("\n\t"));
//////                section.setAttribute("type", semCls.getName());
//////                section.setAttribute("uri", semCls.getURI());
//////                section.setAttribute("displayName", semCls.getDisplayName(lang));
//////                section.appendChild(document.createTextNode("\n\t\t"));
//////                String[] geuris = e.getValue().toString().split("\\|");
//////                section.setAttribute("size", geuris.length + "");
//////                int i = 1;
//////                for (String uri : geuris) {
//////                    Element element = document.createElement("element");
//////                    ProcessElement pe = (ProcessElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uri);
//////                    element.setAttribute("type", semCls.getName());
//////                    element.setAttribute("title", pe.getTitle() != null ? pe.getTitle() : "");
//////                    element.setAttribute("uri", pe.getURI());
//////                    element.appendChild(document.createTextNode("\n\t\t\t" + pe.listDocumentations().next().getText() + "\n\t\t"));
//////                    section.appendChild(element);
//////                    if (i < geuris.length) {
//////                        section.appendChild(document.createTextNode("\n\t\t"));
//////                    }
//////                    i++;
//////                }
//////                section.appendChild(document.createTextNode("\n\t"));
//////                root.appendChild(section);
//////            }
//////            root.appendChild(document.createTextNode("\n"));

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

    public static String getProcessElementModel(String data, String uri, String mode) {
        ProcessElement pe = (ProcessElement) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(uri);
        String model =
                //        String model = "        <link href=\"" + SWBPortal.getContextPath() + "/swbadmin/css/bootstrap/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                //                + "        <link href=\"" + SWBPortal.getContextPath() + "/swbadmin/css/fontawesome/font-awesome.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                //+ "        <link href=\"" + SWBPortal.getContextPath() + "/swbadmin/jsp/process/commons/css/swbp.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                "        <link href=\"" + SWBPortal.getContextPath() + "/swbadmin/jsp/process/modeler/images/modelerFrame.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                //                + "        <script src=\"" + SWBPortal.getContextPath() + "/swbadmin/js/jquery/jquery.js\"></script>\n"
                + "        <script type=\"text/javascript\" src=\"" + SWBPortal.getContextPath() + "/swbadmin/jsp/process/modeler/toolkit.js\"></script>\n"
                + "        <script type=\"text/javascript\" src=\"" + SWBPortal.getContextPath() + "/swbadmin/jsp/process/modeler/modeler.js\"></script>\n";
//                + "        <script src=\"" + SWBPortal.getContextPath() + "/swbadmin/js/bootstrap/bootstrap.js\"></script>\n";
        if (mode.equals("export")) {
            model = "        <link href=\"css/bootstrap/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                    + "        <link href=\"css/fontawesome/font-awesome.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                    + "        <link href=\"css/swbp.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                    + "        <link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                    + "        <link href=\"css/modeler/modelerFrame.css\" rel=\"stylesheet\" type=\"text/css\"></link>\n"
                    + "        <script src=\"js/jquery/jquery.js\"></script>\n"
                    + "        <script type=\"text/javascript\" src=\"js/modeler/toolkit.js\"></script>\n"
                    + "        <script type=\"text/javascript\" src=\"js/modeler/modeler.js\"></script>\n"
                    + "        <script src=\"js/bootstrap/bootstrap.js\"></script>\n";
        }
        model += "<ul class=\"list-unstyled list-inline hidden-print visible-lg text-center\">\n"
                + "    <li>\n"
                + "        <a href=\"#\" class=\"btn btn-default\" data-placement=\"bottom\" data-toggle=\"tooltip\" data-original-title=\"Zoom in\" onclick=\"zoomin();return false;\"><i class=\"fa fa-search-plus\"></i></a>\n"
                + "    </li>\n"
                + "    <li>\n"
                + "        <a href=\"#\" class=\"btn btn-default\" data-placement=\"bottom\" data-toggle=\"tooltip\" data-original-title=\"Zoom out\" onclick=\"zoomout();return false;\"><i class=\"fa fa-search-minus\"></i></a>\n"
                + "    </li>\n"
                + "    <li>\n"
                + "        <a href=\"#\" class=\"btn btn-default\" data-placement=\"bottom\" data-toggle=\"tooltip\" data-original-title=\"Reset zoom\" onclick=\"resetZoom();return false;\"><i class=\"fa fa-desktop\"></i></a>\n"
                + "    </li>\n"
                + "    <li>\n"
                + "        <a href=\"#\" class=\"btn btn-default\" data-placement=\"bottom\" data-toggle=\"tooltip\" data-original-title=\"Pan left\" onclick=\"handlePanning('left');return false;\"><i class=\"fa fa-arrow-left\"></i></a>\n"
                + "    </li>\n"
                + "    <li>\n"
                + "        <a href=\"#\" class=\"btn btn-default\" data-placement=\"bottom\" data-toggle=\"tooltip\" data-original-title=\"Pan down\" onclick=\"handlePanning('down');return false;\"><i class=\"fa fa-arrow-down\"></i></a>\n"
                + "    </li>\n"
                + "    <li>\n"
                + "        <a href=\"#\" class=\"btn btn-default\" data-placement=\"bottom\" data-toggle=\"tooltip\" data-original-title=\"Pan up\" onclick=\"handlePanning('up');return false;\"><i class=\"fa fa-arrow-up\"></i></a>\n"
                + "    </li>\n"
                + "    <li>\n"
                + "        <a href=\"#\" class=\"btn btn-default\" data-placement=\"bottom\" data-toggle=\"tooltip\" data-original-title=\"Pan right\" onclick=\"handlePanning('right');return false;\"><i class=\"fa fa-arrow-right\"></i></a>\n"
                + "    </li>\n"
                + "</ul>\n"
                + "              <svg id=\"modeler\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"100\" height=\"100\" viewBox=\"0 0 1200 800\" class=\"modeler\">\n"
                + "                <style type=\"text/css\"><![CDATA[\n"
                + "                    /*.resizeBox {\n"
                + "                        stroke:#008000;\n"
                + "                        fill:url(#linearGradientStartEvent);\n"
                + "                        stroke-width:1.5;  \n"
                + "                    }*/\n"
                + "                    .task {\n"
                + "                        stroke:#79adc8;\n"
                + "                        fill:url(#linearGradientTask);\n"
                + "                        stroke-width:2;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n";
        if (pe instanceof SubProcess) {// si es SubProcess
            model += "             var obj = Modeler.getGraphElementByURI(null, \"" + uri + "\");\n"
                    + "             ToolKit.setLayer(obj.subLayer);\n";
        }
        model += "                    .task_o {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill:url(#linearGradientTask);\n"
                + "                        stroke-width:2;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "                    .callactivity {\n"
                + "                        stroke:#79adc8;\n"
                + "                        fill:url(#linearGradientTask);\n"
                + "                        stroke-width:4;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .callactivity_o {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill:url(#linearGradientTask);\n"
                + "                        stroke-width:4;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "                    .eventsubTask {\n"
                + "                        stroke:#79adc8;\n"
                + "                        fill:url(#linearGradientTask);\n"
                + "                        stroke-width:2;\n"
                + "                        cursor:pointer;\n"
                + "                        stroke-dasharray:6,4;\n"
                + "                    }\n"
                + "\n"
                + "                    .eventsubTask_o {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill:url(#linearGradientTask);\n"
                + "                        stroke-width:2;\n"
                + "                        cursor:pointer;\n"
                + "                        stroke-dasharray:6,4;\n"
                + "                    }\n"
                + "                    .startEvent\n"
                + "                    {\n"
                + "                        stroke:#008000;\n"
                + "                        fill:url(#linearGradientStartEvent);\n"
                + "                        stroke-width:1.5;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .startEvent_o\n"
                + "                    {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill:url(#linearGradientStartEvent);\n"
                + "                        stroke-width:1.5;\n"
                + "                        cursor: pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .intermediateInterruptingEvent\n"
                + "                    {\n"
                + "                        stroke:#2c5aa0;\n"
                + "                        fill:url(#linearGradientIntermediateEvent);\n"
                + "                        fill-opacity:1;\n"
                + "                        stroke-width:1.5;\n"
                + "                        stroke-dasharray: 3,3;\n"
                + "                        /*stroke-dashoffset: 5;*/\n"
                + "                    }\n"
                + "\n"
                + "                    .intermediateInterruptingEvent_o\n"
                + "                    {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill:url(#linearGradientIntermediateEvent);\n"
                + "                        fill-opacity:1;\n"
                + "                        stroke-width:1.5;\n"
                + "                        stroke-dasharray: 3,3;\n"
                + "                        /*stroke-dashoffset: 5;*/\n"
                + "                    }\n"
                + "\n"
                + "                    .intermediateEvent\n"
                + "                    {\n"
                + "                        stroke:#2c5aa0;\n"
                + "                        fill:url(#linearGradientIntermediateEvent);\n"
                + "                        fill-opacity:1;\n"
                + "                        stroke-width:1.5;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .intermediateEvent_o\n"
                + "                    {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill:url(#linearGradientIntermediateEvent);\n"
                + "                        fill-opacity:1;\n"
                + "                        stroke-width:1.5;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "                    .endEvent\n"
                + "                    {\n"
                + "                        stroke:#550000;\n"
                + "                        fill:url(#linearGradientEndEvent);\n"
                + "                        stroke-width:2.5;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .endEvent_o\n"
                + "                    {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill:url(#linearGradientEndEvent);\n"
                + "                        stroke-width:2.5;\n"
                + "                        cursor:pointer;\n"
                + "                    }    \n"
                + "\n"
                + "                    .gateway\n"
                + "                    {\n"
                + "                        stroke:#d4aa00;\n"
                + "                        fill:url(#linearGradientGateway);\n"
                + "                        stroke-width:2;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .gateway_o\n"
                + "                    {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill:url(#linearGradientGateway);\n"
                + "                        stroke-width:2;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .sequenceFlowSubLine {\n"
                + "                        fill:none;\n"
                + "                        stroke:#ffffff;\n"
                + "                        stroke-opacity:0.1;\n"
                + "                        stroke-width:8;\n"
                + "                        cursor: pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .sequenceFlowSubLine_o {\n"
                + "                        fill:none;\n"
                + "                        stroke:#2cff20;\n"
                + "                        stroke-width:8;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .swimlane\n"
                + "                    {\n"
                + "                        fill: #E8E8FF;\n"
                + "                        stroke-width:2;\n"
                + "                        stroke: #ADADAE;\n"
                + "                        fill-opacity:1;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .swimlane_o\n"
                + "                    {\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill: #E8E8FF;\n"
                + "                        stroke-width:2;\n"
                + "                        fill-opacity:1;\n"
                + "                        cursor: pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .sequenceFlowLine {\n"
                + "                        fill: none;\n"
                + "                        stroke-width: 2;\n"
                + "                        stroke: #000000;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .sequenceFlowLine_o {\n"
                + "                        fill: none;\n"
                + "                        stroke-width: 2;\n"
                + "                        stroke: #2cff20;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .intermediateEvent1\n"
                + "                    {\n"
                + "                        stroke:#2c5aa0;\n"
                + "                        fill:none;\n"
                + "                        stroke-width:1;\n"
                + "                    }\n"
                + "\n"
                + "                    .itemaware {\n"
                + "                        fill:url(#linearGradientDataObject);\n"
                + "                        stroke:#666666;\n"
                + "                        stroke-width:2;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .itemaware_o {\n"
                + "                        fill:url(#linearGradientDataObject);\n"
                + "                        stroke:#2cff20;\n"
                + "                        stroke-width:2;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .transactionSquare {\n"
                + "                        stroke:#2c5aa0;\n"
                + "                        cursor:pointer;\n"
                + "                        fill:none;\n"
                + "                        stroke-width:1.5;\n"
                + "                    }\n"
                + "\n"
                + "                    .group {\n"
                + "                        stroke:#2c5aa0;\n"
                + "                        cursor:pointer;\n"
                + "                        fill:none;\n"
                + "                        stroke-width:1.5;\n"
                + "                    }\n"
                + "\n"
                + "                    .annotationArtifact {\n"
                + "                        fill:none;\n"
                + "                        stroke:#000000;\n"
                + "                        stroke-width:1.5px;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .annotationArtifactRect {\n"
                + "                        fill:none;\n"
                + "                        stroke:none;\n"
                + "                        fill:#E6E6E6;\n"
                + "                        fill-opacity:0.2;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .annotationArtifactRect_o {\n"
                + "                        fill:none;\n"
                + "                        fill:#E6E6E6;\n"
                + "                        stroke:#2cff20;\n"
                + "                        fill-opacity:0.2;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .group_o {\n"
                + "                        stroke:#2cff20;\n"
                + "                        cursor:pointer;\n"
                + "                        fill:none;\n"
                + "                        stroke-width:1.5;\n"
                + "                    }\n"
                + "\n"
                + "                    .taskMarker\n"
                + "                    {\n"
                + "                        stroke:#2c5aa0;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .pathMarker{\n"
                + "                        fill:none;\n"
                + "                        stroke-width:1;\n"
                + "                    }\n"
                + "\n"
                + "                    .startMarker{\n"
                + "                        stroke:#008000;\n"
                + "                        fill:none;\n"
                + "                        stroke-width:2.5;                        \n"
                + "                    }\n"
                + "\n"
                + "                    .startMarker{\n"
                + "                        stroke:#008000;\n"
                + "                        fill:none;\n"
                + "                        stroke-width:2.5;                        \n"
                + "                    }\n"
                + "\n"
                + "                    .startFilledMarker{\n"
                + "                        fill:#008000;\n"
                + "                        stroke:none;\n"
                + "                        stroke-width:1;\n"
                + "                    }\n"
                + "\n"
                + "                    .intermediateMarker{\n"
                + "                        stroke:#2c5aa0;\n"
                + "                        fill:#ffffff;\n"
                + "                        fill-opacity:0.1;\n"
                + "                        stroke-width:2.5;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .intermediateFilledMarker {\n"
                + "                        fill:#2c5aa0;\n"
                + "                        stroke:#2c5aa0;\n"
                + "                        stroke-width:1;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .endFilledMarked\n"
                + "                    {\n"
                + "                        fill:#550000;   \n"
                + "                        stroke:#550000;\n"
                + "                        stroke-width:1;\n"
                + "                        cursor:pointer;\n"
                + "                    }\n"
                + "\n"
                + "                    .navPath {\n"
                + "                        fill:#e7e7e7;\n"
                + "                        stroke:gray;\n"
                + "                        stroke-width:1;\n"
                + "                    }\n"
                + "\n"
                + "                    .navPathHidden {\n"
                + "                        fill:#e7e7e7;\n"
                + "                        stroke:gray;\n"
                + "                        stroke-width:1;\n"
                + "                        display:none;\n"
                + "                    }\n"
                + "                    ]]></style>\n"
                + "                <defs id=\"globalDef\">\n"
                + "                <!--Definición de gradientes para las figuras-->\n"
                + "                <linearGradient id=\"linearGradientStartEvent\" x1=\"0%\" y1=\"0%\" x2=\"100%\" y2=\"100%\">\n"
                + "                <stop offset=\"10%\" style=\"stop-color:#ffffff;stop-opacity:1\" />\n"
                + "                <stop offset=\"90%\" style=\"stop-color:#ccffaa;stop-opacity:1\" />\n"
                + "                </linearGradient>\n"
                + "                <linearGradient id=\"linearGradientEndEvent\" x1=\"0%\" y1=\"0%\" x2=\"100%\" y2=\"100%\">\n"
                + "                <stop offset=\"10%\" style=\"stop-color:#ffffff;stop-opacity:1\" />\n"
                + "                <stop offset=\"90%\" style=\"stop-color:#e7c1c1;stop-opacity:1\" />\n"
                + "                </linearGradient>\n"
                + "                <linearGradient id=\"linearGradientTask\" x1=\"100%\" y1=\"0%\" x2=\"100%\" y2=\"100%\">\n"
                + "                <stop offset=\"10%\" style=\"stop-color:#ffffff;stop-opacity:1\" />\n"
                + "                <stop offset=\"100%\" style=\"stop-color:#c1d3e1;stop-opacity:1\" />\n"
                + "                </linearGradient>\n"
                + "                <linearGradient id=\"linearGradientIntermediateEvent\" x1=\"0%\" y1=\"0%\" x2=\"100%\" y2=\"100%\">\n"
                + "                <stop offset=\"10%\" style=\"stop-color:#ffffff;stop-opacity:1\" />\n"
                + "                <stop offset=\"100%\" style=\"stop-color:#87aade;stop-opacity:1\" />\n"
                + "                </linearGradient>\n"
                + "                <linearGradient id=\"linearGradientGateway\" x1=\"0%\" y1=\"0%\" x2=\"100%\" y2=\"100%\">\n"
                + "                <stop offset=\"10%\" style=\"stop-color:#FFFDE2;stop-opacity:1\" />\n"
                + "                <stop offset=\"100%\" style=\"stop-color:#FFFAA6;stop-opacity:1\" />\n"
                + "                </linearGradient>\n"
                + "                <linearGradient id=\"linearGradientDataObject\" x1=\"0%\" y1=\"0%\" x2=\"100%\" y2=\"100%\">\n"
                + "                <stop offset=\"10%\" style=\"stop-color:#ffffff;stop-opacity:1\" />\n"
                + "                <stop offset=\"100%\" style=\"stop-color:#afbac7;stop-opacity:1\" />\n"
                + "                </linearGradient>\n"
                + "                <circle id=\"resizeBox\" cx=\"0\" cy=\"0\" r=\"5\" class=\"resizeBox\"/>\n"
                + "                <!--definición de marcadores para las figuras-->\n"
                + "                <path id=\"errorMarker\" d=\"m 0.5,1051.8622 17.0774,-39.6847 15.0444,21.9792 19.5171,-27.474 L 34.8582,1048.199 19.8137,1029.6795 0.5,1051.8622 z\" transform=\"scale(0.35,0.35) translate(-26, -1030)\" />\n"
                + "                <g id=\"ruleMarker\">\n"
                + "                <path d=\"m 0,0 0,43.9063 46.75,0 0,-43.9063 z m 4.25,9.875 18.5,0 0,4.8438 -18.5,0 z m 20,0 18.5,0 0,4.8438 -18.5,0 z m -20,8 18.5,0 0,4.8438 -18.5,0 z m 20,0 18.5,0 0,4.8438 -18.5,0 z m -20,8 18.5,0 0,4.8438 -18.5,0 z m 20,0 18.5,0 0,4.8438 -18.5,0 z m -20,8 18.5,0 0,4.8438 -18.5,0 z m 20,0 18.5,0 0,4.8438 -18.5,0 z\" transform=\"scale(0.32,0.32) translate(-23.5,-23.5)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"taskRuleMarker\" class=\"intermediateFilledMarker\">\n"
                + "                <path d=\"m 0,0 0,43.9063 46.75,0 0,-43.9063 z m 4.25,9.875 18.5,0 0,4.8438 -18.5,0 z m 20,0 18.5,0 0,4.8438 -18.5,0 z m -20,8 18.5,0 0,4.8438 -18.5,0 z m 20,0 18.5,0 0,4.8438 -18.5,0 z m -20,8 18.5,0 0,4.8438 -18.5,0 z m 20,0 18.5,0 0,4.8438 -18.5,0 z m -20,8 18.5,0 0,4.8438 -18.5,0 z m 20,0 18.5,0 0,4.8438 -18.5,0 z\" transform=\"scale(0.32,0.32) translate(-23.5,-23.5)\"/>\n"
                + "                </g>\n"
                + "                <path id=\"multipleMarker\" d=\"m 0,0 -33.73973,0.37343 -10.78131,-31.973 27.07653,-20.13383 27.51552,19.52961 z\" transform=\"scale(0.35,0.35) translate(17,23)\" />\n"
                + "                <path id=\"parallelMarker\" d=\"m 0,0 0,19.4788 -19.45519,0 0,10.5627 19.45519,0 0,19.4787 10.58626,0 0,-19.4787 19.47871,0 0,-10.5627 -19.47871,0 0,-19.4788 z\" transform=\"scale(0.35,0.35) translate(-5, -25)\"/>\n"
                + "                <path id=\"linkMarker\" d=\"m 0,0 -20.432,-24.2007 0,16.9761 -25.0594,0 0,14.1289 25.0594,0 0,17.332 z\" transform=\"scale(0.35,0.35) translate(23,0)\"/>\n"
                + "                <path id=\"complexMarker\" d=\"m 0,0 0,15.875 -11.25,-11.2187 -2.34375,2.3125 11.25,11.25 -15.90625,0 0,3.3125 15.90625,0 -11.25,11.2187 2.34375,2.3438 11.25,-11.2188 0,15.875 3.28125,0 0,-15.9062 11.25,11.25 2.3125,-2.3438 -11.21875,-11.2187 15.875,0 0,-3.3125 -15.875,0 11.21875,-11.2188 -2.3125,-2.3437 -11.25,11.25 0,-15.9063 z\" transform=\"translate(-1, -20)\"/>\n"
                + "                <path id=\"signalMarker\" d=\"m 0,0 -23.59924,0 -23.59925,0 11.79962,-20.43755 11.79963,-20.43755 11.79962,20.43755 z\" transform=\"scale(0.35,0.35) translate(24,14)\"/>\n"
                + "                <path id=\"scalationMarker\" transform=\"scale(0.35,0.35) translate(0,-26)\" d=\"m 0,0 -21.34041,47.167 21.34041,-17.3811 21.340402,17.3811 -21.340402,-47.167 z\" />\n"
                + "                <path id=\"cancelMarker\" d=\"m 0,0 -18.3627,18.3627 -18.3848004,-18.3848 -3.3366,3.3367 18.3847004,18.3847 -18.3847004,18.3848 3.3366,3.3367 18.3848004,-18.3848 18.3627,18.3627 3.3366,-3.3367 -18.3626,-18.3627 18.3626,-18.3626 z\" transform=\"scale(0.35,0.35) translate(18, -22)\"/>\n"
                + "                <g id=\"manualMarker\" class=\"taskMarker\" transform=\"scale(0.65)\">\n"
                + "                <path d=\"m 0,0 c -0.27155,0 -0.5225,0.072 -0.75179,0.1792 -0.007,0 -0.0189,0 -0.0259,0 -1.25518,0.3798 -2.16186,4.1013 -2.9035,6.1425 -0.22877,0.5536 -0.36294,1.1549 -0.36294,1.7916 l 0,4.7861 c 0,2.6228 2.13932,4.7348 4.79597,4.7348 l 5.0552,0 5.69034,0 7.72539,0 c 0.97664,0 1.76284,-0.7762 1.76284,-1.7404 0,-0.9641 -0.7862,-1.7403 -1.76284,-1.7403 l -3.11089,0 c 0.0118,-0.043 0.0283,-0.085 0.0389,-0.128 l 4.16081,0 c 0.97664,0 1.76285,-0.7762 1.76285,-1.7404 0,-0.9642 -2.73949,-1.7404 -1.76285,-1.7404 l -4.01823,0 0,-0.064 5.23667,0 c 0.97664,0 1.76284,-0.7762 1.76284,-1.7404 0,-0.9641 -0.7862,-1.7403 -1.76284,-1.7403 l -5.39221,0 c -0.0116,-0.046 -0.0132,-0.095 -0.0259,-0.1408 l 7.56984,0 c 0.97663,0 1.76284,-0.7762 1.76284,-1.7404 0,-0.9642 -0.78621,-1.7404 -1.76284,-1.7404 l -12.18433,0 -0.92031,0 c 0.69854,-0.2311 1.20547,-0.8686 1.20547,-1.638 0,-0.9642 -0.7862,-1.7404 -1.76284,-1.7404 l -10.01967,0 z\" class=\"pathMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"messageThrowMarker\" transform=\"scale(1.3,1.3) translate(-7.5,-4.5)\" >\n"
                + "                <path d=\"m 0,0 0,9.175062 14.84706,0 0,-9.175062 -0.0245,0 -7.40716,4.648917 -7.41535,-4.648917 z\" style=\"stroke:#ffffff;stroke-opacity:0.4\"/>\n"
                + "                <path d=\"m 0.6,0 6.88839,4.318911 6.88838,-4.318911\" style=\"stroke:#ffffff;stroke-opacity:0.4\"/>\n"
                + "                </g>\n"
                + "                <g id=\"taskMessageThrowMarker\" transform=\"scale(1.3,1.3) translate(-7.5,-4.5)\" class=\"intermediateFilledMarker\">\n"
                + "                <path d=\"m 0,0 0,9.175062 14.84706,0 0,-9.175062 -0.0245,0 -7.40716,4.648917 -7.41535,-4.648917 z\" style=\"stroke:#ffffff;stroke-opacity:0.4\"/>\n"
                + "                <path d=\"m 0.6,0 6.88839,4.318911 6.88838,-4.318911\" style=\"stroke:#ffffff;stroke-opacity:0.4\"/>\n"
                + "                </g>\n"
                + "                <g id=\"messageCatchMarker\" transform=\"scale(0.35,0.35) translate(-27,-17)\" >\n"
                + "                <rect width=\"52.704407\" height=\"32.573116\" x=\"0\" y=\"0\"/>\n"
                + "                <path d=\"m 0,0 26.30906,16.49536 26.30905,-16.49536\"/>\n"
                + "                </g>\n"
                + "                <g id=\"taskMessageCatchMarker\" transform=\"scale(0.35,0.35) translate(-27,-17)\" class=\"intermediateMarker\">\n"
                + "                <rect width=\"52.704407\" height=\"32.573116\" x=\"0\" y=\"0\"/>\n"
                + "                <path d=\"m 0,0 26.30906,16.49536 26.30905,-16.49536\"/>\n"
                + "                </g>\n"
                + "                <g id=\"compensaMarker\" transform=\"scale(0.35,0.35) translate(-2,-13)\">\n"
                + "                <path d=\"m 0,0 -28.36636,0 14.18318,-24.56599 z\" transform=\"matrix(0,-1,1,0,0,0)\"/>\n"
                + "                <path d=\"m 0,0 -28.36636,0 14.18318,-24.56599 z\" transform=\"matrix(0,-1,1,0,23,0)\"/>\n"
                + "                </g>\n"
                + "                <filter id=\"dropshadow\" height=\"130%\">\n"
                + "                    <feGaussianBlur in=\"SourceAlpha\" stdDeviation=\"3\"/> \n"
                + "                    <feOffset dx=\"0\" dy=\"0\" result=\"offsetblur\"/>\n"
                + "                    <feComponentTransfer>\n"
                + "                        <feFuncA type=\"linear\" slope=\"0.5\"/>\n"
                + "                    </feComponentTransfer>\n"
                + "                    <feMerge> \n"
                + "                        <feMergeNode/>\n"
                + "                        <feMergeNode in=\"SourceGraphic\"/> \n"
                + "                    </feMerge>\n"
                + "                </filter>\n"
                + "                <g id=\"timerMarker\" transform=\"scale(0.35,0.35) translate(-396,-219)\" >\n"
                + "                <path d=\"m 232.18784,217.1541 a 40.089989,40.089989 0 1 1 -80.17997,0 40.089989,40.089989 0 1 1 80.17997,0 z\" transform=\"matrix(0.6346099,0,0,0.6346099,274.19065,81.345956)\"/>\n"
                + "                <path d=\"m 421.49964,219.1541 -8.76968,0\"/>\n"
                + "                <path d=\"m 396.40906,193.78618 -0.0964,8.76915\"/>\n"
                + "                <path d=\"m 370.76392,218.75252 8.76858,0.13882\"/>\n"
                + "                <path d=\"m 396.04976,244.52342 0.0278,-8.76963\"/>\n"
                + "                <path d=\"m 408.75323,219.1541 -12.86938,0\"/>\n"
                + "                <path d=\"m 401.39526,204.36536 -4.8964,14.70678\"/>\n"
                + "                <path d=\"m 414.26521,201.41346 -6.26889,6.13256\"/>\n"
                + "                <path d=\"m 413.87083,237.28912 -6.13256,-6.26889\"/>\n"
                + "                <path d=\"m 377.99517,236.89474 6.26889,-6.13256\"/>\n"
                + "                <path d=\"m 378.38955,201.01908 6.13256,6.26889\"/>\n"
                + "                </g>\n"
                + "                <g id=\"userMarker\" class=\"taskMarker\" transform=\"scale(0.8) translate(0,-1042)\">\n"
                + "                <path d=\"m 0,1039 c -3.3158023,0 -6,2.8058 -6,6.25 0,2.0045 0.9288788,3.7654 2.34375,4.9062 -3.3002786,1.7745 -5.6842907,5.8793 -6,10.75 l 19.3125,0 c -0.3158218,-4.8705 -2.7074728,-8.9755 -6,-10.75 C 5.0711212,1049.0154 6,1047.2545 6,1045.25 6,1041.8058 3.3158023,1039 0,1039 z\" class=\"pathMarker\" />\n"
                + "                <path d=\"m -377.4453,473.79996 a 9.3826418,9.3826418 0 0 1 -12.52292,0.66553\" transform=\"matrix(0.6398828,0,0,0.66465394,245.76439,734.73647)\" class=\"pathMarker\" />\n"
                + "                <path d=\"m -5.1190626,1060.732 0,-3.8413\" class=\"pathMarker\"/>\n"
                + "                <path d=\"m 5.1190626,1060.732 0,-3.8413\" class=\"pathMarker\" />\n"
                + "                <path d=\"m 0.06204402,1039.6046 c -3.10856302,0 -5.66539592,2.4352 -5.68305502,5.5312 0.8963285,-1.0836 1.7094089,-1.6888 3.0835319,-1.6888 2.07094865,0 4.4437605,1.0758 6.2188589,0.7688 0.570252,-0.099 1.2624582,-0.3804 1.939646,-0.7272 -0.6073258,-2.271 -3.0493799,-3.884 -5.55898178,-3.884 z\" style=\"fill:#2c5aa0;stroke-width:1;\" />\n"
                + "                </g>\n"
                + "                <g id=\"serviceMarker\" class=\"taskMarker\" transform=\"scale(0.8)\">\n"
                + "                <path d=\"m 0,0 1.478093,-2.5912 c -0.762317,-0.4662 -1.601697,-0.8229 -2.495483,-1.0366 l -0.767843,2.8793 -2.495483,0 -0.787044,-2.8793 c -0.895236,0.2142 -1.733065,0.5691 -2.495483,1.0366 l 1.487693,2.5721 -1.766033,1.7659 -2.581869,-1.478 c -0.467511,0.7623 -0.822527,1.6003 -1.036583,2.4952 l 2.86981,0.7678 0,2.5048 -2.860209,0.7775 c 0.21606,0.8875 0.562993,1.7267 1.026982,2.4856 l 2.562669,-1.478\" class=\"pathMarker\" />\n"
                + "                <path d=\"m 0,0 c -0.989621,0.2366 -1.915788,0.629 -2.758588,1.1457 l 1.644541,2.8433 -1.952235,1.9521 -2.85408,-1.6338 c -0.516803,0.8427 -0.909247,1.769 -1.145882,2.7583 l 3.172388,0.8488 0,2.769 -3.161774,0.8593 c 0.238849,0.9811 0.622356,1.9088 1.135268,2.7477 l 2.832859,-1.6337 1.952235,1.952 -1.633935,2.8539 c 0.84654,0.5219 1.774518,0.918 2.769203,1.1564 l 0.848804,-3.1828 2.758588,0 0.870025,3.1828 c 0.988288,-0.238 1.914849,-0.6289 2.758588,-1.1459 l -1.644542,-2.8431 1.952236,-1.9521 2.864694,1.6337 c 0.515612,-0.8389 0.908032,-1.7642 1.145874,-2.7477 l -3.182994,-0.8593 0,-2.769 3.182994,-0.87 c -0.237717,-0.9884 -0.628065,-1.9158 -1.145874,-2.7583 l -2.854088,1.655 -1.952235,-1.9521 1.633935,-2.8645 c -0.842691,-0.5152 -1.77056,-0.9096 -2.758588,-1.1457 l -0.848804,3.1828 -2.758588,0 z m 2.238708,5.697 c 1.994007,0 3.607392,1.6133 3.607392,3.6072 0,1.9938 -1.613385,3.6176 -3.607392,3.6176 -1.994007,0 -3.607392,-1.6238 -3.607392,-3.6176 0,-1.9939 1.613385,-3.6072 3.607392,-3.6072 z\" class=\"pathMarker\" />\n"
                + "                </g>\n"
                + "                <g id=\"scriptMarker\" class=\"taskMarker\"  transform=\"scale(0.7)\">\n"
                + "                <path d=\"m 0,0 c 3.5628793,2.98 3.5212524,7.9199 0,10.8403 l 0,0.1186 13.5952063,-10e-4 c 4.246065,-3.0502 4.698752,-8.6843 0.03211,-12.4597 -1.719778,-1.441 -2.097829,-3.2691 -2.297848,-5.5453 0,-1.9186 0.795633,-3.6321 2.05209,-4.7945 l -9.1791045,0 c -7.65536286,-0.3702 -8.7288909,8.3174 -4.2024826,11.8414 z\" class=\"pathMarker\" />\n"
                + "                <path d=\"m 0,-8 9.5315769,0\" class=\"pathMarker\" />\n"
                + "                <path d=\"m -0.5,-4.1568 9.5315769,0\" class=\"pathMarker\" />\n"
                + "                <path d=\"m 2.5,0 9.5315769,0\" class=\"pathMarker\" />\n"
                + "                <path d=\"m 4.5,4 9.5315768,0\" class=\"pathMarker\" />\n"
                + "                <path d=\"m 4,8 9.5315768,0\" class=\"pathMarker\" />\n"
                + "                </g>\n"
                + "                <g id=\"subProcessMarker\" class=\"taskMarker\">\n"
                + "                <rect x=\"-7\" y=\"-7\" width=\"14\" height=\"14\" style=\"fill:#ffffff;fill-opacity:0.1;\"/>\n"
                + "                <path d=\"M-5 0 L5 0 M0 -5 L0 5\"/>\n"
                + "                </g>\n"
                + "                <marker id=\"sequenceArrow\" viewBox=\"0 0 12 12\" refX=\"0\" refY=\"5\" markerUnits=\"userSpaceOnUse\" markerWidth=\"10\" markerHeight=\"10\" orient=\"auto\" fill=\"black\" stroke=\"none\" stroke-dasharray=\"0\">\n"
                + "                    <path d=\"M 0 0 L 10 5 L 0 10\"/>\n"
                + "                </marker>\n"
                + "                <marker id=\"messageArrow\" viewBox=\"0 0 12 12\" refX=\"7\" refY=\"5\" markerUnits=\"userSpaceOnUse\" markerWidth=\"10\" markerHeight=\"10\" orient=\"auto\" fill=\"none\" stroke=\"black\" stroke-width=\"1.5\" stroke-dasharray=\"0\">\n"
                + "                    <path d=\"M 0 0 L 10 5 L 0 10\"/>\n"
                + "                </marker>\n"
                + "                <marker id=\"conditionTail\" viewBox=\"-6 -6 12 12\" refX=\"3\" refY=\"0\" markerUnits=\"userSpaceOnUse\" markerWidth=\"12\" markerHeight=\"12\" orient=\"auto\" fill=\"none\" stroke=\"black\" stroke-width=\"1.5\" stroke-dasharray=\"0\">\n"
                + "                    <rect x=\"-3\" y=\"-3\" width=\"6\" height=\"6\" transform=\"rotate(-45)\"/>\n"
                + "                </marker>\n"
                + "                <marker id=\"defaultTail\" viewBox=\"0 0 12 12\" refX=\"-3\" refY=\"5\" markerUnits=\"userSpaceOnUse\" markerWidth=\"10\" markerHeight=\"10\" orient=\"auto\" fill=\"none\" stroke=\"black\" stroke-width=\"2\" stroke-dasharray=\"0\">\n"
                + "                    <path d=\"M 5 0 L 0 10\"/>\n"
                + "                </marker>\n"
                + "                <marker id=\"messageTail\" viewBox=\"-5 -5 12 12\" refX=\"3\" refY=\"0\" markerUnits=\"userSpaceOnUse\" markerWidth=\"10\" markerHeight=\"10\" orient=\"auto\" fill=\"none\" stroke=\"black\" stroke-width=\"1.5\" stroke-dasharray=\"0\">\n"
                + "                    <circle r=\"3\" />\n"
                + "                </marker>\n"
                + "                <!--Definición de eventos iniciales-->\n"
                + "                <circle id=\"startEvent\" r=\"15\" bclass=\"startEvent\" oclass=\"startEvent_o\"/>\n"
                + "                <g id=\"messageStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#messageCatchMarker\" x=\"0\" y=\"0\" class=\"startMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"timerStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#timerMarker\" x=\"0\" y=\"0\" class=\"startMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"ruleStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#ruleMarker\" x=\"0\" y=\"0\" class=\"startFilledMarker\" transform=\"scale(1.1)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"signalStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#signalMarker\" x=\"0\" y=\"0\" class=\"startMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"multipleStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#multipleMarker\" x=\"0\" y=\"0\" class=\"startMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"parallelStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#parallelMarker\" x=\"0\" y=\"0\" class=\"startMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"scalationStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#scalationMarker\" x=\"0\" y=\"0\" class=\"startMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"errorStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#errorMarker\" x=\"0\" y=\"0\" class=\"startMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"compensationStartEvent\" bclass=\"startEvent\" oclass=\"startEvent_o\">\n"
                + "                <use xlink:href=\"#startEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#compensaMarker\" x=\"0\" y=\"0\" class=\"startMarker\"/>\n"
                + "                </g>\n"
                + "                <!--Definición de eventos intermedios-->\n"
                + "                <g id=\"intermediateEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <circle r=\"15\"/>\n"
                + "                <circle r=\"12\" class=\"intermediateEvent1\"/>\n"
                + "                </g>\n"
                + "                <g id=\"messageIntermediateCatchEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#messageCatchMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"messageIntermediateThrowEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#messageThrowMarker\" x=\"0\" y=\"0\" class=\"intermediateFilledMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"timerIntermediateEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#timerMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"errorIntermediateEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#errorMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"cancelIntermediateEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#cancelMarker\" x=\"0\" y=\"0\" class=\"intermediateFilledMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"compensationIntermediateCatchEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#compensaMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"compensationIntermediateThrowEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#compensaMarker\" x=\"0\" y=\"0\" class=\"intermediateFilledMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"ruleIntermediateEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#ruleMarker\" x=\"0\" y=\"0\" class=\"intermediateFilledMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"linkIntermediateCatchEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#linkMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"linkIntermediateThrowEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#linkMarker\" x=\"0\" y=\"0\" class=\"intermediateFilledMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"signalIntermediateCatchEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#signalMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"signalIntermediateThrowEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#signalMarker\" x=\"0\" y=\"0\" class=\"intermediateFilledMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"multipleIntermediateCatchEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#multipleMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"multipleIntermediateThrowEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#multipleMarker\" x=\"0\" y=\"0\" class=\"intermediateFilledMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"scalationIntermediateCatchEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#scalationMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"scalationIntermediateThrowEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#scalationMarker\" x=\"0\" y=\"0\" class=\"intermediateFilledMarker\"/>\n"
                + "                </g>\n"
                + "                <g id=\"parallelIntermediateEvent\" bclass=\"intermediateEvent\" oclass=\"intermediateEvent_o\">\n"
                + "                <use xlink:href=\"#intermediateEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#parallelMarker\" x=\"0\" y=\"0\" class=\"intermediateMarker\"/>\n"
                + "                </g>\n"
                + "                <!--Definición de eventos finales-->\n"
                + "                <circle id=\"endEvent\" r=\"15\" bclass=\"endEvent\" oclass=\"endEvent_o\"/>\n"
                + "\n"
                + "                <g id=\"messageEndEvent\" bclass=\"endEvent\" oclass=\"endEvent_o\">\n"
                + "                <use xlink:href=\"#endEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#messageThrowMarker\" x=\"0\" y=\"0\" class=\"endFilledMarked\"/>\n"
                + "                </g>\n"
                + "                <g id=\"signalEndEvent\" bclass=\"endEvent\" oclass=\"endEvent_o\">\n"
                + "                <use xlink:href=\"#endEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#signalMarker\" x=\"0\" y=\"0\" class=\"endFilledMarked\"/>\n"
                + "                </g>\n"
                + "                <g id=\"scalationEndEvent\" bclass=\"endEvent\" oclass=\"endEvent_o\">\n"
                + "                <use xlink:href=\"#endEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#scalationMarker\" x=\"0\" y=\"0\" class=\"endFilledMarked\"/>\n"
                + "                </g>\n"
                + "                <g id=\"errorEndEvent\" bclass=\"endEvent\" oclass=\"endEvent_o\">\n"
                + "                <use xlink:href=\"#endEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#errorMarker\" x=\"0\" y=\"0\" class=\"endFilledMarked\"/>\n"
                + "                </g>\n"
                + "                <g id=\"multipleEndEvent\" bclass=\"endEvent\" oclass=\"endEvent_o\">\n"
                + "                <use xlink:href=\"#endEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#multipleMarker\" x=\"0\" y=\"0\" class=\"endFilledMarked\"/>\n"
                + "                </g>\n"
                + "                <g id=\"cancelationEndEvent\" bclass=\"endEvent\" oclass=\"endEvent_o\">\n"
                + "                <use xlink:href=\"#endEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#cancelMarker\" x=\"0\" y=\"0\" class=\"endFilledMarked\"/>\n"
                + "                </g>\n"
                + "                <g id=\"terminationEndEvent\" bclass=\"endEvent\" oclass=\"endEvent_o\">\n"
                + "                <use xlink:href=\"#endEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <circle r=\"8\" class=\"endFilledMarked\"/>\n"
                + "                </g>\n"
                + "                <g id=\"compensationEndEvent\" bclass=\"endEvent\" oclass=\"endEvent_o\">\n"
                + "                <use xlink:href=\"#endEvent\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#compensaMarker\" x=\"0\" y=\"0\" class=\"endFilledMarked\"/>\n"
                + "                </g>\n"
                + "                <!--Definición de compuertas-->\n"
                + "                <rect id=\"gateway\" x=\"-17.5\" y=\"-17.5\" width=\"35\" height=\"35\" bclass=\"gateway\" oclass=\"gateway_o\" transform=\"rotate(45,0,0)\"/>\n"
                + "                <g id=\"exclusiveDataGateway\" bclass=\"gateway\" oclass=\"gateway_o\" >\n"
                + "                <use xlink:href=\"#gateway\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#cancelMarker\" x=\"0\" y=\"0\" style=\"stroke:none;fill:#d4aa00\" transform=\"scale(1.3,1.3)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"inclusiveDataGateway\" bclass=\"gateway\" oclass=\"gateway_o\" >\n"
                + "                <use xlink:href=\"#gateway\" x=\"0\" y=\"0\"/>\n"
                + "                <circle r=\"14\" style=\"fill:none;stroke:#d4aa00;stroke-width:2;\"/>\n"
                + "                </g>\n"
                + "                <g id=\"exclusiveStartGateway\" bclass=\"gateway\" oclass=\"gateway_o\" >\n"
                + "                <use xlink:href=\"#gateway\" x=\"0\" y=\"0\"/>\n"
                + "                <circle r=\"14\" style=\"fill:none;stroke:#d4aa00;stroke-width:2;\"/>\n"
                + "                <use xlink:href=\"#multipleMarker\" x=\"0\" y=\"0\" style=\"fill:none;stroke:#d4aa00;stroke-width:4.5;\" transform=\"scale(0.9,0.9)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"eventGateway\" bclass=\"gateway\" oclass=\"gateway_o\" >\n"
                + "                <use xlink:href=\"#gateway\" x=\"0\" y=\"0\"/>\n"
                + "                <circle r=\"14\" style=\"fill:none;stroke:#d4aa00;stroke-width:2;\"/>\n"
                + "                <circle r=\"10\" style=\"fill:none;stroke:#d4aa00;stroke-width:2;\"/>\n"
                + "                <use xlink:href=\"#multipleMarker\" x=\"0\" y=\"0\" style=\"fill:none;stroke:#d4aa00;stroke-width:6;\" transform=\"scale(0.75,0.75)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"parallelGateway\" bclass=\"gateway\" oclass=\"gateway_o\" >\n"
                + "                <use xlink:href=\"#gateway\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#parallelMarker\" x=\"0\" y=\"0\" style=\"stroke:none;fill:#d4aa00\" transform=\"scale(1.5,1.5)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"parallelStartGateway\" bclass=\"gateway\" oclass=\"gateway_o\" >\n"
                + "                <use xlink:href=\"#gateway\" x=\"0\" y=\"0\"/>\n"
                + "                <circle r=\"14\" style=\"fill:none;stroke:#d4aa00;stroke-width:2;\"/>\n"
                + "                <use xlink:href=\"#parallelMarker\" x=\"0\" y=\"0\" style=\"stroke:#d4aa00;fill:none;stroke-width:3.5;\"/>\n"
                + "                </g>\n"
                + "                <g id=\"complexGateway\" bclass=\"gateway\" oclass=\"gateway_o\" >\n"
                + "                <use xlink:href=\"#gateway\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#complexMarker\" x=\"0\" y=\"0\" style=\"stroke:none;fill:#d4aa00\" transform=\"scale(0.7,0.7)\"/>\n"
                + "                </g>\n"
                + "                <!--Definición de objetos de datos-->\n"
                + "                <g id=\"data\" bclass=\"itemaware\" oclass=\"itemaware_o\">\n"
                + "                <path transform=\"scale(0.7,0.7) translate(-3,-10)\" d=\"m -25,-25 0,74.61135 58.22841,0 0,-55.2509 -19.88426,-19.36045 -38.34415,0 z\"/>\n"
                + "                <path transform=\"scale(0.7,0.7) translate(-2,-10)\" d=\"m 12.5,-24 0,19.5625 20.0601,0\"/>\n"
                + "                </g>\n"
                + "                <g id=\"dataStore\" bclass=\"itemaware\" oclass=\"itemaware_o\" transform=\"translate(-12,-10)\">\n"
                + "                <path d=\"m 0,0 c -27.75868,0 -50.28125,5.6228 -50.28125,12.5625 0,0.1516 0.0412,0.2871 0.0625,0.4375 l -0.0625,0 0,61.5624 0,0.3125 0.0312,0 c 0.68314,6.7909 22.92187,12.25 50.25,12.25 27.3249498,0 49.53067,-5.4602 50.21875,-12.25 l 0.0312,0 0,-0.3125 0,-61.5624 -0.0312,0 c 0.0212,-0.1501 0.0312,-0.2862 0.0312,-0.4375 0,-6.9397 -22.4913202,-12.5625 -50.25,-12.5625 z\" transform=\"scale(0.6,0.6) translate(22,-24)\"/>\n"
                + "                <path d=\"m 0,0 c 0,6.9397 -22.5028602,12.5654 -50.26153,12.5654 -27.39179,0 -49.73975,-5.4833 -50.25272,-12.33\" transform=\"scale(0.6,0.6) translate(72,-12)\"/>\n"
                + "                </g>	\n"
                + "                <g id=\"dataInput\" bclass=\"itemaware\" oclass=\"itemaware_o\">\n"
                + "                <use xlink:href=\"#data\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#linkMarker\" x=\"-12\" y=\"-17\" style=\"fill:none;stroke:#666666;stroke-width:2.5;\" transform=\"scale(0.8,0.8)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"dataOutput\" bclass=\"itemaware\" oclass=\"itemaware_o\">\n"
                + "                <use xlink:href=\"#data\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#linkMarker\" x=\"-12\" y=\"-17\" style=\"fill:#666666;stroke:none;stroke-width:2.5;\" transform=\"scale(0.8,0.8)\"/>\n"
                + "                </g>\n"
                + "                <!--Definición de tareas-->\n"
                + "                <g id=\"task\" styled=\"stroke:#2c5aa0\">\n"
                + "                <rect x=\"-50\" y=\"-30\" rx=\"10\" ry=\"10\" width=\"100\" height=\"60\" class=\"task\"/>\n"
                + "                </g>\n"
                + "                <g id=\"userTask\" styled=\"stroke:#2c5aa0\">\n"
                + "                <use xlink:href=\"#task\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#userMarker\" transform=\"scale(0.8,0.8) translate(-45, -30)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"serviceTask\" styled=\"stroke:#2c5aa0\">\n"
                + "                <use xlink:href=\"#task\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#serviceMarker\" transform=\"scale(0.8,0.8) translate(-43, -27)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"scriptTask\" styled=\"stroke:#2c5aa0\">\n"
                + "                <use xlink:href=\"#task\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#scriptMarker\" transform=\"scale(0.7,0.7) translate(-60, -24)\"/>\n"
                + "                </g>\n"
                + "                <g id=\"ruleTask\" styled=\"stroke:#2c5aa0\">\n"
                + "                <use xlink:href=\"#task\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#ruleMarker\" transform=\"translate(-35, -17)\" style=\"fill:#2c5aa0;\"/>\n"
                + "                </g>\n"
                + "                <g id=\"sendTask\" styled=\"stroke:#2c5aa0\">\n"
                + "                <use xlink:href=\"#task\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#messageThrowMarker\" transform=\"translate(-35, -19)\" style=\"fill:#2c5aa0;\"/>\n"
                + "                </g>\n"
                + "                <g id=\"receiveTask\" styled=\"stroke:#2c5aa0\">\n"
                + "                <use xlink:href=\"#task\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#messageCatchMarker\" transform=\"translate(-35, -19)\" style=\"fill:none;stroke:#2c5aa0;stroke-width:3;\"/>\n"
                + "                </g>\n"
                + "                <g id=\"manualTask\" styled=\"stroke:#2c5aa0\">\n"
                + "                <use xlink:href=\"#task\" x=\"0\" y=\"0\"/>\n"
                + "                <use xlink:href=\"#manualMarker\" transform=\"scale(0.7,0.7) translate(-60, -35)\" style=\"fill:none;stroke:#2c5aa0;stroke-width:4;\"/>\n"
                + "                </g>\n"
                + "                <!--Definición de swimlanes-->\n"
                + "                <g id=\"pool\" bclass=\"swimlane\" oclass=\"swimlane_o\">\n"
                + "                <rect width=\"600\" x=\"-300\" y=\"-100\" height=\"200\" style=\"fill:#E8E8FF;stroke-width:2\"/>\n"
                + "                <path d=\"m -280,-100 l 0,200\" style=\"fill:none;stroke-width:2;\"/>\n"
                + "                </g>\n"
                + "                </defs>\n"
                + "                </svg>"
                + "<script type=\"text/javascript\">\n"
                + "             Modeler.init('modeler', {mode: 'view', layerNavigation: false}, callbackHandler);\n"
                + "             var zoomFactor = 1.1;\n"
                + "             var panRate = 10;\n"
                + "             function callbackHandler() {\n"
                + "                 var strJSON = '" + data + "';\n"
                + "                 Modeler.loadProcess(strJSON);\n"
                + "\n                 Modeler._svgSize = getDiagramSize();\n"
                + "                 fitToScreen();\n"
                + "             }\n"
                + "             function zoomin() {\n"
                + "                 var viewBox = document.getElementById(\"modeler\").getAttribute('viewBox');\n"
                + "                 var viewBoxValues = viewBox.split(' ');\n"
                + "\n"
                + "                 viewBoxValues[2] = parseFloat(viewBoxValues[2]);\n"
                + "                 viewBoxValues[3] = parseFloat(viewBoxValues[3]);\n"
                + "\n"
                + "                 viewBoxValues[2] /= zoomFactor;\n"
                + "                 viewBoxValues[3] /= zoomFactor;\n"
                + "\n"
                + "                 document.getElementById(\"modeler\").setAttribute('viewBox', viewBoxValues.join(' '));\n"
                + "             }\n"
                + "\n"
                + "             function zoomout() {\n"
                + "                 var viewBox = document.getElementById(\"modeler\").getAttribute('viewBox');\n"
                + "                 var viewBoxValues = viewBox.split(' ');\n"
                + "\n"
                + "                 viewBoxValues[2] = parseFloat(viewBoxValues[2]);\n"
                + "                 viewBoxValues[3] = parseFloat(viewBoxValues[3]);\n"
                + "\n"
                + "                 viewBoxValues[2] *= zoomFactor;\n"
                + "                 viewBoxValues[3] *= zoomFactor;\n"
                + "\n"
                + "                 document.getElementById(\"modeler\").setAttribute('viewBox', viewBoxValues.join(' '));\n"
                + "             }\n"
                + "\n"
                + "             function resetZoom() {\n"
                + "                 var el = document.getElementById(\"modeler\");\n"
                + "                 el.setAttribute('viewBox', '0 0 ' + $(\"#modeler\").parent().width() + ' ' + $(\"#modeler\").parent().height());\n"
                + "                 el.setAttribute('width', '1024');\n"
                + "                 el.setAttribute('height', '768');\n"
                + "             }\n"
                + "\n"
                + "             function handlePanning(code) {\n"
                + "                 var viewBox = document.getElementById(\"modeler\").getAttribute('viewBox');\n"
                + "                 var viewBoxValues = viewBox.split(' ');\n"
                + "                 viewBoxValues[0] = parseFloat(viewBoxValues[0]);\n"
                + "                 viewBoxValues[1] = parseFloat(viewBoxValues[1]);\n"
                + "\n"
                + "                 switch (code) {\n"
                + "                     case 'left':\n"
                + "                         viewBoxValues[0] += panRate;\n"
                + "                         break;\n"
                + "                     case 'right':\n"
                + "                         viewBoxValues[0] -= panRate;\n"
                + "                         break;\n"
                + "                     case 'up':\n"
                + "                         viewBoxValues[1] += panRate;\n"
                + "                         break;\n"
                + "                         case 'down':\n"
                + "                         viewBoxValues[1] -= panRate;\n"
                + "                         break;\n"
                + "                 }\n"
                + "                 document.getElementById(\"modeler\").setAttribute('viewBox', viewBoxValues.join(' '));\n"
                + "             }\n"
                + "\n"
                + "             function getDiagramSize() {\n"
                + "                 var cw = 0;\n"
                + "                 var ch = 0;\n"
                + "                 var fx = null;\n"
                + "                 var fy = null;\n"
                + "                 for (var i = 0; i < ToolKit.contents.length; i++) {\n"
                + "                     var obj = ToolKit.contents[i];\n"
                + "                     if (obj.typeOf && (obj.typeOf(\"GraphicalElement\") || obj.typeOf(\"Pool\"))) {\n"
                + "                         if (obj.layer === ToolKit.layer) {\n"
                + "                             if (obj.getX() > cw) {\n"
                + "                                 cw = obj.getX();\n"
                + "                                 fx = obj;\n"
                + "                             }\n"
                + "\n"
                + "                             if (obj.getY() > ch) {\n"
                + "                                 ch = obj.getY();\n"
                + "                                 fy = obj;\n"
                + "                             }\n"
                + "                         }\n"
                + "                     }\n"
                + "                 }\n"
                + "                 cw = cw + fx.getBBox().width;\n"
                + "                 ch = ch + fy.getBBox().height;\n"
                + "\n"
                + "                 var ret = {w: cw, h: ch};\n"
                + "                 return ret;\n"
                + "             }\n"
                + "\n"
                + "             function fitToScreen() {\n"
                + "                 resetZoom();\n"
                + "                 var ws = $(\"#modeler\").parent().width();\n"
                + "                 var hs = $(\"#modeler\").parent().height();\n"
                + "                 var wi = Modeler._svgSize.w;\n"
                + "                 var hi = Modeler._svgSize.h;\n"
                + "\n"
                + "                 if (wi > ws || hi > hs) {\n"
                + "                     var el = document.getElementById(\"modeler\");\n"
                + "                     el.setAttribute('viewBox', '0 0 ' + wi + ' ' + hi);\n"
                + "                     el.setAttribute('width', ws);\n"
                + "                     el.setAttribute('height', hs);\n"
                + "                 }\n"
                + "             }\n"
                + "     </script>";
        return model;
    }

    public static void deleteDerectory(File dir) {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                deleteDerectory(file);
                file.delete();
            } else {
                file.delete();
            }
        }
        dir.delete();
    }

    public void doUpdateSuri(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.getWriter().print(request.getParameter("suri"));
    }
}
