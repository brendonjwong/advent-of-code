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
  (let [out-of-bounds? (fn [x y]
                         (or (< x 0)
                             (< y 0)
                             (<= width x)
                             (<= height y)))
        {:keys [x y] :as xy} (->xy width index)]
    (for [x [(dec x) x (inc x)]
          y [(dec y) y (inc y)]]
      (if (or (out-of-bounds? x y)
              (and (= x (:x xy)) (= y (:y xy))))
        false
        (= \# (get-seat seats width x y))))))


(defn assign-seats
  [seats width height]
  (map-indexed
    (fn [idx cell]
      (let [occupied (vec (adjacent-occupied seats width height idx))]
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
    (->> (iterate #(assign-seats % width height) seats)
         (partition 2 1)
         (drop-while (fn [[a1 a2]]
                       (println (str/join "\n" (map str/join (partition width a1))) "\n")
                       (not= a1 a2)))
         (first)
         (first)
         (filter #(= \# %))
         (count))))


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
  (problem-1 (map vec example))

  ;; Answer: 2247
  (problem-1 (get-input)))


;;; Part 2

(defn problem-2
  [input])


(comment
  ;; Answer: ???
  (problem-2 (get-input)))
