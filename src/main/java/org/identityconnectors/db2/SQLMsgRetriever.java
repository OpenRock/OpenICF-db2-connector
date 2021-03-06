/*
 * ====================
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2008-2009 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License("CDDL") (the "License").  You may not use this file
 * except in compliance with the License.
 *
 * You can obtain a copy of the License at
 * http://opensource.org/licenses/cddl1.php
 * See the License for the specific language governing permissions and limitations
 * under the License.
 *
 * When distributing the Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://opensource.org/licenses/cddl1.php.
 * If applicable, add the following below this CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * ====================
 */

package org.identityconnectors.db2;

import java.lang.reflect.Method;

final class SQLMsgRetriever {
    String retrieveMsg(Exception e) {
        try {
            e.getClass().getClassLoader().loadClass("com.ibm.db2.jcc.DB2Diagnosable");
            return retrieveDB2DriverMsg(e);
        } catch (Throwable e1) {
        }
        return e.getMessage();
    }

    /** Here use DB2Driver reflection code to avoid the compilation dependency. */
    private String retrieveDB2DriverMsg(Exception e) throws Exception {
        Method getSqlca = e.getClass().getMethod("getSqlca", new Class[0]);
        Object sqlca = getSqlca.invoke(e);
        if (sqlca != null) {
            Method getMessage = sqlca.getClass().getMethod("getMessage", new Class[0]);
            return (String) getMessage.invoke(sqlca);
        }
        return e.getMessage();
    }
}
