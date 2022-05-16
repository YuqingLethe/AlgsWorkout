package Design;

import java.util.Iterator;

/**
 * Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 *
 * PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * boolean hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 * Note: Each language may have a different implementation of the constructor and Iterator,
 *        but they all support the int next() and boolean hasNext() functions.
 *
 * Java Iterator interface reference:
 * https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
 *
 *
 * Example 1:
 * Input
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 2, 2, 3, false]
 * Explanation
 * PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
 * peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
 * peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
 * peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
 * peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
 * peekingIterator.hasNext(); // return False
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * All the calls to next and peek are valid.
 * At most 1000 calls will be made to next, hasNext, and peek.
 *
 * Follow up:
 * How would you extend your design to be generic and work with all types, not just integer?
 *
 * Hints:
 * 1. Think of "looking ahead". You want to cache the next element.
 * 2. Is one variable sufficient? Why or why not?
 * 3. Test your design with call order of peek() before next() vs next() before peek().
 * 4. For a clean implementation, check out Google's guava library source code.
 */
public class Design284PeekingIterator {
    /**
     * April 2022 crib the answer
     */
    class PeekingIterator_PeekedValue implements Iterator<Integer> {
        private Iterator<Integer> iter;
        private Integer peekedValue = null; //其实只能peek一个, 所以只需要存一个即可, 不需要array
        public PeekingIterator_PeekedValue(Iterator<Integer> iterator) {
            this.iter = iterator;
        }

        public Integer peek() {
            if (peekedValue == null) {
                if (!iter.hasNext()) { //注意这里的判断, 否则next会throw exception
                    // throw new NoSuchElementException(); 这是之前的答案, 现在return null即可
                    return null;
                }
                peekedValue = iter.next();
            }
            return peekedValue;
        }

        @Override
        public Integer next() {
            if (peekedValue != null) {
                Integer curr = peekedValue;
                peekedValue = null;
                return curr;
            }
            return iter.next();
        }

        @Override
        public boolean hasNext() { //注意这里别忘了peekedValue. peek不仅影响next()也影响hasNext()
            return peekedValue != null || this.iter.hasNext();
        }
    }

    /**
     * April 2022crib answer
     */
    class PeekingIterator_SaveNext implements Iterator<Integer> {
        private Iterator<Integer> iter;
        private Integer next = null;

        public PeekingIterator_SaveNext(Iterator<Integer> iterator) {
            this.iter = iterator;
            if (iter.hasNext()) {
                next = iter.next();
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer toReturn = next;
            if (iter.hasNext()) {
                next = iter.next();
            } else {
                next = null;
            }
            return toReturn;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}
