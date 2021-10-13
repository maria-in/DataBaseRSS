package SQL

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.databaserss.R
import com.mindorks.framework.databaserss.databinding.FragmentSqlBinding
import fragments.room.CarListener
import kotlin.system.exitProcess

class SQLFragment : Fragment(), CarListener {

    private var sqlFragmentBinding: FragmentSqlBinding? = null
    private val binding get() = sqlFragmentBinding!!

    private lateinit var carDB: CarDatabaseHelper
    private var carIdList = mutableListOf<Int>()
    private var carMarkList = mutableListOf<String>()
    private var carModelList = mutableListOf<String>()
    private var carYearList = mutableListOf<Int>()

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
        sqlFragmentBinding = FragmentSqlBinding.inflate(inflater, container, false)
        carDB = CarDatabaseHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListOfCars()

        binding.sqlCarRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = SQLAdapter(this)
        adapter.setData(carIdList, carMarkList, carModelList, carYearList)
        binding.sqlCarRecycler.adapter = adapter


        binding.sqlFloatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_sqlFragment_to_addSQLFragment)
        }
    }

    private fun getListOfCars() {
        val cursor = carDB.getAllCar()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    carIdList.add(cursor.getInt(0))
                    carMarkList.add(cursor.getString(1))
                    carModelList.add(cursor.getString(2))
                    carYearList.add(cursor.getInt(3))
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }

    override fun deleteCar(id: Int) {
        carDB.deleteCar(id)
        Toast.makeText(context, getString(R.string.w_detele_car), Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_sql_screen){
            navController.navigate(R.id.action_sqlFragment_to_roomFragment)
            item.isEnabled = false
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.change_room_screen).isEnabled = true
        super.onPrepareOptionsMenu(menu)
    }
}
