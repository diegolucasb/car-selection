package dev.lucasdabs.carselection.api.data

import dev.lucasdabs.carselection.BuildConfig
import dev.lucasdabs.carselection.util.Constants
import org.junit.Assert
import org.junit.Test

class RequestParameterTest {

    private val parameter = RequestParameter()

    @Test
    fun `test if parameter da default values`() {
        Assert.assertEquals(parameter.page.second, Constants.Pagination.INITIAL_PAGE)
        Assert.assertEquals(parameter.pageSize.second, Constants.Pagination.PAGE_SIZE)
        Assert.assertEquals(parameter.key.second, BuildConfig.WA_KEY)
        Assert.assertEquals(parameter.manufacturerId?.second, null)
        Assert.assertEquals(parameter.modelId?.second, null)
    }

}