package com.example.riot_elo.ui.screem.detailUserScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.riot_elo.models.MatchInfo
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
class DetailViewmodel @Inject constructor(
    private val ktorService: KtorService,
) : ViewModel() {
    private val _listMatches = MutableStateFlow<List<MatchInfo>>(emptyList())
    val listMatches: StateFlow<List<MatchInfo>> = _listMatches

    init {
        fetchAllMatchesByIdUser("1")
    }

    fun fetchAllMatchesByIdUser(id:String){
        viewModelScope.launch {
            println("matcher 1112323232 : "+ktorService.getAllTeamsMatcherByIdMatch(id))
            _listMatches.value = ktorService.getAllTeamsMatcherByIdMatch(id)!!
        }
    }





}
