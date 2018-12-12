(ns advent-of-code.2018.day-01
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))


(def data
  (->> (io/resource "advent-of-code/2018/day-01.txt")
       slurp
       str/split-lines
       (map read-string)))


;;; Part 1

(defn problem-1
  [data]
  (reduce + data))


;;; Part 2

(defn problem-2
  [data]
  (let [freq-changes (reductions + (cycle data))]
    (loop [frequency (second freq-changes) ; ignore the first frequency since it's not a *change*
           changes (drop 2 freq-changes)
           seen #{}]
      (if (seen frequency)
        frequency
        (recur (first changes) (drop 1 changes) (conj seen frequency))))))
