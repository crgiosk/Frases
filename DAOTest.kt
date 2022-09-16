package co.com.civica.superapp.testdaos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import co.com.civica.superapp.database.CivicaDatabase
import co.com.civica.superapp.database.dao.AgreementsDao
import co.com.civica.superapp.database.dao.CategoriesAgreementDAO
import co.com.civica.superapp.database.entities.pay_services.AgreementEntity
import co.com.civica.superapp.database.entities.pay_services.CategoryAgreementEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import java.util.concurrent.CountDownLatch

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DAOTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var categoriesAgreementDao: CategoriesAgreementDAO
    private lateinit var agreementsDao: AgreementsDao
    private lateinit var database: CivicaDatabase

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CivicaDatabase::class.java
        ).allowMainThreadQueries().build()

        categoriesAgreementDao = database.categoriesAgreementsDao()
        agreementsDao = database.agreementsDao()
    }

    @Test
    fun `Validate success insert Categories`() = runBlocking {
        val category = CategoryAgreementEntity(
            categoryAgreementId = 1,
            categoryAgreementName = "test",
            categoryAgreementImage = "image.jpg",
            categoryAgreementOrder = 1,
        )

        categoriesAgreementDao.saveCategories(listOf(category))

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            categoriesAgreementDao.getCategoriesAgreement().collect { categories ->
                assertThat(categories).contains(category)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()

    }

    @Test
    fun `Validate success insert Agreeement`() = runBlocking {
        val category = CategoryAgreementEntity(
            categoryAgreementId = 1,
            categoryAgreementName = "test",
            categoryAgreementImage = "image.jpg",
            categoryAgreementOrder = 1,
        )

        categoriesAgreementDao.saveCategories(listOf(category))

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            categoriesAgreementDao.getCategoriesAgreement().collect { categories ->
                assertThat(categories).contains(category)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()

        val agreement = AgreementEntity(
            myAgreementId = 0,
            agreementNumber = 123,
            myAgreementDescription = "Agreement",
            myTextReferences1 = "AgreementAgreement",
            myTextReferences2 = "Agreementa",
            myNumberReferences = 321,
            myCollectionType = "AgreementType",
            myValidIdValir = 231,
            myConsultIdWSBDI = 132,
            myIacCode = 312,
            myCategoryId = category.categoryAgreementName,
        )

/*        val latch1 = CountDownLatch(1)
        val job1 = async(Dispatchers.IO) {
            agreementsDao.getAgreements().collect { categories ->
                assertThat(categories).contains(agreement)
                latch1.countDown()
            }
            println("9")
        }
        println("10")
        latch1.await()
        job1.cancelAndJoin()
        println("11")*/

    }

    @Test
    fun `Validate success insert Agreement1`() = runBlocking {
        val category = CategoryAgreementEntity(
            categoryAgreementId = 1,
            categoryAgreementName = "test",
            categoryAgreementImage = "image.jpg",
            categoryAgreementOrder = 1,
        )

        val agreement = AgreementEntity(
            myAgreementId = 0,
            agreementNumber = 123,
            myAgreementDescription = "Agreement",
            myTextReferences1 = "AgreementAgreement",
            myTextReferences2 = "Agreementa",
            myNumberReferences = 321,
            myCollectionType = "AgreementType",
            myValidIdValir = 231,
            myConsultIdWSBDI = 132,
            myIacCode = 312,
            myCategoryId = category.categoryAgreementName,
        )
        categoriesAgreementDao.saveCategories(listOf(category))
        agreementsDao.saveAgreements(listOf(agreement))

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            categoriesAgreementDao.getCategoriesAgreement().collect { categories ->
                agreementsDao.getAgreements().collect { agreement ->
                    assertThat(agreement).contains(agreement)
                    assertThat(categories).contains(category)
                    latch.countDown()
                }
            }
        }
        latch.await()
        job.cancelAndJoin()

    }

    @After
    fun closeDatabase() {
        runBlocking {
            agreementsDao.deleteAllAgreements()
            categoriesAgreementDao.deleteAllCategories()
            database.close()
        }
    }
}

/*
*
*      // Local Unit Tests
    implementation "androidx.test:core:1.4.0"
    implementation "androidx.test.ext:junit-ktx:1.1.3"
    testImplementation "androidx.test:runner:1.4.0"
    testImplementation "junit:junit:4.13.2"
    testImplementation "org.hamcrest:hamcrest-all:1.3"
    testImplementation "androidx.arch.core:core-testing:2.1.0"
    testImplementation "org.robolectric:robolectric:4.3.1"
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.2.1"
    testImplementation "com.google.truth:truth:1.0.1"
* */
