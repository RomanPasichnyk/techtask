package digis.techtask

import org.jeasy.random.EasyRandom
import spock.lang.Specification

class BaseUnitTest extends Specification {

    EasyRandom random = new EasyRandom()

    def setup() {
        0 * _._ // ensure that only expected calls are executed
    }

}
