package com.application.myfamily

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainScreen : AppCompatActivity() {

    val permission = arrayOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS
    )

    val permissionCode = 89

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        askForPermission()

        val navBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)

        navBar.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.nav_home ->{
                    inflateFragment(HomeFragment.newInstance())
                }
                R.id.nav_guard -> {
                    inflateFragment(GuardFragment.newInstance())
                }
                R.id.nav_dashboard -> {
                    inflateFragment(DashboardFragment.newInstance())
                }
                R.id.nav_profile -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
            }
            true
        }
        navBar.selectedItemId = R.id.nav_home
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this,permission,permissionCode)
    }

    private fun inflateFragment(newInstance: Fragment) {
        //load fragment here
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,newInstance)
        transaction.commit()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionCode){
            if (allPermissionGranted()){
                //granted permission work
            }else{
                //denied permission work
            }
        }
    }

    private fun allPermissionGranted(): Boolean {
        for (item in permission){
            if (ContextCompat.checkSelfPermission(this,item) == PackageManager.PERMISSION_GRANTED){
                return true
            }
        }
        return false
    }
}