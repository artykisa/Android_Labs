package adapters

import Interfaces.ActivityListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timer.R
import database.activity

class ActivityAdapter (val activityListner: ActivityListener, val activities:List<String>): RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    private val imgs= listOf(
        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground)
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_actvities, parent, false)//change
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.activityName.setText(activities[position])
        holder.image.setImageResource(imgs[position])
        holder.activityName.setOnClickListener{
            activityListner.onViewClicked(
                activity(position,"descr",10,1,0,0,
                imgs[position],0)
            )
        }

    }

    override fun getItemCount(): Int = activities.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val activityName: TextView = view.findViewById(R.id.activity_name)
        val image: ImageView =view.findViewById(R.id.list_img)
    }
}