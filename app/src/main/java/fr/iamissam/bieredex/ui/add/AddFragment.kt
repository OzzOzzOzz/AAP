package fr.iamissam.bieredex.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import fr.iamissam.bieredex.R
import fr.iamissam.bieredex.data.models.BeerData
import fr.iamissam.bieredex.data.viewmodel.BeerViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private val beerViewModel: BeerViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        view.save_btn.setOnClickListener { insertDataToDb() }

        return view
    }

    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()

        if (mTitle.isNotEmpty()) {
            val newData = BeerData(
                0,
                mTitle,
            )

            beerViewModel.insertData(newData)
            Toast.makeText(requireContext(), getString(R.string.ajout_reussi), Toast.LENGTH_SHORT).show()
            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), getString(R.string.fill_out_all_fields), Toast.LENGTH_SHORT).show()
        }

    }

}