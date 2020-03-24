package com.d3if0093.jurnalmodul7

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.d3if0093.jurnalmodul7.databinding.FragmentTambahDiaryBinding
import javax.sql.DataSource

/**
 * A simple [Fragment] subclass.
 */
class TambahDiary : Fragment() {
    private lateinit var binding: FragmentTambahDiaryBinding
    private lateinit var application: Application
    private lateinit var dataSource: DiaryDAO
    private lateinit var viewModelFactory: DiaryViewModelFactory
    private lateinit var diaryViewModel: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tambah_diary, container, false)
        application = requireNotNull(this.activity).application

        dataSource = DiaryDatabase.getInstance(application).DiaryDao

        viewModelFactory = DiaryViewModelFactory(dataSource, application)

        diaryViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)
        setHasOptionsMenu(true)

      //  binding.tambah.setOnClickListener {
      //      diaryViewModel.onClickTambah(binding.catat.text.toString())
     //       view?.findNavController()?.navigate(R.id.action_tambahDiary_to_tampilDiary)
     //   }



        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_save -> {
                diaryViewModel.onClickTambah(binding.catat.text.toString())
                view?.findNavController()?.navigate(R.id.action_tambahDiary_to_tampilDiary)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

//        when (item.itemId) {
//            R.id.action_save -> binding.tambah.setOnClickListener {
//                diaryViewModel.onClickTambah(binding.catat.text.toString())
//                view?.findNavController()?.navigate(R.id.action_tambahDiary_to_tampilDiary)
//                return@setOnClickListener
//
//            }
//
//            R.id.action_delete -> binding.tambah.setOnClickListener {
//                diaryViewModel.onClickHapus()
//                return@setOnClickListener
////                view?.findNavController()?.navigate(R.id.action_tambahDiary_to_tampilDiary)
//            }
//
//
//        }
//        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_tambah, menu)
    }
}
