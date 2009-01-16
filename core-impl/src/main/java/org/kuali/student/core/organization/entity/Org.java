package org.kuali.student.core.organization.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.core.entity.AttributeOwner;
import org.kuali.student.core.entity.MetaEntity;

@Entity
@Table(name="KS_ORG_T")
public class Org extends MetaEntity implements AttributeOwner<OrgAttribute>{
	
	@Id
	@Column(name = "ORG_ID")
	private String id; 
	
	@Column(name = "ORG_LONG_NAME")
	private String longName; 
	
	@Column(name = "ORG_SHORT_NAME")
	private String shortName; 
	
	@Column(name = "ORG_DESC")
	private String desc; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTIVE_DT")
	private Date effectiveDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRATION_DT")
	private Date expirationDate; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<OrgAttribute> attributes;
	
	@ManyToOne
    @JoinColumn(name="ORG_TYPE")
	private OrgType type; 
	
	@Column(name = "ORG_STATE")
	private String state;
	
	/**
	 * AutoGenerate the Id
	 */
	@PrePersist
	public void prePersist() {
		this.id = UUIDHelper.genStringUUID(this.id);
	}

	@Override
	public List<OrgAttribute> getAttributes() {
		if(attributes==null){
			attributes = new ArrayList<OrgAttribute>();
		}
		return attributes;
	}

	@Override
	public void setAttributes(List<OrgAttribute> attributes) {
		this.attributes=attributes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public OrgType getType() {
		return type;
	}

	public void setType(OrgType type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	} 
	
}
