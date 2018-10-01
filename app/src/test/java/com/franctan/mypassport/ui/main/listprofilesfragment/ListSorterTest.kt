package com.franctan.mypassport.ui.main.listprofilesfragment

import com.franctan.mypassport.ui.main.filterdialogfragment.FilterOptions
import com.franctan.mypassport.ui.main.filterdialogfragment.FilterSortMapper
import com.franctan.mypassport.ui.main.filterdialogfragment.SortOptions
import com.franctan.mypassport.ui.main.listprofilesfragment.TestUtils.Companion.TEST_PROFILE_LIST
import com.franctan.mypassport.ui.main.listprofilesfragment.TestUtils.Companion.profileA_Male
import com.franctan.mypassport.ui.main.listprofilesfragment.TestUtils.Companion.profileB_Female
import com.franctan.mypassport.ui.main.listprofilesfragment.TestUtils.Companion.profileC_Female
import com.franctan.mypassport.ui.main.listprofilesfragment.TestUtils.Companion.profileD_Male
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class ListSorterTest {

    @Mock
    private lateinit var filterSortMapper: FilterSortMapper

    @InjectMocks
    private lateinit var listSorter: ListSorter
   @Before
    fun setUp() {
        initMocks(this)

    }


    @Test
    fun filterAndSortDefault() {
        `when`(filterSortMapper.mapFilter(anyString())).thenReturn(FilterOptions.SHOW_ALL)
        `when`(filterSortMapper.mapSort(anyString())).thenReturn(SortOptions.NAME_ASC)
        val result = listSorter.filterAndSort(TEST_PROFILE_LIST, "", "")

        assertEquals(4, result.count())
        assertEquals(profileA_Male, result[0])
        assertEquals(profileB_Female, result[1])
        assertEquals(profileC_Female, result[2])
        assertEquals(profileD_Male, result[3])
    }


    @Test
    fun filterAndSortMalesOnlyNameDesc() {
        `when`(filterSortMapper.mapFilter(anyString())).thenReturn(FilterOptions.MALES_ONLY)
        `when`(filterSortMapper.mapSort(anyString())).thenReturn(SortOptions.NAME_DESC)
        val result = listSorter.filterAndSort(TEST_PROFILE_LIST, "", "")

        assertEquals(2, result.count())
        assertEquals(profileD_Male, result[0])
        assertEquals(profileA_Male, result[1])
    }


    @Test
    fun filterAndSortFemalesOnlyAgeAsc() {
        `when`(filterSortMapper.mapFilter(anyString())).thenReturn(FilterOptions.FEMALES_ONLY)
        `when`(filterSortMapper.mapSort(anyString())).thenReturn(SortOptions.AGE_ASC)
        val result = listSorter.filterAndSort(TEST_PROFILE_LIST, "", "")

        assertEquals(2, result.count())
        assertEquals(profileB_Female, result[0])
        assertEquals(profileC_Female, result[1])
    }
}