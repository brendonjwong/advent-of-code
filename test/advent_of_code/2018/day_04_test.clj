(ns advent-of-code.2018.day-04-test
  (:require
    [advent-of-code.2018.day-04 :as day-04]
    [clojure.test :refer :all]))


(deftest problem-1-test
  (is (= 240
         (day-04/problem-1 ["[1518-11-01 00:00] Guard #10 begins shift"
                            "[1518-11-01 00:05] falls asleep"
                            "[1518-11-01 00:25] wakes up"
                            "[1518-11-01 00:30] falls asleep"
                            "[1518-11-01 00:55] wakes up"
                            "[1518-11-01 23:58] Guard #99 begins shift"
                            "[1518-11-02 00:40] falls asleep"
                            "[1518-11-02 00:50] wakes up"
                            "[1518-11-03 00:05] Guard #10 begins shift"
                            "[1518-11-03 00:24] falls asleep"
                            "[1518-11-03 00:29] wakes up"
                            "[1518-11-04 00:02] Guard #99 begins shift"
                            "[1518-11-04 00:36] falls asleep"
                            "[1518-11-04 00:46] wakes up"
                            "[1518-11-05 00:03] Guard #99 begins shift"
                            "[1518-11-05 00:45] falls asleep"
                            "[1518-11-05 00:55] wakes up"]))))


(deftest problem-2-test
  (is (= 4455
         (day-04/problem-2 ["[1518-11-01 00:00] Guard #10 begins shift"
                            "[1518-11-01 00:05] falls asleep"
                            "[1518-11-01 00:25] wakes up"
                            "[1518-11-01 00:30] falls asleep"
                            "[1518-11-01 00:55] wakes up"
                            "[1518-11-01 23:58] Guard #99 begins shift"
                            "[1518-11-02 00:40] falls asleep"
                            "[1518-11-02 00:50] wakes up"
                            "[1518-11-03 00:05] Guard #10 begins shift"
                            "[1518-11-03 00:24] falls asleep"
                            "[1518-11-03 00:29] wakes up"
                            "[1518-11-04 00:02] Guard #99 begins shift"
                            "[1518-11-04 00:36] falls asleep"
                            "[1518-11-04 00:46] wakes up"
                            "[1518-11-05 00:03] Guard #99 begins shift"
                            "[1518-11-05 00:45] falls asleep"
                            "[1518-11-05 00:55] wakes up"]))))
