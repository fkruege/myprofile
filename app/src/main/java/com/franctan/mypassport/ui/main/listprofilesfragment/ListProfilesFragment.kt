package com.franctan.mypassport.ui.main.listprofilesfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franctan.firebaserepo.daos.ProfilesDao
import com.franctan.models.Profile
import com.franctan.mypassport.R
import com.franctan.mypassport.ui.main.listprofilesfragment.list.ProfilesAdapter
import dagger.android.support.AndroidSupportInjection
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list_profiles.*
import timber.log.Timber
import javax.inject.Inject

class ListProfilesFragment : Fragment() {

    @Inject
    lateinit var profilesDao: ProfilesDao

    private lateinit var profilesAdapter : ProfilesAdapter

    companion object {
        fun newInstance(): ListProfilesFragment {
            return ListProfilesFragment()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profilesAdapter = ProfilesAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_profiles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = toolbar
        toolbar.title = "Passport Profiles"
        val parentActivity : AppCompatActivity = this.activity as AppCompatActivity
        parentActivity.setSupportActionBar(toolbar)

        setupBottomBarMenu()
        setupRecyclerView()

        profilesDao.getProfiles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Profile>> {
                    override fun onSuccess(t: List<Profile>) {
                        profilesAdapter.update(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })
    }

    private fun setupBottomBarMenu() {
        bottom_appbar.replaceMenu(R.menu.menu)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        rvProfiles.layoutManager = layoutManager
        rvProfiles.adapter = profilesAdapter
    }
}
