/*
 * Copyright 2011 The Kuali Foundation
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
package org.kuali.student.dictionary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.student.datadictionary.util.DictionaryCreator;
import org.kuali.student.enrollment.lpr.dto.LuiPersonRelationInfo;
import org.kuali.student.enrollment.lui.dto.LuiInfo;
import org.kuali.student.enrollment.lui.dto.LuiLuiRelationInfo;

/**
 *
 * @author nwright
 */
public class TestDictionaryCreator {

    public TestDictionaryCreator() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class DictionaryCreator.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        new DictionaryCreator().execute(LuiPersonRelationInfo.class, "target/ks-lui-person-relation-dictinoary.xml");
        new DictionaryCreator().execute(LuiInfo.class, "target/ks-lui-dictinoary.xml");
        new DictionaryCreator().execute(LuiLuiRelationInfo.class, "target/ks-lui-lui-relation-dictinoary.xml");
    }
}
