package com.breezefieldbeena.features.mylearning

// RetryCorrectQuestionAnswerAdapter.kt
import android.content.Context
import com.breezefieldbeena.R
// QuestionAnswerAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.performance_item.view.perform_thumbnail

class RetryInCorrectAdapter(
    private val questions: List<Question_answer_fetch_list>,
    val mContext: Context,
    private val listener: OnRetryClickListener, // Pass listener as a parameter
) : RecyclerView.Adapter<RetryInCorrectAdapter.QuestionViewHolder>() {

    // Define an interface for the retry button click
    interface OnRetryClickListener {
        fun onRetryClicked(question: Question_answer_fetch_list)
    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionText: TextView = itemView.findViewById(R.id.question_text)
        private val tv_qstn_nmbr: TextView = itemView.findViewById(R.id.tv_qstn_nmbr)
        private val tv_qstn_answr: TextView = itemView.findViewById(R.id.tv_qstn_answr)
        private val retry_videowatch: LinearLayout = itemView.findViewById(R.id.retry_videowatch)



        fun bind(question: Question_answer_fetch_list) {
            questionText.text = "${question.question}" // Adding 1 to position for 1-based index
            tv_qstn_nmbr.text = (position + 1).toString()+"."
            tv_qstn_answr.text = "${question.answered}"

            // Set a click listener on the retry button and use the listener's method
            retry_videowatch.setOnClickListener {
                listener.onRetryClicked(question)
            }

            /* // Hide the separator view if this is the last item
             separator_view.visibility = if (position == itemCount - 1) View.GONE else View.VISIBLE*/

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incorrect, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int = questions.size
}