package com.cavss.ralo.ui.views.main.memorial

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.cavss.ralo.BR
import com.cavss.ralo.MainActivity
import com.cavss.ralo.R
import com.cavss.ralo.databinding.*
import com.cavss.ralo.model.*
import com.cavss.ralo.ui.customs.recyclerview.BaseRecyclerViewAdapter
import com.cavss.ralo.ui.customs.recyclerview.CustomItemGap
import com.cavss.ralo.ui.customs.recyclerview.IClickListener
import com.cavss.ralo.util.Strings
import com.cavss.ralo.vm.MainVM

class FowFragment(
    private val setFragTitle : String,
    private val setTierList : ArrayList<TierModel>,
    private val setKdaList : ArrayList<KDAModel>
) : Fragment() {
    private val mainVM : MainVM by viewModels()
    private lateinit var binding : FragFowBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_fow, container, false)
            binding.run {
                title.text = setFragTitle
                setTierRecyclerView(tierRecyclerview)
                setKDARecyclerView(kdaRecyclerview)
            }
        }catch (e:Exception){
            Log.e("mException", "에러발생 : FowFragment, onCreateView // Exception : ${e.message}")
        }finally {
            return binding.root
        }
    }

    private fun setTierRecyclerView(recyclerview : RecyclerView){
        try{
            val clickEvent = object : IClickListener {
                override fun onClick(position: Int, model: Any?) {
                    if(model is TierModel){

                    }
                }
            }

            val customAdapter = object : BaseRecyclerViewAdapter.Adapter<TierModel, HolderTierBinding>(
                layoutResId = R.layout.holder_tier,
                bindingVariableId = BR.model,
                bindingClickVariableID = BR.iclick
            ){}

            customAdapter.let{
                it.setOnClick(clickListener = clickEvent)
                it.updateList(setTierList)
            }

            recyclerview.apply {
                adapter = customAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext()).apply{
                    orientation = LinearLayoutManager.HORIZONTAL
                    isItemPrefetchEnabled = false
                }
                setItemViewCacheSize(0)
            }
        }catch(e:Exception){
            Log.e("mException", "에러발생 : FowFragment, setTierRecyclerView // Exception : ${e.message}")
        }
    }
    private fun setKDARecyclerView(recyclerview : RecyclerView){
        try{
            val clickEvent = object : IClickListener {
                override fun onClick(position: Int, model: Any?) {
                    if(model is KDAModel){

                    }
                }
            }

            val customAdapter = object : BaseRecyclerViewAdapter.Adapter<KDAModel, HolderKdaBinding>(
                layoutResId = R.layout.holder_kda,
                bindingVariableId = BR.model,
                bindingClickVariableID = BR.iclick
            ){}


            customAdapter.let{
                it.setOnClick(clickListener = clickEvent)
                it.updateList(setKdaList)
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
        }catch(e:Exception){
            Log.e("mException", "에러발생 : FowFragment, setKDARecyclerView // Exception : ${e.message}")
        }
    }

}