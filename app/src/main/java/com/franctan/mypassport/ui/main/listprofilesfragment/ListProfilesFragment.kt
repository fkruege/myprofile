package com.franctan.mypassport.ui.main.listprofilesfragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.franctan.lonelyplanetcurrencyguide.injection.view_model.ViewModelFactory
import com.franctan.models.Profile
import com.franctan.mypassport.R
import com.franctan.mypassport.databinding.FragmentListProfilesBinding
import com.franctan.mypassport.ui.main.listprofilesfragment.list.ProfilesAdapter
import com.franctan.mypassport.ui.navigation.Navigator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list_profiles.*
import javax.inject.Inject

class ListProfilesFragment : Fragment(), ProfileClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigator: Navigator

    private lateinit var listProfilesBinding: FragmentListProfilesBinding
    private lateinit var profilesAdapter: ProfilesAdapter
    private lateinit var listProfilesViewModel: ListProfilesViewModel

    companion object {
        fun newInstance(): ListProfilesFragment {
            return ListProfilesFragment()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        loadViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profilesAdapter = ProfilesAdapter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        listProfilesBinding = FragmentListProfilesBinding.inflate(inflater, container, false)
        listProfilesBinding.listProfileViewModel = listProfilesViewModel
        return listProfilesBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.btnFilter) {
            navigator.goToFilterDialog()
        } else if (item.itemId == R.id.btnSort) {
            navigator.goToSortDialog()
        } else if (item.itemId == R.id.btnClear) {
            listProfilesViewModel.clearFilterAndSort()
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()

        listenForProfileUpdates()
        listenForAddNewProfileEvent()
    }

    override fun clicked(profile: Profile) {
        navigator.goToViewProfile(profile.id)
    }

    private fun listenForProfileUpdates() {
        listProfilesViewModel
                .ProfilesLiveData
                .observe(this, Observer<List<Profile>> { list ->
                    list?.let { inList -> profilesAdapter.update(inList) }
                })
    }

    private fun setupToolbar() {
        val toolbar = toolbar
        val parentActivity: AppCompatActivity = this.activity as AppCompatActivity
        parentActivity.setSupportActionBar(toolbar)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        rvProfiles.layoutManager = layoutManager
        rvProfiles.adapter = profilesAdapter
    }

    private fun loadViewModel() {
        listProfilesViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListProfilesViewModel::class.java)
    }

    private fun listenForAddNewProfileEvent() {
        listProfilesViewModel.addNewProfileEvent.observe(this, Observer {
            navigator.goToEditProfile("")
        })
    }

}
