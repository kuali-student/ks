package org.kuali.student.poc.wsdl.personidentity.person.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by the CXF 2.0.4-incubator Thu Mar 20 10:42:46 EDT 2008 Generated source version: 2.0.4-incubator
 */

@XmlRootElement(name = "createPersonType", namespace = "http://student.kuali.org/poc/wsdl/personidentity/person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPersonType", namespace = "http://student.kuali.org/poc/wsdl/personidentity/person")
public class CreatePersonType {

    @XmlElement(name = "personType")
    private org.kuali.student.poc.xsd.personidentity.person.dto.PersonTypeInfoDTO personType;

    public org.kuali.student.poc.xsd.personidentity.person.dto.PersonTypeInfoDTO getPersonType() {
        return personType;
    }

    public void setPersonType(org.kuali.student.poc.xsd.personidentity.person.dto.PersonTypeInfoDTO newPersonType) {
        personType = newPersonType;
    }

}
