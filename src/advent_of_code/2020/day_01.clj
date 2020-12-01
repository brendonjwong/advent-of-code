(ns advent-of-code.2020.day-01
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))


(defn get-data
  []
  (->> (io/resource "2020/day-01.txt")
       (slurp)
       (str/split-lines)
       (map read-string)))


;;; Part 1

(defn problem-1
  []
  (let [nums (get-data)
        [a b] (->> (for [a nums
                         b nums
                         :when (= 2020 (+ a b))]
                     [a b])
                   (first))]
    (* a b)))


;;; Part 2

(defn problem-2
  []
  (let [nums (get-data)
        [a b c] (->> (for [a nums
                           b nums
                           c nums
                           :when (= 2020 (+ a b c))]
                       [a b c])
                     (first))]
    (* a b c)))
