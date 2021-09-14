package util

import io.cucumber.plugin.ConcurrentEventListener
import io.cucumber.plugin.event.EventPublisher
import io.cucumber.plugin.event.TestCaseFinished
import io.cucumber.plugin.event.TestRunFinished
import java.io.BufferedWriter
import java.io.FileWriter


class FeatureLineReporter : ConcurrentEventListener {

    private val report = FeatureLineReport()
    private val reportPath = System.getProperty("LINE_REPORT_PATH")

    override fun setEventPublisher(eventPublisher: EventPublisher) {
        eventPublisher.registerHandlerFor(TestCaseFinished::class.java) {
            report.addScenarioResult(it.testCase.tags, it.result.status)
        }

        eventPublisher.registerHandlerFor(TestRunFinished::class.java) {
            writeReport(this.report.toString())
        }
    }

    private fun writeReport(report: String) {

        val writer = BufferedWriter(FileWriter(reportPath))
        writer.write(report)

        writer.close()
    }
}
