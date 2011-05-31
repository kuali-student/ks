/*
 * Copyright 2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.r2.lum.lrc.infc;

import java.util.Date;

import org.kuali.student.r2.common.infc.HasAttributesAndMeta;
import org.kuali.student.r2.common.infc.IdEntity;

/**
 * Captures the legal value range for a result component 
 * 
 * @author Kuali Student Team (Kamal)
 *
 */
public interface ResultValueRange extends HasAttributesAndMeta {

    /**
     * Name: Unique Id
     *
     * The system assigned unique id to identify this Object.
     * Could be implemented as as sequence number or as a UUID.
     *
     * Attempts to set this value on creates should result in a ReadOnlyException being thrown
     *
     * An Id:<ul>
     * <li>An id is used when the actual value is unimportant and can therefore be a large hex value for example
     * <li>An id value might be 23b9ca9bd203df902
     * <li>An Id is never intended to be used directly by an end user.
     * <li>Ids are assumed to be of different values in different KS implementations
     * <li>Id values are generated by the service implementations
     * <li>Id values are never expected to be used in Configuration or Application code
     * </ul>
     */
    public String getId();
    
    /**
     * Min Result Value string Lower end of the value range. Typically corresponds with the short coded form of the
     * result(ex. "1.0", "25.0" etc.) Should the data resultTypeKey of values (min/max) be numbers and not Strings in the
     * value range?
     * 
     * @name Min Value
     */
    public Float getMinValue();

    /**
     * Max Result Value string Upper end of the value range. Typically corresponds with the short coded form of the
     * result(ex. "3.0", "100.0" etc.). Upper end can be left empty to indicate unbounded upper end.
     * 
     * @name Max Value
     */
    public Float getMaxValue();

    /**
     * Increment number Legal increments in the result values. This has to be a decimal e.g 0.5)
     * 
     * @name Increment
     */
    public Float getIncrement();

    /**
     * Effective Date dateTime Date and time that this result value range became effective. This is a similar concept to the
     * effective date on enumerated values. When an expiration date has been specified, this field must be less than or equal
     * to the expiration date.
     * 
     * @name Effective Date
     */
    public Date getEffectiveDate();

    /**
     * Expiration Date dateTime Date and time that this result value range expires. This is a similar concept to the
     * expiration date on enumerated values. If specified, this should be greater than or equal to the effective date. If
     * this field is not specified, then no expiration date has been currently defined and should automatically be considered
     * greater than the effective date.
     * 
     * @name Expiration Date
     */
    public Date getExpirationDate();

}
