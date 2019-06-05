package dev.lucasdabs.carselection.api.data

import com.google.gson.GsonBuilder
import dev.lucasdabs.carselection.api.response.BaseResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BaseDataTest {

    private lateinit var mockedJson: String
    private val gson by lazy { GsonBuilder().create() }

    @Before
    fun setup() {

        mockedJson = "{\n" +
                "            \"page\": 1,\n" +
                "            \"pageSize\": 30,\n" +
                "            \"totalPageCount\": 3,\n" +
                "            \"wkda\": {\n" +
                "            \"225\": \"Nissan\",\n" +
                "            \"380\": \"Jaguar\" }\n" +
                "        }"
    }

    @Test
    fun `test if base data class is properly parsed from json`() {
        val response = gson.fromJson(mockedJson, BaseResponse::class.java)
        val baseDataParsed = response.data.map { BaseData(it.key, it.value) }

        val baseData = BaseData("255", "Nissan")

        Assert.assertNotNull(response)
        Assert.assertEquals(2, baseDataParsed.size)
        Assert.assertTrue(baseData.toString() == baseDataParsed[0].toString())
    }
}