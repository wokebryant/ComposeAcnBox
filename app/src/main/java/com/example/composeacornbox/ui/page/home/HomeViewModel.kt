package com.example.composeacornbox.ui.page.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composeacornbox.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

@HiltViewModel
class HomeViewModel @Inject constructor(repository: DataRepository) : ViewModel() {

    var viewStates by mutableStateOf(HomeViewState())
            private set

    init {
        viewStates = viewStates.copy(
            accountState = repository.getAccountInfo(),
            dAppsState = repository.getDAppsInfo(),
            recommendState = repository.getRecommendInfo(),
            userCenterState = repository.getUserCenterInfo()
        )
    }
}

data class HomeViewState(
    val accountState: List<Account> = emptyList(),
    val dAppsState: List<DApp> = emptyList(),
    val recommendState: List<Recommend> = emptyList(),
    val userCenterState: List<UserCenter> = emptyList()
)