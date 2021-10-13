package fragments.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.databaserss.data.Car
import com.mindorks.framework.databaserss.databinding.RecyclerItemCarBinding

class RoomAdapter(private val listener: CarListener): RecyclerView.Adapter<RoomAdapter.CarViewHolder>() {

    private var carList = emptyList<Car>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemCarBinding.inflate(layoutInflater, parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carList[position])
    }

    override fun getItemCount() = carList.size

    fun setData(car: List<Car>){
        this.carList = car
        notifyDataSetChanged()
    }

    inner class CarViewHolder(private val binding: RecyclerItemCarBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.itemMark.text = car.mark
            binding.itemModel.text = car.model
            binding.itemYear.text = car.year.toString()
            initDeleteButton(car)
        }

        fun initDeleteButton(car: Car){
            binding.carDeleteButton.setOnClickListener{
                listener.deleteCar(car.id)
            }
        }
    }
}