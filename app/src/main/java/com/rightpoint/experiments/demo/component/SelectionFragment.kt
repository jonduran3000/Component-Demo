package com.rightpoint.experiments.demo.component

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_selection.*

class SelectionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        screens.adapter = SelectionAdapter(requireContext())
        screens.setOnItemClickListener { _, _, i, _ ->
            val screen = Screen.values()[i]
            (requireActivity() as MainActivity).goToScreen(screen)
        }
    }

    private class SelectionAdapter(context: Context) : BindableAdapter<Screen>(context) {
        override fun getItem(position: Int): Screen = Screen.values()[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getCount(): Int = Screen.values().size

        override fun newView(
            inflater: LayoutInflater,
            position: Int,
            container: ViewGroup?
        ): View {
            return inflater.inflate(android.R.layout.simple_list_item_1, container, false)
        }

        override fun bindView(item: Screen, position: Int, view: View) {
            view.findViewById<TextView>(android.R.id.text1).text = item.screenName
        }
    }
}