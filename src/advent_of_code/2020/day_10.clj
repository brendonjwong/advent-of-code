(ns advent-of-code.2020.day-10
  (:require
    [advent-of-code.util :as u]
    [clojure.string :as str]))


(defn get-input
  []
  (map #(Long/parseLong %) (u/get-input "2020/day-10.txt")))


;;; Part 1

(defn adapters
  [input]
  (let [start 0
        device (+ (apply max input) 3)]
    (sort-by - (conj input start device))))


(defn jolt-diffs
  [input]
  (->> (adapters input)
       (partition 2 1)
       (map (partial reduce -))))


(defn problem-1
  [input]
  (let [jolt-diff->frequency (frequencies (jolt-diffs input))]
    (* (get jolt-diff->frequency 1)
       (get jolt-diff->frequency 3))))


(comment
  ;; Answer: 35
  (problem-1 [16 10 15 5 1 11 7 19 6 12 4])

  ;; Answer: 220
  (problem-1 [28 33 18 42 31 14 46 20 48 47 24 23 49 45 19
              38 39 11 1 32 25 35 8 17 7 9 4 2 34 10 3])

  ;; Answer: 2170
  (problem-1 (get-input)))


;;; Part 2

(def count->combos
  "I got this by trying out combinations and counting possiblities...still
  trying to figure out the equation to get this programatically"
  {2 2
   3 4
   4 7
   5 13})


(defn problem-2
  [input]
  (let [equals-3? #(= 3 %)]
    (->> (jolt-diffs input)
         (partition-by equals-3?)
         (remove #(or (every? equals-3? %) (= 1 (count %))))
         (map count)
         (map (partial get count->combos))
         (reduce *))))


(comment
  ;; Answer: 8
  (def short-input [16 10 15 5 1 11 7 19 6 12 4])
  (problem-2 short-input)

  ;; Answer: 19208
  (def med-input [28 33 18 42 31 14 46 20 48 47 24 23 49 45 19
                  38 39 11 1 32 25 35 8 17 7 9 4 2 34 10 3])
  (problem-2 med-input)

  ;; Answer: 24803586664192
  (problem-2 (get-input)))
