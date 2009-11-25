/*
 * Copyright 2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may	obtain a copy of the License at
 *
 * 	http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.orchestration.orch;


import org.kuali.student.common.assembly.client.Data;
import org.kuali.student.lum.lu.assembly.data.client.PropertyEnum;


public class CreditCourseDurationHelper
{
	private static final long serialVersionUID = 1;
	
	public enum Properties implements PropertyEnum
	{
		TERM_TYPE ("termType"),
		QUANTITY ("quantity");
		
		private final String key;
		
		private Properties (final String key)
		{
			this.key = key;
		}
		
		@Override
		public String getKey ()
		{
			return this.key;
		}
	}
	private Data data;
	
	public CreditCourseDurationHelper (Data data)
	{
		this.data = data;
	}
	
	public Data getData ()
	{
		return data;
	}
	
	
	public void setTermType (String value)
	{
		data.set (Properties.TERM_TYPE.getKey (), value);
	}
	
	
	public String getTermType ()
	{
		return (String) data.get (Properties.TERM_TYPE.getKey ());
	}
	
	
	public void setQuantity (Integer value)
	{
		data.set (Properties.QUANTITY.getKey (), value);
	}
	
	
	public Integer getQuantity ()
	{
		return (Integer) data.get (Properties.QUANTITY.getKey ());
	}
	
}

