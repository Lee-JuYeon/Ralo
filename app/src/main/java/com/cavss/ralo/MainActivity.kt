package com.cavss.ralo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cavss.ralo.databinding.ActivityMainBinding
import com.cavss.ralo.databinding.HolderDockBinding
import com.cavss.ralo.databinding.HolderNotifyBinding
import com.cavss.ralo.databinding.HolderTopbarBinding
import com.cavss.ralo.model.DockModel
import com.cavss.ralo.model.NotifyModel
import com.cavss.ralo.model.TopbarModel
import com.cavss.ralo.ui.customs.popup.PopupComplain
import com.cavss.ralo.ui.customs.popup.PopupDeveloper
import com.cavss.ralo.ui.customs.popup.PopupNotify
import com.cavss.ralo.ui.customs.recyclerview.BaseRecyclerViewAdapter
import com.cavss.ralo.ui.customs.recyclerview.CustomItemGap
import com.cavss.ralo.ui.customs.recyclerview.IClickListener
import com.cavss.ralo.ui.views.main.community.CommunityFragment
import com.cavss.ralo.ui.views.main.complain.ComplainFragment
import com.cavss.ralo.ui.views.main.memorial.MemorialFragment
import com.cavss.ralo.ui.views.main.profile.ProfileFragment
import com.cavss.ralo.util.Strings
import com.cavss.ralo.vm.MainVM

class MainActivity : AppCompatActivity() {

