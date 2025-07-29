package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class StationRepositoryTest {

    @Autowired
    private lateinit var stations: StationRepository

    @Test
    fun save() {
        val expected = Station(name = "pankow")
        val actual = stations.save(expected)
        assertThat(actual.id).isNotZero()
        assertThat(actual.name).isEqualTo(expected.name)
    }

    @Test
    fun `findByName() - 1`() {
        val expected = stations.save(Station(name = "pankow"))
        val actual = stations.findByName(expected.name).get()
        assertThat(actual.id).isNotZero()
        assertThat(actual.name).isEqualTo(expected.name)
    }

    @Test
    fun `findByName() - equality`() {
        val expected = "pankow"
        stations.save(Station(name = expected))
        val actual = stations.findByName(expected).get().name
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `identity() - using findById()`() {
        val station1 = stations.save(Station(name = "pankow"))
        val station2 = stations.findById(station1.id).get()
        assertThat(station1 === station2).isTrue()
        assertThat(station1 == station2).isTrue()
    }

    @Test
    fun `identity() - but using findByName()`() {
        val station1 = stations.save(Station(name = "pankow"))
        val station3 = stations.findByName(station1.name).get()
        assertThat(station1 === station3).isTrue()
        assertThat(station1 == station3).isTrue()
    }

    @Test
    fun `identity() - identity vs equality`() {
        val station1 = stations.save(Station(name = "pankow"))
        val station2 = stations.findById(station1.id).get()
        val station3 = stations.findByName(station1.name).get()
        assertThat(station2 === station3).isTrue()
        assertThat(station2 == station3).isTrue()
        assertThat(station2).isEqualTo(station3)
        assertThat(station2).isSameAs(station3)
    }

    @Test
    fun test1() {
        val station1 = stations.save(Station(name = "pankow"))
        stations.flush()
        assertThat(station1.id).isNotZero()
    }

    @Test
    fun update() {
        val station1 = stations.save(Station(name = "pankow"))
        station1.changeName(name = "orange")
        stations.flush()
        station1.changeName(name = "pankow")
        stations.flush()
//        val station2 = stations.findByName("orange").get()
        val station2 = stations.findById(1L).get()
        assertThat(station2).isNotNull()
        assertThat(station2.name).isEqualTo(station1.name)
    }
}
