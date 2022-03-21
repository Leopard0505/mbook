package com.rest.api.mbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MbookApplication

fun main(args: Array<String>) {
	runApplication<MbookApplication>(*args)
}
