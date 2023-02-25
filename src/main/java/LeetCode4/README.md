# LeetCode Extra Credit 4: Atom Counting #
#### https://leetcode.com/problems/number-of-atoms/ ####
#### https://leetcode.com/submissions/detail/904061370/ ####

This LeetCode problem gives us a valid chemical formula and asks us to return the
count of each element in the formula. My solution parses through elements
recognizing uppercase letters as the beginning of an element and then lowercase
letters as the ending of said element, then checks for a number and adds them as a
key-value pair to a `TreeMap`, effectively sorting them alphabetically. In case it
encounters a parenthesis, it recursively parses the inside of the parentheses and
checks if there's a number to multiply the contents afterwards. The implementation
could be cleaner and use less memory, but the runtime is fast enough that memory
becomes irrelevant at these scales.
