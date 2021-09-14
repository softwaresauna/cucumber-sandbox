package util

import io.cucumber.plugin.event.Status

class FeatureLineReport {

    private val tags = mutableSetOf<String>()

    fun addScenarioResult(tags: List<String>, status: Status) {
        this.tags.addAll(tags)
    }

    override fun toString(): String {
        return tags
            .map { "$it\t${Status.PASSED}" }
            .joinToString("\n")
    }
}

