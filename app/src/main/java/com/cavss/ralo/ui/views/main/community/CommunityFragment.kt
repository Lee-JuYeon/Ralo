package com.cavss.ralo.ui.views.main.community

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cavss.ralo.MainActivity
import com.cavss.ralo.R
import com.cavss.ralo.BR
import com.cavss.ralo.databinding.FragCommunityBinding
import com.cavss.ralo.databinding.HolderCommunityBinding
import com.cavss.ralo.model.CommunityModel
import com.cavss.ralo.model.TopbarModel
import com.cavss.ralo.ui.customs.recyclerview.BaseRecyclerViewAdapter
import com.cavss.ralo.ui.customs.recyclerview.CustomItemGap
import com.cavss.ralo.ui.customs.recyclerview.IClickListener
import com.cavss.ralo.util.Strings
import com.cavss.ralo.vm.MainVM

class CommunityFragment : Fragment() {
    private val mainVM : MainVM by viewModels()
    private lateinit var binding : FragCommunityBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_community, container, false)
            binding.run {
                vm = mainVM
                lifecycleOwner = activity as MainActivity
                setRecyclerView(recyclerview)
                uploadPost(enter)
            }

        }catch (e:Exception){
            Log.e("mException", "에러발생 : CommunityFragment, onCreateView // Exception : ${e.message}")
        }finally {
            return binding.root
        }
    }

    override fun onStart() {
        super.onStart()
        setTopbarList()
    }

    private fun setTopbarList(){
        try{
            val list = listOf(
                TopbarModel(Strings.TOPBAR_NOTIFY,"공지사항"),
                TopbarModel(Strings.TOPBAR_DEVELOPER,"개발잡니다")
            )
            (activity as MainActivity).changeTopbarList(list)
        }catch(e:Exception){
            Log.e("mException", "에러발생 : CommunityFragment, setTopbarList // Exception : ${e.message}")
        }
    }
    private fun setRecyclerView(recyclerview : RecyclerView){
        try{
            val clickEvent = object : IClickListener {
                override fun onClick(position: Int, model: Any?) {
                    if(model is CommunityModel){

                    }
                }
            }

            val customAdapter = object : BaseRecyclerViewAdapter.Adapter<CommunityModel, HolderCommunityBinding>(
                layoutResId = R.layout.holder_community,
                bindingVariableId = BR.model,
                bindingClickVariableID = BR.iclick
            ){}

            customAdapter.let{
                it.setOnClick(clickListener = clickEvent)
                it.updateList(mainVM.dummyCommunityList)
            }
            recyclerview.apply {
                adapter = customAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext()).apply{
                    orientation = LinearLayoutManager.VERTICAL
                    isItemPrefetchEnabled = false
                }
                addItemDecoration(CustomItemGap(10))
                setItemViewCacheSize(0)
            }
        }catch (e:Exception){
            Log.e("mException", "에러발생 : CommunityFragment, setRecyclerView // Exception : ${e.message}")
        }
    }
    private fun uploadPost(button : ImageView){
        button.setOnClickListener {
            Log.e("mException", "입력된 텍스트 : ${binding.edittext.text.toString()}")
        }
    }
}