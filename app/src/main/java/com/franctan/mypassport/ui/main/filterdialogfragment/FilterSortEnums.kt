package com.franctan.mypassport.ui.main.filterdialogfragment

enum class FilterOptions {
    SHOW_ALL,
    MALES_ONLY,
    FEMALES_ONLY;

    companion object {
        fun default() = SHOW_ALL
    }
}

enum class SortOptions {
    NAME_ASC,
    NAME_DESC,
    AGE_ASC,
    AGE_DESC;


    companion object {
        fun default() = NAME_ASC
    }
}







