package fr.raluy.attestation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AttestationApplication

fun main(args: Array<String>) {
    runApplication<AttestationApplication>(*args)
}
