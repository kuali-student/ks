package org.kuali.student.krms.service.impl;

import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.api.repository.term.TermResolverDefinition;
import org.kuali.rice.krms.framework.type.TermResolverTypeService;
import org.kuali.student.enrollment.academicrecord.service.AcademicRecordService;
import org.kuali.student.enrollment.courseregistration.service.CourseRegistrationService;
import org.kuali.student.krms.termresolver.AdminOrgNumberTermResolver;
import org.kuali.student.krms.termresolver.CompletedCourseNumberTermResolver;
import org.kuali.student.krms.termresolver.CompletedCourseSetTermResolver;
import org.kuali.student.krms.termresolver.CompletedCourseTermResolver;
import org.kuali.student.krms.termresolver.CompletedCoursesTermResolver;
import org.kuali.student.krms.termresolver.CompletedEffectiveDateFromTermResolver;
import org.kuali.student.krms.termresolver.CompletedEffectiveDateToTermResolver;
import org.kuali.student.krms.termresolver.CompletedLearningObjectivesTermResolver;
import org.kuali.student.krms.termresolver.CourseNumberRangeTermResolver;
import org.kuali.student.krms.termresolver.CourseSetTermResolver;
import org.kuali.student.krms.termresolver.DeptNumberTermResolver;
import org.kuali.student.krms.termresolver.EffectiveDateFromTermResolver;
import org.kuali.student.krms.termresolver.EffectiveDateToTermResolver;
import org.kuali.student.krms.termresolver.EnrolledCourseByTermTermResolver;
import org.kuali.student.krms.termresolver.EnrolledCourseCodeTermResolver;
import org.kuali.student.krms.termresolver.EnrolledCourseNumberTermResolver;
import org.kuali.student.krms.termresolver.EnrolledCourseSetTermResolver;
import org.kuali.student.krms.termresolver.EnrolledCourseTermResolver;
import org.kuali.student.krms.termresolver.EnrolledCoursesByTermTermResolver;
import org.kuali.student.krms.termresolver.EnrolledCoursesTermResolver;
import org.kuali.student.krms.termresolver.EnrolledEffectiveDateFromTermResolver;
import org.kuali.student.krms.termresolver.EnrolledEffectiveDateToTermResolver;
import org.kuali.student.krms.termresolver.EnrolledLearningObjectivesTermResolver;
import org.kuali.student.krms.termresolver.FreeTextTermResolver;
import org.kuali.student.krms.termresolver.GPATermResolver;
import org.kuali.student.krms.termresolver.GradeTermResolver;
import org.kuali.student.krms.termresolver.GradeTypeTermResolver;
import org.kuali.student.krms.termresolver.LearningObjectivesTermResolver;
import org.kuali.student.krms.termresolver.NumberOfCoursesTermResolver;
import org.kuali.student.krms.termresolver.NumberOfCreditsTermResolver;
import org.kuali.student.krms.termresolver.ScoreTermResolver;
import org.kuali.student.krms.termresolver.SubjectCodeTermResolver;
import org.kuali.student.krms.termresolver.TestTermResolver;
import org.kuali.student.krms.util.KSKRMSConstants;
import org.kuali.student.r2.core.organization.service.OrganizationService;

public class KSTermResolverTypeService implements TermResolverTypeService {

	AcademicRecordService acadRecordService;
    CourseRegistrationService courseRegistrationService;
    OrganizationService organizationService;
	
