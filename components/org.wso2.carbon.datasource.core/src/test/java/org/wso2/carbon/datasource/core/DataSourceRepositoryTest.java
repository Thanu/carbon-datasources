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
package org.wso2.carbon.datasource.core;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.wso2.carbon.datasource.core.beans.CarbonDataSource;
import org.wso2.carbon.datasource.core.exception.DataSourceException;

import java.net.MalformedURLException;

/**
 * Test class for DataSourceRepository class.
 */
public class DataSourceRepositoryTest extends BaseTest {

    private DataSourceRepository dsRepository;

    @BeforeSuite
    public void initialize() throws DataSourceException, MalformedURLException {
        super.init();
        dsRepository = dataSourceManager.getDataSourceRepository();
    }

    @Test
    public void getAllDataSourcesTest() {
        int size = dsRepository.getDataSources().size();
        Assert.assertEquals(size, 2, "Only two data source is configured.");
    }


    @Test
    public void getAllDataSourceTest() {
        CarbonDataSource carbonDataSource = dsRepository.getDataSource("WSO2_CARBON_DB");
        Assert.assertNotNull(carbonDataSource, "WSO2_CARBON_DB not found in the repository.");
    }

    @Test(dependsOnMethods = "getAllDataSourceTest")
    public void deleteDataSourceTest() {
        try {
            dsRepository.deleteDataSource("WSO2_CARBON_DB");
            CarbonDataSource carbonDataSource = dsRepository.getDataSource("WSO2_CARBON_DB");
            Assert.assertNull(carbonDataSource, "WSO2_CARBON_DB is deleted, but found in the repository.");
        } catch (DataSourceException e) {
            Assert.fail("Error occurred while deleting the data source");
        }
    }

    @AfterSuite
    public void destroy() {
        clearEnv();
    }

}
