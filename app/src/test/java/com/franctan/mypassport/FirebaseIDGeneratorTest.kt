package com.franctan.mypassport

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class FirebaseIDGeneratorTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testSameTimeDifferentIds() {
        val timestamp = 1468418909016L
        val tsAsString = "-KMZ_aCN"

        val ids = (1..10).map {
            val value = FirebaseIDGenerator.generateId(instant = timestamp)
            System.out.println(value)
            value
        }

        assertThat(ids.sorted(), equalTo(ids))
        assertThat(ids.map { it.substring(0..7) }, equalTo(ids.map { tsAsString }))
        assertThat(ids.distinct().size, equalTo(ids.size))
    }

    @Test
    fun testSameTimeDifferentStates() {
        val initialState = FirebaseIDGenerator.State()

        val timestamp = 1468418909016L
        val result = FirebaseIDGenerator.generateNextId(initialState, timestamp)


        assertThat(result.nextState.lastInstant, equalTo(timestamp))
        assertNotEquals(result.nextState.lastRandChars, initialState.lastRandChars)
    }
}