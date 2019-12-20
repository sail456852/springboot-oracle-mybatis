import beans.Test;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/27/2019<br/>
 * Time: 4:27 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class StreamTest {
    private static String wordsStr = "There once lived in a town of Persia two brothers, " +
            "one named Cassim and the other Ali Baba. " +
            "Their father divided a small inheritance equally between them. " +
            "Cassim married a very rich wife, and became a wealthy merchant. " +
            "Ali Baba married a woman as poor as himself, " +
            "and lived by cutting wood, and bringing it upon three asses into the town to sell.";

    /**
     *  1. existence test
     *  2. Optional.empty equal test
     */
    @Test
    public void filterExistence() {
        // 1. string -> stream
        Stream<String> words = Stream.of(wordsStr.split("\\PL+"));
//        boolean aWordStartsWithQ = words.parallel().anyMatch(s -> s.startsWith("Q"));
//        Optional<String> startsWithQ = words.parallel().filter(s -> s.startsWith("Q")).findAny();
//        if(Optional.empty().equals(startsWithQ)){
//            // this test actually works
//            System.err.println("StreamTest.testFor EMPTY");
//            System.err.println("startsWithQ = " + startsWithQ);
//        }
        // find a string in a collection
        String theWord = "cutting1";
        boolean anyMatch = words.parallel().anyMatch(theWord::equals);
        System.err.println("anyMatch = " + anyMatch);
    }

    @Test
    public void generate() {
        Stream<String> generate = Stream.generate(() -> "Echo");
        streamToListPrint(generate);
    }

    private <T> void streamToListPrint(Stream<T> stream){
        List<T> list = stream.limit(11).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

}

