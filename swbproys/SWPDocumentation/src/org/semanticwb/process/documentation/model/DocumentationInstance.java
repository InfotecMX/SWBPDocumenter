package org.semanticwb.process.documentation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.semanticwb.SWBPlatform;
import org.semanticwb.model.SWBComparator;
import org.semanticwb.model.WebSite;
import org.semanticwb.platform.SemanticClass;
import org.semanticwb.process.model.GraphicalElement;
import org.semanticwb.process.model.ProcessSite;
import org.semanticwb.process.model.UserTask;

public class DocumentationInstance extends org.semanticwb.process.documentation.model.base.DocumentationInstanceBase {

    public DocumentationInstance(org.semanticwb.platform.SemanticObject base) {
        super(base);
    }

    public static Map getDocumentSectionInstance(DocumentationInstance di) {
        Map map = new HashMap();
        Iterator<DocumentSectionInstance> itdsi = di.listDocumnetSectionInstances();
        while (itdsi.hasNext()) {
            DocumentSectionInstance dsit = itdsi.next();
            if (dsit.getSecTypeDefinition() != null) {
                map.put(dsit.getSecTypeDefinition().getURI(), dsit.getURI());
            } else {
                dsit.remove();
            }
        }
        return map;
    }

    public static DocumentationInstance createDocumentSectionInstance(ProcessSite model, DocumentTemplate dt, org.semanticwb.process.model.Process process) {
        DocumentationInstance di = DocumentationInstance.ClassMgr.createDocumentationInstance(model);
        di.setDocTypeDefinition(dt);
        di.setProcessRef(process);
        Iterator<DocumentSection> itdsi = di.getDocTypeDefinition().listDocumentSections();
        while (itdsi.hasNext()) {
            DocumentSection ds = itdsi.next();
            DocumentSectionInstance dsi = DocumentSectionInstance.ClassMgr.createDocumentSectionInstance(model);
            dsi.setSecTypeDefinition(ds);
            di.addDocumnetSectionInstance(dsi);
            SemanticClass cls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(dsi.getSecTypeDefinition().getSectionType().getURI());
            if (FreeText.sclass.getClassId().equals(cls.getClassId())) {
                FreeText ft = FreeText.ClassMgr.createFreeText(model);
                ft.setText("");
                dsi.addDocuSectionElementInstance(ft);
                ft.setParentSection(ds);
            }
            if (Activity.sclass.getClassId().equals(cls.getClassId())) {
                Iterator<GraphicalElement> itge = di.getProcessRef().listAllContaineds();
                while (itge.hasNext()) {
                    GraphicalElement ge = itge.next();
                    if (ge instanceof org.semanticwb.process.model.SubProcess || ge instanceof UserTask) {
                        String urige = ge.getURI();
                        org.semanticwb.process.model.Activity act = (org.semanticwb.process.model.Activity) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urige);

                        ActivityRef actRef = ActivityRef.ClassMgr.createActivityRef(model);
                        actRef.setProcessActivity(act);
                        Activity actFin = Activity.ClassMgr.createActivity(model);
                        actFin.setTitle(act.getTitle());
                        actFin.setDescription(act.getDescription());
                        actFin.setActivityRef(actRef);
                        actFin.setIndex(ge.getIndex());
                        dsi.addDocuSectionElementInstance(actFin);
                        actFin.setParentSection(ds);
                    }
                }
            }
        }
        return di;
    }

    public static ArrayList listDocumentSections(DocumentTemplate dt, Map map, DocumentationInstance di, ProcessSite model, String clsuri) {
        Iterator<DocumentSection> itdst = SWBComparator.sortSemanticObjects(dt.listDocumentSections());
        ArrayList arr = new ArrayList();
        int i = 0;
        while (itdst.hasNext()) {
            DocumentSection dst = itdst.next();
            if (dst.isActive()) {
                SemanticClass semcls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(dst.getSectionType().getURI());
                if (!clsuri.contains(semcls.getClassId())) {
                    if (!map.containsKey(dst.getURI())) {
                        DocumentSectionInstance dsin = DocumentSectionInstance.ClassMgr.createDocumentSectionInstance(model);
                        dsin.setSecTypeDefinition(dst);
                        di.addDocumnetSectionInstance(dsin);
                        SemanticClass cls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(dsin.getSecTypeDefinition().getSectionType().getURI());
                        if (FreeText.sclass.getClassId().equals(cls.getClassId())) {
                            FreeText ft = FreeText.ClassMgr.createFreeText(model);
                            ft.setText("");
                            SectionElement se = (SectionElement) ft.getSemanticObject().createGenericInstance();
                            dsin.addDocuSectionElementInstance(se);
                        }
                        if (Activity.sclass.getClassId().equals(cls.getClassId())) {
                            Iterator<GraphicalElement> itge = di.getProcessRef().listAllContaineds();
                            while (itge.hasNext()) {
                                GraphicalElement ge = itge.next();
                                if (ge instanceof org.semanticwb.process.model.SubProcess || ge instanceof UserTask) {
                                    String urige = ge.getURI();
                                    org.semanticwb.process.model.Activity act = (org.semanticwb.process.model.Activity) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urige);

//                                if(act.getParent() != null && act.getParent() instanceof SubProcess){
//                                    System.out.println("activity : " + act + " is child of subprocess : " + act.getParent());
//                                }

                                    ActivityRef actRef = ActivityRef.ClassMgr.createActivityRef(model);
                                    actRef.setProcessActivity(act);
                                    Activity actFin = Activity.ClassMgr.createActivity(model);
                                    actFin.setTitle(act.getTitle());
                                    actFin.setDescription(act.getDescription());
                                    actFin.setActivityRef(actRef);
                                    actFin.setIndex(ge.getIndex());
                                    dsin.addDocuSectionElementInstance(actFin);
                                }
                            }
                        }
                        arr.add(i, dsin.getURI());
                    } else {
                        arr.add(i, map.get(dst.getURI()));
                    }
                    i++;
                }
            }
        }
        return arr;
    }

    public static ArrayList listDocumentSectionsForPE(DocumentTemplate dt, Map map, DocumentationInstance di, ProcessSite model, String clsuri) {
        Iterator<DocumentSection> itdst = SWBComparator.sortSortableObject(dt.listDocumentSections());
        ArrayList arr = new ArrayList();
        int i = 0;
        Iterator<DocumentSectionInstance> itdsi = SWBComparator.sortSortableObject(di.listDocumnetSectionInstances());
        while (itdsi.hasNext()) {
            DocumentSectionInstance dsi = itdsi.next();
            SemanticClass semcls = SWBPlatform.getSemanticMgr().getVocabulary().getSemanticClass(dsi.getSecTypeDefinition().getSectionType().getURI());
            if (!clsuri.contains(semcls.getClassId())) {
                if (dsi.listDocuSectionElementInstances().hasNext()) {
                    DocumentSection documentSection = dsi.getSecTypeDefinition();
                    if (!map.containsKey(documentSection.getURI())) {
                        arr.add(i, itdst);
                    } else {
                        arr.add(i, map.get(documentSection.getURI()));
                    }
                    i++;
                }
            }
        }
        return arr;
    }

    public static void verifyActivitiesOfProcess(List<GraphicalElement> list, WebSite model, DocumentSectionInstance dsi, org.semanticwb.process.model.Process process) {
        Iterator<GraphicalElement> itge = process.listAllContaineds();
        while (itge.hasNext()) {
            GraphicalElement ge = itge.next();
            if ((ge instanceof org.semanticwb.process.model.SubProcess || ge instanceof UserTask)) {
                if (!list.contains(ge)) {
                    String urige = ge.getURI();
                    org.semanticwb.process.model.Activity act = (org.semanticwb.process.model.Activity) SWBPlatform.getSemanticMgr().getOntology().getGenericObject(urige);
                    ActivityRef actRef = ActivityRef.ClassMgr.createActivityRef(model);
                    actRef.setProcessActivity(act);
                    Activity actFin = Activity.ClassMgr.createActivity(model);
                    actFin.setTitle(act.getTitle());
                    actFin.setDescription(act.getDescription());
                    actFin.setActivityRef(actRef);
                    actFin.setIndex(ge.getIndex());
                    dsi.addDocuSectionElementInstance(actFin);
                    actFin.setParentSection(dsi.getSecTypeDefinition());
                }
            }
        }
    }
}
