package hu.zalatnai.fhrclient.authorities

import com.fasterxml.jackson.annotation.JsonProperty

data class Authority(
    @JsonProperty("LocalAuthorityId") val id: Int,
    @JsonProperty("Name") val name: String
)