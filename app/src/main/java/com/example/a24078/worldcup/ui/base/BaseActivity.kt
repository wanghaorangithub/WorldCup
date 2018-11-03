package com.example.a24078.worldcup.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

/**
 * Created by haoran on 2018/9/28.
 */
abstract class BaseActivity : AppCompatActivity() {
    @LayoutRes
    protected abstract fun layoutId(): Int

    protected abstract fun created(bundle: Bundle?)

    protected abstract fun resume()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)//透明状态栏
        setContentView(layoutId())
        supportActionBar?.hide()
        created(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        resume()
    }

}

