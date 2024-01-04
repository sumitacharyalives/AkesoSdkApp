package com.android.akesosdkapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chintansoni.android.repositorypattern.R
import com.android.akesosdkapp.model.local.entity.User
import com.android.akesosdkapp.view.adapter.UserRecyclerAdapter
import com.android.akesosdkapp.view.fragment.DetailFragment
import com.android.akesosdkapp.view.fragment.ListFragment
import com.example.akesocaresdk.AKesoSdkMethodsImpl
import com.example.akesocaresdk.AkesoSdkMethods

class UsersActivity : AppCompatActivity(), UserRecyclerAdapter.ItemTouchListener {
    override fun onItemClick(user: User) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_up,
                R.anim.slide_down,
                R.anim.slide_up,
                R.anim.slide_down
            )
            .add(R.id.container, DetailFragment.newInstance(user))
            .addToBackStack(detailFragmentTag)
            .commit()
    }

    companion object {
        const val detailFragmentTag = "details_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_activity)
        val akesoSdkMethods: AkesoSdkMethods = AKesoSdkMethodsImpl()
        akesoSdkMethods.init()
        akesoSdkMethods.authenticateApiKey("testKey")


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commit()
        }
    }
}
