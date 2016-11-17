package me.changyuheng.proposeapp

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent

import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ProposeActivity : FragmentActivity() {

    private var TAG = "ProposeActivity"
    private var mContentView: View? = null
    private var mOnPressedContentView: View? = null
    private var mMiddleContentView: View? = null
    private var mBottomContentView: View? = null
    private var mVisible: Boolean = false
    private var mAnim: Animation? = null
    private var mLayout: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_propose)

        mVisible = true
        mContentView = findViewById(R.id.fullscreen_content)
        mOnPressedContentView = findViewById(R.id.on_pressed_fullscreen_content)
        mMiddleContentView = findViewById(R.id.middle_fullscreen_content)
        mBottomContentView = findViewById(R.id.bottom_fullscreen_content)

        mContentView!!.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

        mMiddleContentView!!.viewTreeObserver.addOnGlobalLayoutListener({
            if (!mLayout) {
                mAnim = TranslateAnimation(
                        0f, 0f, 0f, -mMiddleContentView!!.measuredHeight.toFloat())
                mAnim!!.duration = 6000
                mAnim!!.fillAfter = true
                mAnim!!.setAnimationListener(object: Animation.AnimationListener {
                    override fun onAnimationEnd(p0: Animation?) {
                        YoYo.with(Techniques.TakingOff).duration(1).playOn(mMiddleContentView!!)
                        YoYo.with(Techniques.Pulse).duration(700).playOn(mContentView!!)
                        YoYo.with(Techniques.Pulse).duration(700).playOn(mBottomContentView!!)
                        // TODO: make a request
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                    }

                    override fun onAnimationStart(p0: Animation?) {
                    }
                })

                mMiddleContentView!!.startAnimation(mAnim!!)

                // TODO: 喔喔喔喔 你是我的花朵
            }
            mLayout = true
        })
    }
}
