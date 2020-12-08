(ns advent-of-code.2020.day-08
  (:require
    [advent-of-code.util :as u]
    [clojure.set :as set]
    [clojure.string :as str]))


(defn run-program
  [input]
  (loop [acc 0
         visited #{}
         position 0]
    (let [infinite-loop? (contains? visited position)
          program-ended? (= position (count input))]
      (if (or infinite-loop? program-ended?)
        {:accumulator acc
         :succeeded? program-ended?}
        (let [[_ op argument] (re-matches #"(\w+) ([-\+]\d+)" (nth input position))]
          (case op
            "acc" (recur (+ acc (Integer/parseInt argument)) (conj visited position) (inc position))
            "jmp" (recur acc (conj visited position) (+ position (Integer/parseInt argument)))
            "nop" (recur acc (conj visited position) (inc position))))))))


;;; Part 1

(defn problem-1
  [input]
  (:accumulator (run-program input)))


(comment
  (problem-1 (u/get-input "2020/day-08.txt")))


;;; Part 2

(defn problem-2
  [input]
  (->> (map
         (fn [i]
           (cond
             (str/starts-with? (nth input i) "nop")
             (update input i #(str/replace-first % #"nop" "jmp"))

             (str/starts-with? (nth input i) "jmp")
             (update input i #(str/replace-first % #"jmp" "nop"))

             :else nil))
         (range (count input)))
       (filter identity)
       (map run-program)
       (filter :succeeded?)
       (first)
       (:accumulator)))


(comment
  (problem-2 (u/get-input "2020/day-08.txt")))
