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

import org.identityconnectors.dbcommon.DatabaseFilterTranslator;
import org.identityconnectors.dbcommon.FilterWhereBuilder;
import org.identityconnectors.dbcommon.SQLParam;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeUtil;
import org.identityconnectors.framework.common.objects.Name;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.Uid;

/**
 * DB2 filter translator.
 *
 * @since 1.0
 */
class DB2FilterTranslator extends DatabaseFilterTranslator {
    /**
     * The filter translator constructor.
     *
     * @param objectClass
     *            object class
     * @param options
     *            operation options
     */
    public DB2FilterTranslator(final ObjectClass objectClass, final OperationOptions options) {
        super(objectClass, options);
    }

    @Override
    protected FilterWhereBuilder createBuilder() {
        return new FilterWhereBuilder();
    }

    @Override
    protected boolean validateSearchAttribute(Attribute attribute) {
        if (DB2Connector.USER_AUTH_GRANTS.equals(attribute.getName())) {
            return false;
        }
        return super.validateSearchAttribute(attribute);
    }

    @Override
    protected SQLParam getSQLParam(Attribute attribute, ObjectClass oclass, OperationOptions options) {
        if (attribute.is(Name.NAME) || attribute.is(Uid.NAME)) {
            return new SQLParam("UPPER(TRIM(GRANTEE))", AttributeUtil.getSingleValue(attribute));
        }
        // Password or other are invalid columns for query,
        // There could be an exception,but null value would disable this filter
        return null;
    }
}
