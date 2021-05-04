package fr.iamissam.bieredex.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fr.iamissam.bieredex.R
import fr.iamissam.bieredex.data.models.BeerData
import fr.iamissam.bieredex.data.viewmodel.BeerViewModel
import fr.iamissam.bieredex.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private val beerViewModel: BeerViewModel by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.beerViewModel = beerViewModel

        setupRecyclerView()

        beerViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            binding.quantity.setText(resources.getString(R.string.bierre_goutee, it.size))
        })


        beerViewModel.getAllData.value?.isEmpty()

        // Set Menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        swipeToDelete(binding.recyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
                beerViewModel.deleteItem(deletedItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeletedData(viewHolder.itemView, deletedItem, viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(view: View, deletedItem: BeerData, position: Int) {
        val snackBar = Snackbar.make(
            view, getString(R.string.element_supprime, "'${deletedItem.title}'"), Snackbar.LENGTH_LONG
        )

        snackBar.setAction(getString(R.string.cancel)) {
            beerViewModel.insertData(deletedItem)
            adapter.notifyItemChanged(position)
        }
        snackBar.show()
    }
}