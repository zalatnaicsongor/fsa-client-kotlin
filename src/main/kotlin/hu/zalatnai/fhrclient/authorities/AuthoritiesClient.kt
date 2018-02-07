package hu.zalatnai.fhrclient.authorities

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

data class AuthoritiesResult(val authorities: List<Authority>)

@Service
class AuthoritiesClient {
    fun getAuthoritiesById(id: Int): Mono<List<Authority>> {
        return Mono.just(emptyList())
    }
}