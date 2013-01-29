/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.kuali.student.myplan.audit.controller;

import org.apache.log4j.Logger;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.controller.UifControllerBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.student.ap.framework.config.KsapFrameworkServiceLocator;
import org.kuali.student.myplan.audit.dto.AuditProgramInfo;
import org.kuali.student.myplan.audit.dto.AuditReportInfo;
import org.kuali.student.myplan.audit.form.DegreeAuditForm;
import org.kuali.student.myplan.audit.service.DegreeAuditConstants;
import org.kuali.student.myplan.audit.service.DegreeAuditService;
import org.kuali.student.myplan.audit.service.DegreeAuditServiceConstants;
import org.kuali.student.myplan.course.util.CourseSearchConstants;
import org.kuali.student.myplan.plan.PlanConstants;
import org.kuali.student.myplan.plan.util.AtpHelper;
import org.kuali.student.myplan.utils.UserSessionHelper;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.LocaleInfo;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.messages.dto.MessageInfo;
import org.kuali.student.r2.common.messages.service.MessageService;
import org.kuali.student.r2.core.organization.service.OrganizationService;
import org.kuali.student.r2.core.search.dto.SearchRequestInfo;
import org.kuali.student.r2.core.search.dto.SearchResultInfo;
import org.kuali.student.r2.core.search.infc.SearchResult;
import org.kuali.student.r2.core.search.infc.SearchResultCell;
import org.kuali.student.r2.core.search.infc.SearchResultRow;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


// http://localhost:8080/student/myplan/audit?methodToCall=audit&viewId=DegreeAudit-FormView

@Controller
@RequestMapping(value = "/audit/**")
public class DegreeAuditController extends UifControllerBase {

    private final Logger logger = Logger.getLogger(DegreeAuditController.class);

    private transient DegreeAuditService degreeAuditService;

    public static OrganizationService organizationService;

    public DegreeAuditService getDegreeAuditService() {
        if (degreeAuditService == null) {
            degreeAuditService = (DegreeAuditService)
                    GlobalResourceLoader.getService(new QName(DegreeAuditServiceConstants.NAMESPACE,
                            DegreeAuditServiceConstants.SERVICE_NAME));
        }
        return degreeAuditService;
    }

    public void setDegreeAuditService(DegreeAuditService degreeAuditService) {
        this.degreeAuditService = degreeAuditService;
    }

    @Override
    protected UifFormBase createInitialForm(HttpServletRequest request) {
        return new DegreeAuditForm();
    }


