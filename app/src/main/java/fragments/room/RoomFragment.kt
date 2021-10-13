package fragments.room

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.databaserss.R
import com.mindorks.framework.databaserss.data.CarViewModel
import com.mindorks.framework.databaserss.databinding.FragmentRoomBinding
import kotlin.system.exitProcess

class RoomFragment : Fragment(), CarListener {
    private var listFragmentBinding: FragmentRoomBinding? = null
    private val binding get() = listFragmentBinding!!

    private lateinit var carViewModel: CarViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    exitProcess(0)
                }
            }
            )
        navController = findNavController()
        listFragmentBinding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.carRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = RoomAdapter(this)
        binding.carRecycler.adapter = adapter



        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        carViewModel.readAllData.observe(viewLifecycleOwner, Observer {car ->
            adapter.setData(car)
        })

        binding.floatingActionButton.setOnClickListener{
            navController.navigate(R.id.action_roomFragment_to_addRoomFragment)
        }
    }

    override fun deleteCar(id: Int) {
        val car = carViewModel.readAllData.value?.find { it.id == id }
        carViewModel.deleteCar(car!!)
    }
}