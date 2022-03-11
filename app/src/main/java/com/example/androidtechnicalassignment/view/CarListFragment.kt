package com.example.androidtechnicalassignment.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtechnicalassignment.database.CarDatabase
import com.example.androidtechnicalassignment.databinding.FragmentCarListBinding
import com.example.androidtechnicalassignment.model.Car
import com.example.androidtechnicalassignment.viewModel.CarListViewModel
import com.example.androidtechnicalassignment.viewModel.CarListViewModelFactory


class CarListFragment : Fragment(), RecyclerViewListener {

    private lateinit var viewModel: CarListViewModel
    private lateinit var binding: FragmentCarListBinding
    private val carsAdapter = CarListAdapter(arrayListOf(), this, this)
    private var phoneNumber: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCarListBinding.inflate(inflater)
        val viewModelFactory =
            CarListViewModelFactory(CarDatabase.getInstance(requireContext()).carDao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        )[CarListViewModel::class.java]

        binding.carsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = carsAdapter
        }
        viewModel.refresh()

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.cars.observe(viewLifecycleOwner) { cars ->
            cars?.let {
                binding.carsList.visibility = View.VISIBLE
                carsAdapter.updateCars(it)
            }
        }

        viewModel.carLoadError.observe(viewLifecycleOwner) { isError ->
            isError?.let { binding.listError.visibility = if (it) View.VISIBLE else View.GONE }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.carsList.visibility = View.GONE
                }
            }
        }
    }

    private fun carCardClicked(car: Car) {
        this.findNavController()
            .navigate(CarListFragmentDirections.actionCarListFragmentToCarDetailFragment(car))
    }

    private fun callDealerClicked(dealerPhone: String) {
        phoneNumber = dealerPhone
        call()
    }

    private fun call() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            requireActivity().startActivity(callIntent)
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.CALL_PHONE),
                1234
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == 1234 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            call()
        }

    }

    override fun cardClick(car: Car) {
        carCardClicked(car)
    }

    override fun callDealerClick(dealerPhone: String) {
        callDealerClicked(dealerPhone)
    }


}