package com.example.rotationsubn

import com.example.rotationsubn.repo.MainRepo
import com.example.rotationsubn.topbar.TopBarAction
import com.example.rotationsubn.topbar.TopBarReducer
import com.example.rotationsubn.topbar.TopBarState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopBarReducerTest {

    @Mock
    private val repo: MainRepo = mock()

    @Test
    fun reduceNewDimension() = runTest {
        val newDim = TopBarReducer(repo).reduce(
            state = TopBarState(listOf(3, 4, 5), 3),
            action = TopBarAction.UpdateDimension(4)
        ).currentDimension
        assertEquals(4, newDim)
    }
}
