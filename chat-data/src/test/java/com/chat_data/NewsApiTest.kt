package com.chat_data

import android.util.Log
import com.data.api.ApiService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api: ApiService

    @Before
    fun beforeEach() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Test
    fun getLatestNews_success() {
       // val response = NewsResponse("success", emptyList())
        val res = MockResponse()
        res.setBody("[]")
        server.enqueue(res)

        val data = api.getLatestNews().test()
        server.takeRequest()

        data.assertComplete()
        data.assertNoErrors()
        data.assertValue {
            Log.e("Error", "$it")
            true
        }

    }

    @Test
    fun getLatestNews_failure() {
        val testObserver = api.getLatestNews("MA").test()
        testObserver.assertError {
            Log.e("getLatestNews_failure", "$it")
            true
        }
    }

    @After
    fun afterEach() {
        server.shutdown()
    }
}