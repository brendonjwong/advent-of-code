(ns advent-of-code.2020.day-07
  (:require
    [advent-of-code.util :as u]
    [clojure.set :as set]
    [clojure.string :as str]))


;;; Part 1

(defn parse-line
  [line]
  (let [[bag & contents] (map second (re-seq #"(\w+\s?\w*) bag" line))]
    (if (= "no other" (first contents))
      {bag #{}}
      {bag (set contents)})))


(defn parent-containers
  [bags color]
  (map first (filter (fn [[_ contents]] (contains? contents color)) bags)))


(defn problem-1
  [input]
  (let [bags (apply merge (map parse-line input))
        direct-containers (set (parent-containers bags "shiny gold"))]
    (loop [colors direct-containers
           to-check (disj direct-containers (first direct-containers))
           checked #{"shiny gold"}
           color (first direct-containers)]
      (if color
        (let [direct-containers (set (parent-containers bags color))
              new-containers (set/difference direct-containers checked)
              next-color (first to-check)]
          (recur (into colors direct-containers)
                 (set/union (disj to-check color) new-containers)
                 (conj checked color)
                 next-color))
        colors))))


(comment
  ;; Answer: 121
  (problem-1 (u/get-input "2020/day-07.txt")))


;;; Part 2

(defn parse-line-2
  [line]
  (let [[_ color] (re-find #"([\w ]+) bags contain" line)]
    {color (->> (re-seq #"(\d) ([\w ]+) bag" line)
                (map (fn [[_ n color]] {color (Integer/parseInt n)}))
                (apply merge))}))


(defn add-bags
  [bags parent-color]
  (->> (get bags parent-color)
       (map (fn [[color n]]
              (+ n (* n (add-bags bags color)))))
       (reduce +)))


(defn problem-2
  [input]
  (let [bags (apply merge (map parse-line-2 input))]
    (add-bags bags "shiny gold")))


(comment
  ;; Answer: 3805
  (problem-2 (u/get-input "2020/day-07.txt")))
