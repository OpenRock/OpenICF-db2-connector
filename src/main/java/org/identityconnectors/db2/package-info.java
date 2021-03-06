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

/**
 * Provides implementation of connector to DB2 database resource. DB2 Connector
 * uses DB2 database resource to manage users. DB2 uses external authentication
 * provider and internal authorization service. DB2 connector is then pretty
 * limited and should be used with combination of underlying authorization
 * service connector, typically OS connector or LDAP. DB2 connector stores users
 * using passed grants.
 *
 * See {@link org.identityconnectors.db2.DB2Configuration} and
 * {@link org.identityconnectors.db2.DB2Connector} for more information about
 * DB2 connector.
 */
package org.identityconnectors.db2;
