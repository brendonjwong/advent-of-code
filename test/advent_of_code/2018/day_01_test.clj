(ns advent-of-code.2018.day-01-test
  (:require
    [advent-of-code.2018.day-01 :as day-01]
    [clojure.test :refer :all]))


(deftest problem-1-test
  (is (= 1
         (day-01/problem-1 [+1])))
  (is (= 3
         (day-01/problem-1 [+1 +1 +1])))
  (is (= 0
         (day-01/problem-1 [+1 +1 -2])))
  (is (= -6
         (day-01/problem-1 [-1 -2 -3]))))


(deftest problem-2-test
  (is (= 0
         (day-01/problem-2 [+1 -1])))
  (is (= 2
         (day-01/problem-2 [+1 -2 +3 +1])))
  (is (= 10
         (day-01/problem-2 [+3 +3 +4 -2 -4])))
  (is (= 5
         (day-01/problem-2 [-6 +3 +8 +5 -6]))))
