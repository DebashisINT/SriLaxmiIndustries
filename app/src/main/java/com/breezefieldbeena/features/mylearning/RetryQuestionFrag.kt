package com.breezefieldbeena.features.mylearning

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.breezefieldbeena.R
import com.breezefieldbeena.app.NetworkConstant
import com.breezefieldbeena.app.Pref
import com.breezefieldbeena.app.utils.AppUtils
import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.base.presentation.BaseActivity
import com.breezefieldbeena.base.presentation.BaseFragment
import com.breezefieldbeena.features.dashboard.presentation.DashboardActivity
import com.breezefieldbeena.features.mylearning.apiCall.LMSRepoProvider
import com.pnikosis.materialishprogress.ProgressWheel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class RetryQuestionFrag : BaseFragment() {
    private lateinit var mContext: Context
    private lateinit var progress_wheel: ProgressWheel
    private lateinit var tv_retry_qa_question: TextView
    private lateinit var tv_retry_qa_op1: TextView
    private lateinit var tv_retry_qa_op2: TextView
    private lateinit var tv_retry_qa_op3: TextView
    private lateinit var tv_retry_qa_op4: TextView
    private lateinit var card_op1: CardView
    private lateinit var card_op2: CardView
    private lateinit var card_op3: CardView
    private lateinit var card_op4: CardView
    private lateinit var iv_img1: ImageView
    private lateinit var iv_img2: ImageView
    private lateinit var iv_img3: ImageView
    private lateinit var iv_img4: ImageView
    private lateinit var tv_retry_save_qstn_answr_set: LinearLayout
    private var selectedOption: Int = 0
    private var option_point: Int = 0
    private var isCorrect: Boolean = false
    private var option_number: String = ""
    private var previouslySelectedOption: Int? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object {
        var topic_id:Int=0
        var content_id:Int=0
        var question_id:Int=0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_retry_question, container, false)
        initView(view)
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return view
    }

    private fun initView(view: View) {
        progress_wheel = view.findViewById(R.id.retry_progress_wheel_frag_qa)
        tv_retry_qa_question = view.findViewById(R.id.tv_retry_qa_question)
        tv_retry_qa_op1 = view.findViewById(R.id.tv_retry_qa_op1)
        tv_retry_qa_op2 = view.findViewById(R.id.tv_retry_qa_op2)
        tv_retry_qa_op3 = view.findViewById(R.id.tv_retry_qa_op3)
        tv_retry_qa_op4 = view.findViewById(R.id.tv_retry_qa_op4)

        iv_img1 = view.findViewById(R.id.iv_img1)
        iv_img2 = view.findViewById(R.id.iv_img2)
        iv_img3 = view.findViewById(R.id.iv_img3)
        iv_img4 = view.findViewById(R.id.iv_img4)

        card_op1 = view.findViewById(R.id.card_op1)
        card_op2 = view.findViewById(R.id.card_op2)
        card_op3 = view.findViewById(R.id.card_op3)
        card_op4 = view.findViewById(R.id.card_op4)

        tv_retry_save_qstn_answr_set = view.findViewById(R.id.tv_retry_save_qstn_answr_set)
        progress_wheel.stopSpinning()




        questionWithAnswerViewAPICalling()

        card_op1.setOnClickListener { selectOption(1) }
        card_op2.setOnClickListener { selectOption(2) }
        card_op3.setOnClickListener { selectOption(3) }
        card_op4.setOnClickListener { selectOption(4) }

    }

    private fun selectOption(option: Int) {
        // If there was a previously selected option, reset its color
        previouslySelectedOption?.let { prevOption ->
            when (prevOption) {
                1 -> {
                    if (card_op1.isEnabled.not()) {
                        card_op1.setCardBackgroundColor(Color.parseColor("#D3D3D3"))
                    } else {
                        card_op1.setCardBackgroundColor(Color.parseColor("#DCF4EC"))
                    }
                }
                2 -> {
                    if (card_op2.isEnabled.not()) {
                        card_op2.setCardBackgroundColor(Color.parseColor("#D3D3D3"))
                    } else {
                        card_op2.setCardBackgroundColor(Color.parseColor("#DCF4EC"))
                    }
                }
                3 -> {
                    if (card_op3.isEnabled.not()) {
                        card_op3.setCardBackgroundColor(Color.parseColor("#D3D3D3"))
                    } else {
                        card_op3.setCardBackgroundColor(Color.parseColor("#DCF4EC"))
                    }
                }
                4 -> {
                    if (card_op4.isEnabled.not()) {
                        card_op4.setCardBackgroundColor(Color.parseColor("#D3D3D3"))
                    } else {
                        card_op4.setCardBackgroundColor(Color.parseColor("#DCF4EC"))
                    }
                }
            }
        }

        selectedOption = option
        previouslySelectedOption = option // Update the previously selected option

        when (option) {
            1 -> {
                card_op1.setCardBackgroundColor(Color.parseColor("#ffa800"))
                option_number = tv_retry_qa_op1.text.toString()
            }
            2 -> {
                card_op2.setCardBackgroundColor(Color.parseColor("#ffa800"))
                option_number = tv_retry_qa_op2.text.toString()
            }
            3 -> {
                card_op3.setCardBackgroundColor(Color.parseColor("#ffa800"))
                option_number = tv_retry_qa_op3.text.toString()
            }
            4 -> {
                card_op4.setCardBackgroundColor(Color.parseColor("#ffa800"))
                option_number = tv_retry_qa_op4.text.toString()
            }
        }
    }

   /* private fun selectOption(option: Int) {
        resetOptionSelection()
        selectedOption = option
        when (option) {
            1 -> {
                card_op1.setCardBackgroundColor(Color.parseColor("#ffa800"))
                option_number = tv_retry_qa_op1.text.toString()

            }
            2 -> {
                card_op2.setCardBackgroundColor(Color.parseColor("#ffa800"))
                option_number = tv_retry_qa_op2.text.toString()

            }
            3 -> {
                card_op3.setCardBackgroundColor(Color.parseColor("#ffa800"))
                option_number = tv_retry_qa_op3.text.toString()

            }
            4 -> {
                card_op4.setCardBackgroundColor(Color.parseColor("#ffa800"))
                option_number = tv_retry_qa_op4.text.toString()

            }
        }
    }*/

    private fun isOptionAnswered(option: Int): Boolean {
       return when (option) {
           1 -> card_op1.isEnabled.not() // If card_op1 is disabled, it was answered
           2 -> card_op2.isEnabled.not()
           3 -> card_op3.isEnabled.not()
           4 -> card_op4.isEnabled.not()
           else -> false
       }
   }
        private fun resetOptionSelection() {
        card_op1.setCardBackgroundColor(Color.parseColor("#DCF4EC"))
        card_op2.setCardBackgroundColor(Color.parseColor("#DCF4EC"))
        card_op3.setCardBackgroundColor(Color.parseColor("#DCF4EC"))
        card_op4.setCardBackgroundColor(Color.parseColor("#DCF4EC"))
    }

    private fun topicContentWiseAnswerUpdateAPI(
        response: TopicContentWiseAnswerListsFetchResponse,
        question: String,
        questionId: Int,
        optionId: Int,
        option_point: Int,
        isCorrect: Boolean
    ) {
        try {
            //progress_wheel.visibility = View.VISIBLE
            progress_wheel.spin()
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopicContentWiseAnswerUpdate(
                    Pref.user_id!!,Pref.session_token!!, response.topic_id,response.topic_name,response.content_id,
                    questionId,
                    question,optionId,option_number,
                    option_point,
                    isCorrect
                )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as BaseResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            //progress_wheel.visibility = View.GONE
                            progress_wheel.stopSpinning()
                            try {
                                (mContext as DashboardActivity).onBackPressed()
                            } catch (ex: Exception) {
                                ex.printStackTrace()
                                (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                            }
                        } else {
                            //progress_wheel.visibility = View.GONE
                            progress_wheel.stopSpinning()
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))

                        }
                    }, { error ->
                        println("errortopicwiselist"+error.message)
                        //progress_wheel.visibility = View.GONE
                        progress_wheel.stopSpinning()
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            //progress_wheel.visibility = View.GONE
            progress_wheel.stopSpinning()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }

    private fun questionWithAnswerViewAPICalling() {

        try {
            progress_wheel.spin()
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
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
                            progress_wheel.stopSpinning()

                            try {
                                val filteredResponse =
                                    response.question_answer_fetch_list.filter { it.question_id == question_id }
                                tv_retry_qa_question.setText(filteredResponse.get(0).question)
                                tv_retry_qa_op1.setText(filteredResponse.get(0).option_list.get(0).option_no_1)
                                tv_retry_qa_op2.setText(filteredResponse.get(0).option_list.get(0).option_no_2)
                                tv_retry_qa_op3.setText(filteredResponse.get(0).option_list.get(0).option_no_3)
                                tv_retry_qa_op4.setText(filteredResponse.get(0).option_list.get(0).option_no_4)

                                println("filteredResponse"+filteredResponse)

                                val options_list = Match_Option_list(filteredResponse.get(0).option_list.get(0).option_no_1, filteredResponse.get(0).option_list.get(0).option_no_2, filteredResponse.get(0).option_list.get(0).option_no_3, filteredResponse.get(0).option_list.get(0).option_no_4)

                                val answered = filteredResponse.get(0).answered
                                matchAnsweredOption(answered, options_list)

                                when (answered) {
                                    filteredResponse.get(0).option_list.get(0).option_no_1 -> {
                                        card_op1.isEnabled = false
                                        card_op1.setCardBackgroundColor(Color.parseColor("#e5e5e5"))
                                        iv_img1.setBackground(ContextCompat.getDrawable(mContext, R.drawable.letter_a));
                                        iv_img1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#bdbdbd")));
                                        tv_retry_qa_op1.setTextColor(Color.parseColor("#bababa"))

                                    }
                                    filteredResponse.get(0).option_list.get(0).option_no_2 -> {
                                        card_op2.isEnabled = false
                                        card_op2.setCardBackgroundColor(Color.parseColor("#e5e5e5"))
                                        iv_img2.setBackground(ContextCompat.getDrawable(mContext, R.drawable.letter_b));
                                        iv_img2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#bdbdbd")));
                                        tv_retry_qa_op2.setTextColor(Color.parseColor("#bababa"))

                                    }
                                    filteredResponse.get(0).option_list.get(0).option_no_3 -> {
                                        card_op3.isEnabled = false
                                        card_op3.setCardBackgroundColor(Color.parseColor("#e5e5e5"))
                                        iv_img3.setBackground(ContextCompat.getDrawable(mContext, R.drawable.letter_c));
                                        iv_img3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#bdbdbd")));
                                        tv_retry_qa_op3.setTextColor(Color.parseColor("#bababa"))

                                    }
                                    filteredResponse.get(0).option_list.get(0).option_no_4 -> {
                                        card_op4.isEnabled = false
                                        card_op4.setCardBackgroundColor(Color.parseColor("#e5e5e5"))
                                        iv_img4.setBackground(ContextCompat.getDrawable(mContext, R.drawable.letter_d));
                                        iv_img4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#bdbdbd")));
                                        tv_retry_qa_op4.setTextColor(Color.parseColor("#bababa"))

                                    }

                                    else -> {
                                        // Handle case if answered doesn't match any option
                                        Log.d("QuestionAnswer", "No matching option found for answered value: $answered")
                                    }
                                }

                                tv_retry_save_qstn_answr_set.setOnClickListener {

                                    //test purpose

                                    // Retrieve option_point and isCorrect based on the selected option
                                    when (selectedOption) {
                                        1 -> {
                                            option_point = filteredResponse.get(0).option_list[0].option_point_1
                                            isCorrect = filteredResponse.get(0).option_list[0].isCorrect_1
                                            println("tag_points1"+option_point +"~"+isCorrect)
                                        }
                                        2 -> {
                                            option_point = filteredResponse.get(0).option_list[0].option_point_2
                                            isCorrect = filteredResponse.get(0).option_list[0].isCorrect_2
                                            println("tag_points2"+option_point +"~"+isCorrect)
                                        }
                                        3 -> {
                                            option_point = filteredResponse.get(0).option_list[0].option_point_3
                                            isCorrect = filteredResponse.get(0).option_list[0].isCorrect_3
                                            println("tag_points3"+option_point +"~"+isCorrect)
                                        }
                                        4 -> {
                                            option_point = filteredResponse.get(0).option_list[0].option_point_4
                                            isCorrect = filteredResponse.get(0).option_list[0].isCorrect_4
                                            println("tag_points4"+option_point +"~"+isCorrect)
                                        }
                                        else -> {
                                            // Handle case where no option is selected
                                            return@setOnClickListener
                                        }
                                    }


                                    topicContentWiseAnswerUpdateAPI(response,filteredResponse.get(0).question,filteredResponse.get(0).question_id,filteredResponse.get(0).option_list.get(0).option_id,option_point,isCorrect
                                        )
                                }

                            } catch (ex: Exception) {
                                ex.printStackTrace()
                            }
                        } else {
                            progress_wheel.stopSpinning()
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))

                        }
                    }, { error ->
                        println("errortopicwiselist"+error.message)
                        progress_wheel.stopSpinning()
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            progress_wheel.stopSpinning()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }

    fun matchAnsweredOption(answered: String, option: Match_Option_list) {
        val matchedOption = when (answered) {
            option.option_no_1 -> "Option 1: ${option.option_no_1}"
            option.option_no_2 -> "Option 2: ${option.option_no_2}"
            option.option_no_3 -> "Option 3: ${option.option_no_3}"
            option.option_no_4 -> "Option 4: ${option.option_no_4}"
            else -> "No match found"
        }
        println("matchedOption>>"+matchedOption)
    }


}