package com.example.lab1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_keyboard.*
import kotlinx.android.synthetic.main.fragment_keyboard.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KeyboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeyboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_keyboard, container, false)

        view.button__1.setOnClickListener {
            (activity as MainActivity).set_text("1")
        }

        view.button__2.setOnClickListener {
            (activity as MainActivity).set_text("2")
        }

        view.button__3.setOnClickListener {
            (activity as MainActivity).set_text("3")
        }

        view.button__4.setOnClickListener {
            (activity as MainActivity).set_text("4")
        }

        view.button__5.setOnClickListener {
            (activity as MainActivity).set_text("5")
        }

        view.button__6.setOnClickListener {
            (activity as MainActivity).set_text("6")
        }

        view.button__7.setOnClickListener {
            (activity as MainActivity).set_text("7")
        }

        view.button__8.setOnClickListener {
            (activity as MainActivity).set_text("8")
        }

        view.button__9.setOnClickListener {
            (activity as MainActivity).set_text("9")
        }

        view.button__0.setOnClickListener {
            (activity as MainActivity).set_text("0")
        }

        view.button__x.setOnClickListener {
            (activity as MainActivity).x_press()
        }

        view.button__dot.setOnClickListener {
            (activity as MainActivity).dot_press()
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
         * @return A new instance of fragment KeyboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KeyboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}