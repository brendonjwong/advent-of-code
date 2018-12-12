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
           freqs (drop 2 (reductions + (cycle data)))
           seen #{}]
      (if (seen frequency)
        frequency
        (recur (first freqs) (drop 1 freqs) (conj seen frequency))))))
