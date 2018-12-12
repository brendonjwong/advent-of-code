(ns advent-of-code.2018.day-02-test
  (:require
    [advent-of-code.2018.day-02 :as day-02]
    [clojure.test :refer :all]))


(deftest problem-1-test
  (testing "no letters appear exactly two or three times (0 x 0 = 0)"
    (is (= 0
           (day-02/problem-1 ["abcccc"])))
    (is (= 0
           (day-02/problem-1 ["abbcde"]))))
  (is (= 0
         (day-02/problem-1 ["abcccd"]))
      "three c, but no letter appears exactly two times (0 x 1 = 0)")
  (is (= 0
         (day-02/problem-1 ["abcccc"]))
      "three c, but no letter appears exactly two times (0 x 1 = 0)")
  (is (= 2
         (day-02/problem-1 ["abcccd" "bababc"]))
      "one set of two matching letters, two sets of three matching letters (1 x 2 = 2)")
  (is (= 12
         (day-02/problem-1 ["abcdef" "bababc" "abbcde" "abcccd" "aabcdd" "abcdee" "ababab"]))
      "four sets of two matching letters, three sets of three matching letters (4 x 3 = 12)"))


(deftest remove-nth-char-test
  (is (= ""
         (day-02/remove-nth-char 0 "a")))
  (is (= "b"
         (day-02/remove-nth-char 0 "ab")))
  (is (= "a"
         (day-02/remove-nth-char 1 "ab")))
  (is (= "bc"
         (day-02/remove-nth-char 0 "abc")))
  (is (= "ac"
         (day-02/remove-nth-char 1 "abc")))
  (is (= "ab"
         (day-02/remove-nth-char 2 "abc"))))


(deftest problem-2-test
  (is (= "a"
         (day-02/problem-2 ["ab" "ac"])))
  (is (= "abce"
         (day-02/problem-2 ["abcze" "abcye"])))
  (is (= "fgij"
         (day-02/problem-2 ["abcde" "fghij" "klmno" "pqrst" "fguij" "axcye" "wvxyz"]))
      "the IDs fghij and fguij differ by exactly one character, the third (h and u)."))
