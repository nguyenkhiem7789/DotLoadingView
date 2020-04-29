package com.nguyen.dotloadingview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.nguyen.dotloading.DotLoadingDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLoadingButton.setOnClickListener {
            Toast.makeText(this, "Display loading 3s", Toast.LENGTH_SHORT).show()
            DotLoadingDialog.show(this)
            Handler().postDelayed(Runnable {
                DotLoadingDialog.dimiss()
            }, 3000)
        }
    }
}
