package org.kuali.student.ap.academicplan.infc;

import java.math.BigDecimal;
import java.util.Date;

import org.kuali.student.r2.common.infc.HasId;

/**
 * Student's Learning Plan that contains a list of plan items
 *
 * @Author mguilla
 */
public interface DegreeMapRequirement extends HasId {  
    
	/**
	 * The Id of the degree map
	 * @name the degree map id
	 */
	
	public String getDegreeMapId();
	
	
	/**
	 * The degree map effective date
	 * @name the degree map effective date
	 */
	public Date getDegreeMapEffectiveDate();
	
	
	/**
	 * The degree map
	 * @name the degree map
	 */
	
	public DegreeMap getDegreeMap();
    
    /**
     * The term (first year winter, second year spring, etc...)
     * where this item will show up in the degree map
     * @name the term this item will be displayed in the degree map
     */
    public String getDisplayTermId();
	
    /**
     * Item number within a year/term. If multiple items have the same number,
     * they are part of the same requirement.
     * @name the item number within a year/term. 
     */
    
    public int getItemSeq();
    
    /***
     * The sequence key for a course sequence (e.g. first you take this course, then you take this other course).
     * @name the sequence key
     */
    public String getSeqKey();
    
    
    /***
     * The sequence number for a sequence of items (e.g. first you take this course, then you take this other course).
     * so this would be the nth item in the sequence.
     * @name the sequence number
     */
    
    public int getSeqNo();
   
    /**
     * Id to the reference object e.g Course, Placeholder etc
     * @name Reference Object Id
     */
    public String getRefObjectId();

    /**
     * Type of the Reference Object
     * @name Reference Object Type
     */
    public String getRefObjectTypeKey();
    
    /**
     * the requirement description
     * @name The requirement description
     */
    public String getDescr();
    
    /**
    * The requirement credits
    * @name The number of credits
    */
    public BigDecimal getCredit();
    
  
    /**
     * True if this is a critical item
     * @name True if this is a critical item
     */
    
    public boolean isCritical();
    
    /**
     * True if this is a milestone item
     * @name True if this is a milestone item
     */
    public boolean isMilestone();
    
    
    
    /**
    * The minimum grade
    * @name Minimum grade
    */

    public String getMinimumGrade();
       
    
    /**
     * The term (first year winter, second year spring, etc...)
     * it is suggested that this item be completed.
     * @name the term it is suggested the item be completed
     */
    public String getSuggestedTermId();

     
    /**
     * The term (first year winter, second year spring, etc...)
     * it is required that this item be completed.
     * @name the term it is required the item be completed
     */
    public String getRequiredTermId();

    
    /***
     * Notes
     * @name notes
     */
 
    public String getNotes();
}
