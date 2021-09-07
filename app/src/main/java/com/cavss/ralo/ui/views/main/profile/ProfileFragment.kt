package com.cavss.ralo.ui.views.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cavss.ralo.MainActivity
import com.cavss.ralo.R
import com.cavss.ralo.databinding.FragProfileBinding
import com.cavss.ralo.model.TopbarModel
import com.cavss.ralo.model.UserModel
import com.cavss.ralo.util.Strings
import com.cavss.ralo.vm.MainVM

class ProfileFragment : Fragment()  {

    private val mainVM : MainVM by viewModels()
    private lateinit var binding : FragProfileBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_profile, container, false)
            binding.run {
                vm = mainVM
                lifecycleOwner = activity as MainActivity
            }

        }catch (e:Exception){
            Log.e("mException", "에러발생 : ProfileFragment, onCreateView // Exception : ${e.message}")
        }finally {
            return binding.root
        }
    }

    override fun onStart() {
        super.onStart()
        setTopbarList()
        setModel()
    }

    private fun setModel() {
        val model =  UserModel(
            name = "김찬호",
            age = 70,
            selfie = null,
            postUIDs = arrayListOf("",""),
            uid = "UID11"
        )
        binding.run{
            name.text = model.name
            age.text = model.age.toString()
        }
    }

    private fun setTopbarList(){
        try{
            val list = listOf(
                TopbarModel(Strings.TOPBAR_NOTIFY,"공지사항"),
                TopbarModel(Strings.TOPBAR_DEVELOPER,"개발잡니다")
            )
            (activity as MainActivity).changeTopbarList(list)
        }catch(e:Exception){
            Log.e("mException", "에러발생 : ProfileFragment, setTopbarList // Exception : ${e.message}")
        }
    }
}