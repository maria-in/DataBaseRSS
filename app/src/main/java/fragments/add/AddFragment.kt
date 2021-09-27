package fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mindorks.framework.databaserss.R
import com.mindorks.framework.databaserss.data.Car
import com.mindorks.framework.databaserss.data.CarViewModel
import com.mindorks.framework.databaserss.databinding.FragmentAddBinding
import com.mindorks.framework.databaserss.databinding.FragmentListBinding

class AddFragment : Fragment() {
    private var addFragmentBinding: FragmentAddBinding? = null
    private val binding get() = addFragmentBinding!!

    private lateinit var mCarViewModel: CarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addFragmentBinding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        mCarViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        binding.saveButton.setOnClickListener{
            if(checkData()){
                insertDataToDataBase()
                navController.navigate(R.id.action_addFragment_to_listFragment)
            }
        }
    }

    private fun insertDataToDataBase() {
        val mark = binding.editMarkController.text.toString()
        val model = binding.editModelController.text.toString()
        val year = binding.editYearController.text.toString().toInt()
        val car = Car(0, mark, model, year)
        mCarViewModel.addCar(car)
    }

    private fun checkData(): Boolean {
        var state = true
        if(binding.editMarkController.text.isNullOrEmpty()){
            state = false
            binding.editMark.error = "Необходимо ввести марку"
        }
        if (binding.editModelController.text.isNullOrEmpty()){
            state = false
            binding.editModel.error = "Необходимо ввести модель"
        }
        if(binding.editYearController.text.isNullOrEmpty()){
            state = false
            binding.editYear.error = "Необходимо ввести год"
        }
        return state
    }


}