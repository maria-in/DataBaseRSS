package SQL

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.databaserss.databinding.RecyclerItemCarBinding
import fragments.room.CarListener

class SQLAdapter(private val listener: CarListener): RecyclerView.Adapter<SQLAdapter.CarViewHolder>() {

    private var carIdList = emptyList<Int>()
    private var carMarkList = emptyList<String>()
    private var carModelList = emptyList<String>()
    private var carYearList = emptyList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemCarBinding.inflate(layoutInflater, parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carIdList[position], carMarkList[position], carModelList[position], carYearList[position])
    }

    override fun getItemCount() = carMarkList.size

    fun setData(carId: List<Int>, carMark: List<String>, carModel: List<String>, carYear: List<Int>){
        this.carIdList = carId
        this.carMarkList = carMark
        this.carModelList = carModel
        this.carYearList = carYear
    }

    inner class CarViewHolder(private val binding: RecyclerItemCarBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(carId: Int, carMark: String, carModel: String, carYear: Int) {
            binding.itemMark.text = carMark
            binding.itemModel.text = carModel
            binding.itemYear.text = carYear.toString()
            initDeleteButton(carId)
        }

        private fun initDeleteButton(carId: Int){
            binding.carDeleteButton.setOnClickListener{
                listener.deleteCar(carId)
            }
        }
    }
}