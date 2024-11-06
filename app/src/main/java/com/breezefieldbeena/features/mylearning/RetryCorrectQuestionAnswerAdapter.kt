package com.breezefieldbeena.features.mylearning

// RetryCorrectQuestionAnswerAdapter.kt
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.breezefieldbeena.R



class RetryCorrectQuestionAnswerAdapter(
    private val questions: List<Question_answer_fetch_list>,
    mContext: Context
) : RecyclerView.Adapter<RetryCorrectQuestionAnswerAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionText: TextView = itemView.findViewById(R.id.question_text)
        private val tv_qstn_nmbr: TextView = itemView.findViewById(R.id.tv_qstn_nmbr)
        private val crrct_answer: TextView = itemView.findViewById(R.id.crrct_answer)

        fun bind(question: Question_answer_fetch_list) {
            questionText.text = "${question.question}" // Adding 1 to position for 1-based index
            tv_qstn_nmbr.text = (position + 1).toString()+"."
            crrct_answer.text = "${question.answered}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_correct_question_answer, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int = questions.size
}