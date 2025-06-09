package com.example.demo.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class Loggable {
    protected val logger: Logger = LoggerFactory.getLogger(javaClass)
}