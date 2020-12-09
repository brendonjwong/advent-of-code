(ns advent-of-code.2020.day-09
  (:require
    [advent-of-code.util :as u]
    [clojure.string :as str]))


(defn get-input
  []
  (map #(Long/parseLong %) (u/get-input "2020/day-09.txt")))


;;; Part 1

(defn any-combo-equals-n?
  [n coll]
  (some true? (for [a coll
                    b coll]
                (and (not= a b) (= n (+ a b))))))


(defn problem-1
  [input window-size]
  (->> (partition (inc window-size) 1 input)
       (filter #(not (any-combo-equals-n? (last %) (butlast %))))
       (map last)
       (first)))


(comment
  ;; Answer: 1721308972
  (problem-1 (get-input) 25))


;;; Part 2

(defn solve-weakness
  [n coll]
  (let [sums (filter #(<= % n) (reductions + coll))]
    (when (= n (last sums))
      (let [xs (take (dec (count sums)) coll)]
        (+ (apply min xs) (apply max xs))))))


(defn problem-2
  [input]
  (let [invalid-num (problem-1 input 25)]
    (some (partial solve-weakness invalid-num) (iterate rest input))))


(comment
  ;; Answer: 209694133
  (problem-2 (get-input)))
