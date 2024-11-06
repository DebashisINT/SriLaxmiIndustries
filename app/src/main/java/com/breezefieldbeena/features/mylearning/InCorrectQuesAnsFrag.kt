package com.breezefieldbeena.features.mylearning

import android.app.Activity
import android.content.Context
import android.os.Bundle
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
import com.breezefieldbeena.features.mylearning.apiCall.LMSRepoProvider
import com.pnikosis.materialishprogress.ProgressWheel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InCorrectQuesAnsFrag : BaseFragment() , View.OnClickListener, RetryInCorrectAdapter.OnRetryClickListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var incrrct_anim: LottieAnimationView
    private lateinit var adapter: RetryInCorrectAdapter
    private lateinit var mContext: Context
    private lateinit var no_incrcct_answr: TextView
  //  private lateinit var ll_root_in_correct: LinearLayout
   // private lateinit var iv_no_data_found_retry: ImageView
   // private lateinit var content_name: TextView
    lateinit var progress_wheel: ProgressWheel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object {
            var play_url:String=""
            var question_id:Int=0
            var topic_id:Int=0
            var content_id:Int=0

      /*  fun getInstance(topicId: String, storeContentId: String,store_content_url:String): InCorrectQuesAnsFrag {
            val fragment = InCorrectQuesAnsFrag()
            this.topicId = topicId
            this.storeContentId = storeContentId
            this.store_content_url = store_content_url
            return fragment
        }*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_in_correct_ques_ans, container, false)
        (context as Activity).window.setStatusBarColor(resources.getColor(R.color.toolbar_lms))
        initView(view)
        println("Topic ID: ${topic_id}")
        println("Store Content ID: ${content_id}")
        println("Store Content URL: ${play_url}")
        return view
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.rv_incorrect)
      /*  content_name = view.findViewById(R.id.content_name)
        no_incrcct_answr = view.findViewById(R.id.no_incrcct_answr)
        iv_no_data_found_retry = view.findViewById(R.id.iv_no_data_found_retry)
        ll_root_in_correct = view.findViewById(R.id.ll_root_in_correct)
        incrrct_anim = view.findViewById(R.id.incrrct_anim)*/
        progress_wheel = view.findViewById(R.id.incrct_progress_wheel)
        recyclerView.layoutManager = LinearLayoutManager(mContext)
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
                    topic_id.toString(),
                    content_id.toString()
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
                                       // ll_root_in_correct.visibility = View.VISIBLE
                                       // iv_no_data_found_retry.visibility = View.GONE
                                        adapter = RetryInCorrectAdapter(
                                            incorrectAnswers,
                                            mContext,
                                            this
                                        )
                                        recyclerView.adapter = adapter
                                    }else{
                                        //ll_root_in_correct.visibility = View.GONE
                                       // iv_no_data_found_retry.visibility = View.VISIBLE
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


    override fun onClick(v: View?) {


    }

    override fun onRetryClicked(question: Question_answer_fetch_list) {

        RetryPlayFrag.play_url = play_url
        RetryPlayFrag.question_id = question.question_id
        RetryPlayFrag.topic_id = question.topic_id
        RetryPlayFrag.content_id = question.content_id
        (mContext as DashboardActivity).loadFragment(FragType.RetryPlayFrag, true, "")

    }
}