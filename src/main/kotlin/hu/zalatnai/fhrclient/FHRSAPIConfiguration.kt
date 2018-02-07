package hu.zalatnai.fhrclient

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "fhrsapi")
class FHRSAPIConfiguration {

    var uri: String? = null

    companion object {
        val AUTHORITIES_BASIC_API_ENDPOINT = "/Authorities/basic"
        val ESTABLISHMENTS_BASIC_API_ENDPOINT = "/Establishments"
    }
}