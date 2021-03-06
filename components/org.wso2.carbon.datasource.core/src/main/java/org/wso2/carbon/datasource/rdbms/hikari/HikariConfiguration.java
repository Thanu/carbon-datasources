/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.datasource.rdbms.hikari;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

/**
 * Hikari configuration bean class.
 */
@XmlRootElement(name = "configuration")
public class HikariConfiguration {

    private String url;
    private String username;
    private Password passwordPersist;
    private String driverClassName;
    private List<DataSourceProperty> databaseProps;
    private long connectionTimeout = HikariConstants.CONNECTION_TIME_OUT;
    private long idleTimeout = HikariConstants.IDLE_TIME_OUT;
    private long maxLifetime = HikariConstants.MAX_LIFE_TIME;
    private int maximumPoolSize = HikariConstants.MAXIMUM_POOL_SIZE;
    private int minimumIdle = HikariConstants.MAXIMUM_POOL_SIZE;
    private boolean autoCommit = HikariConstants.AUTO_COMMIT;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        if (this.getPasswordPersist() == null) {
            this.passwordPersist = new Password();
        }
        this.passwordPersist.setValue(password);
    }

    @XmlTransient
    public String getPassword() {
        if (this.getPasswordPersist() != null) {
            return this.getPasswordPersist().getValue();
        } else {
            return null;
        }
    }

    @XmlElement(name = "password")
    public Password getPasswordPersist() {
        return passwordPersist;
    }

    public void setPasswordPersist(Password passwordPersist) {
        this.passwordPersist = passwordPersist;
    }

    /**
     * Bean class holding password.
     */
    @XmlRootElement(name = "password")
    public static class Password {

        private String value;

        @XmlValue
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @XmlElement(name = "connectionTimeout")
    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public long getMaxLifetime() {
        return maxLifetime;
    }

    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public int getMinimumIdle() {
        return minimumIdle;
    }

    public void setMinimumIdle(int minimumIdle) {
        this.minimumIdle = minimumIdle;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    @XmlElementWrapper(name = "databaseProps")
    @XmlElement(name = "property")
    public List<DataSourceProperty> getDatabaseProps() {
        return databaseProps;
    }

    public void setDatabaseProps(List<DataSourceProperty> databaseProps) {
        this.databaseProps = databaseProps;
    }

    /**
     * Bean class to hold custom properties
     */
    @XmlRootElement(name = "property")
    public static class DataSourceProperty {

        private String name;

        private String value;

        @XmlAttribute(name = "name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlValue
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

}
