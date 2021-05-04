package fr.iamissam.bieredex.ui.add

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import fr.iamissam.bieredex.R
import fr.iamissam.bieredex.data.models.BeerData
import fr.iamissam.bieredex.data.viewmodel.BeerViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.io.File
import kotlin.math.round


class AddFragment : Fragment() {

    private val beerViewModel: BeerViewModel by viewModels()

    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        view.save_btn.setOnClickListener { insertDataToDb() }

        view.iv_add_photo.setOnClickListener {
            ImagePicker.with(requireActivity())
                .galleryOnly()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start { resultCode, data ->
                    if (resultCode == Activity.RESULT_OK) {
                        //Image Uri will not be null for RESULT_OK
                        val fileUri = data?.data
                        view.iv_add_photo.setImageURI(fileUri)
                        uri = fileUri
                        view.iv_add_photo.background.alpha = 0
                    } else if (resultCode == ImagePicker.RESULT_ERROR) {
                        Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        return view
    }

    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()
        val mDescription = description_et.text.toString()
        val price = round(price_et.text.toString().toFloat() * 100) / 100

        if (mTitle.isNotEmpty()) {
            val newData = BeerData(
                0,
                mTitle,
                mDescription,
                price.toString() + "€",
                uri?.toString()
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