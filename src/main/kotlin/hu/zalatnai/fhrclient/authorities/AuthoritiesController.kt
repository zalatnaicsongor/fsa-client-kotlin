package hu.zalatnai.fhrclient.authorities

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AuthoritiesController(val authoritiesClient: AuthoritiesClient) {
    @GetMapping("/authorities/{id}")
    fun getAuthority(@PathVariable id: Int): Mono<ResponseEntity<Authority>> {
        if (id <= 0) {
            return Mono.just(ResponseEntity.badRequest().build())
        }

        return authoritiesClient.getAuthorityById(id)
            .map { authority -> ResponseEntity.ok(authority) }
            .onErrorResume { Mono.just(ResponseEntity.notFound().build()) }
    }
}