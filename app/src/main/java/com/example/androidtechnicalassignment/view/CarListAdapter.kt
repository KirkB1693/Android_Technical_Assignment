package com.example.androidtechnicalassignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtechnicalassignment.databinding.ItemCarBinding
import com.example.androidtechnicalassignment.model.Car
import com.example.androidtechnicalassignment.utils.capitalizeWords
import com.example.androidtechnicalassignment.utils.getFormattedMileage
import com.example.androidtechnicalassignment.utils.getFormattedPrice
import com.example.androidtechnicalassignment.utils.loadImage


class CarListAdapter(private var cars: ArrayList<Car>, private val cardClickListener: RecyclerViewListener, private val callDealerClickListener: RecyclerViewListener) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    private lateinit var binding: ItemCarBinding

    fun updateCars(newCars : List<Car>) {
        cars.clear()
        cars.addAll(newCars)
        notifyDataSetChanged()
    }

    inner class CarViewHolder(binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
        val cardView = binding.root
        val carPhoto = binding.carPhotoImageView
        private val carYear = binding.carYearTextView
        private val carMake = binding.carMakeTextView
        private val carModel = binding.carModelTextView
        private val carPrice = binding.carPriceTextView
        private val carMileage = binding.carMileageTextView
        private val carLocation = binding.carLocationTextView
        val callDealerButton = binding.carCallDealerButton

        fun bind(car: Car) {
            carPhoto.loadImage(car.images?.firstPhoto?.large)
            carYear.text = car.year
            carMake.text = car.make
            carModel.text = car.model
            carPrice.text = getFormattedPrice(car.price)
            carMileage.text = getFormattedMileage(car.mileage)
            val city = (car.dealer?.city)?.lowercase()?.capitalizeWords()
            carLocation.text = "%s, %s".format(city, car.dealer?.state)

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemCarBinding.inflate(
            inflater, parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.bind(car)
        holder.carPhoto.layout(0,0,0,0)
        holder.cardView.setOnClickListener {
            cardClickListener.cardClick(car)
        }
        holder.callDealerButton.setOnClickListener {
            val dealerPhone = car.dealer?.dealerPhone ?: ""
            callDealerClickListener.callDealerClick(dealerPhone)
        }
    }

    override fun getItemCount() = cars.size

}


interface RecyclerViewListener {
    fun cardClick(car: Car)
    fun callDealerClick(dealerPhone: String)
}




