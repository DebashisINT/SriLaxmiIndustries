package com.breezefieldbeena.features.mylearning

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.breezefieldbeena.R
import com.breezefieldbeena.app.NetworkConstant
import com.breezefieldbeena.app.Pref
import com.breezefieldbeena.app.types.FragType
import com.breezefieldbeena.app.utils.AppUtils
import com.breezefieldbeena.base.presentation.BaseActivity
import com.breezefieldbeena.base.presentation.BaseFragment
import com.breezefieldbeena.features.dashboard.presentation.DashboardActivity
import com.breezefieldbeena.features.mylearning.MyTopicsWiseContents.Companion
import com.breezefieldbeena.features.mylearning.apiCall.LMSRepoProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pnikosis.materialishprogress.ProgressWheel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.performance_item.view.perform_thumbnail

class RetryIncorrectQuizFrag : BaseFragment() , View.OnClickListener ,RetryInCorrectQuestionAnswerAdapter.OnRetryClickListener{
    private lateinit var mContext: Context

   // private lateinit var tv_correct_tab: TextView
    private lateinit var tv_incorrect_tab: TextView
    private lateinit var topic_name: TextView
    private lateinit var content_name: TextView
    private lateinit var content_thumbnail: ImageView
   // private lateinit var iv_crct: ImageView
  //  private lateinit var iv_incrct: ImageView
   // private lateinit var ll_correct_tab: LinearLayout
    private lateinit var ll_incorrect_tab: LinearLayout
    private lateinit var retryTabPagerAdapter: RetryTabPagerAdapter
   // private lateinit var tab_viewpager: ViewPager
    private lateinit var rv_incorrect_answer_tab: RecyclerView
    private lateinit var no_incrcct_answr: TextView
    private lateinit var iv_no_data_found_retry: ImageView
    private lateinit var incrrct_anim: LottieAnimationView
    lateinit var progress_wheel: ProgressWheel
    private lateinit var adapter: RetryInCorrectQuestionAnswerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    companion object {
        var topic_id: String = ""
        var store_content_id: String = ""
        var store_topic_name: String = ""
        var previousFrag: String = ""
        var store_content_url: String = ""
        var content_name_: String = ""
        var content_thumbnail_: String = ""
        fun getInstance(objects: Any): RetryIncorrectQuizFrag {
            val retryIncorrectQuizFrag = RetryIncorrectQuizFrag()

            try {
                if (!TextUtils.isEmpty(objects.toString())) {
                    val parts = objects.toString().split("~")
                    topic_id = parts[0]
                    store_content_id = parts[1]
                    store_topic_name = parts[2]
                    store_content_url = parts[3]
                    content_name_ = parts[4]
                    content_thumbnail_ = parts[5]
                } else {
                    topic_id = ""
                    store_content_id = ""
                    store_topic_name = ""
                    store_content_url = ""
                    content_name_ = ""
                    content_thumbnail_ = ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            println("tag_topic_id" + topic_id)

            return retryIncorrectQuizFrag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_retry_incorrect_quiz, container, false)
        (context as Activity).requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        (context as Activity).window.setStatusBarColor(resources.getColor(R.color.toolbar_lms))
        initView(view)
        //initAdapter()
        return view
    }

    private fun initView(view: View) {

      //  ll_correct_tab = view.findViewById(R.id.ll_correct_tab)
        topic_name = view.findViewById(R.id.topic_name)
        ll_incorrect_tab = view.findViewById(R.id.ll_incorrect_tab)
      //  tv_correct_tab = view.findViewById(R.id.tv_correct_tab)
        tv_incorrect_tab = view.findViewById(R.id.tv_incorrect_tab)
      //  iv_crct = view.findViewById(R.id.iv_crct)
      //  iv_incrct = view.findViewById(R.id.iv_incrct)
      //  tab_viewpager = view.findViewById(R.id.tab_viewpager)
        content_name = view.findViewById(R.id.content_name)
        content_thumbnail = view.findViewById(R.id.content_thumbnail)
        retryTabPagerAdapter = RetryTabPagerAdapter(fragmentManager , topic_id , store_content_id ,store_content_url)
       // tv_correct_tab.setOnClickListener(this)
        tv_incorrect_tab.setOnClickListener(this)
        //tab_viewpager.currentItem = 0
       // tab_viewpager.currentItem = 0
       // isIncorrectWise(true)
       // ll_correct_tab.setBackground(ContextCompat.getDrawable(mContext, R.drawable.new_retry_correctbtn_bck_inactv))
      //  ll_incorrect_tab.setBackground(ContextCompat.getDrawable(mContext, R.drawable.new_retry_incorrectbtn_bck ))
       // iv_incrct.setBackgroundResource(R.drawable.cross_wt);
      //  iv_crct.setBackgroundResource(R.drawable.correct_ans_green_check);

       // tv_correct_tab.setTextColor(Color.parseColor("#000000"))
       // tv_incorrect_tab.setTextColor(Color.parseColor("#FFFFFF"))
        //topic_name.setText("Topic : "+store_topic_name)
        topic_name.setText("Topic : "+store_topic_name)
        //content_name.setText("Content : "+content_name_)
        content_name.setText(content_name_)
        /*tab_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    isIncorrectWise(true)
                } *//*else {
                    isIncorrectWise(false)
                }*//*
            }

        })*/

        rv_incorrect_answer_tab = view.findViewById(R.id.rv_incorrect_answer_tab)
        //no_incrcct_answr = view.findViewById(R.id.no_incrcct_answr)
        iv_no_data_found_retry = view.findViewById(R.id.iv_no_data_found_retry)
        incrrct_anim = view.findViewById(R.id.incrrct_anim)
        progress_wheel = view.findViewById(R.id.incrct_progress_wheel)
        rv_incorrect_answer_tab.layoutManager = LinearLayoutManager(mContext)


        if (store_content_url != null) {

            Glide.with(mContext)
                .load(content_thumbnail_)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_image).error(R.drawable.ic_image)
                )
                .thumbnail(
                    Glide.with(mContext).load(content_thumbnail_)
                )
                .into(content_thumbnail)
        } else {
            //If thumnail image not available ,set default image of that particular content
            content_thumbnail.setImageResource(R.drawable.ic_image)
        }

        getTopicContentWiseAnswerListsAPICalling()
    }

