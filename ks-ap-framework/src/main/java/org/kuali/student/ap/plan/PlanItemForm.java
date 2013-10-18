package org.kuali.student.ap.plan;

import java.util.List;

import org.kuali.student.ap.common.infc.HasUniqueId;
import org.kuali.student.enrollment.acal.infc.Term;
import org.kuali.student.myplan.academicplan.infc.LearningPlan;
import org.kuali.student.myplan.academicplan.infc.PlanItem;
import org.kuali.student.r2.lum.course.infc.Course;

/**
 * Interface for representing a form that deals with plan items.
 * 
 * @author Mark Fyffe <mwfyffe@iu.edu>
 * @version 1.1
 */
@SuppressWarnings("deprecation")
public interface PlanItemForm extends HasUniqueId {

	/**
	 * Get the learning plan ID.
	 * 
	 * @return The learning plan ID.
	 */
	String getLearningPlanId();

	/**
	 * Get the learning plan.
	 * 
	 * @return The learning plan.
	 */
	LearningPlan getLearningPlan();

	/**
	 * Get the plan item ID.
	 * 
	 * @return The plan item ID.
	 */
	String getPlanItemId();

	/**
	 * Get the expected plan item type.
	 * 
	 * @return The expected plan item type.
	 */
	String getExpectedPlanItemType();

	/**
	 * Set the expected plan item type.
	 * 
	 * @param expectedPlanItemType
	 *            The expected plan item type.
	 */
	void setExpectedPlanItemType(String expectedPlanItemType);

	/**
	 * Get the plan item.
	 * 
	 * @return The plan item.
	 */
	PlanItem getPlanItem();

	/**
	 * Get the term ID.
	 * 
	 * @return The term ID.
	 */
	String getTermId();

	/**
	 * Determine if the requested term is a planning term.
	 * 
	 * @return True if the requested term is a planning term, false if not.
	 */
	boolean isPlanning();

	/**
	 * Determine if the requested term is a published term.
	 * 
	 * @return True if the requested term is a published term, false if not.
	 */
	boolean isPublished();

	/**
	 * Get the requested term.
	 * 
	 * @return The requested term.
	 */
	Term getTerm();

	/**
	 * Get the course ID.
	 * 
	 * @return The course ID.
	 */
	String getCourseId();

	/**
	 * Get the course.
	 * 
	 * @return The course.
	 */
	Course getCourse();

	/**
	 * Get all of the plan items that currently exist on the learning plan for
	 * the given course.
	 * 
	 * @return All of the plan items that currently exist on the learning plan
	 *         for the learning course.
	 */
	List<PlanItem> getExistingPlanItems();

}
