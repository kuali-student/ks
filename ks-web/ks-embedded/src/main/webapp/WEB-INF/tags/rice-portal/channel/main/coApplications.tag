<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="Course Offering Applications" />
<div class="body">
    <strong>Course Offering</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Course Offering Rollover" url="${ConfigProperties.application.url}/kr-krad/courseOfferingRollover?viewId=courseOfferingRolloverManagementView&pageId=selectTermsForRollover&methodToCall=start"/></li>
        <li><portal:portalLink displayTitle="true" title="Rollover Details" url="${ConfigProperties.application.url}/kr-krad/courseOfferingRollover?viewId=rolloverResultsPageView&pageId=selectTermForRolloverDetails&methodToCall=start"/></li>
        <li><portal:portalLink displayTitle="true" title="Manage Course Offerings" url="${ConfigProperties.application.url}/kr-krad/courseOfferingManagement?viewId=courseOfferingManagementView&pageId=searchInputPage&methodToCall=start"/></li>
    </ul>
    <strong>Basic DTO Lookup and Inquiry</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Activity Offering Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseoffering.dto.ActivityOfferingInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&showMaintenanceLinks=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Activity Offering Lookup 2" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseoffering.dto.ActivityOfferingInfo&viewId=KS-ActivityOffering-LookupView2&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="CourseOfferingInfo Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseoffering.dto.CourseOfferingInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Enrollment Fee Info Lookup and Inquiry" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.r2.core.fee.dto.EnrollmentFeeInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <%--<li><portal:portalLink displayTitle="true" title="Enrollment Fee Info Inquiry" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.r2.core.fee.dto.EnrollmentFeeInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li> --%>
        <li><portal:portalLink displayTitle="true" title="Format Offering Info Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseoffering.dto.FormatOfferingInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&showMaintenanceLinks=true" /></li>
        <li><portal:portalLink displayTitle="true" title="SocRolloverResultInfo Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseofferingset.dto.SocRolloverResultInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="SocRolloverResultItemInfo Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseofferingset.dto.SocRolloverResultItemInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <%--<li><portal:portalLink displayTitle="true" title="Offering Instructor Info Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=KS-Person-LookupView&methodToCall=start&dataObjectClassName=org.kuali.rice.kim.api.identity.Person&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>--%>
        <li><portal:portalLink displayTitle="true" title="Term Info Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.acal.dto.TermInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    </ul>

    <strong>Maintenance eDoc</strong> <br/>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Activity Offering (New)" url="${ConfigProperties.application.url}/kr-krad/maintenance?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.ActivityOfferingWrapper&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"/></li>
        <li><portal:portalLink displayTitle="true" title="Activity Offering (Edit)" url="${ConfigProperties.application.url}/kr-krad/maintenance?methodToCall=maintenanceEdit&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.ActivityOfferingWrapper&aoInfo.id=Lui-4&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"/></li>
        <li><portal:portalLink displayTitle="true" title="Course Offering (Edit)" url="${ConfigProperties.application.url}/kr-krad/maintenance?methodToCall=maintenanceEdit&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.CourseOfferingEditWrapper&coInfo.id=LuiCO-69&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"/></li>
        <li><portal:portalLink displayTitle="true" title="Enrollment Fee Info" url="${ConfigProperties.application.url}/kr-krad/maintenance?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.EnrollmentFeeFormObject&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"/></li>
        <li><portal:portalLink displayTitle="true" title="Format Offering" url="${ConfigProperties.application.url}/kr-krad/maintenance?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseoffering.dto.FormatOfferingInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"/></li>
    </ul>
</div>
<channel:portalChannelBottom />
