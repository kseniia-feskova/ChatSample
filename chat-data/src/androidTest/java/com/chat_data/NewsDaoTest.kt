package com.chat_data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.data.db.NewsDAO
import com.data.db.NewsDB
import com.domain.model.NewsItem
import org.junit.After
import org.junit.Before
import org.junit.Test

class NewsDaoTest {
    private lateinit var testDatabase: NewsDB
    private lateinit var dao: NewsDAO

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        testDatabase = Room.inMemoryDatabaseBuilder(context, NewsDB::class.java).build()
        dao = testDatabase.getNewsDAO()
    }

    @Test
    fun getNewsTest() {
        val testProducts = DataProvider.getNews(5)
        dao.addNews(testProducts).test().assertComplete()
        dao.getNews().test()
            .assertValue { cachedProducts ->
                areContentsSame(testProducts, cachedProducts)
            }
    }

    @Test
    fun clearNewsTest() {
        val testProducts = DataProvider.getNews(5)
        dao.addNews(testProducts).test().assertComplete()
        dao.getNews().test()
            .assertValue { cachedProducts ->
                areContentsSame(testProducts, cachedProducts)
            }
        dao.deleteAll().test().assertComplete()
        dao.getNews().test().assertValue { cachedProducts ->
            cachedProducts.isEmpty()
        }
    }

    @After
    fun closeDb() {
        testDatabase.close()
    }

    private fun areContentsSame(list1: List<NewsItem>, list2: List<NewsItem>): Boolean {
        return if (list1 != list2 || list1.size != list2.size) false
        else {
            var allItemsTheSame = true
            var i = 0
            while (i < list1.size) {
                val itemFormFirst = list1[i]
                val itemFromSecond = list2[i]
                allItemsTheSame = itemFormFirst == itemFromSecond
                if (allItemsTheSame) i++
                else break
            }
            allItemsTheSame
        }
    }
}
