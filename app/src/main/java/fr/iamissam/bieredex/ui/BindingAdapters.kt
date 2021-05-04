package fr.iamissam.bieredex.ui

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.iamissam.bieredex.R

class BindingAdapters {

    companion object {

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    Log.d("ISSAM", "il a cliqué la")
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:isVisible")
        @JvmStatic
        fun isVisible(view: View, visible: Boolean) {
            if (visible) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }

        @BindingAdapter("android:fromUri")
        @JvmStatic
        fun fromUri(imageView: ImageView, uri: String?) {
            imageView.background.alpha = 255
            uri?.let {
                val fileUri = Uri.parse(uri)
                imageView.setImageURI(fileUri)
                imageView.background.alpha = 0
            }
        }
    }

}