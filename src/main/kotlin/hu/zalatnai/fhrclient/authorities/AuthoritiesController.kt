package hu.zalatnai.fhrclient.authorities

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AuthoritiesController(val authoritiesClient: AuthoritiesClient) {
    @GetMapping("/authorities/{id}")
    fun getAuthorities(@PathVariable id: Int): Mono<ResponseEntity<List<Authority>>> {
        if (id <= 0) {
            return Mono.just(ResponseEntity.badRequest().build())
        }
        
        return authoritiesClient.getAuthoritiesById(1).map { authorityList -> ResponseEntity.ok(authorityList) }
    }
}