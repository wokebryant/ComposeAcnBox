package com.example.composeacornbox.ui.page.usercenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composeacornbox.data.DataRepository
import com.example.composeacornbox.data.WorkBox
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

@HiltViewModel
class WorkBoxViewModel @Inject constructor(repository: DataRepository) : ViewModel() {

    var viewStates by mutableStateOf(WorkBoxState())
        private set

    init {
        viewStates = viewStates.copy(
            workBoxList = repository.getWorkBoxInfo()
        )
    }
}

data class WorkBoxState(
    val workBoxList: List<WorkBox> = emptyList()
)