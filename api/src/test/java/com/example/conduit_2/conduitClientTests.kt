package com.example.conduit_2

import com.example.api.ConduitClient
import com.example.api.models.entities.UserCreds
import com.example.api.models.requests.SignupRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.random.Random

class conduitClientTests {
    private val conduitClient=ConduitClient()
    @Test
    fun `GET articles`(){
        runBlocking {
            val articles=conduitClient.publicApi.getArticles()
            assertNotNull(articles.body()?.articles)
        }

    }
@Test
    fun `POST users - create user`(){
        runBlocking {
            val userCreds=UserCreds(
            email = "testemail${Random.nextInt(999,9999)}@test.com",
            password = "${Random.nextInt(99999,99999999)}",
            username = "rand_user${Random.nextInt(99,999)}"
            )
            val resp =conduitClient.publicApi.signupUser(SignupRequest((userCreds)))
            assertEquals(userCreds.username,resp.body()?.user?.username)
        }
    }
}