package com.example.userlist

import com.example.userlist.util.parseDate
import org.junit.Assert.*
import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun data_parsing_should_work() {
        val string = "1996-04-30T19:26:49.610Z"
        val correctDate = parseDate(string)
        assertEquals("avr. 30 1996", correctDate)
    }
}