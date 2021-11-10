package com.maximshuhman.LSM

import android.content.Intent
import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SplashActivity : AppCompatActivity() {

    private lateinit var appVersionView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SplashTheme)
        setContentView(R.layout.activity_splash)

        appVersionView = findViewById(R.id.app_version_view)

        val packageInfo: PackageInfo = this.packageManager
            .getPackageInfo(this.packageName, 0)

        appVersionView.text =  packageInfo.versionName

        Thread.sleep(600)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)

    }
}