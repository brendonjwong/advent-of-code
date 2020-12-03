(ns advent-of-code.2020.day-03
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))


(defn get-data
  []
  (->> (io/resource "2020/day-03.txt")
       (slurp)
       (str/split-lines)))


;;; Part 1


(defn problem-1
  [rows {:keys [right down]}]
  (let [width (count (first rows))]
    (loop [slope-map (drop down rows)
           x-pos 0
           trees-encountered 0]
      (if (empty? slope-map)
        trees-encountered
        (let [new-pos (mod (+ x-pos right) width)
              encountered-tree? (= \# (get (first slope-map) new-pos))]
          (recur (drop down slope-map)
                 new-pos
                 (if encountered-tree? (inc trees-encountered) trees-encountered)))))))


(comment
  (problem-1 (get-data) {:right 3 :down 1}))


;;; Part 2

(defn problem-2
  []
  (let [rows (get-data)]
    (* (problem-1 rows {:right 1 :down 1})
       (problem-1 rows {:right 3 :down 1})
       (problem-1 rows {:right 5 :down 1})
       (problem-1 rows {:right 7 :down 1})
       (problem-1 rows {:right 1 :down 2}))))
