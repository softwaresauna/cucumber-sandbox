package util

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.cucumber.plugin.event.Status
import org.junit.Test

class FeatureLineReportTest {

    @Test
    fun `initial report is empty`() {
        assertThat(FeatureLineReport().toString()).isEqualTo("")
    }

    @Test
    fun `single tag mapped to single line report`() {

        val report = FeatureLineReport()

        report.addScenarioResult(
            tags = listOf("foo"),
            status = Status.PASSED
        )

        assertThat(report.toString()).isEqualTo("foo\t${Status.PASSED}")
    }

    @Test
    fun `one report line per tag`() {

        val report = FeatureLineReport()

        report.addScenarioResult(
            tags = listOf("foo", "bar", "pop"),
            status = Status.PASSED
        )

        assertThat(report.toString()).isEqualTo(
            "foo\t${Status.PASSED}\nbar\t${Status.PASSED}\npop\t${Status.PASSED}"
        )
    }
}
