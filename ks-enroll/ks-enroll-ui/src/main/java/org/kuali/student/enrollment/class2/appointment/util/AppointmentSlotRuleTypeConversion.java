/**
 * Copyright 2012 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * Created by Adi Rajesh on 4/11/12
 */
package org.kuali.student.enrollment.class2.appointment.util;


import org.kuali.student.r2.common.dto.TimeAmountInfo;
import org.kuali.student.r2.common.dto.TimeOfDayInfo;
import org.kuali.student.r2.core.appointment.dto.AppointmentSlotRuleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class //TODO ...
 *
 * @author Kuali Student Team
 */
public class AppointmentSlotRuleTypeConversion {

    //Converting appt. rule type code to object
    // Ex: 1,3,4;8;4;kuali.type.duration.minutes;15;kuali.type.duration.minutes;0
    public static AppointmentSlotRuleInfo convToAppointmentSlotRuleInfo(String slotRuleTypeCode){
        AppointmentSlotRuleInfo slotRule = new AppointmentSlotRuleInfo();
        String[] codeWords = slotRuleTypeCode.split(";");
        String weekDaysArray[] = codeWords[0].split(",");
        if(weekDaysArray.length >0){
            List<Integer> weekdays = new ArrayList<Integer>();
            for (String s : weekDaysArray) {
                weekdays.add(Integer.parseInt(s));
            }
            slotRule.setWeekdays(weekdays);
        }
        slotRule.setStartTimeOfDay(convertToTimeOfDayInfo(Long.parseLong(codeWords[1])));
        slotRule.setEndTimeOfDay(convertToTimeOfDayInfo(Long.parseLong(codeWords[2])));
        slotRule.setSlotStartInterval(convertToTimeAmountInfo(codeWords[3],Integer.parseInt(codeWords[4])));
        slotRule.setSlotDuration(convertToTimeAmountInfo(codeWords[5],Integer.parseInt(codeWords[6])));
        return slotRule;
    }

    //Converting appt. rule type object to code
    public static String convTotAppointmentSlotRuleCode(AppointmentSlotRuleInfo slotRuleInfo){
        String slotRule = "";
        StringBuilder tempSlotRule = new StringBuilder();

        if(slotRuleInfo != null){
            if(slotRuleInfo.getWeekdays() != null){
                // Generate comma delimited days of week to save (max length is 13 characters)
                List<Integer> weekdays = slotRuleInfo.getWeekdays(); // not null
                for (Integer day : weekdays) {
                    if (tempSlotRule.length() > 0) {
                        tempSlotRule.append(",");
                    }
                    tempSlotRule.append(day);
                }
            }
            tempSlotRule.append(";"+slotRuleInfo.getStartTimeOfDay().getMilliSeconds());
            tempSlotRule.append(";"+slotRuleInfo.getEndTimeOfDay().getMilliSeconds());
            tempSlotRule.append(";"+slotRuleInfo.getSlotStartInterval().getAtpDurationTypeKey());
            tempSlotRule.append(";"+slotRuleInfo.getSlotStartInterval().getTimeQuantity());
            tempSlotRule.append(";"+slotRuleInfo.getSlotDuration().getAtpDurationTypeKey());
            tempSlotRule.append(";"+slotRuleInfo.getSlotDuration().getTimeQuantity());
            slotRule = tempSlotRule.toString();
        }
        return slotRule;
    }

    private static TimeOfDayInfo convertToTimeOfDayInfo(Long time) {
        if(time == null){
            return null;
        }
        TimeOfDayInfo info = new TimeOfDayInfo();
        info.setMilliSeconds(time);
        return info;
    }

    private static TimeAmountInfo convertToTimeAmountInfo(String typeKey, Integer quantity) {
        if ((typeKey == null) && (quantity == null)) {
            return null;
        }
        TimeAmountInfo info = new TimeAmountInfo();
        info.setAtpDurationTypeKey(typeKey);
        if (quantity != null) {
            info.setTimeQuantity(quantity);
        }
        return info;
    }

}
