package fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.databaserss.R
import com.mindorks.framework.databaserss.data.CarViewModel
import com.mindorks.framework.databaserss.databinding.FragmentListBinding


class ListFragment : Fragment(), CarListener {
    private var listFragmentBinding: FragmentListBinding? = null
    private val binding get() = listFragmentBinding!!

    private lateinit var carViewModel: CarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        listFragmentBinding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.carRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = ListAdapter(this)
        binding.carRecycler.adapter = adapter



        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        carViewModel.readAllData.observe(viewLifecycleOwner, Observer {car ->
            adapter.setData(car)
        })
        binding.floatingActionButton.setOnClickListener{
            navController.navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    override fun deleteCar(id: Int) {
        Toast.makeText(context, "Удалить!", Toast.LENGTH_SHORT).show()
    }

}