package com.example.androidtechnicalassignment.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtechnicalassignment.databinding.FragmentCarDetailBinding
import com.example.androidtechnicalassignment.model.Car
import com.example.androidtechnicalassignment.utils.capitalizeWords
import com.example.androidtechnicalassignment.utils.getFormattedMileage
import com.example.androidtechnicalassignment.utils.getFormattedPrice
import com.example.androidtechnicalassignment.utils.loadImage


class CarDetailFragment : Fragment() {

    private lateinit var binding: FragmentCarDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCarDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val car = CarDetailFragmentArgs.fromBundle(requireArguments()).argCar
        binding.detailCallDealerButton.setOnClickListener { callDealer(car.dealer?.dealerPhone) }
        setCarDetails(car)
        return binding.root
    }

    private fun setCarDetails(car: Car) {
        binding.detailCarPhotoImageView.loadImage(car.images?.firstPhoto?.large)
        binding.detailCarYearTextView.text = car.year
        binding.detailCarMakeTextView.text = car.make
        binding.detailCarModelTextView.text = car.model
        binding.detailCarPriceTextView.text = getFormattedPrice(car.price)
        binding.detailCarMileageTextView.text = getFormattedMileage(car.mileage)
        val city = (car.dealer?.city)?.lowercase()?.capitalizeWords()
        binding.detailCarLocationTextView.text = "%s, %s".format(city, car.dealer?.state)
        binding.detailCarExteriorColorTextView.text = car.exteriorColor
        binding.detailCarInteriorColorTextView.text = car.interiorColor
        binding.detailCarDriveTypeTextView.text = car.driveType
        binding.detailCarTransmissionTextView.text = car.transmission
        binding.detailCarBodyStyleTextView.text = car.bodyStyle
        binding.detailCarEngineTextView.text = car.engine
        binding.detailCarFuelTextView.text = car.fuelType
    }

    private fun callDealer(dealerPhone: String?) {
        val uri = Uri.parse("tel:$dealerPhone")
        val callIntent = Intent(Intent.ACTION_CALL, uri)
        context?.startActivity(callIntent)
    }


}