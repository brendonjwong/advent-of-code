(ns advent-of-code.2020.day-11
  (:require
    [advent-of-code.util :as u]
    [clojure.string :as str]))


(defn get-input
  []
  (map vec (u/get-input "2020/day-11.txt")))


;;; Part 1

(defn ->xy
  [width index]
  {:x (mod index width)
   :y (int (/ index width))})


(defn get-seat
  [seats width x y]
  (nth seats (+ x (* y width))))


(defn adjacent-occupied
  [seats width height index]
  (let [xy (->xy width index)
        x (:x xy)
        y (:y xy)]
    (for [x [(dec x) x (inc x)]
          y [(dec y) y (inc y)]]
      (if (or (< x 0)
              (< y 0)
              (<= width x)
              (<= height y)
              (and (= x (:x xy)) (= y (:y xy))))
        false
        (= \# (get-seat seats width x y))))))


(defn assign-seats
  [seats width height]
  (map-indexed
    (fn [idx cell]
      (let [occupied (adjacent-occupied seats width height idx)]
        (cond
          (= \. cell)
          \.

          (and (= \L cell) (every? false? occupied))
          \#

          (and (= \#) (<= 4 (count (filter true? occupied))))
          \L

          :else cell)))
    seats))


(defn problem-1
  [input]
  (let [width (count (first input))
        height (count input)
        seats (flatten input)]
    (loop [arrangements (iterate #(assign-seats % width height) seats)]
      (let [curr (first arrangements)
            next (second arrangements)]
        (if (= curr next)
          (count (filter #(= \# %) curr))
          (recur (rest arrangements)))))))


(comment
  ;; Answer: 37
  (def example ["L.LL.LL.LL"
                "LLLLLLL.LL"
                "L.L.L..L.."
                "LLLL.LL.LL"
                "L.LL.LL.LL"
                "L.LLLLL.LL"
                "..L.L....."
                "LLLLLLLLLL"
                "L.LLLLLL.L"
                "L.LLLLL.LL"])
  (problem-1 (map vec example)))

  ;; Answer: 2170
(problem-1 (get-input))


;;; Part 2

(defn problem-2
  [input])


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
