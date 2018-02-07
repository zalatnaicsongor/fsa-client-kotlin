package hu.zalatnai.fhrclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FhrclientApplication

fun main(args: Array<String>) {
    runApplication<FhrclientApplication>(*args)
}
