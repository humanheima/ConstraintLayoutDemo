package com.dmw.constraintlayoutdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by dumingwei on 2020/7/12
 *
 * Desc:
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBasic.setOnClickListener {
            BasicActivity.launch(this)
        }
        btnDynamic.setOnClickListener {
            DynamicAddViewChangeConstraintActivity.launch(this)
        }
    }
}
