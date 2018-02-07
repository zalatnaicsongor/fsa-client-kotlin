package hu.zalatnai.fhrclient.authorities

import hu.zalatnai.fhrclient.FHRSAPIConfiguration
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

data class AuthoritiesResult(val authorities: List<Authority>?)

@Service
class AuthoritiesClient(val webClient: WebClient) {
    fun getAuthorityById(id: Int): Mono<Authority> {
        return webClient.get()
            .uri(FHRSAPIConfiguration.AUTHORITIES_BASIC_API_ENDPOINT + "/" + id)
            .retrieve()
            .onStatus({ HttpStatus.NOT_FOUND == it }, { Mono.just(AuthorityNotFoundException()) })
            .onStatus(HttpStatus::isError, { Mono.just(RuntimeException())})
            .bodyToMono(Authority::class.java)
    }
}

