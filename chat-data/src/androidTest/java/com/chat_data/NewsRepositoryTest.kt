package com.chat_data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.data.api.ApiService
import com.data.db.NewsDB
import com.domain.model.NewsResponse
import com.example.chatsample.data.repository.NewsRepository
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class NewsRepositoryTest {

    private lateinit var testDatabase: NewsDB
    private lateinit var apiService: ApiService
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        testDatabase = Room.inMemoryDatabaseBuilder(context, NewsDB::class.java).build()
        apiService = mock(ApiService::class.java)
        newsRepository = NewsRepository(apiService, testDatabase)
    }

    @Test
    fun getCurrentNewsFromApi() {
        val mockResponse = NewsResponse("success", emptyList())
        `when`(apiService.getLatestNews()).thenReturn(Single.just(mockResponse))
        val testObserver = newsRepository.getCurrentNewsFromApi().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }

    @Test
    fun getNewsFromDB() {
        val testProducts = DataProvider.getNews(5)
        testDatabase.getNewsDAO().addNews(testProducts).test().assertComplete()
        val testObserver = newsRepository.getCurrentNewsFromDB().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue {
            it.size == 5
        }
    }

    @Test
    fun saveNewsLocally() {
        newsRepository.saveNewsLocally(DataProvider.getNews(2)).test().assertComplete()
        val testObserver = newsRepository.getCurrentNewsFromDB().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue {
            it.size == 2
        }
        newsRepository.saveNewsLocally(DataProvider.getNews(5)).test().assertComplete()
        val testObserverAfter = newsRepository.getCurrentNewsFromDB().test()
        testObserverAfter.assertComplete()
        testObserverAfter.assertNoErrors()
        testObserverAfter.assertValue {
            it.size == 5
        }
    }

    @After
    fun closeDb() {
        testDatabase.close()
    }
}