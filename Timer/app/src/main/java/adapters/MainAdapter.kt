package adapters

import Interfaces.ActivityListener
import Interfaces.UDActivityListener
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timer.R
import database.Exercise
import database.activity

class MainAdapter () : RecyclerView.Adapter<MainAdapter.ViewHolder>(), ActivityListener {
    var activities= mutableListOf<activity>()
    lateinit var crudListner: UDActivityListener
    var exercise:Exercise?=null
    lateinit var  stractivities:List<String>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mainadapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = activities[position]
        holder.activityName.setText(stractivities[item.title])
        holder.activityReps.setText(item.repitations.toString())
        holder.duration.text=item.duration.toString()
        holder.decrButton.setOnClickListener{
            if(item.repitations>1)
            item.repitations-=1
            holder.activityReps.setText(item.repitations.toString())
        }
        holder.increaseButton.setOnClickListener{
            if(item.repitations<10)
            item.repitations+=1
            holder.activityReps.setText(item.repitations.toString())
            crudListner.update(activities[position])
        }
        holder.delButton.setOnClickListener{
            crudListner.onDelete(activities[position])
            this.notifyItemRemoved(position)
            activities.removeAt(position)
            this.notifyItemRangeChanged(0,activities.size)

        }
        holder.decreaseDurationButton.setOnClickListener{
            if(activities[position].duration>1)
                activities[position].duration-=1
            holder.duration.text=activities[position].duration.toString()
            //this.notifyItemChanged(position)
            crudListner.update(activities[position])
        }
        holder.increaseDurationButton.setOnClickListener{
            if(activities[position].duration<200)
                activities[position].duration+=1
            holder.duration.text=activities[position].duration.toString()
            //this.notifyItemChanged(position)
            crudListner.update(activities[position])
        }
        holder.increaseRestBtn.setOnClickListener{
            if(activities[position].rest < 200)
                activities[position].rest += 1
            holder.restValue.text=activities[position].rest.toString()
            crudListner.update(activities[position])
        }
        holder.decreaseRestBtn.setOnClickListener{
            if(activities[position].rest > 1)
                activities[position].rest -= 1
            //this.notifyItemChanged(position)
            holder.restValue.text=activities[position].rest.toString()
            crudListner.update(activities[position])
        }

        holder.linearLayout.setBackgroundColor(exercise?.color?: Color.WHITE)
    }

    override fun getItemCount(): Int = activities.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val activityName: TextView = view.findViewById(R.id.crud_activity_name)
        val activityReps: TextView =view.findViewById(R.id.rep_num)
        val decrButton: ImageButton =view.findViewById(R.id.decrease_btn)
        val increaseButton: ImageButton =view.findViewById(R.id.increase_nmbr)
        val image: ImageView =view.findViewById(R.id.crud_list_img)
        val delButton: ImageButton =view.findViewById(R.id.del_act)
        val increaseDurationButton: ImageButton =view.findViewById(R.id.increase_duration)
        val decreaseDurationButton: ImageButton =view.findViewById(R.id.decrease_duration_btn)
        val duration: TextView =view.findViewById(R.id.duration)
        val linearLayout: LinearLayout =view.findViewById(R.id.crud_activity_item)
        val increaseRestBtn: ImageButton =view.findViewById(R.id.increase_rest_nmbr)
        val decreaseRestBtn: ImageButton =view.findViewById(R.id.decrease_rest_btn)
        val restValue: TextView =view.findViewById(R.id.rest_value)
    }

    override fun onViewClicked(exercise: activity) {
        activities.add(exercise)
        this.notifyItemInserted(activities.size-1)
    }
    fun insertValues(activities:List<activity>,exercise: Exercise){
        val oldLength=this.activities.size
        this.activities.addAll(activities)
        this.exercise=exercise
        this.notifyItemRangeInserted(oldLength-1,oldLength-1+activities.size)
    }

    fun setOnCRUDlistner(listner: UDActivityListener)
    {
        crudListner=listner
    }
    fun renewItems(list:MutableList<activity>){
        activities=list
        notifyItemRangeChanged(0,activities.size)

    }


}