	@Override
	public TermResolver<?> loadTermResolver(
			TermResolverDefinition termResolverDefinition) {
		
		if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COMPLETED_COURSE)) {
			CompletedCourseTermResolver resolver = new CompletedCourseTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;	
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ENROLLED_COURSE_BY_TERM)) {
			// TODO implement the Term resolvers by instantiating it and setting the required services...
			EnrolledCourseByTermTermResolver resolver = new EnrolledCourseByTermTermResolver();
			resolver.setCourseRegistrationService(courseRegistrationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COMPLETED_COURSES)) {
			CompletedCoursesTermResolver resolver = new CompletedCoursesTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ENROLLED_COURSES_BY_TERM)) {
			EnrolledCoursesByTermTermResolver resolver = new EnrolledCoursesByTermTermResolver();
			resolver.setCourseRegistrationService(courseRegistrationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COURSE_NUMBER_RANGE)) {
			CourseNumberRangeTermResolver resolver = new CourseNumberRangeTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_SUBJECT_CODE)) {
			SubjectCodeTermResolver resolver = new SubjectCodeTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COURSE_SET)) {
			CourseSetTermResolver resolver = new CourseSetTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_EFFECTIVE_DATE_FROM)) {
			EffectiveDateFromTermResolver resolver = new EffectiveDateFromTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_EFFECTIVE_DATE_TO)) {
			EffectiveDateToTermResolver resolver = new EffectiveDateToTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_FREE_TEXT)) {
			FreeTextTermResolver resolver = new FreeTextTermResolver();
			//resolver.setAcademicRecordService(acadRecordService); Does not exist
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_GPA)) {
			GPATermResolver resolver = new GPATermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_GRADE)) {
			GradeTermResolver resolver = new GradeTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_GRADE_TYPE)) {
			GradeTypeTermResolver resolver = new GradeTypeTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_LEARNING_OBJECTIVES)) {
			LearningObjectivesTermResolver resolver = new LearningObjectivesTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_NUMBER_OF_COURSES)) {
			NumberOfCoursesTermResolver resolver = new NumberOfCoursesTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_NUMBER_OF_CREDITS)) {
			NumberOfCreditsTermResolver resolver = new NumberOfCreditsTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_SCORE)) {
			ScoreTermResolver resolver = new ScoreTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_TEST)) {
			TestTermResolver resolver = new TestTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_DEPT_NUMBER)) {
			DeptNumberTermResolver resolver = new DeptNumberTermResolver();
			resolver.setOrganizationService(organizationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ADMIN_ORG_NUMBER)) {
			AdminOrgNumberTermResolver resolver = new AdminOrgNumberTermResolver();
			resolver.setOrganizationService(organizationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COMPLETED_COURSE)) {
			CompletedCourseNumberTermResolver resolver = new CompletedCourseNumberTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COMPLETED_COURSE_SET)) {
			CompletedCourseSetTermResolver resolver = new CompletedCourseSetTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COMPLETED_EFFECTIVE_DATE_FROM)) {
			CompletedEffectiveDateFromTermResolver resolver = new CompletedEffectiveDateFromTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COMPLETED_EFFECTIVE_DATE_TO)) {
			CompletedEffectiveDateToTermResolver resolver = new CompletedEffectiveDateToTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_COMPLETED_LEARNING_OBJ_DESCR)) {
			CompletedLearningObjectivesTermResolver resolver = new CompletedLearningObjectivesTermResolver();
			resolver.setAcademicRecordService(acadRecordService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ENROLLED_COURSE_NUMBER_SUBJECT_CODE)) {
			EnrolledCourseCodeTermResolver resolver = new EnrolledCourseCodeTermResolver();
			resolver.setCourseRegistrationService(courseRegistrationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ENROLLED_SET)) {
			EnrolledCourseSetTermResolver resolver = new EnrolledCourseSetTermResolver();
			resolver.setCourseRegistrationService(courseRegistrationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ENROLLED_COURSES)) {
			EnrolledCoursesTermResolver resolver = new EnrolledCoursesTermResolver();
			resolver.setCourseRegistrationService(courseRegistrationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ENROLLED_EFFECTIVE_DATE_FROM)) {
			EnrolledEffectiveDateFromTermResolver resolver = new EnrolledEffectiveDateFromTermResolver();
			resolver.setCourseRegistrationService(courseRegistrationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ENROLLED_EFFECTIVE_DATE_TO)) {
			EnrolledEffectiveDateToTermResolver resolver = new EnrolledEffectiveDateToTermResolver();
			resolver.setCourseRegistrationService(courseRegistrationService);
			return resolver;
		}else if (termResolverDefinition.getName().equals(KSKRMSConstants.TERM_SPEC_ENROLLED_LEARNING_OBJ_DESCR)) {
			EnrolledLearningObjectivesTermResolver resolver = new EnrolledLearningObjectivesTermResolver();
			resolver.setCourseRegistrationService(courseRegistrationService);
			return resolver;
		}
		return null;
	}
	
	public AcademicRecordService getAcadRecordService() {
		return acadRecordService;
	}
	public void setAcademicRecordService(AcademicRecordService acadRecordService) {
		this.acadRecordService = acadRecordService;
	}
	
	public CourseRegistrationService getCourseRegistrationService() {
		return courseRegistrationService;
	}
	
	public void setCourseRegistrationService(CourseRegistrationService courseRegistrationService) {
		this.courseRegistrationService = courseRegistrationService;
	}

}
