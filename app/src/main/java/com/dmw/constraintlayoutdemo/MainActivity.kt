package com.dmw.constraintlayoutdemo

import android.os.Bundle
import android.transition.TransitionManager
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //addViewUseLayoutParams()
        //addViewUseConstraintSet()
        //addGuideLineUseLayoutParams()
        //addGuideLineUseConstraintSet()
        //addPartView()
        //changeLayoutParams()
        /*ivLeft.setOnClickListener {
            changeConstraintSet()
        }*/
        
        addGuideLineUseLayoutParams()
    }

    /**
     * All view is dynamically added.
     * 使用布局参数
     */
    private fun addViewUseLayoutParams() {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.id = R.id.clRoot
        constraintLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        //先设置根布局
        setContentView(constraintLayout)

        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.ic_lake)

        val ivLeftLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ScreenUtil.dpToPx(this, 100), 0
        )
        ivLeftLayoutParams.leftToLeft = R.id.clRoot
        ivLeftLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)
        ivLeftLayoutParams.topToTop = R.id.clRoot
        ivLeftLayoutParams.topMargin = ScreenUtil.dpToPx(this, 8)
        ivLeftLayoutParams.dimensionRatio = "h,16:9"

        ivLeft.layoutParams = ivLeftLayoutParams

        val tvRight = TextView(this)
        tvRight.id = R.id.tvRight
        tvRight.text = getString(R.string.lake_tahoe_title)
        tvRight.textSize = 30F
        val tvRightLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvRightLayoutParams.startToEnd = R.id.ivLeft
        tvRightLayoutParams.topToTop = R.id.clRoot
        tvRightLayoutParams.marginStart = ScreenUtil.dpToPx(this, 16)
        tvRightLayoutParams.topMargin = ScreenUtil.dpToPx(this, 16)

        tvRight.layoutParams = tvRightLayoutParams


        val tvBottom = TextView(this)
        tvBottom.id = R.id.tvBottom
        tvBottom.text = getString(R.string.lake_discription)
        val tvBottomLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            0,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvBottomLayoutParams.startToStart = R.id.clRoot
        tvBottomLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.endToEnd = R.id.clRoot
        tvBottomLayoutParams.marginEnd = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.topToBottom = R.id.ivLeft
        tvBottomLayoutParams.topMargin = ScreenUtil.dpToPx(this, 24)

        tvBottom.layoutParams = tvBottomLayoutParams

        constraintLayout.addView(ivLeft)
        constraintLayout.addView(tvRight)
        constraintLayout.addView(tvBottom)

    }


    /**
     * Add View use ConstraintSet
     */
    private fun addViewUseConstraintSet() {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.id = R.id.clRoot
        constraintLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        //先设置根布局
        setContentView(constraintLayout)

        val constraintSet = ConstraintSet()

        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.ic_lake)

        constraintSet.constrainWidth(R.id.ivLeft, ScreenUtil.dpToPx(this, 100))
        constraintSet.constrainHeight(R.id.ivLeft, 0)
        constraintSet.setDimensionRatio(R.id.ivLeft, "h,16:9")

        //layout_constraintTop_toTopOf
        constraintSet.connect(
            R.id.ivLeft, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP,
            ScreenUtil.dpToPx(this, 16)
        )

        constraintSet.connect(
            R.id.ivLeft, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,
            ScreenUtil.dpToPx(this, 16)
        )


        val tvRight = TextView(this)
        tvRight.id = R.id.tvRight
        tvRight.text = getString(R.string.lake_tahoe_title)
        tvRight.textSize = 30F

        constraintSet.constrainHeight(R.id.tvRight, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        constraintSet.constrainWidth(R.id.tvRight, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        constraintSet.connect(
            R.id.tvRight, ConstraintSet.START, R.id.ivLeft, ConstraintSet.END,
            ScreenUtil.dpToPx(this, 16)
        )
        constraintSet.connect(
            R.id.tvRight, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP,
            ScreenUtil.dpToPx(this, 16)
        )


        val tvBottom = TextView(this)
        tvBottom.id = R.id.tvBottom
        tvBottom.text = getString(R.string.lake_discription)
        //设置高度
        constraintSet.constrainHeight(R.id.tvBottom, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        constraintSet.connect(
            R.id.tvBottom, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,
            ScreenUtil.dpToPx(this, 8)
        )

        constraintSet.connect(
            R.id.tvBottom, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END,
            ScreenUtil.dpToPx(this, 8)
        )

        constraintSet.connect(
            R.id.tvBottom, ConstraintSet.TOP, R.id.ivLeft, ConstraintSet.BOTTOM,
            ScreenUtil.dpToPx(this, 24)
        )


        constraintLayout.addView(ivLeft)
        constraintLayout.addView(tvRight)
        constraintLayout.addView(tvBottom)

        TransitionManager.beginDelayedTransition(constraintLayout)

        constraintSet.applyTo(constraintLayout)
    }


    private fun addGuideLineUseLayoutParams() {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.id = R.id.clRoot
        constraintLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        //先设置根布局
        setContentView(constraintLayout)

        val guideline = Guideline(this)
        guideline.id = R.id.guideline

        val guideLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        guideLayoutParams.guidePercent = 0.4f
        //注意
        guideLayoutParams.orientation = ConstraintLayout.LayoutParams.HORIZONTAL

        guideline.layoutParams = guideLayoutParams

        constraintLayout.addView(guideline)

        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.ic_lake)

        val ivLeftLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            0, 0
        )
        ivLeftLayoutParams.dimensionRatio = "h,16:9"
        ivLeftLayoutParams.topToBottom = R.id.guideline
        ivLeftLayoutParams.startToStart = R.id.clRoot
        ivLeftLayoutParams.endToEnd = R.id.clRoot

        ivLeft.layoutParams = ivLeftLayoutParams

        constraintLayout.addView(ivLeft)

    }

    private fun addGuideLineUseConstraintSet() {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.id = R.id.clRoot
        constraintLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        //先设置根布局
        setContentView(constraintLayout)

        val guideline = Guideline(this)
        guideline.id = R.id.guideline

        val set = ConstraintSet()
        set.create(R.id.guideline, ConstraintSet.HORIZONTAL_GUIDELINE)
        set.constrainWidth(R.id.guideline, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        set.constrainHeight(R.id.guideline, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        set.setGuidelinePercent(R.id.guideline, 0.4f)

        constraintLayout.addView(guideline)

        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.ic_lake)

        set.constrainWidth(R.id.ivLeft, 0)
        set.constrainHeight(R.id.ivLeft, 0)
        set.setDimensionRatio(R.id.ivLeft, "h,16:9")
        set.connect(R.id.ivLeft, ConstraintSet.TOP, R.id.guideline, ConstraintSet.BOTTOM)
        set.connect(R.id.ivLeft, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(R.id.ivLeft, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        constraintLayout.addView(ivLeft)

        TransitionManager.beginDelayedTransition(clRoot)
        set.applyTo(clRoot)
        //设置一个动画效果，让约束改变平滑一点，这一步不是必须的
    }


    private fun addPartView() {
        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.ic_lake)

        val ivLeftLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ScreenUtil.dpToPx(this, 100), 0
        )
        ivLeftLayoutParams.dimensionRatio = "h,16:9"
        ivLeftLayoutParams.topMargin = ScreenUtil.dpToPx(this, 16)
        ivLeftLayoutParams.marginStart = ScreenUtil.dpToPx(this, 16)
        ivLeftLayoutParams.leftToLeft = R.id.clRoot
        ivLeftLayoutParams.topToTop = R.id.clRoot

        ivLeft.layoutParams = ivLeftLayoutParams

        val tvRight = TextView(this)
        tvRight.id = R.id.tvRight
        tvRight.text = getString(R.string.lake_tahoe_title)
        tvRight.textSize = 30F
        val tvRightLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvRightLayoutParams.startToEnd = R.id.ivLeft
        tvRightLayoutParams.topToTop = R.id.clRoot
        tvRightLayoutParams.marginStart = ScreenUtil.dpToPx(this, 16)
        tvRightLayoutParams.topMargin = ScreenUtil.dpToPx(this, 16)

        tvRight.layoutParams = tvRightLayoutParams


        val tvBottomLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            0,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvBottomLayoutParams.startToStart = R.id.clRoot
        tvBottomLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.endToEnd = R.id.clRoot
        tvBottomLayoutParams.marginEnd = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.topToBottom = R.id.ivLeft
        tvBottomLayoutParams.topMargin = ScreenUtil.dpToPx(this, 24)

        tvBottomLayoutParams.bottomMargin = ScreenUtil.dpToPx(this, 8)

        //重新为布局中已经存在的tvBottom设置新的布局参数。
        tvBottom.layoutParams = tvBottomLayoutParams

        clRoot.addView(ivLeft)
        clRoot.addView(tvRight)

    }

    private fun changeLayoutParams() {
        val ivLeftLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            0, 0
        )
        ivLeftLayoutParams.leftMargin = ScreenUtil.dpToPx(this, 16)
        ivLeftLayoutParams.rightMargin = ScreenUtil.dpToPx(this, 16)
        ivLeftLayoutParams.topMargin = ScreenUtil.dpToPx(this, 16)
        ivLeftLayoutParams.leftToLeft = R.id.clRoot
        ivLeftLayoutParams.rightToRight = R.id.clRoot
        ivLeftLayoutParams.dimensionRatio = "h,16:9"
        ivLeftLayoutParams.topToTop = R.id.clRoot

        //修改布局参数
        ivLeft.layoutParams = ivLeftLayoutParams

        val tvRightLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvRightLayoutParams.leftToLeft = R.id.clRoot
        tvRightLayoutParams.topToBottom = R.id.ivLeft
        tvRightLayoutParams.marginStart = ScreenUtil.dpToPx(this, 16)
        tvRightLayoutParams.topMargin = ScreenUtil.dpToPx(this, 16)

        tvRight.layoutParams = tvRightLayoutParams


        val tvBottomLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            0,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvBottomLayoutParams.startToStart = R.id.clRoot
        tvBottomLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.endToEnd = R.id.clRoot
        tvBottomLayoutParams.marginEnd = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.topToBottom = R.id.tvRight
        tvBottomLayoutParams.topMargin = ScreenUtil.dpToPx(this, 24)

        tvBottomLayoutParams.bottomMargin = ScreenUtil.dpToPx(this, 8)

        tvBottom.layoutParams = tvBottomLayoutParams
    }

    private fun changeConstraintSet() {
        val constraintSet = ConstraintSet()
        //从根布局中克隆约束参数
        constraintSet.clone(clRoot)

        //清空控件原有的约束
        constraintSet.clear(R.id.ivLeft)
        constraintSet.clear(R.id.tvRight)
        constraintSet.clear(R.id.tvBottom)

        constraintSet.constrainWidth(R.id.ivLeft, 0)
        constraintSet.constrainHeight(R.id.ivLeft, 0)
        //设置ivLeft顶部和父布局顶部对齐
        constraintSet.connect(
            R.id.ivLeft, ConstraintSet.TOP, R.id.clRoot, ConstraintSet.TOP,
            ScreenUtil.dpToPx(this, 16)
        )
        constraintSet.connect(
            R.id.ivLeft, ConstraintSet.START, R.id.clRoot, ConstraintSet.START,
            ScreenUtil.dpToPx(this, 16)
        )
        constraintSet.connect(
            R.id.ivLeft, ConstraintSet.END, R.id.clRoot, ConstraintSet.END,
            ScreenUtil.dpToPx(this, 16)
        )
        //设置宽高比
        constraintSet.setDimensionRatio(R.id.ivLeft, "h,16:9")


        constraintSet.constrainWidth(R.id.tvRight, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        constraintSet.constrainHeight(R.id.tvRight, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        constraintSet.connect(
            R.id.tvRight, ConstraintSet.TOP, R.id.ivLeft, ConstraintSet.BOTTOM,
            ScreenUtil.dpToPx(this, 24)
        )

        constraintSet.connect(
            R.id.tvRight, ConstraintSet.START, R.id.clRoot, ConstraintSet.START,
            ScreenUtil.dpToPx(this, 8)
        )

        constraintSet.constrainHeight(R.id.tvBottom, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        constraintSet.connect(
            R.id.tvBottom, ConstraintSet.START, R.id.clRoot, ConstraintSet.START,
            ScreenUtil.dpToPx(this, 8)
        )
        constraintSet.connect(
            R.id.tvBottom, ConstraintSet.END, R.id.clRoot, ConstraintSet.END,
            ScreenUtil.dpToPx(this, 8)
        )

        constraintSet.connect(
            R.id.tvBottom, ConstraintSet.TOP, R.id.tvRight, ConstraintSet.BOTTOM,
            ScreenUtil.dpToPx(this, 24)
        )

        constraintSet.applyTo(clRoot)
        //设置一个动画效果，让约束改变平滑一点，这一步不是必须的
        TransitionManager.beginDelayedTransition(clRoot)
    }


}