    private fun getTopicContentWiseAnswerListsAPICalling() {

        try {
            progress_wheel.visibility = View.VISIBLE
            Log.d("deleteImei call","" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopicContentWiseAnswerLists(
                    Pref.user_id!!,
                    topic_id,
                    store_content_id
                )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as TopicContentWiseAnswerListsFetchResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            progress_wheel.visibility = View.GONE

                            try {
                                // content_name.setText("Content : "+response.content_name)
                                if (response.question_answer_fetch_list != null && response.question_answer_fetch_list.isNotEmpty()) {
                                    // Filter list to get only the items with isCorrectAnswer set to false (incorrect answers)
                                    val incorrectAnswers = response.question_answer_fetch_list.filter { !it.isCorrectAnswer }
                                    println("Incorrect Answers: $incorrectAnswers")

                                    // Filter list to get only the items with isCorrectAnswer set to true (correct answers)
                                    val correctAnswers = response.question_answer_fetch_list.filter { it.isCorrectAnswer }
                                    println("Correct Answers: $correctAnswers")

                                    if (incorrectAnswers.size>0) {
                                        //ll_root_in_correct.visibility = View.VISIBLE
                                        iv_no_data_found_retry.visibility = View.GONE
                                        adapter = RetryInCorrectQuestionAnswerAdapter(incorrectAnswers ,mContext , this ,
                                            store_topic_name , content_name_ ,content_thumbnail_)
                                        rv_incorrect_answer_tab.adapter = adapter
                                    }else{
                                        //ll_root_in_correct.visibility = View.GONE
                                        iv_no_data_found_retry.visibility = View.VISIBLE
                                    }

                                } else {
                                    progress_wheel.visibility = View.GONE
                                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                                }
                            } catch (ex: Exception) {
                                ex.printStackTrace()
                            }
                        } else {
                            progress_wheel.visibility = View.GONE
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))

                        }
                    }, { error ->
                        println("errortopicwiselist"+error.message)
                        progress_wheel.visibility = View.GONE
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            progress_wheel.visibility = View.GONE
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }

    /*  private fun initAdapter() {
          tab_viewpager.adapter = retryTabPagerAdapter
      }

      open fun refreshAdapter() {
          tab_viewpager.adapter?.notifyDataSetChanged()
      }*/


    override fun onClick(p0: View?) {
        when (p0!!.id) {

           /* R.id.tv_incorrect_tab -> {
                isIncorrectWise(true)
                tab_viewpager.currentItem = 0
            }*/
            /*R.id.tv_correct_tab -> {
                isIncorrectWise(false)
                tab_viewpager.currentItem = 1
            }*/
        }
    }

    fun isIncorrectWise(isIncorrectWise: Boolean) {

        if (isIncorrectWise) {
            tv_incorrect_tab.isSelected = true
           // tv_correct_tab.isSelected = false

           // ll_incorrect_tab.setBackground(ContextCompat.getDrawable(mContext, R.drawable.new_retry_incorrectbtn_bck ))
           // ll_correct_tab.setBackground(ContextCompat.getDrawable(mContext, R.drawable.new_retry_correctbtn_bck_inactv))

           // iv_incrct.setBackgroundResource(R.drawable.cross_wt);
           // iv_crct.setBackgroundResource(R.drawable.correct_ans_green_check);

           // tv_incorrect_tab.setTextColor(Color.parseColor("#FFFFFF"))
          //  tv_correct_tab.setTextColor(Color.parseColor("#000000"))


        } else {
          //  tv_incorrect_tab.isSelected = false
           // tv_correct_tab.isSelected = true

          //  ll_correct_tab.setBackground(ContextCompat.getDrawable(mContext, R.drawable.new_retry_correctbtn_bck))
          //  ll_incorrect_tab.setBackground(ContextCompat.getDrawable(mContext, R.drawable.new_retry_incorrectbtn_bck_inactv))

          //  iv_crct.setBackgroundResource(R.drawable.checked_wt)
          //  iv_incrct.setBackgroundResource(R.drawable.cross_red);

          //  tv_correct_tab.setTextColor(Color.parseColor("#FFFFFF"))
          //  tv_incorrect_tab.setTextColor(Color.parseColor("#000000"))
        }
    }

    override fun onRetryClicked(question: Question_answer_fetch_list) {
        RetryPlayFrag.play_url = store_content_url
        RetryPlayFrag.question_id = question.question_id
        RetryPlayFrag.topic_id = question.topic_id
        RetryPlayFrag.content_id = question.content_id


        /*InCorrectQuesAnsFrag.play_url = store_content_url
        InCorrectQuesAnsFrag.question_id = question.question_id
        InCorrectQuesAnsFrag.topic_id = question.topic_id
        InCorrectQuesAnsFrag.content_id = question.content_id*/

        //(mContext as DashboardActivity).loadFragment(FragType.InCorrectQuesAnsFrag, true, topic_id +"~"+ store_content_id +"~"+ topic_name + "~"+ store_content_url)

        (mContext as DashboardActivity).loadFragment(FragType.RetryPlayFrag, true, "")
    }


}