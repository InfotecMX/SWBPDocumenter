/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.semanticwb.process.documentation.resources;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticwb.Logger;
import org.semanticwb.SWBPlatform;
import org.semanticwb.SWBUtils;
import org.semanticwb.model.SWBContext;
import org.semanticwb.portal.api.GenericResource;
import org.semanticwb.portal.api.SWBActionResponse;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;
import org.semanticwb.portal.api.SWBResourceURL;

/**
 *
 * @author carlos.alvarez
 */
public class ViewDocumentationT extends GenericResource {

    private final Logger log = SWBUtils.getLogger(ViewDocumentationT.class);
    public final static String MODE_VIEW_IFRAME = "mvi";

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        Boolean isAdmin = paramRequest.getWebPage().getWebSite().equals(SWBContext.getAdminWebSite());
        if (isAdmin) {
            PrintWriter out = response.getWriter();
            SWBResourceURL url = paramRequest.getRenderUrl().setCallMethod(SWBResourceURL.Call_DIRECT).setMode(MODE_VIEW_IFRAME);
            url.setParameter("suri", request.getParameter("suri"));
            out.println("<iframe src=\"" + url + "\" style=\"width:100%; height:100%;\" frameborder=\"0\"></iframe>");
        } else {
            String path = SWBPlatform.getContextPath() + "/swbadmin/jsp/process/financiera/viewDocumentation.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(path);
            request.setAttribute("suri", request.getParameter("suri"));
            request.setAttribute("paramRequest", paramRequest);
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
        if (mode.equals(MODE_VIEW_IFRAME)) {
            doViewIframe(request, response, paramRequest);
        } else {
            super.processRequest(request, response, paramRequest);
        }
    }

    @Override
    public void processAction(HttpServletRequest request, SWBActionResponse response) throws SWBResourceException, IOException {
        super.processAction(request, response);
    }

    public void doViewIframe(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String path = SWBPlatform.getContextPath() + "/swbadmin/jsp/process/financiera/viewDocumentation.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(path);
        request.setAttribute("suri", request.getParameter("suri"));
        request.setAttribute("paramRequest", paramRequest);
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            log.error("Error on doViewIframe, " + path + ", " + ex.getMessage());
        }
    }
}
