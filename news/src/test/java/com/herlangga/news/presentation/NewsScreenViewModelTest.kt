package com.herlangga.news.presentation

import android.view.View
import app.cash.turbine.test
import com.herlangga.core.data.ViewState
import com.herlangga.news.domain.model.News
import com.herlangga.news.domain.usecase.NewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Designed and developed by Herlangga Wicaksono on 19/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@OptIn(ExperimentalCoroutinesApi::class)
class NewsScreenViewModelTest {

	private val testCoroutine = UnconfinedTestDispatcher()

	@MockK
	private lateinit var newsUseCase: NewsUseCase

	@InjectMockKs
	private lateinit var sut: NewsScreenViewModel

	@Before
	fun setup() {
		MockKAnnotations.init(this, relaxUnitFun = true)
		Dispatchers.setMain(testCoroutine)
	}

	@After
	fun cleanUp() {
		unmockkAll()
		Dispatchers.resetMain()
	}

	@Test
	fun `on receive OnFetchNews should trigger NewsUseCase`() = runTest {
		coEvery { newsUseCase.invoke() } returns emptyList()
		sut.onEvent(NewsUiEvent.OnFetchNews)
		coVerify { newsUseCase.invoke() }
	}

	@Test
	fun `on receive OnFetchNews should update uiState to Loading then Success after receiving value`() = runTest {
		val expected = listOf(
			News(testId = "1"),
			News(testId = "2"),
			News(testId = "3"),
		)
		coEvery { newsUseCase.invoke() } returns expected
		sut.uiState.test {
			sut.onEvent(NewsUiEvent.OnFetchNews)
			val loadingState = awaitItem()
			assert(loadingState.viewState is ViewState.Loading) {
				"ViewState is not updated"
			}
			val successState = awaitItem()
			assert(successState.viewState is ViewState.Content) {
				"ViewState Success is not received"
			}
			assert(successState.news.count() == 3) {
				"News size is not correct"
			}
			assert(successState.news ==  expected) {
				"News is different from expected value"
			}
			cancelAndIgnoreRemainingEvents()
		}
	}

	@Test
	fun `when error occurred on usecase, should update uiState to Error`() = runTest {
		val expectedMsg = "Unknown Error"
		coEvery { newsUseCase.invoke() } throws Error(expectedMsg)
		sut.uiState.test {
			sut.onEvent(NewsUiEvent.OnFetchNews)
			val lastViewState = expectMostRecentItem().viewState
			assert(lastViewState is ViewState.Error && lastViewState.message == expectedMsg) {
				"ViewState is not updated or message is not matched"
			}
			cancelAndIgnoreRemainingEvents()
		}
	}
}