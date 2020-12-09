package com.example.timer

import adapters.RecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timer.R.layout.fragment_activity_list

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [activityListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class activityListFragment(val supplier: RecyclerAdapter.RecyclerViewSupplier) : Fragment(),TimerActivity.onTimertickListner {
    // TODO: Rename and change types of parameters
    private var columnCount = 1
    private lateinit var tickListener: TimerActivity.onTimertickListner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragment_activity_list, container, false)

        // Set the adapter
        val ra=RecyclerAdapter(supplier,resources.getStringArray(R.array.activity_type).toList())
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =ra

            }
            tickListener=ra
        }
        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(supplier: RecyclerAdapter.RecyclerViewSupplier) =
            activityListFragment(supplier)
    }

    override fun onTick(pos: Int) {
        tickListener.onTick(pos)
    }

    override fun onFinish(pos: Int) {
        tickListener.onFinish(pos)
    }

    override fun onBegin(pos: Int) {
        tickListener.onBegin(pos)
    }

    override fun onNext(pos: Int) {
        tickListener.onNext(pos)
    }

    override fun onSkip(pos: Int) {
        tickListener.onSkip(pos)
    }
}