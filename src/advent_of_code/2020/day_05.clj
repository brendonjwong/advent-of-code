(ns advent-of-code.2020.day-05
  (:require
    [advent-of-code.util :as u]))


(def input-path "2020/day-05.txt")


;;; Part 1

(defn bisect
  [l ch]
  (let [midpoint (/ (count l) 2)]
    (case ch
      (\F \L) (take midpoint l)
      (\B \R) (drop midpoint l))))


(defn parse-seat
  [bsp-seat]
  (let [row-spec (take 7 bsp-seat)
        col-spec (drop 7 bsp-seat)
        row (first (reduce bisect (range 128) row-spec))
        col (first (reduce bisect (range 8) col-spec))]
    {:row row
     :column col
     :id (+ (* row 8) col)}))


(defn problem-1
  [input]
  (->> (map parse-seat input)
       (map :id)
       (apply max)))


(comment
  (problem-1 (u/get-input input-path)))


;;; Part 2

(defn find-missing
  [seats]
  (let [seats (sort-by :id seats)]
    (loop [prev-id (:id (first seats))
           remaining (rest seats)
           missing []]
      (if (seq remaining)
        (let [seat-id (:id (first remaining))]
          (recur seat-id
                 (rest remaining)
                 ;; if this seat is two higher than the last,
                 ;; there's a missing seat in between
                 (if (= prev-id (- seat-id 2))
                   (conj missing (dec seat-id))
                   missing)))
        missing))))


(defn problem-2
  [input]
  (->> (map parse-seat input)
       (find-missing)
       (first)))


(comment
  (problem-2 (u/get-input input-path)))
