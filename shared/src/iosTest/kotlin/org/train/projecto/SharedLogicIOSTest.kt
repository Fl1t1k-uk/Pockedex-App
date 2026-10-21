package org.train.projecto

import kotlin.test.Test
import kotlin.test.assertEquals
import org.train.projecto.presentation.localization.stringsFor

class SharedLogicIOSTest {

    @Test
    fun `Ukrainian strings are available in the shared iOS target`() {
        assertEquals("Налаштування", stringsFor("uk").settings)
    }
}
