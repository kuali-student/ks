package org.kuali.student.ap.sb;

import java.util.List;

import org.kuali.student.ap.sb.infc.CourseOption;
import org.kuali.student.enrollment.acal.infc.Term;
import org.kuali.student.myplan.academicplan.infc.PlanItem;

/**
 * Defines methods for interacting with the shopping cart.
 * 
 * @author Mark Fyffe <mwfyffe@iu.edu>
 * @version 0.7.5
 */
public interface ShoppingCartStrategy {

	/**
	 * Determine if shopping cart is available for the student on the given
	 * term.
	 * 
	 * @param termId
	 *            The term ID.
	 * @param campusCode
	 *            The campus code value, from the "campusCode" dynamic attribute
	 *            on the course. When null, this method will check for access to
	 *            the shopping cart for the given term on all campuses.
	 * @return True if the student has access to the shopping cart for the given
	 *         term, false if not.
	 */
	boolean isCartAvailable(String termId, String campusCode);

	/**
	 * Get the course options for use with shopping cart for a specific term,
	 * based on a plan item.
	 * 
	 * @param term
	 *            The term to create a shopping cart request for.
	 * @param planItem
	 *            The plan item to create a shopping cart request for.
	 * @return A shopping cart request for the specific plan item and term.
	 */
	List<CourseOption> getCourseOptionsForPlanItem(Term term, PlanItem planItem);

	/**
	 * Create a shopping cart request for a specific term, based on a plan item.
	 * 
	 * @param term
	 *            The term to create a shopping cart request for.
	 * @param planItem
	 *            The plan item to create a shopping cart request for.
	 * @return A shopping cart request for the specific plan item and term.
	 */
	List<ShoppingCartRequest> createRequests(Term term, List<CourseOption> courseOptions);

	/**
	 * Process shopping cart requests.
	 * 
	 * @param requests
	 *            The shopping cart requests.
	 * @return Shopping cart requests, with results populated.
	 */
	List<ShoppingCartRequest> processRequests(List<ShoppingCartRequest> requests);

}
