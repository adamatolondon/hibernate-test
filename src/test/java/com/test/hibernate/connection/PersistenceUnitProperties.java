/*
 * Copyright (C) 2021 Antonio Damato <anto.damato@gmail.com>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.test.hibernate.connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Antonio Damato <anto.damato@gmail.com>
 */
public class PersistenceUnitProperties {

    private static final Logger log = LogManager.getLogger(PersistenceUnitProperties.class);
    private static final ConnectionProperties connectionProperties = new ConnectionProperties();

    public static Map<String, String> getProperties() throws IOException {
        String dbId = System.getProperty("hibernate.test");
        log.info("hibernate.test={}", dbId);
        if (dbId == null || dbId.isBlank())
            return null;

        Map<String, String> properties = connectionProperties.load(dbId);
        properties.forEach((k, v) -> log.info("{}={}", k, v));

        Map<String, String> map = new HashMap<>(properties);
        map.put("javax.persistence.jdbc.url", properties.get("url"));
        map.put("javax.persistence.jdbc.driver", properties.get("driver"));
        map.put("javax.persistence.jdbc.user", properties.get("user"));
        map.put("javax.persistence.jdbc.password", properties.get("password"));

        map.put("hibernate.dialect", properties.get("dialect"));
        return map;
    }
}
