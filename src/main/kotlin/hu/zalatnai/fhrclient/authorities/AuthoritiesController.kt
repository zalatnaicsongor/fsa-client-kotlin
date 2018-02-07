package hu.zalatnai.fhrclient.authorities

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

data class Authority(
    @JsonProperty("LocalAuthorityId") val id: Int,
    @JsonProperty("Name") val name: String
)

@RestController
class AuthoritiesController {
    @GetMapping("/authorities/{id}")
    fun getAuthorities(@PathVariable id: Int): Mono<ResponseEntity<List<Authority>>> {
        return Mono.just(ResponseEntity.ok(emptyList()))
    }
}