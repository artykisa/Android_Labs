package com.example.timer

import Interfaces.ActivitySubsriber
import Interfaces.UDActivityListener
import adapters.MainAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import database.Exercise
import database.activity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), ActivitySubsriber {

    private var columnCount = 1
    val recycleAdapter: MainAdapter = MainAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Set the adapter
        recycleAdapter.stractivities=resources.getStringArray(R.array.activity_type).toList()
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =recycleAdapter
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            MainFragment()
    }

    override fun onNext(activity: activity) {
        recycleAdapter.onViewClicked(activity)
    }

    override fun insertValues(list: List<activity>,exercise: Exercise) {

        recycleAdapter.insertValues(list,exercise)
    }

    override fun renewItems(list: MutableList<activity>) {
        recycleAdapter.renewItems(list)
    }

    fun setCRUDListner(listner: UDActivityListener)
    {
        recycleAdapter.setOnCRUDlistner(listner)
    }
}