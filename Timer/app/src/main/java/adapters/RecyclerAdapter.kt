package adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timer.R
import com.example.timer.TimerActivity
import database.Exercise
import database.activity

class RecyclerAdapter (
    supplier:RecyclerViewSupplier,val strActivities:List<String>)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(), TimerActivity.onTimertickListner {
    var exercise: Exercise
    val activities:List<activity>
    val colorList:MutableList<Int>
    init {
        exercise=supplier.getExersice()
        activities=supplier.getActivities()
        colorList= MutableList(activities.size){ R.color.white}
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_activity_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = activities[position]
        holder.layout.setBackgroundColor(colorList[position])
        holder.repetition.text=item.repitations.toString()
        holder.name.text=strActivities[item.title]
        holder.duration.text=item.duration.toString()
        holder.rest.text=item.rest.toString()
    }

    override fun getItemCount(): Int = activities.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: LinearLayout =view.findViewById(R.id.exe_info_layout)
        val repetition: TextView =view.findViewById(R.id.info_rep_num)
        val duration: TextView =view.findViewById(R.id.info_duration)
        val name: TextView =view.findViewById(R.id.info_activity_name)
        val img: ImageView =view.findViewById(R.id.info_activity_list_img)
        val rest: TextView =view.findViewById(R.id.info_rest)

    }

    interface RecyclerViewSupplier{
        fun getActivities():List<activity>
        fun getExersice():Exercise
    }

    override fun onTick(pos:Int) {
        activities[pos].repitations-=1
        if(activities[pos].repitations==0)
            colorList[pos]= Color.LTGRAY
        notifyItemChanged(pos)
    }

    override fun onFinish(pos: Int) {
        activities[pos].repitations=0
        colorList[pos]= Color.LTGRAY
        notifyItemChanged(pos)
    }

    override fun onBegin(pos: Int) {
        colorList[pos]= Color.rgb(150,217,201)
        notifyItemChanged(pos)

    }

    override fun onNext(pos: Int) {
        colorList[pos]= Color.rgb(150,217,201)
        notifyItemChanged(pos)
    }

    override fun onSkip(pos: Int) {
        colorList[pos]= Color.rgb(195,174,246)
        notifyItemChanged(pos)
    }
}