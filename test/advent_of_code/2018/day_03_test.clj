(ns advent-of-code.2018.day-03-test
  (:require
    [advent-of-code.2018.day-03 :as day-03]
    [clojure.test :refer :all]))


(deftest claim-parser-test
  (is (= {:id 123 :from-left 3 :from-top 2 :width 5 :height 4}
         (day-03/parse-claim "#123 @ 3,2: 5x4"))))


(deftest problem-1-test
  (is (= 0
         (day-03/problem-1 ["#1 @ 1,1: 1x1"
                            "#2 @ 2,2: 1x1"])))
  (is (= 1
         (day-03/problem-1 ["#1 @ 1,1: 1x1"
                            "#2 @ 1,1: 1x1"])))
  (is (= 4
         (day-03/problem-1 ["#1 @ 1,3: 4x4"
                            "#2 @ 3,1: 4x4"
                            "#3 @ 5,5: 2x2"]))))


(deftest problem-2-test
  (is (= 3
         (day-03/problem-2 ["#1 @ 1,3: 4x4"
                            "#2 @ 3,1: 4x4"
                            "#3 @ 5,5: 2x2"]))))
