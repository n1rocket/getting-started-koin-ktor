package org.koin.sample

import org.junit.After
import org.junit.Test
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest
import org.koin.test.checkModules

class CheckTest : KoinTest {

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun checkAllModules() {
        checkModules(listOf(helloAppModule))
    }
}