    private val mainVM : MainVM by viewModels()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            binding.run {
                vm = mainVM
                lifecycleOwner = this@MainActivity
                setDockView(dockview)
                setTopbar(topbar)
            }
            mainVM.getFragmentType.observe(this, { type ->
                try{
                    changeFragment(type)
                }catch(e:Exception){
                    changeFragment(Strings.MEMORIAL)
                    Log.e("mException", "에러발생 : MainActivity, onCreate, mainVM.getFragmentType.observe // Exception : ${e.message}")
                }catch(e:NoSuchElementException){
                    changeFragment(Strings.MEMORIAL)
                    Log.e("mException", "에러발생 : MainActivity, onCreate, mainVM.getFragmentType.observe  // NoSuchElementException : ${e.message}")
                }
            })
        }catch (e:Exception){
            Log.e("mException", "에러발생 : MainActivity, onCreate  // Exception : ${e.message}")
        }
    }

    private val fragComplain = ComplainFragment()
    private val fragCommunity = CommunityFragment()
    private val fragMemorial = MemorialFragment()
    private val fragProfile = ProfileFragment()
    private fun changeFragment(frag : String){
        try{
            val manager = (this as FragmentActivity).supportFragmentManager.beginTransaction()
            when(frag){
                Strings.COMPLAIN -> {
                    manager.replace(R.id.frame, fragComplain).commit()
                }
                Strings.COMMUNITY -> {
                    manager.replace(R.id.frame, fragCommunity).commit()
                }
                Strings.MEMORIAL -> {
                    manager.replace(R.id.frame, fragMemorial).commit()
                }
                Strings.PROFILE -> {
                    manager.replace(R.id.frame, fragProfile).commit()
                }
            }
        }catch (e:Exception){
            Log.e("mException", "에러발생 : MainActivity, changeFragment  // Exception : ${e.message}")
        }
    }
    private fun setDockView(recyclerview : RecyclerView){
        try{
            val clickEvent = object : IClickListener {
                override fun onClick(position: Int, model: Any?) {
                    if(model is DockModel){
                        when(model.title){
                            Strings.COMPLAIN -> {
                                mainVM.setFragmentType(Strings.COMPLAIN)
                            }
                            Strings.COMMUNITY -> {
                                mainVM.setFragmentType(Strings.COMMUNITY)
                            }
                            Strings.MEMORIAL -> {
                                mainVM.setFragmentType(Strings.MEMORIAL)
                            }
                            Strings.PROFILE -> {
                                mainVM.setFragmentType(Strings.PROFILE)
                            }
                        }
                    }
                }
            }

            val dockAdapter = object : BaseRecyclerViewAdapter.Adapter<DockModel, HolderDockBinding>(
                layoutResId = R.layout.holder_dock,
                bindingVariableId = BR.model,
                bindingClickVariableID = BR.iclick
            ){}

            val dockList = mutableListOf(
                DockModel(
                    title = Strings.COMPLAIN,
                    image = ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null)
                ),
                DockModel(
                    title = Strings.MEMORIAL,
                    image = ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null)
                ),
                DockModel(
                    title = Strings.COMMUNITY,
                    image = ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null)
                ),
                DockModel(
                    title = Strings.PROFILE,
                    image = ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null)
                )
            )
            dockAdapter.let {
                it.setOnClick(clickListener = clickEvent)
                it.updateList(dockList)
            }

            recyclerview.apply {
                adapter = dockAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity).apply{
                    isItemPrefetchEnabled = false
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                setItemViewCacheSize(0)
            }
        }catch(e:Exception){
            Log.e("mException", "에러발생 : MainActivity, setDockView  // Exception : ${e.message}")
        }
    }

    private var topbarAdapter : BaseRecyclerViewAdapter.Adapter<TopbarModel, HolderTopbarBinding>? = null
    private fun setTopbar(recyclerview: RecyclerView){
        try{
            val clickEvent = object : IClickListener {
                override fun onClick(position: Int, model: Any?) {
                    if(model is TopbarModel){
                        when(model.itemEnglish){
                            Strings.TOPBAR_NOTIFY -> { showNotifyPopupView() }
                            Strings.TOPBAR_COMPLAIN -> { showComplainView() }
                            Strings.TOPBAR_DEVELOPER -> { showDeveloper() }
                        }
                    }
                }
            }

            topbarAdapter = object : BaseRecyclerViewAdapter.Adapter<TopbarModel, HolderTopbarBinding>(
                layoutResId = R.layout.holder_topbar,
                bindingVariableId = BR.model,
                bindingClickVariableID = BR.iclick
            ){}

            val customList = listOf<TopbarModel>()

            topbarAdapter.let {
                it?.setOnClick(clickListener = clickEvent)
                it?.updateList(customList)
            }

            recyclerview.apply {
                adapter = topbarAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity).apply{
                    orientation = LinearLayoutManager.HORIZONTAL
                    isItemPrefetchEnabled = false
                }
                setItemViewCacheSize(0)
            }
        }catch(e:Exception){
            Log.e("mException", "에러발생 : MainActivity, setTopbar  // Exception : ${e.message}")
        }
    }
    fun changeTopbarList(list : List<TopbarModel>){
        topbarAdapter?.updateList(list)
    }

    private fun showNotifyPopupView(){
        try {
            val popupNotify = PopupNotify()

            val clickEvent = object : IClickListener {
                override fun onClick(position: Int, model: Any?) {
                    if(model is NotifyModel){
                        Log.e("mException", "${model.title}이 클릭됨")
//                        popupNotify.dismiss()
                    }
                }
            }

            val customAdapter = object : BaseRecyclerViewAdapter.Adapter<NotifyModel, HolderNotifyBinding>(
                layoutResId = R.layout.holder_notify,
                bindingVariableId = BR.model,
                bindingClickVariableID = BR.iclick
            ){}

            customAdapter.let {
                it.setOnClick(clickListener = clickEvent)
                it.updateList(mainVM.dummyNotifyList)
            }

            popupNotify.run {
                setContext(this@MainActivity)
                setPlace(binding.frame)
                setPopupTitle("공지사항")
                showPopupView().setOnDismissListener {
                }
                getRecyclerView().run {
                    adapter = customAdapter
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@MainActivity).apply{
                        isItemPrefetchEnabled = false
                        orientation = LinearLayoutManager.VERTICAL
                    }
                    addItemDecoration(CustomItemGap(10))
                    setItemViewCacheSize(0)
                }
            }
        }catch (e:Exception){
            Log.e("mException", "에러발생 : MainActivity, setColourPopupView  // Exception : ${e.message}")
        }
    }
    private fun showComplainView(){
        try{
            val popupComplain = PopupComplain()
            popupComplain.run {
                setContext(this@MainActivity)
                setPlace(binding.frame)
                setPopupTitle("건의하기")
                setHint("건의사항을 입력해주세요")
                showPopupView().setOnDismissListener {

                }
                getEnter(
                    enterButton = { button : ImageView, editText : EditText ->
                        button.setOnClickListener {
                            Log.e("mException", "입력된 텍스트 : ${editText.text.toString()}")
                            popupComplain.dismiss()
                        }
                    }
                )
            }
        }catch(e:Exception){
            Log.e("mException", "에러발생 : MainActivity, showComplainView  // Exception : ${e.message}")
        }
    }
    private fun showDeveloper(){
        try{
            val popupComplain = PopupDeveloper()
            popupComplain.run {
                setContext(this@MainActivity)
                setPlace(binding.frame)
                setPopupTitle("반갑습니다, 개발자입니다.")
                setContent(mainVM.dummyDeveloperInfo)
                showPopupView().setOnDismissListener {

                }
            }
        }catch(e:Exception){
            Log.e("mException", "에러발생 : MainActivity, showDeveloper  // Exception : ${e.message}")
        }
    }

}