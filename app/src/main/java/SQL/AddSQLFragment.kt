package SQL

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mindorks.framework.databaserss.R
import com.mindorks.framework.databaserss.databinding.FragmentAddBinding

class AddSQLFragment: Fragment() {
    private var addFragmentBinding: FragmentAddBinding? = null
    private val binding get() = addFragmentBinding!!


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

        binding.saveButton.setOnClickListener{
            if(checkData()){
                val result = insertDataToDataBase()
                Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_addSQLFragment_to_sqlFragment)
            }
        }
    }

    private fun insertDataToDataBase(): String {
        val mark = binding.editMarkController.text.toString()
        val model = binding.editModelController.text.toString()
        val year = binding.editYearController.text.toString().toInt()
        val carDB = CarDatabaseHelper(requireContext())
        return carDB.addCar(mark, model, year)
    }

    private fun checkData(): Boolean {
        var state = true
        if(binding.editMarkController.text.isNullOrEmpty()){
            state = false
            binding.editMark.error = getString(R.string.w_car_brand)
        }
        if (binding.editModelController.text.isNullOrEmpty()){
            state = false
            binding.editModel.error = getString(R.string.w_car_model)
        }
        if(binding.editYearController.text.isNullOrEmpty()){
            state = false
            binding.editYear.error = getString(R.string.w_car_year)
        }
        return state
    }


}