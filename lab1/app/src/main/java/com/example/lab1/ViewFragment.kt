package com.example.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_view.*
import kotlinx.android.synthetic.main.fragment_view.view.*
import kotlinx.android.synthetic.main.fragment_view.view.result_Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val model: MyViewModel by activityViewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity as MainActivity
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_view, container, false)
        view.spinner_value.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                if (view.spinner_value.selectedItem.toString() == "Weight") {
                    val adapter_weight= context?.let { model.get_adapter_weight(it) }
                    view.spinner_choose.adapter = adapter_weight
                    view.spinner_choose2.adapter = adapter_weight
                } else if (view.spinner_value.selectedItem.toString() == "Distance") {
                    val adapter_distance= context?.let { model.get_adapter_distance(it) }
                    view.spinner_choose.adapter = adapter_distance
                    view.spinner_choose2.adapter = adapter_distance
                } else {
                    val adapter_curency= context?.let { model.get_adapter_currency(it) }
                    view.spinner_choose.adapter = adapter_curency
                    view.spinner_choose2.adapter = adapter_curency
                }
            }
        }

        fun refresh(){
            view.result_Text.text = model.number
            view.result_Text2.text = model.number_conv
        }

        view.button_change.setOnClickListener {
            model.change()
            refresh()
            // add for spinner
        }



        view.button_result.setOnClickListener {
            var text_to_transform = model.number
            if(text_to_transform==""){
                val text = "No data!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(activity, text, duration)
                toast.show()
                model.number_conv = ""
            }
            else {
                var int_value = text_to_transform.toDouble()
                if(view.spinner_value.selectedItem.toString()=="Weight"){
                    if(view.spinner_choose2.selectedItem.toString()=="Gram"){
                        if(view.spinner_choose.selectedItem.toString()=="Kilogram"){
                            int_value /= 1000
                        }
                        else if(view.spinner_choose.selectedItem.toString()=="Gram"){
                            int_value = int_value
                        }
                        else{
                            int_value /= 1000000
                        }
                    }
                    else if(view.spinner_choose2.selectedItem.toString()=="Kilogram"){
                        if(view.spinner_choose.selectedItem.toString()=="Kilogram"){
                            int_value = int_value
                        }
                        else if(view.spinner_choose.selectedItem.toString()=="Gram"){
                            int_value = int_value * 1000
                        }
                        else{
                            int_value /= 1000
                        }
                    }
                    else{
                        if(view.spinner_choose.selectedItem.toString()=="Kilogram"){
                            int_value /= 1000
                        }
                        else if(view.spinner_choose.selectedItem.toString()=="Gram"){
                            int_value /= 1000000
                        }
                        else{
                            int_value = int_value
                        }
                    }
                }
                else if(view.spinner_value.selectedItem.toString()=="Distance"){
                    if(view.spinner_choose2.selectedItem.toString()=="Centimeter"){
                        if(view.spinner_choose.selectedItem.toString()=="Meter"){
                            int_value /= 100
                        }
                        else if(view.spinner_choose.selectedItem.toString()=="Centimeter"){
                            int_value = int_value
                        }
                        else{
                            int_value /= 100000
                        }
                    }
                    else if(view.spinner_choose2.selectedItem.toString()=="Meter"){
                        if(view.spinner_choose.selectedItem.toString()=="Meter"){
                            int_value = int_value
                        }
                        else if(view.spinner_choose.selectedItem.toString()=="Centimeter"){
                            int_value = int_value * 100
                        }
                        else{
                            int_value /= 1000
                        }
                    }
                    else{
                        if(view.spinner_choose.selectedItem.toString()=="Meter"){
                            int_value *= 1000
                        }
                        else if(view.spinner_choose.selectedItem.toString()=="Centimeter"){
                            int_value *= 100000
                        }
                        else{
                            int_value = int_value
                        }
                    }
                }
                else {
                    if (view.spinner_choose2.selectedItem.toString() == "BYN") {
                        if (view.spinner_choose.selectedItem.toString() == "BYN") {
                            int_value = int_value
                        } else if (view.spinner_choose.selectedItem.toString() == "Dollar") {
                            int_value /= 2.6
                        } else {
                            int_value /= 3.17
                        }
                    }
                    else if (view.spinner_choose2.selectedItem.toString() == "Dollar") {
                        if (view.spinner_choose.selectedItem.toString() == "BYN") {
                            int_value *= 2.6
                        }
                        else if (view.spinner_choose.selectedItem.toString() == "Dollar") {
                            int_value = int_value
                        }
                        else {
                            int_value /= 1.17
                        }
                    }
                    else{
                        if (view.spinner_choose.selectedItem.toString() == "BYN") {
                            int_value *= 3.1
                        }
                        else if (view.spinner_choose.selectedItem.toString() == "Dollar") {
                            int_value = int_value * 1.17
                        }
                        else {
                            int_value = int_value
                        }
                    }
                }
                model.number_conv = int_value.toString()
                refresh()
               // view.result_Text2.text = int_value.toString()
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}