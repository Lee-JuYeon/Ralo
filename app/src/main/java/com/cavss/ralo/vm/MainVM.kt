package com.cavss.ralo.vm


import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavss.ralo.model.*
import com.cavss.ralo.util.Strings

class MainVM : ViewModel(){

    private val postList = MutableLiveData< ArrayList<CommunityModel>>()
    fun setPostList(list : ArrayList<CommunityModel>){ postList.value = list }
    val getPostList : LiveData< ArrayList<CommunityModel> >
        get() = postList

    private val fragmentType = MutableLiveData<String>("")
    fun setFragmentType(type : String){ fragmentType.value = type }
    val getFragmentType : LiveData<String>
        get() = fragmentType


    private val complainList = MutableLiveData< ArrayList<ComplainModel>>()
    fun setComplainList(list : ArrayList<ComplainModel>){ complainList.value = list }
    val getComplainList : LiveData< ArrayList<ComplainModel>>
        get() = complainList

    private val developer = MutableLiveData<DeveloperModel>()
    fun setDeveloperInfo(get : DeveloperModel){ developer.value = get}
    val getDeveloperInfo : LiveData<DeveloperModel>
        get() = developer

    val dummyNotifyList : ArrayList<NotifyModel> = arrayListOf(
        NotifyModel(title = "공지사항1",description = "바뀐내용과 설명1",timeStamp = "2021-09-09 01:01",expanded = false),
        NotifyModel(title = "공지사항2",description = "바뀐내용과 설명2",timeStamp = "2021-09-09 02:02",expanded = false),
        NotifyModel(title = "공지사항3",description = "바뀐내용과 설명3",timeStamp = "2021-09-09 03:03",expanded = false),
        NotifyModel(title = "공지사항4",description = "바뀐내용과 설명4",timeStamp = "2021-09-09 04:04",expanded = false),
        NotifyModel(title = "공지사항5",description = "바뀐내용과 설명5",timeStamp = "2021-09-09 05:05",expanded = false),
        NotifyModel(title = "공지사항6",description = "바뀐내용과 설명6",timeStamp = "2021-09-09 06:06",expanded = false)
    )
    val dummyCommunityList = arrayListOf(
        CommunityModel(
            description = "앱 쓸데없이 고퀄이면 개추 ㅋㅋㅋ 일단 나부터",
            time = "2021-09-09 09:09",
            postUID = "postUID1",
            user = UserModel(
                name = "김잼민1",
                age = 22,
                selfie = null,
                postUIDs = arrayListOf<String?>("postUID1", "postUID2"),
                uid = "userUID1"
            )
        ),
        CommunityModel(
            description = "야스",
            time = "2021-09-09 09:09",
            postUID = "postUID1",
            user = UserModel(
                name = "이잼민",
                age = 22,
                selfie = null,
                postUIDs = arrayListOf<String?>("postUID1", "postUID2"),
                uid = "userUID1"
            )
        ),
        CommunityModel(
            description = "이앱 머임??",
            time = "2021-09-09 09:09",
            postUID = "postUID1",
            user = UserModel(
                name = "조잼민",
                age = 22,
                selfie = null,
                postUIDs = arrayListOf<String?>("postUID1", "postUID2"),
                uid = "userUID1"
            )
        )
    )
    var dummyComplainList : ArrayList<ComplainModel> = arrayListOf(
        ComplainModel(
            expanded = false,
            timeStamp = "지금",
            complain = "개발자님이 랄로를 아시는지 궁금합니다",
            answer = "Q. 네 알려드렸습니다~",
            user = UserModel(
                name = "김잼민",
                age = 15,
                selfie = null,
                postUIDs = arrayListOf(),
                uid = "잼민이 1호"
            )
        ),
        ComplainModel(
            expanded = false,
            timeStamp = "지금",
            complain = "개발자님이 괴물쥐는 아시는지 궁금합니다",
            answer = "Q. 네 알려드렸습니다~",
            user = UserModel(
                name = "김잼민",
                age = 15,
                selfie = null,
                postUIDs = arrayListOf(),
                uid = "잼민이 1호"
            )
        ),
        ComplainModel(
            expanded = false,
            timeStamp = "지금",
            complain = "개발자님이 파카 또한 아시는지 궁금합니다",
            answer = "Q. 네 알려드렸습니다~",
            user = UserModel(
                name = "김잼민",
                age = 15,
                selfie = null,
                postUIDs = arrayListOf(),
                uid = "잼민이 1호"
            )
        )
    )
    val dummyDeveloperInfo = DeveloperModel(
        name = "C.A.V.S.S",
        age = "만 26세",
        selfie =  null,
        description = "아 예 반갑습니다. 개발자입니다. \n" +
                "우선 뭐 그렇게 됐습니다.\n" +
                "팬심에 개발을 하긴 했는데, 여러가지 기능을 테스트하는 용도로 사용될 듯 싶습니다.\n" +
                "자꾸 신상을 묻는 이 무매몽지한 친구들이 있는데,\n" +
                "어과초 정주행5번 166cm 132kg 공익출신입니다.\n" +
                "\n" +
                "그럼 단단!"
    )
    val dummyQuotoList = arrayListOf(
        QuotoModel(quoto = "\" 비트코인은 수명이 다했다. \n범지구적인 폭탄 돌리기가 시작되었다. \"", uri = "https://www.youtube.com/watch?v=c7kM4kmq1uc&t=57s"),
        QuotoModel(quoto = "\" 아니 비트코인은 뭐 부모도 없나? \"", uri = "https://www.youtube.com/watch?v=mDbMgR_bWJU"),
        QuotoModel(quoto = "\" 가버려~ 가버려~ 가버렷!! \"", uri = "https://www.youtube.com/watch?v=81XTBCbDZMI&list=UUD2YO_A_PVMgMDN9jpRrpVA&index=2")
    )

    init {
        postList.value = dummyCommunityList
//        complainList.value = dummyComplainList

        fragmentType.value = Strings.MEMORIAL
    }
}
