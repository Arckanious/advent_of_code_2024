package org.helpers

import org.slf4j.Logger
import org.slf4j.LoggerFactory

// Base class with logging
open class LoggerAware {
    val log: Logger = LoggerFactory.getLogger(this::class.java)
}