package com.example.riot_elo.ui.screem.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.riot_elo.models.User
import com.example.riot_elo.models.UserDetial
import com.example.riot_elo.models.imageFaker
import com.example.riot_elo.models.setRankForUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import online.devphu.truyenhub.Api.service.KtorService
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val ktorService: KtorService,
) : ViewModel() {
    private val _listUser = MutableStateFlow<List<User>>(emptyList())
    val listUser: StateFlow<List<User>> = _listUser

    private val _userDetail = MutableStateFlow<UserDetial?>(UserDetial(
        id = "",
        name  = "",
        kElo = "",
        lp = "",
        win = "",
        rankLever = "",
        imageUrl = imageFaker,
        rank = "1",
        totalMatches = "",
        totalWins = "120",
        winRate = "50%"
    ))
    val userDetail: StateFlow<UserDetial?> = _userDetail

    init {
        fetchListUser()
    }

    fun fetchListUser(){
        viewModelScope.launch {
            println(setRankForUsers(ktorService.getAllUser()!!))
            _listUser.value = ktorService.getAllUser()!!
        }
    }
    fun fetchUserDetailById(id:String){
        viewModelScope.launch {
            println(ktorService.getDetialByID(id))
            _userDetail.value = ktorService.getDetialByID(id)!!
        }
    }






}
