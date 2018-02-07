package hu.zalatnai.fhrclient.authorities

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.reactive.function.client.WebClientResponseException

@RunWith(SpringRunner::class)
@SpringBootTest(
    properties = ["fhrsapi.uri=http://localhost:\${wiremock.server.port}"],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWireMock(port = 0, stubs = ["classpath:/stubs"])
class AuthoritiesClientIT {
    companion object {
        const val id200 = 1
        const val id500 = 500
        const val id404 = 404
    }

    @Autowired
    lateinit var authoritiesClient: AuthoritiesClient

    @Test
    fun `retrieves the result from the authorities api endpoint`() {
        val authoritiesById = authoritiesClient.getAuthorityById(id200)
        assertTrue(authoritiesById.block() != null)
    }

    @Test(expected = RuntimeException::class)
    fun `in case of a server error the client propagates the error`() {
        val authoritiesById = authoritiesClient.getAuthorityById(id500)
        authoritiesById.block()
    }

    @Test(expected = AuthorityNotFoundException::class)
    fun `if the authority cannot be found an exception is thrown`() {
        val authoritiesById = authoritiesClient.getAuthorityById(id404)
        authoritiesById.block()
    }
}