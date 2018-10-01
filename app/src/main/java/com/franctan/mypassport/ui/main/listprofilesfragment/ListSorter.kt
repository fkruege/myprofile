package com.franctan.mypassport.ui.main.listprofilesfragment

import com.franctan.models.Gender
import com.franctan.models.Profile
import com.franctan.mypassport.ui.main.filterdialogfragment.FilterOptions
import com.franctan.mypassport.ui.main.filterdialogfragment.FilterSortMapper
import com.franctan.mypassport.ui.main.filterdialogfragment.SortOptions
import javax.inject.Inject


class ListSorter
@Inject constructor(
        private val filterSortMapper: FilterSortMapper
) {

    fun filterAndSort(profiles: List<Profile>, filterString: String, sortString: String): List<Profile> {
        val filterOptions = filterSortMapper.mapFilter(filterString)
        val sortOptions = filterSortMapper.mapSort(sortString)

        val filteredList = filterProfiles(profiles, filterOptions)
        val sortedList = sortProfiles(filteredList, sortOptions)
        return sortedList
    }

    private fun filterProfiles(profiles: List<Profile>, filterOptions: FilterOptions): List<Profile> {
        if (filterOptions == FilterOptions.MALES_ONLY) {
            return profiles.filterByGender(Gender.Male)
        } else if (filterOptions == FilterOptions.FEMALES_ONLY) {
            return profiles.filterByGender(Gender.Female)
        } else {
            return profiles
        }
    }

    private fun List<Profile>.filterByGender(gender: Gender): List<Profile> {
        return this.filter { profile -> profile.gender == gender }
    }


    private fun sortProfiles(profiles: List<Profile>, sortOptions: SortOptions): List<Profile> {
        if (sortOptions == SortOptions.NAME_DESC) {
            return profiles.sortedWith(compareByDescending<Profile> { it.firstName }.thenByDescending { it.lastName })
        } else if (sortOptions == SortOptions.AGE_ASC) {
            return profiles.sortedBy { it.age }
        } else if (sortOptions == SortOptions.AGE_DESC) {
            return profiles.sortedByDescending { it.age }
        } else {
            return profiles.sortedWith(compareBy({ it.firstName }, { it.lastName }))
        }

    }


}


