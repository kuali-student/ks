
package org.kuali.student.core.organization.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.1.3
 * Wed Jan 21 13:23:58 EST 2009
 * Generated source version: 2.1.3
 */

@XmlRootElement(name = "searchForOrgPersonRelations", namespace = "http://org.kuali.student/core/organization")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchForOrgPersonRelations", namespace = "http://org.kuali.student/core/organization")

public class SearchForOrgPersonRelations {

    @XmlElement(name = "orgPersonRelationCriteria")
    private org.kuali.student.core.organization.dto.OrgPersonRelationCriteria orgPersonRelationCriteria;

    public org.kuali.student.core.organization.dto.OrgPersonRelationCriteria getOrgPersonRelationCriteria() {
        return this.orgPersonRelationCriteria;
    }

    public void setOrgPersonRelationCriteria(org.kuali.student.core.organization.dto.OrgPersonRelationCriteria newOrgPersonRelationCriteria)  {
        this.orgPersonRelationCriteria = newOrgPersonRelationCriteria;
    }

}