    public OrganizationService getOrganizationService() {
        if (this.organizationService == null) {
            //   TODO: Use constants for namespace.
            this.organizationService = (OrganizationService) GlobalResourceLoader.getService(new QName("http://student.kuali.org/wsdl/organization", "orgService"));
        }
        return this.organizationService;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(params = "methodToCall=audit")
    public ModelAndView audit(@ModelAttribute("KualiForm") DegreeAuditForm form, BindingResult result,
                              HttpServletRequest request, HttpServletResponse response) {
        boolean systemKeyExists = true;
        try {
            // TODO: determine factory for context? /mwfyffe
            ContextInfo context = new ContextInfo();
            populateFormMessageMap(form, context);
            Map<String, String> campusMap = populateCampusMap(context);
            boolean isAuditServiceUp = Boolean.valueOf(request.getAttribute(DegreeAuditConstants.IS_AUDIT_SERVICE_UP).toString());

            Person user = GlobalVariables.getUserSession().getPerson();
            String systemKey = UserSessionHelper.getAuditSystemKey();
            if (StringUtils.hasText(systemKey)) {
                DegreeAuditService degreeAuditService = getDegreeAuditService();
                String auditId = form.getAuditId();
                ContextInfo contextInfo = new ContextInfo();
                Date startDate = new Date();
                Date endDate = new Date();
                String programParam = null;
                form.setCampusParam(campusMap.get("0"));
                logger.info("audit systemkey " + systemKey);
                if (!isAuditServiceUp) {
                    AtpHelper.addServiceError("programParamSeattle");
                } else {
                    List<AuditReportInfo> auditReportInfoList = degreeAuditService.getAuditsForStudentInDateRange(systemKey, startDate, endDate, contextInfo);
                    if (auditId == null && auditReportInfoList.size() > 0) {
                        auditId = auditReportInfoList.get(0).getAuditId();
                        programParam = auditReportInfoList.get(0).getProgramId();
                    }

                    // TODO: For now we are getting the auditType from the end user. This needs to be removed before going live and hard coded to audit type key html
                    if (auditId != null) {
                        AuditReportInfo auditReportInfo = degreeAuditService.getAuditReport(auditId, form.getAuditType(), contextInfo);
                        if (auditReportInfoList != null && auditReportInfoList.size() > 0) {
                            for (AuditReportInfo report : auditReportInfoList) {
                                if (report.getAuditId().equalsIgnoreCase(auditReportInfo.getAuditId())) {
                                    programParam = report.getProgramId();
                                }
                            }
                        }
                        InputStream in = auditReportInfo.getReport().getDataSource().getInputStream();
                        StringWriter sw = new StringWriter();

                        int c = 0;
                        while ((c = in.read()) != -1) {
                            sw.append((char) c);
                        }

                        String html = sw.toString();

                        String preparedFor = user.getLastName() + ", " + user.getFirstName();
                        html = html.replace("$$PreparedFor$$", preparedFor);
                        form.setAuditHtml(html);


                        /*Impl to set the default values for campusParam and programParam properties*/
                        List<AuditProgramInfo> auditProgramInfoList = new ArrayList<AuditProgramInfo>();
                        try {
                            auditProgramInfoList = getDegreeAuditService().getAuditPrograms(DegreeAuditConstants.CONTEXT_INFO);
                        } catch (Exception e) {
                            logger.error("could not retrieve AuditPrograms", e);
                        }
                        for (AuditProgramInfo auditProgramInfo : auditProgramInfoList) {
                            if (auditProgramInfo.getProgramTitle().equalsIgnoreCase(programParam)) {
                                int campusPrefix = Integer.parseInt(auditProgramInfo.getProgramId().substring(0, 1));
                                form.setCampusParam(campusMap.get(String.valueOf(campusPrefix)));
                                switch (campusPrefix) {
                                    case 0:
                                        form.setProgramParamSeattle(auditProgramInfo.getProgramId());
                                        break;
                                    case 1:
                                        form.setProgramParamBothell(auditProgramInfo.getProgramId());
                                        break;
                                    case 2:
                                        form.setProgramParamTacoma(auditProgramInfo.getProgramId());
                                        break;
                                    default:
                                        break;
                                }

                                break;
                            }
                        }
                    }
                }
            }
        } catch (DataRetrievalFailureException e) {
            e.printStackTrace();
            systemKeyExists = false;
            form.setPageId(DegreeAuditConstants.AUDIT_NON_STUDENT_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
            String[] params = {};
            GlobalVariables.getMessageMap().putWarning("programParamSeattle", DegreeAuditConstants.TECHNICAL_PROBLEM, params);
        }

        return getUIFModelAndView(form);
    }

    @RequestMapping(params = "methodToCall=runAudit")
    public ModelAndView runAudit(@ModelAttribute("KualiForm") DegreeAuditForm form, BindingResult result,
                                 HttpServletRequest request, HttpServletResponse response) {
        if (UserSessionHelper.isAdviser()) {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("audit_report_section", PlanConstants.ERROR_KEY_ADVISER_ACCESS);
            return getUIFModelAndView(form);
        }
        String auditID = null;
        try {
            Person user = GlobalVariables.getUserSession().getPerson();
//            String studentId = user.getPrincipalId();
//            String systemKey = UserSessionHelper.getAuditSystemKey();
            String regid = UserSessionHelper.getStudentId();
            if (StringUtils.hasText(regid)) {
                DegreeAuditService degreeAuditService = getDegreeAuditService();
                String programId = null;
                if ("306".equals(form.getCampusParam())) {
                    programId = form.getProgramParamSeattle();

                } else if ("310".equals(form.getCampusParam())) {
                    programId = form.getProgramParamBothell();

                } else if ("323".equals(form.getCampusParam())) {
                    programId = form.getProgramParamTacoma();

                }
                if (!programId.equalsIgnoreCase(DegreeAuditConstants.DEFAULT_KEY)) {
                    ContextInfo context = new ContextInfo();
                    AuditReportInfo report = degreeAuditService.runAudit(regid, programId, form.getAuditType(), context);
                    auditID = report.getAuditId();
                    // TODO: For now we are getting the auditType from the end user. This needs to be remvoed before going live and hard coded to audit type key html
                    AuditReportInfo auditReportInfo = degreeAuditService.getAuditReport(auditID, form.getAuditType(), context);
                    InputStream in = auditReportInfo.getReport().getDataSource().getInputStream();
                    StringWriter sw = new StringWriter();

                    int c = 0;
                    while ((c = in.read()) != -1) {
                        sw.append((char) c);
                    }

                    String html = sw.toString();
                    String preparedFor = user.getLastName() + ", " + user.getFirstName();
                    html = html.replace("$$PreparedFor$$", preparedFor);
                    form.setAuditHtml(html);
                } else {
                    String[] params = {};
                    GlobalVariables.getMessageMap().putError("programParamSeattle", DegreeAuditConstants.AUDIT_RUN_FAILED, params);
                    form.setAuditHtml(String.format(DegreeAuditConstants.AUDIT_FAILED_HTML, ConfigContext.getCurrentContextConfig().getProperty(DegreeAuditConstants.APPLICATION_URL)));
                }
            }

        } catch (DataRetrievalFailureException e) {
            String[] params = {};
            form.setCampusParam("306");
            GlobalVariables.getMessageMap().putError("programParamSeattle", DegreeAuditConstants.NO_SYSTEM_KEY, params);

        } catch (Exception e) {
            logger.error("Could not complete audit run");
            String[] params = {};
            GlobalVariables.getMessageMap().putError("programParamSeattle", DegreeAuditConstants.AUDIT_RUN_FAILED, params);
            String errorMessage = getErrorMessageFromXml(e.getCause().getMessage());
            String html = String.format(DegreeAuditConstants.AUDIT_FAILED_HTML, ConfigContext.getCurrentContextConfig().getProperty(DegreeAuditConstants.APPLICATION_URL), errorMessage);
            form.setAuditHtml(html);
        }
        return getUIFModelAndView(form);
    }

    @RequestMapping(value = "/status")
    public void getJsonResponse(HttpServletResponse response, HttpServletRequest request) {
        String programId = request.getParameter("programId").replace("$", " ");
        String auditId = request.getParameter("auditId");
        String systemKey = UserSessionHelper.getAuditSystemKey();
        String status = null;
        ContextInfo contextInfo = new ContextInfo();
        Date startDate = new Date();
        Date endDate = new Date();

        try {
            status = getDegreeAuditService().getAuditStatus(systemKey, programId, auditId);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        /*Building the Json String*/
        StringBuilder jsonString = new StringBuilder();
        jsonString = jsonString.append("{\"status\":").append("\"" + status + "\"}");
        try {
            response.getWriter().println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public Map<String, String> populateCampusMap(ContextInfo context) {
        Map<String, String> orgCampusTypes = new HashMap<String, String>();
        SearchRequestInfo searchRequest = new SearchRequestInfo(CourseSearchConstants.ORG_QUERY_SEARCH_BY_TYPE_REQUEST);
        searchRequest.addParam(DegreeAuditConstants.ORG_QUERY_PARAM, DegreeAuditConstants.CAMPUS_LOCATION);
        SearchResult searchResult = new SearchResultInfo();
        try {
            searchResult = getOrganizationService().search(searchRequest, context);
        } catch (MissingParameterException e) {
            logger.error("Search Failed to get the Organization Data ", e);
        } catch (InvalidParameterException e) {
            logger.error("Search Failed to get the Organization Data ", e);
        } catch (OperationFailedException e) {
            logger.error("Search Failed to get the Organization Data ", e);
        } catch (PermissionDeniedException e) {
            logger.error("Search Failed to get the Organization Data ", e);
        }
        for (SearchResultRow row : searchResult.getRows()) {

            if (getCellValue(row, "org.resultColumn.orgShortName").equalsIgnoreCase("seattle")) {
                orgCampusTypes.put("0", getCellValue(row, "org.resultColumn.orgId"));
            }
            if (getCellValue(row, "org.resultColumn.orgShortName").equalsIgnoreCase("bothell")) {
                orgCampusTypes.put("1", getCellValue(row, "org.resultColumn.orgId"));
            }
            if (getCellValue(row, "org.resultColumn.orgShortName").equalsIgnoreCase("tacoma")) {
                orgCampusTypes.put("2", getCellValue(row, "org.resultColumn.orgId"));
            }

        }
        return orgCampusTypes;
    }

    public void populateFormMessageMap(DegreeAuditForm form , ContextInfo context) {
        //Map <String, MessageInfo> mapMessageInfo = new HashMap <String, MessageInfo> ();
        List <MessageInfo> listOfMessageInfo = new ArrayList <MessageInfo> ();
        LocaleInfo localInfo = context.getLocale()  ;
        localInfo.setLocaleLanguage(DegreeAuditServiceConstants.DEGREE_AUDIT_EN_US_LOCALE);
        localInfo.setLocaleRegion(DegreeAuditServiceConstants.DEGREE_AUDIT_EN_US_LOCALE);
        localInfo.setLocaleScript(DegreeAuditServiceConstants.DEGREE_AUDIT_EN_US_LOCALE);
        localInfo.setLocaleVariant(DegreeAuditServiceConstants.DEGREE_AUDIT_EN_US_LOCALE);
        //MessageService messageService = new MessageServiceDecorator();
        MessageService messageService = KsapFrameworkServiceLocator.getMessageService();
        try {
            listOfMessageInfo = messageService.getMessagesByGroup(localInfo, DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_GROUP_NAME, context);
            if ((listOfMessageInfo != null) && (! listOfMessageInfo.isEmpty())) {
                Iterator<MessageInfo> iterator = listOfMessageInfo.iterator();
                while (iterator.hasNext()) {
                    MessageInfo messageInfo = (MessageInfo) iterator.next();
                    if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_SELECT_A_PROGRAM_FROM)) {
                             form.setSelectAProgramFrom(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_HOW_TO_USE_DEGREE_AUDIT)) {
                        form.setHowToUseDegreeAudit(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_THE_AUDIT_FEATURE_DISABLED)) {
                        form.setTheAuditFeatureDisabled(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_TRACK_YOUR_PROGRESS)) {
                        form.setTrackYourProgress(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_STAY_ON_TOP_OF_YOUR_REQUIRED)) {
                        form.setStayOnTopOfUrRequired(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_SELECT_YOUR_PROGRAM)) {
                        form.setSelectYourProgram(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_EXPLORE_PROGRAMS_AND_MINORS)) {
                        form.setExploreProgramsAndMinors(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_STILL_DECIDING)) {
                        form.setStillDeciding(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_SELECT_YOUR_CAMPUS)) {
                        form.setSelectYourCampus(messageInfo.getValue());
                    }else if (messageInfo.getMessageKey().contains(DegreeAuditServiceConstants.DEGREE_AUDIT_HOME_PAGE_MESSAGE_SOME_PROGRAMS_NOT_INCLUDED)) {
                        form.setSomeProgramsNotIncluded(messageInfo.getValue());
                    }
                }

            }

        } catch (DoesNotExistException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidParameterException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (MissingParameterException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OperationFailedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (PermissionDeniedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getCellValue(SearchResultRow row, String key) {
        for (SearchResultCell cell : row.getCells()) {
            if (key.equals(cell.getKey())) {
                return cell.getValue();
            }
        }
        throw new RuntimeException("cell result '" + key + "' not found");
    }

    public String getErrorMessageFromXml(String xmlString) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            if (doc.getElementsByTagName("StatusDescription").getLength() != 0) {
                return doc.getElementsByTagName("StatusDescription").item(0).getTextContent();
            } else {
                return "Technical Problems";
            }
        } catch (Exception e) {
            return "Technical Problems";
        }
    }

    /**
     * Initializes the error page.
     */
    private ModelAndView doErrorPage(DegreeAuditForm form, String errorKey, String[] params, String page, String section) {
        GlobalVariables.getMessageMap().putErrorForSectionId(section, errorKey, params);
        return getUIFModelAndView(form, page);
    }

    /**
     * Initializes the warning page.
     */
    private ModelAndView doWarningPage(DegreeAuditForm form, String errorKey, String[] params, String page, String section) {
        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().putWarningForSectionId(section, errorKey, params);
        return getUIFModelAndView(form, page);
    }
}