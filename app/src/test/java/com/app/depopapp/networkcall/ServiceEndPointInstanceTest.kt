package com.app.depopapp.networkcall

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/*  @Author : Sumit Patel
    Write test case for testing a service with coroutine
    runBloking method to check service is success or fail to call
* */
class ServiceEndPointInstanceTest {

    // Test case success when it find status = S in response object
    @Test
    fun testServiceStatusSuccess() = runBlocking{
        val serviceInstance = ServiceEndPointInstance.getInstance().create(Service::class.java)
        val response = serviceInstance.getProductList("10")

        val actualResponse = response.objects[0].status
        val expectedStatus = "S"
        assertEquals(expectedStatus, actualResponse)
    }

    // Status from service response not match than this test will fail
    @Test
    fun testServiceStatusFail() = runBlocking{
        val serviceInstance = ServiceEndPointInstance.getInstance().create(Service::class.java)
        val response = serviceInstance.getProductList("10")

        val actualResponse = response.objects[0].status
        val expectedStatus = "F"
        assertEquals(expectedStatus, actualResponse)
    }
}