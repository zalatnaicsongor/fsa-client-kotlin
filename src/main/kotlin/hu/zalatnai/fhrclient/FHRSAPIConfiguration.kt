package hu.zalatnai.fhrclient

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@ConfigurationProperties(prefix = "fhrsapi")
class FHRSAPIConfiguration {
    lateinit var uri: String

    companion object {
        const val AUTHORITIES_BASIC_API_ENDPOINT = "/Authorities"
    }

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl(uri)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .defaultHeader("x-api-version", "2")
            .build()
    }
}