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
import com.cavss.ralo.databinding.FragMemorialBinding
import com.cavss.ralo.databinding.HolderPlatformBinding
import com.cavss.ralo.databinding.HolderQuotoBinding
import com.cavss.ralo.model.*
import com.cavss.ralo.ui.customs.recyclerview.BaseRecyclerViewAdapter
import com.cavss.ralo.ui.customs.recyclerview.CustomItemGap
import com.cavss.ralo.ui.customs.recyclerview.IClickListener
import com.cavss.ralo.util.Strings
import com.cavss.ralo.vm.MainVM

class MemorialFragment : Fragment()  {
    private val mainVM : MainVM by viewModels()
    private lateinit var binding : FragMemorialBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.frag_memorial, container, false)
            binding.run {
                vm = mainVM
                lifecycleOwner = activity as MainActivity
                setPlatformRecyclerView(platformRecyclerview)
                setQuetoRecyclerView(quotoRecyclerview)
                setFowview(toprateViewpager2)
            }
        }catch (e:Exception){
            Log.e("mException", "에러발생 : MemorialFragment, onCreateView // Exception : ${e.message}")
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
            Log.e("mException", "에러발생 : MemorialFragment, setTopbarList // Exception : ${e.message}")
        }
    }
    private fun setPlatformRecyclerView(recyclerview : RecyclerView){
        try{
            val clickEvent = object : IClickListener {
                override fun onClick(position: Int, model: Any?) {
                    if(model is PlatformModel){
                        //해당 사이트로 이동
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.itemURI))
                        startActivity(intent)
                    }
                }
            }

            val customAdapter = object : BaseRecyclerViewAdapter.Adapter<PlatformModel, HolderPlatformBinding>(
                layoutResId = R.layout.holder_platform,
                bindingVariableId = BR.model,
                bindingClickVariableID = BR.iclick
            ){}

            val customList = listOf<PlatformModel>(
                PlatformModel(
                    "트위치",
                    requireContext().resources.getDrawable(R.drawable.image_ralo_purple, null),
                    requireContext().resources.getString(R.string.ralo_twitch)
                ),
                PlatformModel(
                    "트게더",
                    requireContext().resources.getDrawable(R.drawable.image_ralo_twgether, null),
                    requireContext().resources.getString(R.string.ralo_twgether)
                ),
                PlatformModel(
                    "유투부",
                    requireContext().resources.getDrawable(R.drawable.image_ralo_green, null),
                    requireContext().resources.getString(R.string.ralo_youtube)
                ),
            )
            customAdapter.let{
                it.setOnClick(clickListener = clickEvent)
                it.updateList(customList)
            }

            recyclerview.apply {
                adapter = customAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext()).apply{
                    orientation = LinearLayoutManager.HORIZONTAL
                    isItemPrefetchEnabled = false
                }
                addItemDecoration(CustomItemGap(10))
                setItemViewCacheSize(0)
            }
        }catch(e:Exception){
            Log.e("mException", "에러발생 : MemorialFragment, setPlatformRecyclerView // Exception : ${e.message}")
        }
    }
    private fun setQuetoRecyclerView(recyclerview : RecyclerView){
        try{
            val clickEvent = object : IClickListener {
                override fun onClick(position: Int, model: Any?) {
                    if(model is QuotoModel){
                        //해당 사이트로 이동
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.uri))
                        startActivity(intent)
                    }
                }
            }

            val customAdapter = object : BaseRecyclerViewAdapter.Adapter<QuotoModel, HolderQuotoBinding>(
                layoutResId = R.layout.holder_quoto,
                bindingVariableId = BR.model,
                bindingClickVariableID = BR.iclick
            ){}


            customAdapter.let{
                it.setOnClick(clickListener = clickEvent)
                it.updateList(mainVM.dummyQuotoList)
            }

            recyclerview.apply {
                adapter = customAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext()).apply{
                    orientation = LinearLayoutManager.HORIZONTAL
                    isItemPrefetchEnabled = false
                }
                addItemDecoration(CustomItemGap(10))
                setItemViewCacheSize(0)
            }
        }catch(e:Exception){
            Log.e("mException", "에러발생 : MemorialFragment, setPlatformRecyclerView // Exception : ${e.message}")
        }
    }
    private fun setFowview(viewpager2 : ViewPager2){
        try{
            val customAdapter = FowAdapter(requireActivity())
            customAdapter.updateList(listOf(firstFrag,secondFrag))

            viewpager2.run{
                adapter = customAdapter
            }
        }catch(e:Exception){
            Log.e("mException", "에러발생 : MemorialFragment, setFowview // Exception : ${e.message}")
        }
    }

    private val firstFrag = FowFragment(
        setFragTitle = "GreenDay",
        setTierList = arrayListOf(
            TierModel("S21", "D1"),
            TierModel("S20", "D2"),
            TierModel("S19", "D3"),
            TierModel("S18", "D4"),
            TierModel("S17", "D4")
        ),
        setKdaList = arrayListOf(
            KDAModel("null","KDA","승률"),
            KDAModel("베인","00.73%","14.54"),
            KDAModel("리신","90.73%","1.52"),
            KDAModel("트린다미어","80.73%","0.52"),
            KDAModel("코그모","70.73%","2.72"),
            KDAModel("헤카림","60.73%","1.33"),
            KDAModel("이즈리얼","50.73%","5.12"),
            KDAModel("아크샨","40.73%","1.72"),
            KDAModel("우두루","30.73%","6.22")
        )
    )
    private val secondFrag = FowFragment(
        setFragTitle = "돈절래",
        setTierList = arrayListOf(
            TierModel("S21", "D1"),
            TierModel("S20", "D2"),
            TierModel("S19", "D3"),
            TierModel("S18", "D4"),
            TierModel("S17", "D4")
        ),
        setKdaList = arrayListOf(
            KDAModel("null","KDA","승률"),
            KDAModel("베인","00.73%","14.54"),
            KDAModel("리신","90.73%","1.52"),
            KDAModel("트린다미어","80.73%","0.52"),
            KDAModel("코그모","70.73%","2.72"),
            KDAModel("헤카림","60.73%","1.33"),
            KDAModel("이즈리얼","50.73%","5.12"),
            KDAModel("아크샨","40.73%","1.72"),
            KDAModel("우두루","30.73%","6.22")
        )
    )
}