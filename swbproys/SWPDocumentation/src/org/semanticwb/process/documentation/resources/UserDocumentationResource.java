package org.semanticwb.process.documentation.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticwb.Logger;
import org.semanticwb.SWBUtils;
import org.semanticwb.model.Role;
import org.semanticwb.model.SWBComparator;
import org.semanticwb.process.documentation.model.DocumentationInstance;
import org.semanticwb.model.User;
import org.semanticwb.model.WebSite;
import org.semanticwb.portal.api.GenericAdmResource;
import org.semanticwb.portal.api.SWBActionResponse;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;
import org.semanticwb.process.model.StartEvent;
import org.semanticwb.process.model.Process;

/**
 * @author hasdai
 */
public class UserDocumentationResource extends GenericAdmResource {

    private static final Logger log = SWBUtils.getLogger(UserDocumentationResource.class);
    public static final String ATT_PARAMREQUEST = "paramRequest";
    public static final String ATT_PROCESSLIST = "processes";
    public static final String MODE_VIEWDOC = "viewdoc";
    public static final String PARAM_PROCESSID = "suri";
    public static final String MODE_VIEW_INSTANCE = "viewins";

    @Override
    public void processAction(HttpServletRequest request, SWBActionResponse response) throws SWBResourceException, IOException {
        super.processAction(request, response); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        String mode = paramRequest.getMode();
        if (mode.equals(MODE_VIEWDOC)) {
            doViewDoc(request, response, paramRequest);
        } else if (mode.equals(MODE_VIEW_INSTANCE)) {
            doViewInst(request, response, paramRequest);
        } else {
            super.processRequest(request, response, paramRequest); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
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
        String jsp = "/work/models/" + paramRequest.getWebPage().getWebSiteId() + "/jsp/documentation/userDocumentationView.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        try {
            request.setAttribute(ATT_PARAMREQUEST, paramRequest);
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("UserDocumentationResource - problema al incluir userDocumentationView.jsp", ex);
        }
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

    /**
     * Obtiene la lista de procesos a los cuales tiene acceso el usuario.
     *
     * @param user Usuario de SWB en sesión
     * @param site Sitio de procesos
     * @return Lista de procesos a los que el usuario tiene acceso
     */
    private List<Process> listUserProcesses(User user, WebSite site, HttpServletRequest request) {
        ArrayList<Process> unpaged = new ArrayList();
        int page = 1;
        int itemsPerPage = 10;
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
        
        Iterator<Process> processes = SWBComparator.sortByDisplayName(Process.ClassMgr.listProcesses(site),user.getLanguage()!=null?user.getLanguage():"es");
        while (processes.hasNext()) {
            Process process = processes.next();
            if (process.isValid() && !unpaged.contains(process)) {
                unpaged.add(process);
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
}
