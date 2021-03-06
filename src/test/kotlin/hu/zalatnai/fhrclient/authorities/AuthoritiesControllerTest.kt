package hu.zalatnai.fhrclient.authorities

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import reactor.core.publisher.Mono
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class AuthoritiesControllerTest {

    private val authority = Authority(123, "name")

    @Mock
    lateinit var authoritiesClient: AuthoritiesClient

    lateinit var authoritiesController: AuthoritiesController

    @Before
    fun setUp() {
        authoritiesController = AuthoritiesController(authoritiesClient)

        `when`(authoritiesClient.getAuthorityById(1)).thenReturn(Mono.just(authority))
    }

    @Test
    fun `returns a bad request if the id is invalid`() {
        assertEquals(
            400,
            authoritiesController.getAuthority(0).block()?.statusCodeValue
        )
    }

    @Test
    fun `delegates the call to the client`() {
        authoritiesController.getAuthority(1)

        verify(authoritiesClient).getAuthorityById(1)
    }

    @Test
    fun `returns an ok response containing the result returned by the client`() {
        assertEquals(
            authority,
            authoritiesController.getAuthority(1).block()?.body
        )
    }
}