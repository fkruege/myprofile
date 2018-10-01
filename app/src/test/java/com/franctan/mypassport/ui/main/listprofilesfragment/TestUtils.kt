package com.franctan.mypassport.ui.main.listprofilesfragment

import com.franctan.models.Gender
import com.franctan.models.Profile

class TestUtils {
    companion object {

        val profileA_Male = Profile("a", "A", "A", 1, Gender.Male, emptyList(), "")
        val profileB_Female = Profile("b", "B", "B", 2, Gender.Female, emptyList(), "")
        val profileC_Female = Profile("c", "C", "C", 3, Gender.Female, emptyList(), "")
        val profileD_Male = Profile("d", "D", "D", 4, Gender.Male, emptyList(), "")

        val TEST_PROFILE_LIST = listOf(profileA_Male, profileD_Male, profileB_Female, profileC_Female)
    }
}


