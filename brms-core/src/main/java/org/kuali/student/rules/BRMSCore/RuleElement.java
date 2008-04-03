package org.kuali.student.rules.BRMSCore;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "RuleElement_T")
@TableGenerator(name = "idGen")
public class RuleElement {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGen")
	private Long id;
	private RuleElementType operation;
	private Integer ordinalPosition;
	private String name;
	private String description;
	@Embedded
	private RuleMetaData ruleMetaData;
	@ManyToOne @JoinColumn(name="fkFunctionalBusinessRule") private FunctionalBusinessRule functionalBusinessRule;
	@OneToOne
	private RuleProposition ruleProposition;
	
	/**
	 * 
	 */
	public RuleElement() {
		id = null;
		operation = null;
		ordinalPosition = null;
		name = null;
		description = null;
		ruleMetaData = null;
		ruleProposition = null;
	}

	/**
	 * @param id
	 * @param operation
	 * @param ordinalPosition
	 * @param name
	 * @param description
	 * @param ruleMetaData
	 */
	public RuleElement(RuleElementType operation,
			Integer ordinalPosition, String name, String description,
			RuleMetaData ruleMetaData, RuleProposition ruleProposition) {
		//this.id = id;
		this.operation = operation;
		this.ordinalPosition = ordinalPosition;
		this.name = name;
		this.description = description;
		this.ruleMetaData = ruleMetaData;
		this.ruleProposition = ruleProposition;
	}

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the ruleMetaData
	 */
	public final RuleMetaData getRuleMetaData() {
		return ruleMetaData;
	}

	/**
	 * @param ruleMetaData the ruleMetaData to set
	 */
	public final void setRuleMetaData(RuleMetaData ruleMetaData) {
		this.ruleMetaData = ruleMetaData;
	}

	/**
	 * @return the operation
	 */
	public final RuleElementType getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public final void setOperation(RuleElementType operation) {
		this.operation = operation;
	}

	/**
	 * @return the ordinalPosition
	 */
	public final Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	/**
	 * @param ordinalPosition the ordinalPosition to set
	 */
	public final void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	/**
	 * @return the functionalBusinessRule
	 */
	public final FunctionalBusinessRule getFunctionalBusinessRule() {
		return functionalBusinessRule;
	}

	/**
	 * @param functionalBusinessRule the functionalBusinessRule to set
	 */
	public final void setFunctionalBusinessRule(
			FunctionalBusinessRule functionalBusinessRule) {
		this.functionalBusinessRule = functionalBusinessRule;
	}

	/**
	 * @return the ruleProposition
	 */
	public final RuleProposition getRuleProposition() {
		return ruleProposition;
	}

	/**
	 * @param ruleProposition the ruleProposition to set
	 */
	public final void setRuleProposition(RuleProposition ruleProposition) {
		this.ruleProposition = ruleProposition;
	}
}
