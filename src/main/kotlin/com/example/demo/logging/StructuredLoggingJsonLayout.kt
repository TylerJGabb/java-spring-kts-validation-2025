package com.example.demo.logging

import ch.qos.logback.classic.spi.ILoggingEvent
import com.google.cloud.spring.logging.StackdriverJsonLayout
import org.springframework.stereotype.Component

@Component
class StructuredLoggingJsonLayout : StackdriverJsonLayout() {
    override fun addCustomDataToJsonMap(map: MutableMap<String, Any>?, event: ILoggingEvent?) {
        if (map != null && event != null) {
            val pairs = event.keyValuePairs
            pairs?.forEach { kvp ->
                map[kvp.key] = kvp.value
            }
        }
    }
}
